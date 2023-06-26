<template>
  <view>
    <view class="box">
      <image :src="avatar" class="Avatar"></image>
      <view>
        <text>{{username}}</text>
      </view>
    </view>
    <uni-section title="个人中心" type="line" style="width: 90%;margin: 0% 5% 5% 5%;border-radius: 10px;">
      <uni-list>
        <uni-list-item showArrow title="修改密码" clickable="true" @click="newpass"/>
        <uni-list-item showArrow title="退出登录" clickable="true" @click="LogOutOfLogin"/>
      </uni-list>
    </uni-section>
    <uni-section title="挽悦云" type="line" style="width: 90%;margin: 0% 5% 5% 5%;border-radius: 10px;">
      <uni-list>
        <uni-list-item showArrow title="入群交流" clickable="true" @click="QqGroup"/>
        <uni-list-item showArrow title="应用反馈" clickable="true" @click="Feedback"/>
        <uni-list-item showArrow title="系统公告" clickable="true" @click="getMore"/>
        <uni-list-item showArrow title="检查更新" clickable="true" @click="CheckForUpdates"/>
        <uni-list-item showArrow title="关于软件" clickable="true" @click="About"/>
        <uni-list-item showArrow title="赞助挽悦云" clickable="true" @click="Sponsor"/>
      </uni-list>
    </uni-section>
    <!-- 导航栏 -->
    <navigation-bar ref="nav"></navigation-bar>
    <view>
      <!-- 提示窗 -->
      <uni-popup ref="alertDialog" type="dialog">
        <uni-popup-dialog type="warning" confirmText="确定" :title="this.Notice.title"
                          :content="this.Notice.content"></uni-popup-dialog>
      </uni-popup>
    </view>
    <!-- 修改密码 -->
    <uni-popup ref="newpass" background-color="#fff">
      <view class="popup">
        <uni-easyinput type="password" class="uni-mt-5" trim="all" v-model="newpassword"
                       placeholder="请输入新密码"></uni-easyinput>
        <button @click="updataPass"
                style="color: #fff;margin-top: 10px;font-size: 13px;border-radius: 10px;background-color:cornflowerblue">
          修改
        </button>
      </view>
    </uni-popup>
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
      avatar: null,
      username: uni.getStorageSync('username'),
      // 公告
      Notice: {
        title: null,
        content: null
      },
      newpassword: null
    }
  },
  methods: {
    initData() {
      this.$refs["nav"].current_type = 2
      this.$refs["nav"].datalist[0].icon = require('@/static/icon/HomePage.png')
      this.$refs["nav"].datalist[1].icon = require('@/static/icon/Document.png')
      this.$refs["nav"].datalist[2].icon = require('@/static/icon/Me_Selected.png')
    },
    LogOutOfLogin() {
      uni.showModal({
        title: '退出登录',
        content: "确定退出登录？",
        showCancel: true,
        confirmText: '确定',
        success: res => {
          if (res.confirm) {
            uni.removeStorageSync('username');
            uni.removeStorageSync('password');
            uni.redirectTo({
              //关闭当前页面，跳转到应用内的某个页面。
              url: 'login'
            });
          }
        }
      })
    },
    QqGroup() {
      let key = 'h-S6xEd5pRgjpGFYwIWYNw5NA0t_bUGD';
      plus.runtime.openURL('mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D' + key);
    },
    CheckForUpdates() {
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
              this.msg.msg("已是最新版本");
            }
          }
        });
      })
    },
    // 公告
    getMore() {
      this.$refs.alertDialog.open()
    },
    newpass() {
      this.$refs.newpass.open()
    },
    updataPass() {
      uni.request({
        url: this.config.url + 'up_user',
        data: {
          username: this.username,
          password: uni.getStorageSync('password'),
          NewPassword: this.newpassword
        },
        method: 'POST',//请求方式，必须为大写
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        success: (res) => {
          uni.removeStorageSync('username');
          uni.removeStorageSync('password');
          uni.redirectTo({
            //关闭当前页面，跳转到应用内的某个页面。
            url: 'login'
          });
        },
      })
    },
    Feedback() {
      uni.navigateTo({
        url: 'Feedback'
      });
    },
    // 赞助
    Sponsor() {
      uni.saveImageToPhotosAlbum({
        filePath: require('@/static/SponsorshipCode.jpg'),
        success: function () {
          uni.showModal({
            title: '赞助',
            content: '已将赞助码保存至相册',
            showCancel: false,
            confirmText: '确定',
            success: ress => {

            }
          })
        }
      });
    },
    About() {
      uni.navigateTo({
        url: 'About'
      });
    }
  },
  mounted() {
    this.initData()
  },
  created() {
    // 获取QQ头像
    uni.request({
      url: 'https://ptlogin2.qq.com/getface',
      data: {
        imgtype: 1,
        uin: this.username.split("@")[0]
      },
      method: 'GET',//请求方式，必须为大写
      success: (res) => {
        if (res.data.indexOf(this.username.split("@")[0]) != -1) {
          this.avatar = "https://q.qlogo.cn/headimg_dl?dst_uin=" + this.username.split("@")[0] + "&spec=640&img_type=jpg"
        } else {
          this.avatar = require('@/static/logo.png')
        }
      },
    })
    // 获取公告
    uni.request({
      url: this.config.url + 'Notice',
      method: 'GET',//请求方式，必须为大写
      success: (res) => {
        this.Notice = res.data.data[0]
      }
    });
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