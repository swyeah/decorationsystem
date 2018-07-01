package com.sise.util;

import com.sise.po.MyAuth;

import java.util.List;

/**
 * @Author:swye
 * @Description:
 * @Date:Create in 22:24 2018/3/9
 */
public class MyAuthUtils {

    public static boolean findAuth(List<MyAuth> myAuths, Integer authNum){
        boolean status = false;
        for (MyAuth m:myAuths){
            System.out.println(m);
            if (m.getAuthId()==authNum){
                status = true;
                return status;
            }
        }
        System.out.println(status);
        return status;
    }
}
