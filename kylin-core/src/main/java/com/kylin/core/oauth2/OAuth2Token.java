package com.kylin.core.oauth2;


import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description: token
 * @author: kylin
 * @create: 2018-01-31 14:41
 **/
public class OAuth2Token implements AuthenticationToken {
    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
