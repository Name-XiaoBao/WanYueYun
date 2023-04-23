package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import com.xiaobao.Util.LimitRequest;
import com.xiaobao.dao.ApiDao;
import com.xiaobao.dao.StartStatisticsDao;
import com.xiaobao.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API启动次数统计
 * @author 小暴
 */
@RestController
public class StartStatisticsContoller {
    @Autowired
    private ApiDao apiDao;
    @Autowired
    private StartStatisticsDao startStatisticsDao;
    @Autowired
    private UserDao userDao;
    Json json=new Json();
    /**
     * 增加API启动次数（三分钟内只能访问一次，访问一次+1）
     * @param Api API名称
     * @return
     */
    @RequestMapping("/AddStartStatistics")
    @LimitRequest(time = 180000,count = 1)
    public Json AddStartStatistics(String Api){
        if (apiDao.api(Api)==null){
            json.json(404,"API不存在",null);
            return json;
        }
        if (startStatisticsDao.Api(Api)==null){
            startStatisticsDao.AddApi(Api);
            json.json(200,"启动次数+1",null);
        }else {
            startStatisticsDao.UpApi(Api);
            json.json(200,"启动次数+1",null);
        }
        return json;
    }

    /**
     * 获取API启动次数
     * @param Api API名称
     * @return
     */
    @RequestMapping("/StartStatistics")
    public Json StartStatistics(String Api){
        if (apiDao.api(Api)==null){
            json.json(404,"API不存在",null);
            return json;
        }
        json.json(200,"查询成功",startStatisticsDao.Api(Api));
        return json;
    }
    /**
     * 清空API启动次数
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param Api API名称
     * @return
     */
    @RequestMapping("/DelStartStatistics")
    public Json DelStartStatistics(String username,String password,String Api){
        if (userDao.user_pass(username,password)==null){
            json.json(400,"用户名或密码不正确",null);
            return json;
        }
        if (apiDao.Api(username,Api)==null){
            json.json(404,"API不存在",null);
            return json;
        }
        startStatisticsDao.DelApi(Api);
        json.json(200,"清空成功！",0);
        return json;
    }
}
