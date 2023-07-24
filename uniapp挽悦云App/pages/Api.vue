<template>
  <view>
    <uni-card :title="name" :extra="id" style="border-radius: 10px;">
      <text>
        用户数量：{{ usernum }}
      </text>
      <text style="float: right;">
        启动次数：{{ startStatistics }}
      </text>
      <button @click="delApi"
              style="color: #fff;margin-top: 10px;font-size: 13px;border-radius: 10px;background-color:cornflowerblue">
        删除此API
      </button>
    </uni-card>
    <uni-card title="功能" style="border-radius: 10px;">
      <uni-grid :column="3" :showBorder="false" :square="false" :highlight="false">
        <uni-grid-item>
          <view class="Function" @click="userlist">
            <image src="@/static/icon/UserManagement.png" class="icon"></image>
            <text>用户管理</text>
          </view>
        </uni-grid-item>
        <uni-grid-item>
          <view class="Function" @click="filelist">
            <image src="@/static/icon/RemoteDocuments.png" class="icon"></image>
            <text>远程文档</text>
          </view>
        </uni-grid-item>
        <uni-grid-item>
          <view class="Function" @click="cloudlist">
            <image src="@/static/icon/CloudStorage.png" class="icon"></image>
            <text>云储存</text>
          </view>
        </uni-grid-item>
        <uni-grid-item>
          <view class="Function" @click="Carmilist">
            <image src="@/static/icon/Carmi.png" class="icon"></image>
            <text>卡密列表</text>
          </view>
        </uni-grid-item>
        <uni-grid-item>
          <view class="Function" @click="msg">
            <image src="@/static/icon/msg.png" class="icon"></image>
            <text>聊天室</text>
          </view>
        </uni-grid-item>
        <uni-grid-item>
          <view class="Function" @click="Empty">
            <image src="@/static/icon/Empty.png" class="icon"></image>
            <text style="color: #c1c1c1;">清空启动次数</text>
          </view>
        </uni-grid-item>
      </uni-grid>
    </uni-card>
    <view>
      <!-- 提示窗 -->
      <uni-popup ref="alertDialog" type="dialog">
        <uni-popup-dialog type="error" confirmText="确定" title="删除API" content="删除后将无法恢复,确定删除？"
                          @confirm="ConfirmDeletionOfApi"></uni-popup-dialog>
      </uni-popup>
    </view>
    <view>
      <!-- 提示窗 -->
      <uni-popup ref="StartStatistics" type="dialog">
        <uni-popup-dialog type="error" confirmText="确定" title="清空启动统计" content="清空后将无法恢复,确定清空？"
                          @confirm="Emptys"></uni-popup-dialog>
      </uni-popup>
    </view>
  </view>
</template>

<script>
export default {
  onLoad: function (option) { //option为object类型，会序列化上个页面传递的参数
    this.id = "ID:" + option.ApiID,
        this.name = option.ApiName,
        this.apiID = option.ApiID
  },
  data() {
    return {
      id: null,
      name: null,
      usernum: null,
      startStatistics: null
    }
  },
  methods: {
    // 删除API
    delApi() {
      this.$refs.alertDialog.open()
    },
    // 确认删除API点击事件
    ConfirmDeletionOfApi() {
      uni.request({
        url: this.config.url + 'deApi',
        data: {
          username: uni.getStorageSync('username'),
          password: uni.getStorageSync('password'),
          id: this.apiID
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          this.msg.msg(res.data.msg);
          uni.redirectTo({
            //关闭当前页面，跳转到应用内的某个页面。
            url: 'index'
          });
        },
      })
    },
    // 清空启动统计
    Empty() {
      this.$refs.StartStatistics.open()
    },
    // 清空启动统计点击事件
    Emptys() {
      uni.request({
        url: this.config.url + 'DelStartStatistics',
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
          if (res.data.code == 200) {
            uni.request({
              url: this.config.url + 'StartStatistics',
              data: {
                Api: this.name
              },
              header: {
                'content-type': "application/x-www-form-urlencoded"
              },
              method: 'POST',//请求方式，必须为大写
              success: (res) => {
                if (res.data.data == null) {
                  this.startStatistics = 0
                } else {
                  this.startStatistics = res.data.data.num
                }
              },
            })
          }
        },
      })
    },
    userlist() {
      uni.navigateTo({
        url: 'user/UserList?ApiName=' + this.name
      });
    },
    filelist() {
      uni.navigateTo({
        url: 'file/FileList?ApiName=' + this.name
      });
    },
    cloudlist() {
      uni.navigateTo({
        url: 'cloud/CloudList?ApiName=' + this.name
      });
    },
    Carmilist() {
      uni.navigateTo({
        url: 'carmi/CarmiList?ApiName=' + this.name
      });
    },
    msg() {
      uni.navigateTo({
        url: 'msg?ApiName=' + this.name
      });
    }
  },
  created() {
    // 改变标题栏
    uni.setNavigationBarTitle({
      title: this.name
    });
    // 获取用户数量
    uni.request({
      url: this.config.url + 'api/UserList',
      data: {
        username: uni.getStorageSync('username'),
        password: uni.getStorageSync('password'),
        name: this.name
      },
      header: {
        'content-type': "application/x-www-form-urlencoded"
      },
      method: 'POST',//请求方式，必须为大写
      success: (res) => {
        this.usernum = res.data.data.length
      },
    })
    // 获取API启动次数
    uni.request({
      url: this.config.url + 'StartStatistics',
      data: {
        Api: this.name
      },
      header: {
        'content-type': "application/x-www-form-urlencoded"
      },
      method: 'POST',//请求方式，必须为大写
      success: (res) => {
        if (res.data.data == null) {
          this.startStatistics = 0
        } else {
          this.startStatistics = res.data.data.num
        }
      },
    })
  }
}
</script>

<style>
.Function {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: #000;
}

.icon {
  width: 35px;
  height: 35px;
  margin-bottom: 10px;
  padding-top: 33px;
}
</style>