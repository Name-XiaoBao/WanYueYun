let api = [];
const apiDocListSize = 1
api.push({
    name: 'default',
    order: '1',
    list: []
})
api[0].list.push({
    alias: 'ApiContoller',
    order: '1',
    link: 'api管理',
    desc: 'API管理',
    list: []
})
api[0].list[0].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/addApi',
    desc: '创建API',
});
api[0].list[0].list.push({
    order: '2',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/upApi',
    desc: '修改API信息',
});
api[0].list[0].list.push({
    order: '3',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/ApiList',
    desc: '查询用户API列表',
});
api[0].list[0].list.push({
    order: '4',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/deApi',
    desc: '删除用户API',
});
api[0].list.push({
    alias: 'ApiUserContoller',
    order: '2',
    link: 'api用户管理',
    desc: 'API用户管理',
    list: []
})
api[0].list[1].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/Register',
    desc: 'API用户注册功能',
});
api[0].list[1].list.push({
    order: '2',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/login',
    desc: 'API用户登录功能',
});
api[0].list[1].list.push({
    order: '3',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/UserList',
    desc: '查询API用户',
});
api[0].list[1].list.push({
    order: '4',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/UserInfo',
    desc: '查询API用户信息',
});
api[0].list[1].list.push({
    order: '5',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/UpPass',
    desc: '修改API用户密码',
});
api[0].list[1].list.push({
    order: '6',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/upIntegral',
    desc: '修改API用户积分',
});
api[0].list[1].list.push({
    order: '7',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/upMembershipTime',
    desc: '修改API用户会员时间',
});
api[0].list[1].list.push({
    order: '8',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/deluser',
    desc: '删除API用户',
});
api[0].list.push({
    alias: 'CarouselContoller',
    order: '3',
    link: '',
    desc: '',
    list: []
})
api[0].list[2].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/Carousel',
    desc: '获取轮播图列表',
});
api[0].list.push({
    alias: 'CodeContoller',
    order: '4',
    link: '发送邮箱验证码（间隔1分钟一次）',
    desc: '发送邮箱验证码（间隔1分钟一次）',
    list: []
})
api[0].list[3].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/VerificationCode',
    desc: '发送验证码（间隔1分钟一次）',
});
api[0].list.push({
    alias: 'EditionContoller',
    order: '5',
    link: '版本信息查询',
    desc: '版本信息查询',
    list: []
})
api[0].list[4].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/Edition',
    desc: '查询版本信息',
});
api[0].list.push({
    alias: 'EmailContoller',
    order: '6',
    link: '发送邮件',
    desc: '发送邮件',
    list: []
})
api[0].list[5].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/email',
    desc: '发送邮件（间隔三分钟一次）',
});
api[0].list.push({
    alias: 'FeedbackContoller',
    order: '7',
    link: '用户反馈（间隔三分钟一次）',
    desc: '用户反馈（间隔三分钟一次）',
    list: []
})
api[0].list[6].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/UserFeedback',
    desc: 'API用户反馈',
});
api[0].list[6].list.push({
    order: '2',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/Feedback',
    desc: '向挽悦云官方反馈',
});
api[0].list.push({
    alias: 'FileContoller',
    order: '8',
    link: '云储存',
    desc: '云储存',
    list: []
})
api[0].list[7].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/upload',
    desc: '上传API文件',
});
api[0].list[7].list.push({
    order: '2',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/file',
    desc: '查询API文件列表',
});
api[0].list[7].list.push({
    order: '3',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/DelFile',
    desc: '删除API文件',
});
api[0].list.push({
    alias: 'InterfaceContoller',
    order: '9',
    link: '接口文档查询',
    desc: '接口文档查询',
    list: []
})
api[0].list[8].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/Interface',
    desc: '查询API接口文档',
});
api[0].list.push({
    alias: 'LinksContoller',
    order: '10',
    link: '友情链接',
    desc: '友情链接',
    list: []
})
api[0].list[9].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/links',
    desc: '友情链接',
});
api[0].list.push({
    alias: 'Md5Contoller',
    order: '11',
    link: 'md5加密',
    desc: 'MD5加密',
    list: []
})
api[0].list[10].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/Md5',
    desc: 'Md5加密',
});
api[0].list.push({
    alias: 'NoticeContoller',
    order: '12',
    link: '公告查询',
    desc: '公告查询',
    list: []
})
api[0].list[11].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/Notice',
    desc: '查询公告',
});
api[0].list.push({
    alias: 'RemoteDocumentContoller',
    order: '13',
    link: 'api文档',
    desc: 'API文档',
    list: []
})
api[0].list[12].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/AddFile',
    desc: '创建API文档',
});
api[0].list[12].list.push({
    order: '2',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/RemoteDocument',
    desc: '查询用户API文档信息',
});
api[0].list[12].list.push({
    order: '3',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/FileList',
    desc: '查询API文档列表',
});
api[0].list[12].list.push({
    order: '4',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/UpdateFile',
    desc: 'Api文档修改',
});
api[0].list[12].list.push({
    order: '5',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/api/DelFile',
    desc: 'API文档删除',
});
api[0].list.push({
    alias: 'StartStatisticsContoller',
    order: '14',
    link: 'api启动次数统计',
    desc: 'API启动次数统计',
    list: []
})
api[0].list[13].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/AddStartStatistics',
    desc: '增加API启动次数（三分钟内只能访问一次，访问一次+1）',
});
api[0].list[13].list.push({
    order: '2',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/StartStatistics',
    desc: '获取API启动次数',
});
api[0].list[13].list.push({
    order: '3',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/DelStartStatistics',
    desc: '清空API启动次数',
});
api[0].list.push({
    alias: 'ThirdPartyLoginContoller',
    order: '15',
    link: '第三方调用挽悦云账号登录',
    desc: '第三方调用挽悦云账号登录',
    list: []
})
api[0].list[14].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/ThirdPartyLogin',
    desc: '第三方调用挽悦云账号登录',
});
api[0].list.push({
    alias: 'UserContoller',
    order: '16',
    link: '挽悦云用户管理',
    desc: '挽悦云用户管理',
    list: []
})
api[0].list[15].list.push({
    order: '1',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/session',
    desc: '获取session',
});
api[0].list[15].list.push({
    order: '2',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/Register',
    desc: '用户注册',
});
api[0].list[15].list.push({
    order: '3',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/login',
    desc: '用户登录',
});
api[0].list[15].list.push({
    order: '4',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/end',
    desc: '注销登录',
});
api[0].list[15].list.push({
    order: '5',
    deprecated: 'false',
    url: 'http://wanyue.xbnb666.top/up_user',
    desc: '修改用户密码',
});
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code === 13) {
        const search = document.getElementById('search');
        const searchValue = search.value.toLocaleLowerCase();

        let searchGroup = [];
        for (let i = 0; i < api.length; i++) {

            let apiGroup = api[i];

            let searchArr = [];
            for (let i = 0; i < apiGroup.list.length; i++) {
                let apiData = apiGroup.list[i];
                const desc = apiData.desc;
                if (desc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                    searchArr.push({
                        order: apiData.order,
                        desc: apiData.desc,
                        link: apiData.link,
                        list: apiData.list
                    });
                } else {
                    let methodList = apiData.list || [];
                    let methodListTemp = [];
                    for (let j = 0; j < methodList.length; j++) {
                        const methodData = methodList[j];
                        const methodDesc = methodData.desc;
                        if (methodDesc.toLocaleLowerCase().indexOf(searchValue) > -1) {
                            methodListTemp.push(methodData);
                            break;
                        }
                    }
                    if (methodListTemp.length > 0) {
                        const data = {
                            order: apiData.order,
                            desc: apiData.desc,
                            link: apiData.link,
                            list: methodListTemp
                        };
                        searchArr.push(data);
                    }
                }
            }
            if (apiGroup.name.toLocaleLowerCase().indexOf(searchValue) > -1) {
                searchGroup.push({
                    name: apiGroup.name,
                    order: apiGroup.order,
                    list: searchArr
                });
                continue;
            }
            if (searchArr.length === 0) {
                continue;
            }
            searchGroup.push({
                name: apiGroup.name,
                order: apiGroup.order,
                list: searchArr
            });
        }
        let html;
        if (searchValue === '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchGroup,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            let $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiGroups, liClass, display) {
    let html = "";
    if (apiGroups.length > 0) {
        if (apiDocListSize === 1) {
            let apiData = apiGroups[0].list;
            let order = apiGroups[0].order;
            for (let j = 0; j < apiData.length; j++) {
                html += '<li class="'+liClass+'">';
                html += '<a class="dd" href="#_'+order+'_'+apiData[j].order+'_' + apiData[j].link + '">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
                html += '<ul class="sectlevel2" style="'+display+'">';
                let doc = apiData[j].list;
                for (let m = 0; m < doc.length; m++) {
                    let spanString;
                    if (doc[m].deprecated === 'true') {
                        spanString='<span class="line-through">';
                    } else {
                        spanString='<span>';
                    }
                    html += '<li><a href="#_'+order+'_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + spanString + doc[m].desc + '<span></a> </li>';
                }
                html += '</ul>';
                html += '</li>';
            }
        } else {
            for (let i = 0; i < apiGroups.length; i++) {
                let apiGroup = apiGroups[i];
                html += '<li class="'+liClass+'">';
                html += '<a class="dd" href="#_'+apiGroup.order+'_' + apiGroup.name + '">' + apiGroup.order + '.&nbsp;' + apiGroup.name + '</a>';
                html += '<ul class="sectlevel1">';

                let apiData = apiGroup.list;
                for (let j = 0; j < apiData.length; j++) {
                    html += '<li class="'+liClass+'">';
                    html += '<a class="dd" href="#_'+apiGroup.order+'_'+ apiData[j].order + '_'+ apiData[j].link + '">' +apiGroup.order+'.'+ apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
                    html += '<ul class="sectlevel2" style="'+display+'">';
                    let doc = apiData[j].list;
                    for (let m = 0; m < doc.length; m++) {
                       let spanString;
                       if (doc[m].deprecated === 'true') {
                           spanString='<span class="line-through">';
                       } else {
                           spanString='<span>';
                       }
                       html += '<li><a href="#_'+apiGroup.order+'_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">'+apiGroup.order+'.' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + spanString + doc[m].desc + '<span></a> </li>';
                   }
                    html += '</ul>';
                    html += '</li>';
                }

                html += '</ul>';
                html += '</li>';
            }
        }
    }
    return html;
}