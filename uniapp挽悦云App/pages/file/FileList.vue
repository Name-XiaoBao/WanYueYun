<template>
  <view>
    <uni-section :title="'文档数量：'+filenum" type="line">
      <template v-slot:right>
        <uni-tag text="添加" type="primary" @click="addfile"/>
      </template>
    </uni-section>
    <image v-if="filenum==0"
           style="width: 100%;position: absolute;top: 50%;left: 50%;-webkit-transform: translate(-50%, -50%);-moz-transform: translate(-50%, -50%);-ms-transform: translate(-50%, -50%);-o-transform: translate(-50%, -50%);transform: translate(-50%, -50%);"
           src="@/static/nullData.svg"></image>
    <view v-else class="box" v-for="(item,key) in filelist">
      <text class="title" v-text="item.title"></text>
      <view class="Operation">
        <image src="/static/icon/CopyLink.png" @click="Copy(item.api,item.id,item.secretKey)"></image>
        <image src="/static/icon/Edit.png" @click="EditDocument(item.api,item.id,item.secretKey)"></image>
        <image src="/static/icon/Delete.png" @click="delApiFlie(item.id)"></image>
      </view>
    </view>
    <view>
      <!-- 提示窗 -->
      <uni-popup ref="alertDialog" type="dialog">
        <uni-popup-dialog type="error" confirmText="确定" title="删除文档" content="删除后将无法恢复,确定删除？"
                          @confirm="ConfirmDeletionOfApiFile"></uni-popup-dialog>
      </uni-popup>
    </view>
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
      filenum: null,
      filelist: null,
      delfileid: null
    }
  },
  methods: {
    addfile() {
      uni.navigateTo({
        url: 'AddFile?ApiName=' + this.name
      });
    },
    Copy(api, id, SecretKey) {
      if (SecretKey == null || SecretKey == "") {
        uni.setClipboardData({
          data: this.config.url + "api/RemoteDocument?&Api=" + api + "&id=" + id,
          success: function () {
            this.msg.msg("复制成功");
          }
        })
      } else {
        uni.setClipboardData({
          data: this.config.url + "api/RemoteDocument?&Api=" + api + "&id=" + id + "&SecretKey=" + SecretKey,
          success: function () {
            this.msg.msg("复制成功");
          }
        })
      }
    },
    delApiFlie(id) {
      this.$refs.alertDialog.open()
      this.delfileid = id
    },
    ConfirmDeletionOfApiFile() {
      uni.request({
        url: this.config.url + 'api/DelFile',
        data: {
          username: uni.getStorageSync('username'),
          password: uni.getStorageSync('password'),
          Api: this.name,
          id: this.delfileid
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          if (res.data.code == 200) {
            this.msg.msg(res.data.msg);
            // 获取文档列表
            uni.request({
              url: this.config.url + 'api/FileList',
              data: {
                username: uni.getStorageSync('username'),
                password: uni.getStorageSync('password'),
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
            this.delfileid = null
          } else {
            this.msg.msg(res.data.msg);
          }
        },
      })
    },
    EditDocument(api, id, SecretKey) {
      uni.navigateTo({
        url: 'UpFile?ApiName=' + this.name + "&id=" + id + "&SecretKey=" + SecretKey
      })
    },
    initData() {
      uni.request({
        url: this.config.url + 'api/FileList',
        data: {
          username: uni.getStorageSync('username'),
          password: uni.getStorageSync('password'),
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
      title: "远程文档（" + this.name + "）"
    });
    // 获取文档列表
    uni.request({
      url: this.config.url + 'api/FileList',
      data: {
        username: uni.getStorageSync('username'),
        password: uni.getStorageSync('password'),
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

.title {
  margin-left: 5%;
  width: 60% !important;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

.Operation {
  float: right;
  position: absolute;
  left: 70%;
}

.Operation image {
  width: 20px;
  height: 20px;
  margin-left: 10px;
}

.vip {
  margin-left: 5px;
  width: 30px;
  height: 15px;
  border-radius: 20px;
}

.integral {
  margin: 0px 5px 0px 15px;
  width: 20px;
  height: 20px;
}

.popup {
  padding: 20px;
  width: 250px;
}
</style>