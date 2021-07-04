package com.skylight.testing;

import com.skylight.base.settings.Setting;
import com.skylight.base.utils.login.LoginUtil;

import java.awt.*;

public class Test {
    public static void main(String[] args) {
        System.out.println(LoginUtil.getNewLogin("username", "password"));
//        new Color(0x58F818)
    }
}
