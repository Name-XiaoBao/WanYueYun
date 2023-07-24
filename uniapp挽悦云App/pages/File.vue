<template>
  <view>
    <uni-search-bar placeholder="搜索" @confirm="search" v-model="keyword" @clear="clearSearch" @cancel="clearSearch"/>
    <uni-card v-for="(item, index) in filteredItems" :key="index" :title="item.title">
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
      InterfaceDocumentation: [],
      filteredItems: [],
      keyword: ''
    };
  },
  methods: {
    initData() {
      this.$refs["nav"].current_type = 1;
      this.$refs["nav"].datalist[0].icon = require("@/static/icon/HomePage.png");
      this.$refs["nav"].datalist[1].icon = require("@/static/icon/Document_Selected.png");
      this.$refs["nav"].datalist[2].icon = require("@/static/icon/Me.png");
    },
    search() {
      const value = this.keyword.trim();
      if (!value) {
        this.filteredItems = this.InterfaceDocumentation;
        return;
      }
      const regex = new RegExp(value, "i");
      this.filteredItems = this.InterfaceDocumentation.filter((item) =>
          regex.test(item.title)
      );
    },
    clearSearch() {
      this.keyword = '';
      this.filteredItems = this.InterfaceDocumentation;
    }
  },
  mounted() {
    this.initData();
  },
  created() {
    // 获取接口文档
    uni.request({
      url: this.config.url + "Interface",
      method: "GET",
      success: (res) => {
        this.InterfaceDocumentation = res.data.data;
        this.filteredItems = res.data.data;
      }
    });
  }
};
</script>

<style>
</style>
