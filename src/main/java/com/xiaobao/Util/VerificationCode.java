package com.xiaobao.Util;

import java.util.Random;

/**
 * @author 小暴
 */
public class VerificationCode {
    public String generateVerifyCode(int number) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= number; i++) {
            builder.append(random.nextInt(10));
        }
        return builder.toString();
    }
}
