package com.skylight.base.utils.login;

public class HwidUtil {
    public static String getHWID() {
        return System.getenv("COMPUTERNAME") + System.getProperty("user.name") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_LEVEL");
    }
}
