package org.rodneyparshall.rightrx.constant;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000;// Expiration time of token set to 5 days in milliseconds
    public static final String TOKEN_PREFIX= "Bearer "; //Verifies ownership
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED ="Token cannot be verified";
    public static final String RIGHTRX = "RightRX, LLC";
    public static final String RIGHTRX_ADMIN = "User Management Portal";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE ="You need to login to access this page.";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page.";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
//    public static final String[] PUBLIC_URLS = {"/user/register", "/user/login"};
    public static final String[] PUBLIC_URLS = {"**"};
}
