package com.xiaobao.service.Impl;

import com.xiaobao.dao.StartStatisticsDao;
import com.xiaobao.model.StartStatistics;
import com.xiaobao.service.StartStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 小暴
 */
@Service("startStatisticsService")
public class StartStatisticsServiceImpl implements StartStatisticsService {
    @Autowired
    private StartStatisticsDao startStatisticsDao;
    /**
     * 判断是否存在API
     *
     * @param Api
     * @return
     */
    @Override
    public StartStatistics Api(String Api) {
        return startStatisticsDao.Api(Api);
    }

    /**
     * 添加API
     *
     * @param Api
     * @return
     */
    @Override
    public int AddApi(String Api) {
        return startStatisticsDao.AddApi(Api);
    }

    /**
     * 增加启动次数
     *
     * @param Api
     * @return
     */
    @Override
    public int UpApi(String Api) {
        return startStatisticsDao.UpApi(Api);
    }

    /**
     * 清空启动次数
     *
     * @param Api
     * @return
     */
    @Override
    public int DelApi(String Api) {
        return startStatisticsDao.DelApi(Api);
    }

    /**
     * 删除启动次数的API
     *
     * @param Api
     * @return
     */
    @Override
    public int del(String Api) {
        return startStatisticsDao.del(Api);
    }
}
