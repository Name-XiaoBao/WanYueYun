var isReady = false;
var onReadyCallbacks = [];
var isServiceReady = false;
var onServiceReadyCallbacks = [];
var __uniConfig = {
    "pages": ["pages/index", "pages/File", "pages/login", "pages/Api", "pages/PersonalCenter", "pages/user/UserList", "pages/user/UpUser", "pages/file/FileList", "pages/file/AddFile", "pages/file/UpFile", "pages/cloud/CloudList", "pages/cloud/FileUpload", "pages/Feedback", "pages/About", "pages/carmi/CarmiList", "pages/msg"],
    "window": {"bounce": "none", "scrollIndicator": "none"},
    "darkmode": false,
    "nvueCompiler": "uni-app",
    "nvueStyleCompiler": "uni-app",
    "renderer": "auto",
    "splashscreen": {"alwaysShowBeforeRender": true, "autoclose": false},
    "appname": "挽悦云",
    "compilerVersion": "3.8.7",
    "entryPagePath": "pages/index",
    "networkTimeout": {"request": 60000, "connectSocket": 60000, "uploadFile": 60000, "downloadFile": 60000}
};
var __uniRoutes = [{
    "path": "/pages/index",
    "meta": {"isQuit": true},
    "window": {
        "navigationBarTitleText": "挽悦云",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/File",
    "meta": {},
    "window": {
        "navigationBarTitleText": "文档",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/login",
    "meta": {},
    "window": {
        "navigationBarTitleText": "登录",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/Api",
    "meta": {},
    "window": {
        "navigationBarTitleText": "API",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/PersonalCenter",
    "meta": {},
    "window": {
        "navigationBarTitleText": "个人中心",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/user/UserList",
    "meta": {},
    "window": {
        "navigationBarTitleText": "用户管理",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/user/UpUser",
    "meta": {},
    "window": {
        "navigationBarTitleText": "用户修改",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/file/FileList",
    "meta": {},
    "window": {
        "navigationBarTitleText": "远程文档",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/file/AddFile",
    "meta": {},
    "window": {
        "navigationBarTitleText": "新建文档",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/file/UpFile",
    "meta": {},
    "window": {
        "navigationBarTitleText": "文档Edit",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/cloud/CloudList",
    "meta": {},
    "window": {
        "navigationBarTitleText": "云储存",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black",
        "enablePullDownRefresh": true
    }
}, {
    "path": "/pages/cloud/FileUpload",
    "meta": {},
    "window": {
        "navigationBarTitleText": "文件上传",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/Feedback",
    "meta": {},
    "window": {
        "navigationBarTitleText": "反馈",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/About",
    "meta": {},
    "window": {
        "navigationBarTitleText": "关于",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}, {
    "path": "/pages/carmi/CarmiList",
    "meta": {},
    "window": {
        "navigationBarTitleText": "卡密列表",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black",
        "enablePullDownRefresh": true
    }
}, {
    "path": "/pages/msg",
    "meta": {},
    "window": {
        "navigationBarTitleText": "聊天室",
        "navigationBarBackgroundColor": "#fff",
        "navigationBarTextStyle": "black"
    }
}];
__uniConfig.onReady = function (callback) {
    if (__uniConfig.ready) {
        callback()
    } else {
        onReadyCallbacks.push(callback)
    }
};
Object.defineProperty(__uniConfig, "ready", {
    get: function () {
        return isReady
    }, set: function (val) {
        isReady = val;
        if (!isReady) {
            return
        }
        const callbacks = onReadyCallbacks.slice(0);
        onReadyCallbacks.length = 0;
        callbacks.forEach(function (callback) {
            callback()
        })
    }
});
__uniConfig.onServiceReady = function (callback) {
    if (__uniConfig.serviceReady) {
        callback()
    } else {
        onServiceReadyCallbacks.push(callback)
    }
};
Object.defineProperty(__uniConfig, "serviceReady", {
    get: function () {
        return isServiceReady
    }, set: function (val) {
        isServiceReady = val;
        if (!isServiceReady) {
            return
        }
        const callbacks = onServiceReadyCallbacks.slice(0);
        onServiceReadyCallbacks.length = 0;
        callbacks.forEach(function (callback) {
            callback()
        })
    }
});
service.register("uni-app-config", {
    create(a, b, c) {
        if (!__uniConfig.viewport) {
            var d = b.weex.config.env.scale, e = b.weex.config.env.deviceWidth, f = Math.ceil(e / d);
            Object.assign(__uniConfig, {viewport: f, defaultFontSize: Math.round(f / 20)})
        }
        return {
            instance: {
                __uniConfig: __uniConfig,
                __uniRoutes: __uniRoutes,
                global: void 0,
                window: void 0,
                document: void 0,
                frames: void 0,
                self: void 0,
                location: void 0,
                navigator: void 0,
                localStorage: void 0,
                history: void 0,
                Caches: void 0,
                screen: void 0,
                alert: void 0,
                confirm: void 0,
                prompt: void 0,
                fetch: void 0,
                XMLHttpRequest: void 0,
                WebSocket: void 0,
                webkit: void 0,
                print: void 0
            }
        }
    }
});
