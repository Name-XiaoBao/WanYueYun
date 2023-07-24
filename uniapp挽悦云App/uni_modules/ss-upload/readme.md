#ss-upload

### 本地上传图片，视频和文档，兼容H5端，微信小程序端，移动端APP

### 不清楚使用方式可点击右侧导入示例项目运行完整示例

## 属性使用说明

| 属性		            | 是否必填	 |     值类型	 |   默认值	 |                   说明			                   |
|-----------------|-------|---------:|-------:|:-----------------------------------------:|
| isUploadServer	 | 	否		  | Boolean	 | false	 | 上传成功之后是否直接上传服务器；设置为true时必传uploadOptions		 |
| uploadOptions	  | 	否 		 |  Object	 |    {}	 |                上传服务器相关信息		                |
| webviewStyle	   | 	否		  |  Object	 |    {}	 |               App原生子窗体样式		                |
| fileInput	      | 	否 		 |  Object	 |    {}	 |      APP原生子窗体webview内部上传input样式与内容		      |

##事件使用说明
| 事件名称 | 说明 | 返回值 |
| --------- | -------- | -----:    |
| getFile | 获取上传文件本地地址 | -- |
| uploadSuccess | 上传到服务器返回结果触发 | [{errorcode：0,msg:'操作成功',data:''}]    |

## 注意事项
### H5插件内部是使用uni-app官方内部方法uni.chooseFile
### 小程序端因hybrid不能使用本地HTML，所以插件提供的是uni-app官方内部方法wx.chooseMessageFile
### 移动端是使用 web-view 组件，在 web-view组件内使用input元素type="file"进行实现；目前支持上传所有文件（图片，视频，文件)，想要单个只上传图片或者视频或者某一个文件可以参考input的accept属性
### H5端和小程序端支持一次性上传多个，APP端目前测试只支持一次性上传一个

### 不清楚使用方式可点击右侧导入示例项目运行完整示例、
### 如果插件对您有用请点亮小星星，谢谢！
