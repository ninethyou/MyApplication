package com.cookandroid.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static String shop;
    private static String IP_Address = "124.51.190.90:1234";
    public static ArrayList menulist = new ArrayList();

    public static String getShop() {
        return shop;
    }

    public static void setShop(String shop) {
        Data.shop = shop;
    }

    public static String getIP() {
        return IP_Address;
    }

    public static void setIP(String IP_Address) {
        Data.IP_Address = IP_Address;
    }
}
