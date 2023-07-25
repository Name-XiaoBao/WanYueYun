package com.xiaobao.dao;

import com.xiaobao.model.Carmi;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小暴
 */
@Component("carmiDao")
public interface CarmiDao {
    /**
     * 添加卡密
     *
     * @param api
     * @param type
     * @param Carmi
     * @param Content
     * @return
     */
    int addCarmi(String api, String type, String Carmi, String Content);

    /**
     * 查询卡密类型
     *
     * @param api
     * @param type
     * @return
     */
    List<Carmi> CarmiType(String api, String type);

    /**
     * 获取卡密信息
     *
     * @param Carmi
     * @return
     */
    Carmi carmi(String Carmi);

    /**
     * 使用积分卡密
     *
     * @param api
     * @param user
     * @param Integral
     * @return
     */
    int IntegralCarmi(String api, String user, String Integral);

    /**
     * 使用会员卡密
     *
     * @param api
     * @param user
     * @return
     */
    int MemberCarmi(String api, String user, String Member);

    /**
     * 删除卡密
     *
     * @param api
     * @param Carmi
     * @return
     */
    int delCarmi(String api, String Carmi);

    /**
     * 删除所有卡密
     *
     * @param api
     * @return
     */
    int delAllCarmi(String api);

    ;
}