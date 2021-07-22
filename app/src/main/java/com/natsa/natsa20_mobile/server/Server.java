package com.natsa.natsa20_mobile.server;

public class Server {
    public static final String url = "http://192.168.1.2:8000/";
    public static final String urlWithoutSlash = "http://192.168.1.2:8000";
    public static final String urlApi = "http://192.168.1.2:8000/api/";
    public static final String storage = url+"storage/";

    //lupa password
    public static final String forgetPassword = url+"forgot-password/";

    //sawah
    public static final String riceFields = "riceFields/";
    public static final String product = "product/";
    public static final String searchProduct = riceFields+"search/";
    public static final String deletePhoto = riceFields+"delete/photo/";
    public static final String ketersediaan = riceFields+"ketersediaan/";

    //user
    public static final String user = "user/";
    public static final String users = "users/";
    public static final String register = users+"register/";
    public static final String login = users+"login/";
    public static final String logout = users+"logout/";
    public static final String userDetails = user+"details/";
    public static final String updatePassword = users+"password/";

    //makelar
    public static final String makelar = "makelar/";

    //history
    public static final String history = "history/";

    //bookmark
    public static final String bookmarks = "bookmarks/";

    //regions
    public static final String regions = "regions/";

    //vestiges
    public static final String vestiges = "vestiges/";

    //irrigations
    public static final String irrigations = "irrigations/";

    //social media
    public static final String socialMedia = "social-media/";
    public static final String userSocialMedia = "user/social-media/";
}
