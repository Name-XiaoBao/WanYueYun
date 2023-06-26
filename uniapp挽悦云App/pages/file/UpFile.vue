<template>
  <view>
    <uni-card :title="title" :extra="id" style="border-radius: 10px;padding-bottom: 10%;">
      <text>所属API：</text>
      <uni-easyinput style="margin-bottom: 10px;" disabled class="uni-mt-5" trim="all" v-model="name"
                     placeholder="请输入内容"></uni-easyinput>
      <text>文档秘钥：</text>
      <uni-easyinput type="password" style="margin-bottom: 10px;" class="uni-mt-5" trim="all" v-model="secretKey"
                     placeholder="文档秘钥(设置后访问文档需输入秘钥)"></uni-easyinput>
      <text>文档标题：</text>
      <uni-easyinput style="margin-bottom: 10px;" class="uni-mt-5" trim="all" v-model="title"
                     placeholder="请输入标题"></uni-easyinput>
      <text>文档内容：</text>
      <uni-easyinput v-model="content" maxlength=-1 style="margin-bottom: 10px;" type="textarea" autoHeight
                     placeholder="请输入内容"></uni-easyinput>
      <text>上次保存为：{{updateTime}}</text>
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
    this.id = option.id
    this.secretKey = option.SecretKey
  },
  data() {
    return {
      name: null,
      id: null,
      secretKey: null,
      title: null,
      content: null,
      updateTime: null
    }
  },
  methods: {
    SaveChanges() {
      if (this.secretKey == "" || this.secretKey === null) {
        uni.request({
          url: this.config.url + 'api/UpdateFile',
          data: {
            username: uni.getStorageSync('username'),
            password: uni.getStorageSync('password'),
            Api: this.name,
            id: this.id,
            Jurisdiction: 0,
            SecretKey: this.secretKey,
            title: this.title,
            Content: this.content
          },
          header: {
            'content-type': "application/x-www-form-urlencoded"
          },
          method: 'POST',//请求方式，必须为大写
          success: (res) => {
            if (res.data.code == 200) {
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
        })
      } else {
        uni.request({
          url: this.config.url + 'api/UpdateFile',
          data: {
            username: uni.getStorageSync('username'),
            password: uni.getStorageSync('password'),
            Api: this.name,
            id: this.id,
            Jurisdiction: 1,
            SecretKey: this.secretKey,
            title: this.title,
            Content: this.content
          },
          header: {
            'content-type': "application/x-www-form-urlencoded"
          },
          method: 'POST',//请求方式，必须为大写
          success: (res) => {
            if (res.data.code == 200) {
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
        })
      }
    }
  },
  created() {
    uni.request({
      url: this.config.url + 'api/RemoteDocument',
      data: {
        Api: this.name,
        id: this.id,
        SecretKey: this.secretKey
      },
      header: {
        'content-type': "application/x-www-form-urlencoded"
      },
      method: 'POST',//请求方式，必须为大写
      success: (res) => {
        this.title = res.data.data[0].title
        this.content = res.data.data[0].content.replace(/\\n/g, '\n')
        this.secretKey = res.data.data[0].secretKey
        this.updateTime = res.data.data[0].updateTime
      },
    })
  }
}
</script>

<style>
</style>