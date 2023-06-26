<template>
  <view style="height: 60px;margin-top: 8%;">
    <view class="nav">
      <view class="view-box" v-for="(item,index) in datalist" :key="index"
            :class="current_type === index ? 'typeActive' : ''" @click="btn(index,item.url)">
        <image class="icon" :src="item.icon"></image>
        <text class="title" v-text="item.title"></text>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  data() {
    return {
      datalist: [
        {title: "首页", icon: require('@/static/icon/HomePage.png'), url: "index"},
        {title: "文档", icon: require('@/static/icon/Document.png'), url: "File"},
        {title: "我", icon: require('@/static/icon/Me.png'), url: "PersonalCenter"},
      ],
      current_type: 0
    }
  },
  methods: {
    btn(index, url) {
      if (this.current_type != index) {
        this.current_type = index;
        this.typePopup = false;
        this.type_txt = this.datalist[index].title;
        uni.redirectTo({
          //关闭当前页面，跳转到应用内的某个页面。
          url: url
        });
      }

    }
  },
  created() {

  }
}
</script>

<style>
.nav {
  width: 100%;
  height: 60px;
  background-color: #fff;
  position: fixed; /*1、【relative】相对定位；2、【absolute】绝对定位；3、【fixed】固定定位；4、【static】默认值；5、【sticky】粘性定位。*/
  left: 0px; /*定位：离页面左边0px的位置*/
  bottom: 0px; /*定位：离页面底部0px的位置*/
  padding-top: 10px;
  flex-flow: row;
  justify-content: space-around;
  display: flex;
}

.view-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: #fff;
}

.icon {
  width: 5vw;
  height: 5vw;
}

.title {
  margin-top: 3px;
  font-size: 20 upx;
}

.typeActive {
  color: cornflowerblue;
}
</style>