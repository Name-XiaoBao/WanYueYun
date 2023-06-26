<template>
  <view>
    <uni-card title="反馈" style="border-radius: 10px;padding-bottom: 10%;">
      <text>联系方式：</text>
      <uni-easyinput style="margin-bottom: 10px;" class="uni-mt-5" trim="all" v-model="contactInformation"
                     placeholder="您的联系方式"></uni-easyinput>
      <text>内容：</text>
      <uni-easyinput maxlength=-1 style="margin-bottom: 10%;" type="textarea" autoHeight v-model="content"
                     placeholder="请输入内容"></uni-easyinput>
      <button @click="SendFeedback"
              style="color: #fff;margin-top: 10px;font-size: 13px;border-radius: 10px;background-color:cornflowerblue">
        发送
      </button>
    </uni-card>
  </view>
</template>

<script>
export default {
  data() {
    return {
      contactInformation: null,
      content: null
    }
  },
  methods: {
    SendFeedback() {
      if (this.contactInformation == null || this.contactInformation == "" || this.content == null || this.content == "") {
        this.msg.msg("请填写完整！");
      } else {
        uni.request({
          url: this.config.url + 'Feedback',
          data: {
            Content: this.content,
            ContactInformation: this.contactInformation
          },
          method: 'POST',//请求方式，必须为大写
          header: {
            'content-type': "application/x-www-form-urlencoded"
          },
          success: (res) => {
            this.msg.msg(res.data.msg);
          },
        })
      }
    }
  },
  created() {

  }
}
</script>

<style>
</style>