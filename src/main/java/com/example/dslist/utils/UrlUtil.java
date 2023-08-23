package com.example.dslist.utils;

import jakarta.servlet.http.HttpServletRequest;

public class UrlUtil {

    public static String getApplicationUrl(HttpServletRequest request){
        String appURL = request.getRequestURL().toString();
        return  appURL.replace(request.getServletPath(), "");
    }
}
