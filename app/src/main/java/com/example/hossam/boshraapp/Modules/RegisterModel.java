package com.example.hossam.boshraapp.Modules;

/**
 * Created by hossam on 13/08/2018.
 */

public class RegisterModel {

    /**
     * success : true
     * data : {"id":38,"client_photo":null,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjM4LCJleHAiOjE1MzQ3NTk1NTJ9.MqA-TwEudNb-flq9UBQAWxfeqVV0f7-uXtekz8u3oVw"}
     */

    private boolean success;
    private DataBean data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 38
         * client_photo : null
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjM4LCJleHAiOjE1MzQ3NTk1NTJ9.MqA-TwEudNb-flq9UBQAWxfeqVV0f7-uXtekz8u3oVw
         */

        private int id;
        private Object client_photo;
        private String token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getClient_photo() {
            return client_photo;
        }

        public void setClient_photo(Object client_photo) {
            this.client_photo = client_photo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
