package com.cookandroid.myapplication;

import java.util.ArrayList;

public class DB {
    private static String shop;
    private static String IP_Address = "124.51.190.90:1234";
    private static ArrayList dataList = new ArrayList();
    private static User user = new User();

    public static ArrayList getDataList() { return dataList; }
    public static void setDataList(ArrayList dataList) { DB.dataList = dataList; }
    public static void clearDataList() { dataList.clear(); }
    public static void addData(String data) { dataList.add(data); }
    public static Object getData(int index) { return dataList.get(index); }

    public static String getShop() { return shop; }
    public static void setShop(String shop) { DB.shop = shop; }

    public static User getUser() { return user; }
    public static void setUser(User user) { DB.user = user; }

    public static String getIP() { return IP_Address; }

}
