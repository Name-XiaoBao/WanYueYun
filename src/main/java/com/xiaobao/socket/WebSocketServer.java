package com.xiaobao.socket;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @author 小暴
 */
@Component
@ServerEndpoint("/ChatRoom/{Api}/{username}")
@Slf4j
public class WebSocketServer {

    /**
     * 用来记录当前在线连接数量,应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    /**
     * concurrent包是线程安全的Set，用来存放每个客户端对应的WebSocket对象。
     */
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据.
     */
    private Session session;

    /**
     * 接收客户端消息的API
     */
    private String Api = "";

    /**
     * 接收客户端消息的账号
     */
    private String username = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("Api") String Api,@PathParam("username") String username) {
        this.session = session;
        this.Api = Api;
        this.username=username;
        if (webSocketMap.containsKey(Api)) {
            webSocketMap.remove(Api);
            //加入到set中
            webSocketMap.put(Api+username, this);
        } else {
            //加入set中
            webSocketMap.put(Api+username, this);
            //在线数加1
            addOnlineCount();
        }

        log.info("用户" + username + "已连接,所有API在线人数为:" + getOnlineCount());
        log.info("API"+Api+"在线人数为："+WebSocketServer.getOnlineCountByApi(Api));
        try {
            sendMsg("连接成功");
        } catch (IOException e) {
            log.error("用户:" + username + ",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(username)) {
            webSocketMap.remove(username);
            //从set中删除
            subOnlineCount();
        }
        log.info("用户"+ username +"退出,当前所有API在线总人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("用户id:" + username + ",接收到的报文:" + message);
        // 获取当前客户端连接的API信息
        String api = this.Api;
        // 生成 JSON 格式的消息
        JSONObject jsonMsg = new JSONObject();
        jsonMsg.put("Api", Api);
        jsonMsg.put("user", username);
        jsonMsg.put("msg", message);
        // 遍历所有WebSocket对象，找到与当前客户端连接的API相同的WebSocket对象进行群发
        for (WebSocketServer webSocket : webSocketMap.values()) {
            if (webSocket.Api.equals(api)) {
                webSocket.sendMsg(jsonMsg.toString());
            }
        }
    }
    /**
     * 处理错误
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.username + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    private void sendMsg(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    /**
     * 发送自定义消息
     */
    public void sendInfo(String message, @PathParam("username") String username) throws IOException {
        log.info("发送消息到:" + Api+username + ",发送的报文:" + message);
        if (!StringUtils.isEmpty(Api+username) && webSocketMap.containsKey(Api+username)) {
            webSocketMap.get(Api+username).sendMsg(message);
        } else {
            log.error("用户" + Api+username + ",不在线！");
        }
    }

    private static synchronized int getOnlineCount() {
        return onlineCount;
    }

    private static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    private static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    /**
     * 获取某个API下的在线人数
     * @param api API
     * @return 在线人数
     */
    public static int getOnlineCountByApi(String api) {
        int count = 0;
        for (WebSocketServer webSocket : webSocketMap.values()) {
            if (webSocket.Api.equals(api)) {
                count++;
            }
        }
        return count;
    }
}
