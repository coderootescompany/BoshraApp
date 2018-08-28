package com.example.hossam.boshraapp.Modules;

import java.util.List;

/**
 * Created by hossam on 14/08/2018.
 */

public class CommentsModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 68
         * post : Hello I like the hospital so much
         * created : 2018-08-08T09:29:53+0000
         * user_id : 1
         * experiencecomments : [{"id":1,"comment":"","created":"2018-08-07T08:45:26+0000","myexperience_id":68,"user_id":10,"user":{"id":10,"user_group_id":"2","username":"Noor","email":"Noormaad@gmail.com","first_name":"Noor","last_name":"Maad","gender":null,"photo":null,"bday":null,"active":1,"email_verified":1,"last_login":"2018-02-19T10:45:17+0000","ip_address":"37.236.179.6","created":"2018-02-19T10:17:43+0000","modified":"2018-02-19T10:45:17+0000","created_by":null,"modified_by":null,"mobile":""}},{"id":29,"comment":"Osama","created":"2018-08-13T17:22:08+0000","myexperience_id":68,"user_id":49,"user":{"id":49,"user_group_id":null,"username":"hoss","email":null,"first_name":null,"last_name":null,"gender":null,"photo":null,"bday":null,"active":1,"email_verified":1,"last_login":null,"ip_address":null,"created":"2018-08-13T12:19:49+0000","modified":"2018-08-13T12:19:49+0000","created_by":null,"modified_by":null,"mobile":"123456"}},{"id":30,"comment":"osama","created":"2018-08-13T17:23:49+0000","myexperience_id":68,"user_id":49,"user":{"id":49,"user_group_id":null,"username":"hoss","email":null,"first_name":null,"last_name":null,"gender":null,"photo":null,"bday":null,"active":1,"email_verified":1,"last_login":null,"ip_address":null,"created":"2018-08-13T12:19:49+0000","modified":"2018-08-13T12:19:49+0000","created_by":null,"modified_by":null,"mobile":"123456"}}]
         */

        private int id;
        private String post;
        private String created;
        private int user_id;
        private List<ExperiencecommentsBean> experiencecomments;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public List<ExperiencecommentsBean> getExperiencecomments() {
            return experiencecomments;
        }

        public void setExperiencecomments(List<ExperiencecommentsBean> experiencecomments) {
            this.experiencecomments = experiencecomments;
        }

        public static class ExperiencecommentsBean {
            /**
             * id : 1
             * comment :
             * created : 2018-08-07T08:45:26+0000
             * myexperience_id : 68
             * user_id : 10
             * user : {"id":10,"user_group_id":"2","username":"Noor","email":"Noormaad@gmail.com","first_name":"Noor","last_name":"Maad","gender":null,"photo":null,"bday":null,"active":1,"email_verified":1,"last_login":"2018-02-19T10:45:17+0000","ip_address":"37.236.179.6","created":"2018-02-19T10:17:43+0000","modified":"2018-02-19T10:45:17+0000","created_by":null,"modified_by":null,"mobile":""}
             */

            private int id;
            private String comment;
            private String created;
            private int myexperience_id;
            private int user_id;
            private UserBean user;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public int getMyexperience_id() {
                return myexperience_id;
            }

            public void setMyexperience_id(int myexperience_id) {
                this.myexperience_id = myexperience_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class UserBean {
                /**
                 * id : 10
                 * user_group_id : 2
                 * username : Noor
                 * email : Noormaad@gmail.com
                 * first_name : Noor
                 * last_name : Maad
                 * gender : null
                 * photo : null
                 * bday : null
                 * active : 1
                 * email_verified : 1
                 * last_login : 2018-02-19T10:45:17+0000
                 * ip_address : 37.236.179.6
                 * created : 2018-02-19T10:17:43+0000
                 * modified : 2018-02-19T10:45:17+0000
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
                private String ip_address;
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

                public String getIp_address() {
                    return ip_address;
                }

                public void setIp_address(String ip_address) {
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
    }
}
