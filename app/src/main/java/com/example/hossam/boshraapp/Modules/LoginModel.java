package com.example.hossam.boshraapp.Modules;

/**
 * Created by hossam on 13/08/2018.
 */

public class LoginModel {

    /**
     * success : true
     * data : {"userid":49,"groupid":null,"mobile":"123456","username":"hoso","photo":"15351911661601835020.jpg","token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjQ5LCJleHAiOjE1NDEyMDA2NTN9.ZZ7CQ-f5IDGpQmPA5ZBSQv6Uf5BTZimMF80ENHdDosw"}
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
         * userid : 49
         * groupid : null
         * mobile : 123456
         * username : hoso
         * photo : 15351911661601835020.jpg
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjQ5LCJleHAiOjE1NDEyMDA2NTN9.ZZ7CQ-f5IDGpQmPA5ZBSQv6Uf5BTZimMF80ENHdDosw
         */

        private int userid;
        private Object groupid;
        private String mobile;
        private String username;
        private String photo;
        private String token;

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public Object getGroupid() {
            return groupid;
        }

        public void setGroupid(Object groupid) {
            this.groupid = groupid;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
