<template>
  <view>
    <view class="login" v-if="login">
      <input type="text" placeholder="邮箱账号" v-model="username">
      <input type="password" placeholder="密码" v-model="password">
      <button style="background-color:#1E9FFF" @click="logins">登录</button>
      <text style="float: left;margin: 11%;color: red;" @click="ForgotPassword">忘记密码</text>
      <text style="float: right;margin: 11%;color: blue;" @click="state">注册</text>
    </view>
    <view class="register" v-else>
      <input type="text" placeholder="邮箱账号" v-model="username">
      <input type="password" placeholder="密码" v-model="password">
      <input type="number" placeholder="验证码" v-model="code">
      <button style="background-color: cornflowerblue;margin-bottom: 5%;" @click="codes"
              :disabled="sendModel.countFlag">{{sendModel.btnMsg == null ? sendModel.countNum+'s后重新发送' :
        sendModel.btnMsg}}
      </button>
      <button style="background-color: dodgerblue;" @click="register">注册</button>
      <text style="float: left;margin: 11%;color: red;" @click="ForgotPassword">忘记密码</text>
      <text style="float: right;margin: 11%;color: blue;" @click="state">登录</text>
    </view>
    <view>
      <image src="/static/Wave.svg" class="wave"></image>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      login: true,
      username: null,
      password: null,
      codemsg: null,
      code: null,
      sendModel: {
        // 倒计时周期
        countNum: 60,
        // 用于倒计时标记，true-正在倒计时
        countFlag: false,
        // 定时器
        intervalBtn: {},
        // 默认按钮的值
        btnMsg: '获取验证码'
      }
    }
  },
  methods: {
    state() {
      this.login = this.login !== true;
    },
    codes() {
      if (this.username === null || this.username == "") {
        this.msg.msg("邮箱账号不能为空");
      } else {
        uni.request({
          url: this.config.url + 'VerificationCode',
          data: {
            username: this.username
          },
          header: {
            'content-type': "application/x-www-form-urlencoded"
          },
          method: 'POST',//请求方式，必须为大写
          success: (res) => {
            if (res.data.code != 200) {
              this.msg.msg(res.data.message);
            } else {
              this.msg.msg(res.data.msg);
              this.codemsg = res.data.data;
              // 设置btn倒计时时显示的信息
              this.sendModel.btnMsg = null
              // 更改btn状态
              this.sendModel.countFlag = !this.sendModel.countFlag
              // 设置倒计时
              this.sendModel.intervalBtn = setInterval(() => {
                if (this.sendModel.countNum <= 0) {
                  // 重置btn提示信息
                  this.sendModel.btnMsg = '获取验证码'
                  // 清除定时器
                  clearInterval(this.sendModel.intervalBtn)
                  // 更改btn状态
                  this.sendModel.countFlag = !this.sendModel.countFlag
                  // 重置倒计时状态
                  this.sendModel.countNum = 60
                }
                ;
                // 倒计时
                this.sendModel.countNum--
              }, 1000)
            }
          }
        });
      }
    },
    // 注册
    register() {
      if (this.code === null || this.code == "") {
        this.msg.msg("验证码不能为空");
      } else {
        uni.request({
          url: this.config.url + 'Md5',
          data: {
            text: this.code
          },
          header: {
            'content-type': "application/x-www-form-urlencoded"
          },
          method: 'POST',//请求方式，必须为大写
          success: (res) => {
            if (this.codemsg === res.data.data) {
              uni.request({
                url: this.config.url + 'Register',
                data: {
                  username: this.username,
                  password: this.password
                },
                header: {
                  'content-type': "application/x-www-form-urlencoded"
                },
                method: 'POST',//请求方式，必须为大写
                success: (res) => {
                  if (res.data.code == 200) {
                    this.msg.msg(res.data.msg);
                    uni.setStorageSync('username', this.username);
                    uni.setStorageSync('password', this.password);
                    setTimeout(() => {
                      uni.redirectTo({
                        //关闭当前页面，跳转到应用内的某个页面。
                        url: 'index'
                      });
                    }, 1000)
                  } else {
                    this.msg.msg(res.data.msg);
                  }
                },
              })
            } else {
              this.msg.msg("验证码错误");
            }
          }
        })
      }
    },
    logins() {
      uni.request({
        url: this.config.url + 'login',
        data: {
          username: this.username,
          password: this.password
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          if (res.data.code === 200) {
            this.msg.msg(res.data.msg);
            uni.setStorageSync('username', this.username);
            uni.setStorageSync('password', this.password);
            setTimeout(() => {
              uni.redirectTo({
                //关闭当前页面，跳转到应用内的某个页面。
                url: 'index'
              });
            }, 1000)
          } else {
            this.msg.msg(res.data.msg);
          }
        },
      })
    },
    ForgotPassword() {
      this.msg.msg("请联系官方负责人\nQQ：1753147761");
    }
  },
  created() {

  }
}
</script>

<style>
input {
  border-bottom: black 1px solid;
  padding: 5px;
  margin: 12%;
}

button {
  margin-left: 11%;
  margin-right: 11%;
  border-radius: 18px;
  color: #fff;
}

.login {
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -moz-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  -o-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  background-color: #fff;
  width: 300px;
  height: 300px;
  border-radius: 20px;
  box-shadow: 0 0 20 upx rgba(0, 0, 0, 0.15);
}

.register {
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -moz-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  -o-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  background-color: #fff;
  width: 300px;
  height: 420px;
  border-radius: 20px;
  box-shadow: 0 0 20 upx rgba(0, 0, 0, 0.15);
  z-index: 1;
}

.wave {
  width: 100%;
}
</style>