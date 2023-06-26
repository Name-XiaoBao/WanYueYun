<template>
  <view>
    <uni-card :title="user" :extra="id" style="border-radius: 10px;">
      <text>所属API：</text>
      <uni-easyinput style="margin-bottom: 10px;" disabled class="uni-mt-5" trim="all" v-model="name"
                     placeholder="请输入内容"></uni-easyinput>
      <text>积分：</text>
      <uni-easyinput style="margin-bottom: 10px;" class="uni-mt-5" trim="all" v-model="integral"
                     placeholder="0"></uni-easyinput>
      <text>会员：</text>
      <uni-datetime-picker style="margin-bottom: 10px;" type="datetime" v-model="membershipTime"/>
      <text>密码：</text>
      <uni-easyinput type=password style="margin-bottom: 10px;" class="uni-mt-5" trim="all" v-model="newPass"
                     placeholder="请输入内容"></uni-easyinput>
      <button @click="SaveChanges"
              style="color: #fff;margin-top: 10px;font-size: 13px;border-radius: 10px;background-color:cornflowerblue">
        保存修改
      </button>
    </uni-card>
  </view>
</template>

<script>
export default {
  onLoad: function (option) { //option为object类型，会序列化上个页面传递的参数
    this.name = option.ApiName
    this.user = option.user
    this.pass = option.pass
    this.newPass = option.pass
  },
  data() {
    return {
      id: null,
      name: null,
      user: null,
      pass: null,
      newPass: null,
      integral: null,
      membershipTime: null
    }
  },
  methods: {
    SaveChanges() {
      uni.request({
        url: this.config.url + 'api/UpPass',
        data: {
          username: uni.getStorageSync('username'),
          password: uni.getStorageSync('password'),
          name: this.name,
          id: this.id,
          user: this.user,
          pass: this.pass,
          Newpass: this.newPass
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          if (res.data.code == 200) {
            uni.request({
              url: this.config.url + 'api/upIntegral',
              data: {
                username: uni.getStorageSync('username'),
                password: uni.getStorageSync('password'),
                name: this.name,
                id: this.id,
                user: this.user,
                pass: this.newPass,
                Integral: this.integral
              },
              header: {
                'content-type': "application/x-www-form-urlencoded"
              },
              method: 'POST',//请求方式，必须为大写
              success: (res) => {

              },
            })
            uni.request({
              url: this.config.url + 'api/upMembershipTime',
              data: {
                username: uni.getStorageSync('username'),
                password: uni.getStorageSync('password'),
                name: this.name,
                id: this.id,
                user: this.user,
                pass: this.newPass,
                MembershipTime: this.membershipTime
              },
              header: {
                'content-type': "application/x-www-form-urlencoded"
              },
              method: 'POST',//请求方式，必须为大写
              success: (res) => {

              },
            })
          }
        },
      })
      setTimeout(() => {
        // 更新上一页数据
        let pages = getCurrentPages()
        // 获取上一页栈
        let prevPage = pages[pages.length - 2]
        prevPage.$vm.listInfo = [] // listInfo 为上一页数据
        prevPage.$vm.initData() // 为上一页获取数据方法
        // 返回上一页
        uni.navigateBack({
          delta: 1
        })
      }, 500)
    }
  },
  created() {
    // 获取用户信息
    uni.request({
      url: this.config.url + 'api/UserInfo',
      data: {
        username: uni.getStorageSync('username'),
        name: this.name,
        user: this.user,
        pass: this.pass
      },
      header: {
        'content-type': "application/x-www-form-urlencoded"
      },
      method: 'POST',//请求方式，必须为大写
      success: (res) => {
        this.id = res.data.data.id
        this.integral = res.data.data.integral
        this.membershipTime = res.data.data.membershipTime
      },
    })
  }
}
</script>

<style>
</style>