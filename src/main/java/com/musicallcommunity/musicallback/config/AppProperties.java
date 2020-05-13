package com.musicallcommunity.musicallback.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final Auth auth = new Auth();

    private final Support support = new Support();

    public static class Auth {
        private String jwtSecret;
        private long jwtExpirationMsec;

        public String getJwtSecret() {
            return jwtSecret;
        }

        public void setJwtSecret(String tokenSecret) {
            this.jwtSecret = tokenSecret;
        }

        public long getJwtExpirationMsec() {
            return jwtExpirationMsec;
        }

        public void setJwtExpirationMsec(long tokenExpirationMsec) {
            this.jwtExpirationMsec = tokenExpirationMsec;
        }
    }

    public static class Support {
        private String email;

        public String getMail() {
            return email;
        }

        public void setMail(String email) {
            this.email = email;
        }
    }

    public Auth getAuth() {
        return auth;
    }

    public Support getSupport() {return support; }

}
