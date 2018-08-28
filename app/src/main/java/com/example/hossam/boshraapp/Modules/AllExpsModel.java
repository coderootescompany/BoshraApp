package com.example.hossam.boshraapp.Modules;

import java.util.List;

/**
 * Created by hossam on 13/08/2018.
 */

public class AllExpsModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * id : 12
         * post : تجربه ساره
         * created : 2018-08-14T09:16:00+0000
         * user_id : 1
         * experiencecomments : [{"id":54,"comment":"ttr","created":"2018-08-15T10:10:46+0000","myexperience_id":12,"user_id":49,"user":{"id":49,"user_group_id":null,"username":"hoss","email":null,"first_name":null,"last_name":null,"gender":null,"photo":"","bday":null,"active":1,"email_verified":1,"last_login":null,"ip_address":null,"created":"2018-08-13T12:19:49+0000","modified":"2018-08-15T09:38:03+0000","created_by":null,"modified_by":null,"mobile":"123456"}}]
         * likes : [{"id":11,"created":"2018-08-07T09:20:13+0000","myexperience_id":12,"user_id":1,"status":0}]
         * total_comment : [{"myexperience_id":12,"count":1}]
         * total_like : [{"myexperience_id":12,"count":19}]
         * user : {"id":1,"user_group_id":"1","username":"admin","email":"admin@admin.com","first_name":"Admin","last_name":"","gender":null,"photo":"","bday":null,"active":1,"email_verified":1,"last_login":"2018-07-20T08:45:21+0000","ip_address":null,"created":"2017-09-18T11:03:28+0000","modified":"2018-08-15T09:39:58+0000","created_by":null,"modified_by":null,"mobile":""}
         * experienceimage : [{"id":56,"image":"15342463591427287062.jpg","myexperience_id":12}]
         */

        private int id;
        private String post;
        private String created;
        private String accept;
        private int user_id;
        private UserBean user;
        private List<ExperiencecommentsBean> experiencecomments;
        private List<LikesBean> likes;
        private List<TotalCommentBean> total_comment;
        private List<TotalLikeBean> total_like;
        private List<ExperienceimageBean> experienceimage;

        public String getAccept() {
            return accept;
        }

        public void setAccept(String accept) {
            this.accept = accept;
        }

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

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public List<ExperiencecommentsBean> getExperiencecomments() {
            return experiencecomments;
        }

        public void setExperiencecomments(List<ExperiencecommentsBean> experiencecomments) {
            this.experiencecomments = experiencecomments;
        }

        public List<LikesBean> getLikes() {
            return likes;
        }

        public void setLikes(List<LikesBean> likes) {
            this.likes = likes;
        }

        public List<TotalCommentBean> getTotal_comment() {
            return total_comment;
        }

        public void setTotal_comment(List<TotalCommentBean> total_comment) {
            this.total_comment = total_comment;
        }

        public List<TotalLikeBean> getTotal_like() {
            return total_like;
        }

        public void setTotal_like(List<TotalLikeBean> total_like) {
            this.total_like = total_like;
        }

        public List<ExperienceimageBean> getExperienceimage() {
            return experienceimage;
        }

        public void setExperienceimage(List<ExperienceimageBean> experienceimage) {
            this.experienceimage = experienceimage;
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
             * photo :
             * bday : null
             * active : 1
             * email_verified : 1
             * last_login : 2018-07-20T08:45:21+0000
             * ip_address : null
             * created : 2017-09-18T11:03:28+0000
             * modified : 2018-08-15T09:39:58+0000
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
            private String photo;
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

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
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

        public static class ExperiencecommentsBean {
            /**
             * id : 54
             * comment : ttr
             * created : 2018-08-15T10:10:46+0000
             * myexperience_id : 12
             * user_id : 49
             * user : {"id":49,"user_group_id":null,"username":"hoss","email":null,"first_name":null,"last_name":null,"gender":null,"photo":"","bday":null,"active":1,"email_verified":1,"last_login":null,"ip_address":null,"created":"2018-08-13T12:19:49+0000","modified":"2018-08-15T09:38:03+0000","created_by":null,"modified_by":null,"mobile":"123456"}
             */

            private int id;
            private String comment;
            private String created;
            private int myexperience_id;
            private int user_id;
            private UserBeanX user;

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

            public UserBeanX getUser() {
                return user;
            }

            public void setUser(UserBeanX user) {
                this.user = user;
            }

            public static class UserBeanX {
                /**
                 * id : 49
                 * user_group_id : null
                 * username : hoss
                 * email : null
                 * first_name : null
                 * last_name : null
                 * gender : null
                 * photo :
                 * bday : null
                 * active : 1
                 * email_verified : 1
                 * last_login : null
                 * ip_address : null
                 * created : 2018-08-13T12:19:49+0000
                 * modified : 2018-08-15T09:38:03+0000
                 * created_by : null
                 * modified_by : null
                 * mobile : 123456
                 */

                private int id;
                private Object user_group_id;
                private String username;
                private Object email;
                private Object first_name;
                private Object last_name;
                private Object gender;
                private String photo;
                private Object bday;
                private int active;
                private int email_verified;
                private Object last_login;
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

                public Object getUser_group_id() {
                    return user_group_id;
                }

                public void setUser_group_id(Object user_group_id) {
                    this.user_group_id = user_group_id;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public Object getEmail() {
                    return email;
                }

                public void setEmail(Object email) {
                    this.email = email;
                }

                public Object getFirst_name() {
                    return first_name;
                }

                public void setFirst_name(Object first_name) {
                    this.first_name = first_name;
                }

                public Object getLast_name() {
                    return last_name;
                }

                public void setLast_name(Object last_name) {
                    this.last_name = last_name;
                }

                public Object getGender() {
                    return gender;
                }

                public void setGender(Object gender) {
                    this.gender = gender;
                }

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
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

                public Object getLast_login() {
                    return last_login;
                }

                public void setLast_login(Object last_login) {
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

        public static class LikesBean {
            /**
             * id : 11
             * created : 2018-08-07T09:20:13+0000
             * myexperience_id : 12
             * user_id : 1
             * status : 0
             */

            private int id;
            private String created;
            private int myexperience_id;
            private int user_id;
            private int status;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class TotalCommentBean {
            /**
             * myexperience_id : 12
             * count : 1
             */

            private int myexperience_id;
            private int count;

            public int getMyexperience_id() {
                return myexperience_id;
            }

            public void setMyexperience_id(int myexperience_id) {
                this.myexperience_id = myexperience_id;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class TotalLikeBean {
            /**
             * myexperience_id : 12
             * count : 19
             */

            private int myexperience_id;
            private int count;

            public int getMyexperience_id() {
                return myexperience_id;
            }

            public void setMyexperience_id(int myexperience_id) {
                this.myexperience_id = myexperience_id;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }

        public static class ExperienceimageBean {
            /**
             * id : 56
             * image : 15342463591427287062.jpg
             * myexperience_id : 12
             */

            private int id;
            private String image;
            private int myexperience_id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getMyexperience_id() {
                return myexperience_id;
            }

            public void setMyexperience_id(int myexperience_id) {
                this.myexperience_id = myexperience_id;
            }
        }
    }
}
