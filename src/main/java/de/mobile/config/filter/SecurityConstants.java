package de.mobile.config.filter;

interface SecurityConstants {

    String TOKEN_SECRET = "h4of9eh48vmg02nfu30v27yen295hfj65";
    long EXPIRATION_TIME = 864_000_000; // 10 days
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
