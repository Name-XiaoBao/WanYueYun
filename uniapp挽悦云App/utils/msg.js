const msg = function (text) {
    uni.showLoading({
        title: text,
        icon: 'none'
    })
    setTimeout(() => {
        uni.hideLoading()
        ole.log(val)
        this.value = val
        this.$refs.inputDialog.close()
    }, 2000)
}
//声明函数，可以被引用
module.exports = {
    msg
}
