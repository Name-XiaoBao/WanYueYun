package com.xiaobao.dao;

import com.xiaobao.model.StartStatistics;
import org.springframework.stereotype.Component;

/**
 * @author 小暴
 */
@Component("startStatisticsDao")
public interface StartStatisticsDao {
    /**
     * 判断API是否存在 & 查询API启动次数
     * @param Api
     * @return
     */
    StartStatistics Api(String Api);

    /**
     * 添加API
     * @param Api
     * @return
     */
    int AddApi(String Api);

    /**
     * 增加启动次数
     * @param Api
     * @return
     */
    int UpApi(String Api);

    /**
     * 清空启动次数
     * @param Api
     * @return
     */
    int DelApi(String Api);

    /**
     * 删除启动次数的API
     * @param Api
     * @return
     */
    int del(String Api);
}
