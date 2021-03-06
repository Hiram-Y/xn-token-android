package com.cdkj.baselibrary.appmanager;


public class MyConfig {

    // 微信应用ID
    public static final String WX_APPID ="wx8cb7c18fa507f630";

    // 法币币种
    public final static String CURRENCY = "CNY";

    // 支持币种
    public final static String[] COIN_TYPE = {"OGC","BTC","ETH"};

    public final static String COMPANYCODE = "CD-TOKEN00018";
    public final static String SYSTEMCODE = "CD-TOKEN00018";

    public final static String USERTYPE = "C";//用户类型

    //默认七牛url
    public static String IMGURL = "http://ozfszueqz.bkt.clouddn.com/";

    public static boolean IS_DEBUG = false;

    // 拍照文件保存路径
    public static final String CACHDIR = "beikeying";
    // 环境访问地址
    public static final String BASE_URL_DEV = "http://120.26.6.213:2101/forward-service/"; // 研发
    public static final String BASE_URL_TEST = "http://47.96.161.183:2101/forward-service/"; // 测试
    public static final String BASE_URL_ONLINE = "http://139.196.162.23:2101/forward-service/"; // 线上
//    public static final String BASE_URL_ONLINE = "http://47.100.163.225:2101/forward-service/"; // 线上
}
