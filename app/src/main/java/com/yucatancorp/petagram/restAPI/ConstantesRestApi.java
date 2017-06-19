package com.yucatancorp.petagram.restAPI;

/**
 * Created by marcos on 20/04/2017.
 */

public final class ConstantesRestApi  {

    //https://api.instagram.com/v1/
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "5333811435.d8d7e8a.5fcbabe2e764487587055f107b0890bb";

    //https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String URL_SEARCH_USERS = "users/search";
    public static final String KEY_GET_RECENT_MEDIA_USER = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;




}
