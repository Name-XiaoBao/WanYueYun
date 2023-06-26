<template>
  <view>
    <uni-section :title="'用户数量：'+usernum" type="line">
      <template v-slot:right>
        <uni-tag text="添加" type="primary" @click="adduser"/>
      </template>
    </uni-section>
    <image v-if="usernum==0"
           style="width: 100%;position: absolute;top: 50%;left: 50%;-webkit-transform: translate(-50%, -50%);-moz-transform: translate(-50%, -50%);-ms-transform: translate(-50%, -50%);-o-transform: translate(-50%, -50%);transform: translate(-50%, -50%);"
           src="@/static/nullData.svg"></image>
    <view v-else class="box" v-for="(item,key) in userlist">
      <text class="name" v-text="item.user"></text>
      <image src="/static/icon/vip.png" class="vip" v-if="item.membershipTime>date()"></image>
      <image src="/static/icon/NoVip.png" class="vip" v-else></image>
      <image src="/static/icon/Integral.png" class="integral"></image>
      <text v-if="item.integral==null">0</text>
      <text v-else>{{item.integral}}</text>
      <view class="Operation">
        <image src="/static/icon/Edit.png" @click="ModifyUsers(item.user,item.pass)"></image>
        <image src="/static/icon/Delete.png" @click="delApiUser(item.user)"></image>
      </view>
    </view>
    <view>
      <!-- 添加用户 -->
      <uni-popup ref="popup" background-color="#fff">
        <view class="popup">
          <uni-easyinput class="uni-mt-5" trim="all" v-model="addusername" placeholder="请输入账号"></uni-easyinput>
          <uni-easyinput type="password" class="uni-mt-5" trim="all" v-model="adduserpass"
                         placeholder="请输入密码"></uni-easyinput>
          <button @click="useradd"
                  style="color: #fff;margin-top: 10px;font-size: 13px;border-radius: 10px;background-color:cornflowerblue">
            创建
          </button>
        </view>
      </uni-popup>
    </view>
    <view>
      <!-- 提示窗 -->
      <uni-popup ref="alertDialog" type="dialog">
        <uni-popup-dialog type="error" confirmText="确定" title="删除用户" content="删除后将无法恢复,确定删除？"
                          @confirm="ConfirmDeletionOfApiUser"></uni-popup-dialog>
      </uni-popup>
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
      usernum: null,
      userlist: null,
      addusername: null,
      adduserpass: null,
      deluser: null
    }
  },
  methods: {
    date() {
      var date = new Date(),
          year = date.getFullYear(),
          month = date.getMonth() + 1,
          day = date.getDate(),
          hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours(),
          minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes(),
          second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      month >= 1 && month <= 9 ? (month = "0" + month) : "";
      day >= 0 && day <= 9 ? (day = "0" + day) : "";
      var timer = year + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
      return timer;
    },
    adduser() {
      this.$refs.popup.open()
    },
    useradd() {
      uni.request({
        url: this.config.url + 'api/Register',
        data: {
          username: uni.getStorageSync('username'),
          name: this.name,
          user: this.addusername,
          pass: this.adduserpass
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          if (res.data.code == 200) {
            this.msg.msg(res.data.msg);
            // 获取用户列表
            uni.request({
              url: this.config.url + 'api/UserList',
              data: {
                username: uni.getStorageSync('username'),
                password: uni.getStorageSync('password'),
                name: this.name
              },
              header: {
                'content-type': "application/x-www-form-urlencoded"
              },
              method: 'POST',//请求方式，必须为大写
              success: (res) => {
                this.usernum = res.data.data.length
                this.userlist = res.data.data
              },
            })
            this.addusername = null
            this.adduserpass = null
            this.$refs.popup.close()
          } else if (res.data.status == 500) {
            this.msg.msg("请一分钟后重试！");
          } else {
            this.msg.msg(res.data.msg);
          }
        },
      })
    },
    delApiUser(user) {
      this.$refs.alertDialog.open()
      this.deluser = user
    },
    ConfirmDeletionOfApiUser() {
      uni.request({
        url: this.config.url + 'api/deluser',
        data: {
          username: uni.getStorageSync('username'),
          password: uni.getStorageSync('password'),
          name: this.name,
          user: this.deluser
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          if (res.data.code == 200) {
            this.msg.msg(res.data.msg);
            // 获取用户列表
            uni.request({
              url: this.config.url + 'api/UserList',
              data: {
                username: uni.getStorageSync('username'),
                password: uni.getStorageSync('password'),
                name: this.name
              },
              header: {
                'content-type': "application/x-www-form-urlencoded"
              },
              method: 'POST',//请求方式，必须为大写
              success: (res) => {
                this.usernum = res.data.data.length
                this.userlist = res.data.data
              },
            })
            this.deluser = null
          } else {
            this.msg.msg(res.data.msg);
          }
        },
      })
    },
    ModifyUsers(user, pass) {
      uni.navigateTo({
        url: 'UpUser?ApiName=' + this.name + '&user=' + user + '&pass=' + pass
      })
    },
    initData() {
      uni.request({
        url: this.config.url + 'api/UserList',
        data: {
          username: uni.getStorageSync('username'),
          password: uni.getStorageSync('password'),
          name: this.name
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          this.usernum = res.data.data.length
          this.userlist = res.data.data
        },
      })
    }
  },
  created() {
    // 改变标题栏
    uni.setNavigationBarTitle({
      title: "用户管理（" + this.name + "）"
    });
    // 获取用户列表
    uni.request({
      url: this.config.url + 'api/UserList',
      data: {
        username: uni.getStorageSync('username'),
        password: uni.getStorageSync('password'),
        name: this.name
      },
      header: {
        'content-type': "application/x-www-form-urlencoded"
      },
      method: 'POST',//请求方式，必须为大写
      success: (res) => {
        this.usernum = res.data.data.length
        this.userlist = res.data.data
      },
    })
  }
}
</script>

<style>
.box {
  width: 96%;
  /* padding: 0 3%; */
  height: 15vw;
  background-color: #fefefe;
  border-radius: 24 upx;
  /*  阴影 */
  box-shadow: 0 0 20 upx rgba(0, 0, 0, 0.15);
  margin: 2% 2% 0 upx 2%;
  display: flex;
  margin-bottom: 10px;
  align-items: center;
}

.name {
  font-size: 18px;
  margin-left: 10%;
}

.Operation {
  float: right;
  position: absolute;
  left: 73%;
}

.Operation image {
  width: 20px;
  height: 20px;
  margin: 8px;
}

.vip {
  margin-left: 5px;
  width: 30px;
  height: 15px;
  border-radius: 20px;
}

.integral {
  margin: 0px 5px 0px 15px;
  width: 20px;
  height: 20px;
}

.popup {
  padding: 20px;
  width: 250px;
}
</style>