<template>
  <view>
    <swiper indicator-dots="true" autoplay="true" interval="3000" style="height: 200px;">
      <swiper-item v-for="(carousel,index) in rotation" @click="rotationChart(carousel.url)">
        <image style="width: 100%;height: 100%;" :src="carousel.img"></image>
      </swiper-item>
    </swiper>
    <uni-notice-bar :speed=50 show-get-more show-icon scrollable more-text="查看" :text="Notice.content"
                    @click="getMore"/>
    <uni-section :title="'API列表：'+ApiNum" type="line">
      <template v-slot:right>
        <uni-tag text="添加" type="primary" @click="addApi"/>
      </template>
    </uni-section>
    <image v-if="ApiNum==0" style="width: 100%;" src="../static/nullData.svg"></image>
    <view v-else class="box" v-for="api in ApiList" @click="apiList(api.id,api.name)">
      <text class="name" v-text="api.name"></text>
      <uni-tag inverted="true" circle="true" :text="api.id" type="primary" class="id"/>
    </view>
    <!-- 导航栏 -->
    <navigation-bar ref="nav"></navigation-bar>
    <view>
      <!-- 提示窗 -->
      <uni-popup ref="alertDialog" type="dialog">
        <uni-popup-dialog type="warning" confirmText="确定" :title="this.Notice.title"
                          :content="this.Notice.content"></uni-popup-dialog>
      </uni-popup>
    </view>
    <view>
      <!-- 输入框示例 -->
      <uni-popup ref="inputDialog" type="dialog">
        <uni-popup-dialog ref="inputClose" mode="input" title="创建API" placeholder="请输入API名称"
                          @confirm="CreateApi"></uni-popup-dialog>
      </uni-popup>
    </view>
  </view>
</template>

<script>
import navigationBar from '@/components/NavigationBar.vue';

export default {
  mounted() {
    this.initData()
  },
  components: {
    navigationBar
  },
  data() {
    return {
      // 轮播图
      rotation: null,
      // 公告
      Notice: {
        title: null,
        content: null
      },
      // API列表
      ApiList: null,
      // API数量
      ApiNum: null,
      // 要创建的API名称
      ApiName: null
    }
  },
  methods: {
    // 设置导航栏选中状态
    initData() {
      this.$refs["nav"].datalist[0].icon = require('@/static/icon/HomePage_Selected.png')
      this.$refs["nav"].datalist[1].icon = require('@/static/icon/Document.png')
      this.$refs["nav"].datalist[2].icon = require('@/static/icon/Me.png')
    },
    // 轮播图点击事件
    rotationChart(url) {
      plus.runtime.openWeb(url);
    },
    // 公告点击事件
    getMore() {
      this.$refs.alertDialog.open()
    },
    // API列表点击事件
    apiList(id, name) {
      uni.navigateTo({
        url: 'Api?ApiID=' + id + '&ApiName=' + name
      });
    },
    // 创建API
    addApi() {
      this.$refs.inputDialog.open()
    },
    // 确认创建API点击事件
    CreateApi(apiname) {
      this.ApiName = apiname
      uni.request({
        url: this.config.url + 'addApi',
        data: {
          username: uni.getStorageSync('username'),
          password: uni.getStorageSync('password'),
          name: apiname
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          this.msg.msg(res.data.msg);
          this.ApiName = null
          // 刷新API列表
          uni.request({
            url: this.config.url + 'ApiList',
            data: {
              username: uni.getStorageSync('username'),
              password: uni.getStorageSync('password')
            },
            header: {
              'content-type': "application/x-www-form-urlencoded"
            },
            method: 'POST',//请求方式，必须为大写
            success: (res) => {
              this.ApiList = res.data.data
              this.ApiNum = res.data.data.length
            },
          })
        },
      })
    }
  },
  created() {
    // 检查更新
    plus.runtime.getProperty(plus.runtime.appid, (info) => {
      uni.request({
        url: this.config.url + 'Edition',
        method: 'GET',//请求方式，必须为大写
        success: (res) => {
          if (res.data.data.androidVersion > info.version) {
            uni.showModal({
              //提醒用户更新
              title: '更新提示',
              content: res.data.data.versionUpdateContent,
              showCancel: false,
              confirmText: '升级',
              success: ress => {
                if (ress.confirm) {
                  // 更新操作，resetURL 是app更新的接口地址
                  let resetURL = res.data.data.androidDownload;
                  plus.runtime.openURL(resetURL);
                }
              }
            })
          } else if (res.data.data.androidVersion <= info.version) {
            console.log("最新版本");
          }
        }
      });
    })
    // 检测是否登录
    if (uni.getStorageSync('username') == "" && uni.getStorageSync('password') == "") {
      uni.redirectTo({
        //关闭当前页面，跳转到应用内的某个页面。
        url: 'login'
      });
    } else {
      console.log("已登录");
      console.log("用户名：" + uni.getStorageSync('username'));
      console.log("密码：" + uni.getStorageSync('password'));
    }
    // 获取轮播图
    uni.request({
      url: this.config.url + 'Carousel',
      method: 'GET',//请求方式，必须为大写
      success: (res) => {
        this.rotation = res.data.data
      }
    });
    // 获取公告
    uni.request({
      url: this.config.url + 'Notice',
      method: 'GET',//请求方式，必须为大写
      success: (res) => {
        this.Notice = res.data.data[0]
      }
    });
    // 获取API列表
    uni.request({
      url: this.config.url + 'ApiList',
      data: {
        username: uni.getStorageSync('username'),
        password: uni.getStorageSync('password')
      },
      header: {
        'content-type': "application/x-www-form-urlencoded"
      },
      method: 'POST',//请求方式，必须为大写
      success: (res) => {
        this.ApiList = res.data.data
        this.ApiNum = res.data.data.length
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

.id {
  position: absolute;
  left: 80%;
}
</style>
