package com.xiaobao.socket;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.xiaobao.service.Impl.MessageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 小暴
 */
@Component
@ServerEndpoint("/ChatRoom/{Api}/{username}")
@Slf4j
public class WebSocketServer {
    private static int onlineCount = 0;
    private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    private Session session;
    private String Api = "";
    private String username = "";
    @Autowired
    private MessageServiceImpl messageService;
    // 心跳检测定时任务
    private ScheduledExecutorService heartBeatScheduler;
    private static final String HEARTBEAT_MESSAGE = "heartbeat";
    // 每30秒发送一次心跳包
    private static final long HEARTBEAT_INTERVAL = 30000;

    @OnOpen
    public void onOpen(Session session, @PathParam("Api") String Api, @PathParam("username") String username) {
        this.session = session;
        this.Api = Api;
        this.username = username;

        if (webSocketMap.containsKey(Api + username)) {
            webSocketMap.remove(Api + username);
            webSocketMap.put(Api + username, this);
        } else {
            webSocketMap.put(Api + username, this);
            addOnlineCount();
        }

        log.info("用户" + username + "已连接,所有API在线人数为:" + getOnlineCount());
        log.info("API" + Api + "在线人数为：" + WebSocketServer.getOnlineCountByApi(Api));
        try {
            sendMsg("连接成功");
        } catch (IOException e) {
            log.error("用户:" + username + ",网络异常!!!!!!");
        }

        // 启动心跳检测定时任务
        startHeartBeatTask();
    }

    @OnClose
    public void onClose() {
        if (webSocketMap.containsKey(Api + username)) {
            webSocketMap.remove(Api + username);
            subOnlineCount();
        }
        log.info("用户" + username + "退出,当前所有API在线总人数为:" + getOnlineCount());
        // 停止心跳检测定时任务
        stopHeartBeatTask();
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("用户id:" + username + ",接收到的报文:" + message);
        if (message.equals(HEARTBEAT_MESSAGE)) {
            // 处理心跳包消息
            return;
        }
        String api = this.Api;
        JSONObject jsonMsg = new JSONObject();
        jsonMsg.put("Api", Api);
        jsonMsg.put("user", username);
        jsonMsg.put("msg", message);
        // 将消息储存到数据库
        // messageService.addMsg(api, username, message, "2014-01-01 00:00:00");
        for (WebSocketServer webSocket : webSocketMap.values()) {
            if (webSocket.Api.equals(api)) {
                webSocket.sendMsg(jsonMsg.toString());
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:" + this.username + ",原因:" + error.getMessage());
        error.printStackTrace();
    }

    private void sendMsg(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    public void sendInfo(String message, @PathParam("username") String username) throws IOException {
        log.info("发送消息到:" + Api + username + ",发送的报文:" + message);
        if (!StringUtils.isEmpty(Api + username) && webSocketMap.containsKey(Api + username)) {
            webSocketMap.get(Api + username).sendMsg(message);
        } else {
            log.error("用户" + Api + username + ",不在线！");
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

    public static int getOnlineCountByApi(String api) {
        int count = 0;
        for (WebSocketServer webSocket : webSocketMap.values()) {
            if (webSocket.Api.equals(api)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 启动心跳检测定时任务
     */
    private void startHeartBeatTask() {
        heartBeatScheduler = Executors.newSingleThreadScheduledExecutor();
        heartBeatScheduler.scheduleAtFixedRate(() -> {
            try {
                sendMsg(HEARTBEAT_MESSAGE);
            } catch (IOException e) {
                log.error("用户:" + username + ",心跳检测异常!!!!!!");
            }
        }, HEARTBEAT_INTERVAL, HEARTBEAT_INTERVAL, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止心跳检测定时任务
     */
    private void stopHeartBeatTask() {
        if (heartBeatScheduler != null) {
            heartBeatScheduler.shutdownNow();
            heartBeatScheduler = null;
        }
    }
}
