<template>
  <view class="parent-container">
    <scroll-view class="messages-container" scroll-y='true' :scroll-into-view="scrollIntoIndex"
                 scroll-with-animation="true">
      <uni-card v-for="(msg,index) in messages" :key="index" :id="'msg_' + index" :title="'用户：'+msg.user">
        <text>{{msg.msg}}</text>
      </uni-card>
    </scroll-view>
    <view class="input-container">
      <uni-easyinput class="uni-mt-5" trim="all" v-model="inputMessage" placeholder="请输入内容"></uni-easyinput>
      <button class="send-button" @click="SendMsg">发送</button>
    </view>
  </view>
</template>

<script>
export default {
  onLoad: function (option) { //option为object类型，会序列化上个页面传递的参数
    this.name = option.ApiName
  },
  data() {
    return {
      name: null,
      user: uni.getStorageSync('username'),
      messages: [],// 定义一个数组用于存储解析后的消息,
      inputMessage: null,
      scrollIntoIndex: null
    }
  },
  methods: {
    SendMsg() {
      const message = this.inputMessage.trim();
      if (message) {
        uni.sendSocketMessage({
          data: message
        });
        this.inputMessage = ''; // 清空输入框
      }
    },
    // 跳转页面底部
    scrollToBottom() {
      this.$nextTick(() => {
        let index = this.messages.length - 1;
        this.scrollIntoIndex = 'msg_' + index;
      });
    },
  },
  created() {
    const self = this; // 存储当前组件实例的引用

    uni.connectSocket({
      url: 'ws://wanyueyun.xbnb666.top:5543/ChatRoom/' + this.name + '/' + this.user
    });

    // 监听连接成功事件
    uni.onSocketOpen(function () {
      console.log('WebSocket connected');
    });

    // 监听接收消息事件
    uni.onSocketMessage(function (res) {
      if (res.data !== "heartbeat") {
        try {
          const data = JSON.parse(res.data);
          console.log('解析后的消息：', data);
          self.messages.push(data); // 将解析后的消息添加到数组中
          self.scrollToBottom(); // 加载新消息后滚动到底部
        } catch (error) {
          console.error('解析消息错误：', error);
        }
      }
    });

    // 监听连接错误事件
    uni.onSocketError(function (err) {
      console.error('WebSocket error:', err);
    });
  },
  beforeDestroy() {
    // 关闭 WebSocket 连接
    uni.closeSocket();

    // 监听连接关闭事件
    uni.onSocketClose(function (res) {
      console.log('WebSocket closed:', res);
    });
  }
}
</script>

<style>
.parent-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.messages-container {
  flex-grow: 1;
  overflow-y: scroll;
  margin-bottom: 20%;
}

.input-container {
  display: flex;
  justify-content: space-between;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  padding: 0px 8px 20px;
  background-color: white;
}

.send-button {
  color: #fff;
  margin-top: 10px;
  font-size: 13px;
  background-color: cornflowerblue;
}
</style>
