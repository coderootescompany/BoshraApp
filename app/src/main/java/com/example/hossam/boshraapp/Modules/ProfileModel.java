package com.example.hossam.boshraapp.Modules;

/**
 * Created by hossam on 14/08/2018.
 */

public class ProfileModel {


    /**
     * user : {"id":1,"user_group_id":"1","username":"admin","email":"admin@admin.com","first_name":"Admin","last_name":"","gender":null,"photo":null,"bday":null,"active":1,"email_verified":1,"last_login":"2018-07-20T08:45:21+0000","ip_address":null,"created":"2017-09-18T11:03:28+0000","modified":"2018-07-20T08:45:21+0000","created_by":null,"modified_by":null,"mobile":""}
     */

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 1
         * user_group_id : 1
         * username : admin
         * email : admin@admin.com
         * first_name : Admin
         * last_name :
         * gender : null
         * photo : null
         * bday : null
         * active : 1
         * email_verified : 1
         * last_login : 2018-07-20T08:45:21+0000
         * ip_address : null
         * created : 2017-09-18T11:03:28+0000
         * modified : 2018-07-20T08:45:21+0000
         * created_by : null
         * modified_by : null
         * mobile :
         */

        private int id;
        private String user_group_id;
        private String username;
        private String email;
        private String first_name;
        private String last_name;
        private Object gender;
        private Object photo;
        private Object bday;
        private int active;
        private int email_verified;
        private String last_login;
        private Object ip_address;
        private String created;
        private String modified;
        private Object created_by;
        private Object modified_by;
        private String mobile;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUser_group_id() {
            return user_group_id;
        }

        public void setUser_group_id(String user_group_id) {
            this.user_group_id = user_group_id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public Object getGender() {
            return gender;
        }

        public void setGender(Object gender) {
            this.gender = gender;
        }

        public Object getPhoto() {
            return photo;
        }

        public void setPhoto(Object photo) {
            this.photo = photo;
        }

        public Object getBday() {
            return bday;
        }

        public void setBday(Object bday) {
            this.bday = bday;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public int getEmail_verified() {
            return email_verified;
        }

        public void setEmail_verified(int email_verified) {
            this.email_verified = email_verified;
        }

        public String getLast_login() {
            return last_login;
        }

        public void setLast_login(String last_login) {
            this.last_login = last_login;
        }

        public Object getIp_address() {
            return ip_address;
        }

        public void setIp_address(Object ip_address) {
            this.ip_address = ip_address;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public Object getCreated_by() {
            return created_by;
        }

        public void setCreated_by(Object created_by) {
            this.created_by = created_by;
        }

        public Object getModified_by() {
            return modified_by;
        }

        public void setModified_by(Object modified_by) {
            this.modified_by = modified_by;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
