package com.xiaobao.contoller;

import com.xiaobao.Util.Json;
import com.xiaobao.Util.Uuid;
import com.xiaobao.service.ApiService;
import com.xiaobao.service.CarmiService;
import com.xiaobao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 卡密功能
 *
 * @author 小暴
 */
@RestController
@RequestMapping("/api")
public class CarmiContoller {
    @Autowired
    private CarmiService carmiService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApiService apiService;
    Json json = new Json();
    Uuid uuid = new Uuid();

    /**
     * 创建卡密
     *
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param api      API名称
     * @param type     卡密类型 Integral或Member
     * @param num      卡密数量
     * @param Content  卡密内容 类型为Integral时为具体数值 类型为Member时为天数
     * @return
     */
    @PostMapping("/AddCarmi")
    public Json AddCarmi(String username, String password, String api, String type, int num, String Content) {
        if ("".equals(Content)) {
            json.json(403, "不允许为空！", null);
            return json;
        }
        if (userService.user(username) == null) {
            json.json(404, "该用户不存在", null);
            return json;
        }
        if (userService.user_pass(username, password) == null) {
            json.json(403, "账号或密码错误", null);
            return json;
        }
        if (apiService.Api(username, api) == null) {
            json.json(404, "API不存在", null);
            return json;
        }
        if ("Integral".equals(type) || "Member".equals(type)) {
            for (int i = 0; i < num; i++) {
                carmiService.addCarmi(api, type, uuid.uuid(), Content);
            }
            json.json(200, "创建成功", null);
        } else {
            json.json(403, "无效的卡密类型", null);
        }
        return json;
    }

    /**
     * 查询卡密类型
     *
     * @param username 挽悦云账号
     * @param password 挽悦云密码
     * @param api      API名称
     * @param type     卡密类型
     * @return
     */
    @PostMapping("/CarmiType")
    public Json CarmiType(String username, String password, String api, String type) {
        if (userService.user(username) == null) {
            json.json(404, "该用户不存在", null);
            return json;
        }
        if (userService.user_pass(username, password) == null) {
            json.json(403, "账号或密码错误", null);
            return json;
        }
        if (apiService.Api(username, api) == null) {
            json.json(404, "API不存在", null);
            return json;
        }
        if ("Integral".equals(type) || "Member".equals(type)) {
            json.json(200, "查询成功", carmiService.CarmiType(api, type));
        } else {
            json.json(403, "无效的卡密类型", null);
        }
        return json;
    }

    /**
     * 卡密兑换
     *
     * @param Carmi 卡密
     * @param user  账号
     * @return
     */
    @PostMapping("/Carmi")
    public Json Carmi(String Carmi, String user) {
        try {
            if ("Integral".equals(carmiService.carmi(Carmi).getType())) {
                if (carmiService.IntegralCarmi(carmiService.carmi(Carmi).getApi(), user, carmiService.carmi(Carmi).getContent()) == 1) {
                    json.json(200, "兑换成功", null);
                    carmiService.delCarmi(carmiService.carmi(Carmi).getApi(), Carmi);
                } else {
                    json.json(400, "兑换失败", null);
                }
                return json;
            } else if ("Member".equals(carmiService.carmi(Carmi).getType())) {
                if (carmiService.MemberCarmi(carmiService.carmi(Carmi).getApi(), user, carmiService.carmi(Carmi).getContent()) == 1) {
                    json.json(200, "兑换成功", null);
                    carmiService.delCarmi(carmiService.carmi(Carmi).getApi(), Carmi);
                } else {
                    json.json(400, "兑换失败", null);
                }
                return json;
            } else {
                json.json(403, "无效的卡密类型", null);
            }
            return json;
        } catch (NullPointerException e) {
            json.json(400, "卡密不存在！", e);
            return json;
        }
    }
}
