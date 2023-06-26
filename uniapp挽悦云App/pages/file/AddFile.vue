<template>
  <view>
    <uni-card title="新建文档" style="border-radius: 10px;padding-bottom: 10%;">
      <text>所属API：</text>
      <uni-easyinput style="margin-bottom: 10px;" disabled class="uni-mt-5" trim="all" v-model="name"
                     placeholder="请输入内容"></uni-easyinput>
      <text>文档标题：</text>
      <uni-easyinput style="margin-bottom: 10px;" class="uni-mt-5" trim="all" v-model="title"
                     placeholder="请输入标题"></uni-easyinput>
      <text>文档内容：</text>
      <uni-easyinput maxlength=-1 style="margin-bottom: 10%;" type="textarea" autoHeight v-model="content"
                     placeholder="请输入内容"></uni-easyinput>
      <button @click="addFile"
              style="color: #fff;margin-top: 10px;font-size: 13px;border-radius: 10px;background-color:cornflowerblue">
        新建
      </button>
    </uni-card>
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
      title: null,
      content: null
    }
  },
  methods: {
    addFile() {
      uni.request({
        url: this.config.url + 'api/AddFile',
        data: {
          username: uni.getStorageSync('username'),
          password: uni.getStorageSync('password'),
          Api: this.name,
          Title: this.title,
          Content: this.content
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          this.msg.msg(res.data.msg);
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
        },
      })
    }
  },
  created() {

  }
}
</script>

<style>
</style>