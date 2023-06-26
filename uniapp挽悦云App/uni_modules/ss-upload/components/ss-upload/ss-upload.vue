<template>
  <view class="box">
    <slot></slot>
    <!-- #ifdef APP-PLUS-->
    <web-view src="/uni_modules/ss-upload/hybrid/html/file.html" @message="getMessage"></web-view>
    <!-- #endif -->
  </view>
</template>

<script>
var wvCurrent; //计划创建的webview
export default {
  name: 'ss-upload',
  props: {
    isUploadServer: { //上传成功之后是否直接上传服务器；设置为true时必传uploadOptions
      type: Boolean,
      default: false,
    },
    uploadOptions: {//上传服务器相关信息
      type: Object,
      default: () => {
        return {
          // 上传服务器地址，此地址需要替换为你的接口地址
          url: '',
          //请求方式，get,post
          type: 'post',
          // 上传附件的key
          name: 'file',
          // 根据你接口需求自定义请求头
          header: {
            // 'token':'',
          },
          // 根据你接口需求自定义body参数
          formData: {
            // key:'1'
          }
        }
      }
    },
    webviewStyle: {//原生子窗体样式
      type: Object,
      default: () => {
        return {
          top: 125,
          left: 24,
          height: 50,
          width: 130,
        }
      }
    },
    fileInput: {//webview内部上传input样式与内容
      type: Object,
      default: () => {
        return {
          fileStyle: {
            borderRadius: '10px',
            backgroundColor: '#1975F7',
            color: '#fff',
            fontSize: '20px',
          },
          fileTitle: '上传附件'
        }
      }
    }
  },
  data() {
    return {};
  },
  mounted() {
    // #ifdef APP-PLUS
    var currentWebview = this.$parent.$scope.$getAppWebview()
    setTimeout(() => {
      wvCurrent = this.$parent.$scope.$getAppWebview().children()[0];
      wvCurrent.setStyle(this.webviewStyle)
      wvCurrent.evalJS(`setStyle(${JSON.stringify(this.fileInput)})`);
    }, 500)//如果是页面初始化调用时，需要延时一下，如果界面出不来，延时时间可改长一点
    // #endif
  },
  methods: {
    getMessage(e) {
      if (e.detail.data[0].filePath) {
        let result = e.detail.data[0].filePath
        this.$emit('getFile', result)
      }
      if (e.detail.data[0].files) {
        let result = [JSON.parse(e.detail.data[0].files)]
        this.$emit('uploadSuccess', result)
      }
    },
    uploadFile() {
      // // 选择图片文件
      var that = this;
      //#ifdef H5
      uni.chooseFile({
        count: 10, //最多可以选择的文件数量，默认100
        type: 'all', //所选的文件的类型image,video,pdf等，默认all
        //extension:['.png','.zip','.doc'],//根据文件拓展名过滤，每一项都不能是空字符串。默认不过滤。暂只支持文件后缀名，例如['.zip','.exe','.js']，不支持application/msword等类似值
        sourceType: ['album',
          'camera'], //仅在type为image或video时可用）album 从相册选图，camera 使用相机，默认二者都有。如需直接开相机或直接选相册，请只使用一个选项
        success(res) {
          // tempFilePath可以作为img标签的src属性显示图片
          console.log('h5上传成功', res)
          if (that.isUploadServer) {
            that.getUpload(res.tempFiles)
          }
        },
        fail(err) {
          //接口调用失败
          console.log('失败' + err)
        },
        complete(result) {
          //接口调用结束（调用成功、失败）
          that.$emit('getFile', result)
        }
      })
      //#endif
      //#ifdef MP-WEIXIN
      wx.chooseMessageFile({ //仅支持选择微信文件里面的文件，还可使用uni.chooseMedia仅支持微信小程序，只能从相册上传视频或者图片
        count: 9, //可同时选择视频和图片还有文件，不可单选
        success(res) {
          console.log('wx上传成功', res)
          if (that.isUploadServer) {
            that.getUpload(res.tempFiles)
          }
        },
        fail(err) {
          //接口调用失败
          console.log('失败' + err)
        },
        complete(result) {
          //接口调用结束（调用成功、失败）
          that.$emit('getFile', result)
        }
      })
      //#endif

    },
    async getUpload(fileList) {
      let that = this;
      let result = await Promise.all(
          fileList.map(item => {
            console.log("item", item)
            return new Promise((reslove, reject) => {
              //使用uni.uploadfile上传注意两点
              //1.微信小程序只能单个上传，不能上传文件数组，只能上传filepath,会导致后端拿不到name，解决办法可使用formData将name传递给后端保存，
              //2.formData内部传参不能传boolean值，否则会导致上传失败
              uni.uploadFile({
                url: that.uploadOptions.url,
                filePath: item.path ? item.path : item,
                name: that.uploadOptions.name,
                header: that.uploadOptions.header,
                complete: uploadFileRes => {
                  let data = JSON.parse(uploadFileRes.data);
                  reslove(data);
                },
              });
            });
          })
      );
      console.log('服务器返回结果', result)
      this.$emit('uploadSuccess', result)
    }
  },
  watch: {
    uploadOptions: {
      deep: true,
      immediate: true,
      handler: function (value) {
        // #ifdef APP-PLUS
        if (this.isUploadServer) {
          setTimeout(() => {
            wvCurrent.evalJS(`passInfo(${JSON.stringify(value)})`);
          }, 1000)
        }
        // #endif
      }
    },
  }
};
</script>
<style lang="scss" scoped>
.box {
  padding: 20 rpx 40 rpx;
}

.fileClass {
  width: 130px;
  height: 50px;
  border-radius: 10px;
  overflow: hidden;
  background-color: #1975F7;
  color: #fff;
  text-decoration: none;
  text-indent: 0;
  line-height: 50px;
  font-size: 20px;
}
</style>