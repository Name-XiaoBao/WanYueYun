<template>
  <view>
    <web-view :src="this.config.url+'FileUpload.html?username='+username+'&api='+api"></web-view>
  </view>
</template>

<script>
export default {
  onLoad: function (option) { //option为object类型，会序列化上个页面传递的参数
    this.api = option.ApiName
  },
  data() {
    return {
      username: uni.getStorageSync('username'),
      api: null
    }
  },
  methods: {}
}
</script>

<style>

</style>
