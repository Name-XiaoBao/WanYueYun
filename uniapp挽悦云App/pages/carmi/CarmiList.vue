<template>
  <view>
    <uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" styleType="button"
                           activeColor="#007aff"></uni-segmented-control>
    <view class="content">
      <view v-show="current === 0">
        <uni-section :title="'积分卡密数量：'+IntegralCarmiNum" type="line">
          <template v-slot:right>
            <uni-tag text="添加" type="primary" @click="IntegralCarmipopup"/>
          </template>
        </uni-section>
        <image v-if="IntegralCarmiNum==0"
               style="width: 100%;position: absolute;top: 50%;left: 50%;-webkit-transform: translate(-50%, -50%);-moz-transform: translate(-50%, -50%);-ms-transform: translate(-50%, -50%);-o-transform: translate(-50%, -50%);transform: translate(-50%, -50%);"
               src="@/static/nullData.svg"></image>
        <view v-else class="box" v-for="(item,key) in IntegralCarmi">
          <text class="name" v-text="item.carmi"></text>
          <image src="/static/icon/Integral.png" class="integral"></image>
          <text>{{ item.content }}</text>
          <view class="Operation">
            <image src="/static/icon/CopyLink.png" @click="Copy(item.carmi)"></image>
          </view>
        </view>
      </view>
      <view v-show="current === 1">
        <uni-section :title="'会员卡密数量：'+MemberCarmiNum" type="line">
          <template v-slot:right>
            <uni-tag text="添加" type="primary" @click="MemberCarmipopup"/>
          </template>
        </uni-section>
        <image v-if="MemberCarmiNum==0"
               style="width: 100%;position: absolute;top: 50%;left: 50%;-webkit-transform: translate(-50%, -50%);-moz-transform: translate(-50%, -50%);-ms-transform: translate(-50%, -50%);-o-transform: translate(-50%, -50%);transform: translate(-50%, -50%);"
               src="@/static/nullData.svg"></image>
        <view v-else class="box" v-for="(item,key) in MemberCarmi">
          <text class="name" v-text="item.carmi"></text>
          <image src="/static/icon/vip.png" class="vip"></image>
          <text>{{ item.content }}天</text>
          <view class="Operation">
            <image src="/static/icon/CopyLink.png" @click="Copy(item.carmi)"></image>
          </view>
        </view>
      </view>
    </view>
    <view>
      <!-- 添加积分卡密 -->
      <uni-popup ref="IntegralCarmipopup" background-color="#fff">
        <view class="popup">
          <uni-easyinput v-model="CardNumber" class="uni-mt-5" trim="all" placeholder="卡密数量"></uni-easyinput>
          <uni-easyinput v-model="HamiltonianValue" class="uni-mt-5" trim="all" placeholder="积分数值"></uni-easyinput>
          <button @click="AddIntegralCarmi"
                  style="color: #fff;margin-top: 10px;font-size: 13px;border-radius: 10px;background-color:cornflowerblue">
            创建
          </button>
        </view>
      </uni-popup>
    </view>
    <view>
      <!-- 添加会员卡密 -->
      <uni-popup ref="MemberCarmipopup" background-color="#fff">
        <view class="popup">
          <uni-easyinput v-model="CardNumber" class="uni-mt-5" trim="all" placeholder="卡密数量"></uni-easyinput>
          <uni-easyinput v-model="HamiltonianValue" class="uni-mt-5" trim="all" placeholder="会员天数"></uni-easyinput>
          <button @click="AddMemberCarmi"
                  style="color: #fff;margin-top: 10px;font-size: 13px;border-radius: 10px;background-color:cornflowerblue">
            创建
          </button>
        </view>
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
      items: ['积分卡密', '会员卡密'],
      current: 0,
      IntegralCarmiNum: null,
      MemberCarmiNum: null,
      IntegralCarmi: null,
      MemberCarmi: null,
      CardNumber: null,
      HamiltonianValue: null
    }
  },
  methods: {
    onClickItem(e) {
      if (this.current != e.currentIndex) {
        this.current = e.currentIndex;
      }
    },
    initData() {
      // 获取积分卡密列表
      uni.request({
        url: this.config.url + 'api/CarmiType',
        data: {
          username: uni.getStorageSync('username'),
          password: uni.getStorageSync('password'),
          api: this.name,
          type: 'Integral'
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          this.IntegralCarmiNum = res.data.data.length
          this.IntegralCarmi = res.data.data
          // 获取会员卡密列表
          uni.request({
            url: this.config.url + 'api/CarmiType',
            data: {
              username: uni.getStorageSync('username'),
              password: uni.getStorageSync('password'),
              api: this.name,
              type: 'Member'
            },
            header: {
              'content-type': "application/x-www-form-urlencoded"
            },
            method: 'POST',//请求方式，必须为大写
            success: (res) => {
              this.MemberCarmiNum = res.data.data.length
              this.MemberCarmi = res.data.data
            },
          })
        },
      })
    },
    Copy(Carmi) {
      uni.setClipboardData({
        data: Carmi,
        success: function () {
          this.msg.msg("复制成功");
        }
      })
    },
    IntegralCarmipopup() {
      this.$refs.IntegralCarmipopup.open()
    },
    MemberCarmipopup() {
      this.$refs.MemberCarmipopup.open()
    },
    AddIntegralCarmi() {
      uni.request({
        url: this.config.url + 'api/AddCarmi',
        data: {
          username: uni.getStorageSync('username'),
          password: uni.getStorageSync('password'),
          api: this.name,
          type: 'Integral',
          num: this.CardNumber,
          Content: this.HamiltonianValue
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          if (res.data.code == 200) {
            this.msg.msg("创建成功");
            this.CardNumber = null
            this.HamiltonianValue = null
            this.$refs.IntegralCarmipopup.close()
            this.initData()
          } else {
            this.msg.msg("创建失败");
          }
        },
      })
    },
    AddMemberCarmi() {
      uni.request({
        url: this.config.url + 'api/AddCarmi',
        data: {
          username: uni.getStorageSync('username'),
          password: uni.getStorageSync('password'),
          api: this.name,
          type: 'Member',
          num: this.CardNumber,
          Content: this.HamiltonianValue
        },
        header: {
          'content-type': "application/x-www-form-urlencoded"
        },
        method: 'POST',//请求方式，必须为大写
        success: (res) => {
          if (res.data.code == 200) {
            this.msg.msg("创建成功");
            this.CardNumber = null
            this.HamiltonianValue = null
            this.$refs.MemberCarmipopup.close()
            this.initData()
          } else {
            this.msg.msg("创建失败");
          }
        },
      })
    }
  },
  created() {
    // 改变标题栏
    uni.setNavigationBarTitle({
      title: "卡密列表（" + this.name + "）"
    });
    // 获取积分卡密列表
    uni.request({
      url: this.config.url + 'api/CarmiType',
      data: {
        username: uni.getStorageSync('username'),
        password: uni.getStorageSync('password'),
        api: this.name,
        type: 'Integral'
      },
      header: {
        'content-type': "application/x-www-form-urlencoded"
      },
      method: 'POST',//请求方式，必须为大写
      success: (res) => {
        this.IntegralCarmiNum = res.data.data.length
        this.IntegralCarmi = res.data.data
        // 获取会员卡密列表
        uni.request({
          url: this.config.url + 'api/CarmiType',
          data: {
            username: uni.getStorageSync('username'),
            password: uni.getStorageSync('password'),
            api: this.name,
            type: 'Member'
          },
          header: {
            'content-type': "application/x-www-form-urlencoded"
          },
          method: 'POST',//请求方式，必须为大写
          success: (res) => {
            this.MemberCarmiNum = res.data.data.length
            this.MemberCarmi = res.data.data
          },
        })
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
  margin-left: 5%;
}

.Operation {
  float: right;
  position: absolute;
  left: 85%;
}

.Operation image {
  width: 20px;
  height: 20px;
  margin: 8px;
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