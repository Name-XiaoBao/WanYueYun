package com.xiaobao.service.Impl;

import com.xiaobao.dao.CarmiDao;
import com.xiaobao.model.Carmi;
import com.xiaobao.service.CarmiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 小暴
 */
@Component("carmiService")
public class CarmiServiceImpl implements CarmiService {
    @Autowired
    private CarmiDao carmiDao;

    /**
     * 添加卡密
     *
     * @param api
     * @param type
     * @param Carmi
     * @param Content
     * @return
     */
    @Override
    public int addCarmi(String api, String type, String Carmi, String Content) {
        return carmiDao.addCarmi(api, type, Carmi, Content);
    }

    /**
     * 查询卡密类型
     *
     * @param api
     * @param type
     * @return
     */
    @Override
    public List<Carmi> CarmiType(String api, String type) {
        return carmiDao.CarmiType(api, type);
    }

    /**
     * 获取卡密信息
     *
     * @param Carmi
     * @return
     */
    @Override
    public Carmi carmi(String Carmi) {
        return carmiDao.carmi(Carmi);
    }

    /**
     * 使用积分卡密
     *
     * @param api
     * @param user
     * @param Integral
     * @return
     */
    @Override
    public int IntegralCarmi(String api, String user, String Integral) {
        return carmiDao.IntegralCarmi(api, user, Integral);
    }

    /**
     * 使用会员卡密
     *
     * @param api
     * @param user
     * @param Member
     * @return
     */
    @Override
    public int MemberCarmi(String api, String user, String Member) {
        return carmiDao.MemberCarmi(api, user, Member);
    }

    /**
     * 删除卡密
     *
     * @param api
     * @param Carmi
     * @return
     */
    @Override
    public int delCarmi(String api, String Carmi) {
        return carmiDao.delCarmi(api, Carmi);
    }

    /**
     * 删除所有卡密
     *
     * @param api
     * @return
     */
    @Override
    public int delAllCarmi(String api) {
        return carmiDao.delAllCarmi(api);
    }
}
