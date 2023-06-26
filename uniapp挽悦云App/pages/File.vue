<template>
  <view>
    <uni-card v-for="item in InterfaceDocumentation" :title="item.title">
      <text selectable="true" class="uni-body" v-html="item.content"></text>
    </uni-card>
    <!-- 导航栏 -->
    <navigation-bar ref="nav"></navigation-bar>
  </view>
</template>

<script>
import navigationBar from '@/components/NavigationBar.vue';

export default {
  components: {
    navigationBar
  },
  data() {
    return {
      InterfaceDocumentation: null
    }
  },
  methods: {
    initData() {
      this.$refs["nav"].current_type = 1
      this.$refs["nav"].datalist[0].icon = require('@/static/icon/HomePage.png')
      this.$refs["nav"].datalist[1].icon = require('@/static/icon/Document_Selected.png')
      this.$refs["nav"].datalist[2].icon = require('@/static/icon/Me.png')
    }
  },
  mounted() {
    this.initData()
  },
  created() {
    // 获取接口文档
    uni.request({
      url: this.config.url + 'Interface',
      method: 'GET',//请求方式，必须为大写
      success: (res) => {
        this.InterfaceDocumentation = res.data.data
      }
    });
  }
}
</script>

<style>
</style>