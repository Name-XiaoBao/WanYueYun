<template>
  <view>
    <uni-section :title="'文件数量：'+filenum" type="line">
      <template v-slot:right>
        <uni-tag text="上传" type="primary" @click="FileUpload"/>
      </template>
    </uni-section>
    <image v-if="filenum==0"
           style="width: 100%;position: absolute;top: 50%;left: 50%;-webkit-transform: translate(-50%, -50%);-moz-transform: translate(-50%, -50%);-ms-transform: translate(-50%, -50%);-o-transform: translate(-50%, -50%);transform: translate(-50%, -50%);"
           src="@/static/nullData.svg"></image>
    <view v-else style="padding-bottom:5%;" v-for="(item,key) in filelist">
      <uni-card spacing="0" padding="0"
                v-if="item.url.split('.')[item.url.split('.').length-1]=='jpg' || item.url.split('.')[item.url.split('.').length-1]=='png' || item.url.split('.')[item.url.split('.').length-1]=='jpeg'">
        <template v-slot:cover>
          <view>
            <image class="Picture" mode="aspectFill" :src="item.url"></image>
          </view>
        </template>
        <uni-list>
          <uni-list-item @click="CopyLink(item.url)" :title="item.fileName+'（点击复制图片链接）'" link
                         showArrow></uni-list-item>
          <uni-list-item @click="DeleteFiles(item.fileName)" title="删除此图片" link showArrow></uni-list-item>
        </uni-list>
      </uni-card>
      <uni-card spacing="0" padding="0"
                v-else-if="item.url.split('.')[item.url.split('.').length-1]=='mp3' || item.url.split('.')[item.url.split('.').length-1]=='m4a'">
        <template v-slot:cover>
          <view>
            <audio class="Picture" :src="item.url" controls></audio>
          </view>
        </template>
        <uni-list>
          <uni-list-item @click="CopyLink(item.url)" :title="item.fileName+'（点击复制音频链接）'" link
                         showArrow></uni-list-item>
          <uni-list-item @click="DeleteFiles(item.fileName)" title="删除此音频" link showArrow></uni-list-item>
        </uni-list>
      </uni-card>
      <uni-card spacing="0" padding="0" v-else-if="item.url.split('.')[item.url.split('.').length-1]=='mp4'">
        <template v-slot:cover>
          <view>
            <video class="Picture" :src="item.url" controls></video>
          </view>
        </template>
        <uni-list>
          <uni-list-item @click="CopyLink(item.url)" :title="item.fileName+'（点击复制视频链接）'" link
                         showArrow></uni-list-item>
          <uni-list-item @click="DeleteFiles(item.fileName)" title="删除此视频" link showArrow></uni-list-item>
        </uni-list>
      </uni-card>
      <uni-card spacing="0" padding="0"
                v-else-if="item.url.split('.')[item.url.split('.').length-1]=='txt' || item.url.split('.')[item.url.split('.').length-1]=='html' || item.url.split('.')[item.url.split('.').length-1]=='js' || item.url.split('.')[item.url.split('.').length-1]=='css' || item.url.split('.')[item.url.split('.').length-1]=='json'">
        <template v-slot:cover>
          <view style="overflow: hidden;">
            <iframe :src="item.url" style="width: calc(100% + 17px); height: calc(100% + 17px);"
                    scrolling="no"></iframe>
          </view>
        </template>
        <uni-list>
          <uni-list-item @click="CopyLink(item.url)" :title="item.fileName+'（点击复制文件链接）'" link
                         showArrow></uni-list-item>
          <uni-list-item @click="DeleteFiles(item.fileName)" title="删除此文件" link showArrow></uni-list-item>
        </uni-list>
      </uni-card>
      <uni-card spacing="0" padding="0" v-else>
        <template v-slot:cover>
          <view>
            <image class="Picture" mode="aspectFill" src="@/static/NotSupported.svg"></image>
          </view>
        </template>
        <uni-list>
          <uni-list-item @click="CopyLink(item.url)" :title="item.fileName+'（点击复制文件链接）'" link
                         showArrow></uni-list-item>
          <uni-list-item @click="DeleteFiles(item.fileName)" title="删除此文件" link showArrow></uni-list-item>
        </uni-list>
      </uni-card>
    </view>
    <view>
      <!-- 提示窗 -->
      <uni-popup ref="alertDialog" type="dialog">
        <uni-popup-dialog type="error" confirmText="确定" title="删除文件" content="删除后将无法恢复,确定删除？"
                          @confirm="DeleteFile"></uni-popup-dialog>
      </uni-popup>
    </view>
  </view>
</template>

<script>
export default {
  onPullDownRefresh() {
    this.initData()
    uni.stopPullDownRefresh()
  },
  onLoad: function (option) { //option为object类型，会序列化上个页面传递的参数
    this.name = option.ApiName
  },
  data() {
    return {
      name: null,
      filenum: null,
      filelist: null,
      filename: null
    }
  },
  methods: {
    // 复制链接
    CopyLink(url) {
      uni.setClipboardData({
        data: url,
        success: function () {
          this.msg.msg("复制成功");
        }
      })
    },
    // 删除文件点击事件
    DeleteFiles(name) {
      this.$refs.alertDialog.open()
      this.filename = name
    },
    // 删除文件
    DeleteFile() {
      uni.request({
        url: this.config.url + 'DelFile',
        data: {
          username: uni.getStorageSync('username'),
          password: uni.getStorageSync('password'),
          Api: this.name,
          filename: this.filename
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          if (res.data.code == 200) {
            this.msg.msg(res.data.msg);
            uni.request({
              url: this.config.url + 'file',
              data: {
                username: uni.getStorageSync('username'),
                Api: this.name
              },
              header: {
                'content-type': "application/x-www-form-urlencoded"
              },
              method: 'POST',//请求方式，必须为大写
              success: (res) => {
                this.filenum = res.data.data.length
                this.filelist = res.data.data
              },
            })
          } else {
            console.log(res);
          }
        },
      })
    },
    FileUpload() {
      uni.navigateTo({
        url: 'FileUpload?ApiName=' + this.name
      });
    },
    initData() {
      uni.request({
        url: this.config.url + 'file',
        data: {
          username: uni.getStorageSync('username'),
          Api: this.name
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          this.filenum = res.data.data.length
          this.filelist = res.data.data
        },
      })
    }
  },
  created() {
    // 改变标题栏
    uni.setNavigationBarTitle({
      title: "云储存（" + this.name + "）"
    });
    // 获取文件列表
    uni.request({
      url: this.config.url + 'file',
      data: {
        username: uni.getStorageSync('username'),
        Api: this.name
      },
      header: {
        'content-type': "application/x-www-form-urlencoded"
      },
      method: 'POST',//请求方式，必须为大写
      success: (res) => {
        this.filenum = res.data.data.length
        this.filelist = res.data.data
      },
    })
  }
}
</script>

<style>
.box {
  width: 96%;
  /* padding: 0 3%; */
  background-color: #fefefe;
  border-radius: 24 upx;
  /*  阴影 */
  box-shadow: 0 0 20 upx rgba(0, 0, 0, 0.15);
  margin: 2% 2% 0 upx 2%;
  display: flex;
  margin-bottom: 10px;
  align-items: center;
}

.Picture {
  width: 100%;
}

.popup {
  padding: 20px;
  width: 250px;
}
</style>
