<template>
  <view>
    <view class="box">
      <image src="@/static/logo.png" class="Avatar"></image>
      <view>
        <text>挽悦云后台</text>
      </view>
    </view>
    <uni-section title="关于我们" type="line" style="width: 90%;margin: 0% 5% 5% 5%;border-radius: 10px;">
      <uni-list>
        <uni-list-item
            title="挽悦云是由小暴技术团队精心开发的免费云后台服务。该服务提供了用户管理、云文档Edit等丰富的功能。\n除此之外，挽悦云后台还拥有简洁易用的界面设计和稳定高效的服务性能，为用户提供了优质的云端服务体验。\n后台开发时间为2022年12月10日\n上线时间为2023年02月15日"/>
      </uni-list>
    </uni-section>
    <uni-section title="联系我们" type="line" style="width: 90%;margin: 0% 5% 5% 5%;border-radius: 10px;">
      <uni-list>
        <uni-list-item title="官方Q群:816467119"/>
        <uni-list-item title="总负责人QQ:1753147761"/>
        <uni-list-item title="总负责人微信:bao1753147761"/>
      </uni-list>
    </uni-section>
    <uni-section title="友情链接" type="line"
                 style="width: 90%;margin: 0% 5% 5% 5%;border-radius: 10px;padding-bottom: 10%;">
      <uni-list>
        <uni-list-item v-for="url in links" :title="url.title" clickable="true" showArrow
                       @click="FriendlyLinks(url.url)"/>
      </uni-list>
    </uni-section>
  </view>
</template>

<script>
export default {
  data() {
    return {
      links: null
    }
  },
  methods: {
    FriendlyLinks(url) {
      plus.runtime.openWeb(url);
    }
  },
  created() {
    uni.request({
      url: this.config.url + 'links',
      data: {},
      method: 'POST',//请求方式，必须为大写
      header: {
        'content-type': "application/x-www-form-urlencoded"
      },
      success: (res) => {
        this.links = res.data.data
      },
    })
  }
}
</script>

<style>
.box {
  width: 90%;
  height: 170px;
  margin: 5%;
  text-align: center;
  border-radius: 5%;
  background: url('@/static/WaveBackground.svg') 0 / auto 100%;
  box-shadow: 0px 0px 1.2px rgba(0, 0, 0, 0.025),
  0px 0px 2.9px rgba(0, 0, 0, 0.036),
  0px 0px 5.5px rgba(0, 0, 0, 0.045),
  0px 0px 9.8px rgba(0, 0, 0, 0.054),
  0px 0px 18.4px rgba(0, 0, 0, 0.065),
  0px 0px 44px rgba(0, 0, 0, 0.09);
}

.Avatar {
  width: 80px;
  height: 80px;
  margin: 5%;
  border-radius: 100%;
  box-shadow: 0px 0px 1.4px rgba(0, 0, 0, 0.036),
  0px 0px 4.7px rgba(0, 0, 0, 0.054),
  0px 0px 21px rgba(0, 0, 0, 0.09);
}

.popup {
  padding: 20px;
  width: 250px;
}
</style>