package com.example.hossam.boshraapp.Modules;

import java.util.List;

/**
 * Created by hossam on 14/08/2018.
 */

public class MyReservationModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : الدكتورة إيمان العزاوي
         * mobile : 01151355233
         * 	res_doctor : 1
         * date_res : 13/سبتمبر/2018
         * doctor : {"id":1,"title":"اختصاصية أمراض نساء وإخصاب","photo":"eman.jpg","phone":"+96895597021","fb":"","twitter":"","name_en":"Dr.Eman El-Aezaway","title_en":"FERTILITY DEPARTMENT","name":"الدكتورة إيمان العزاوي","ratings":[{"doctor_id":1,"stars":3,"count":1}],"department":{"name_en":"Fertility Department","name":"قسم أطفال الأنابيب وعلدج العقم"}}
         */

        private String name;
        private String mobile;
        private String res_doctor;
        private String date_res;
        private DoctorBean doctor;

        public String getRes_doctor() {
            return res_doctor;
        }

        public void setRes_doctor(String res_doctor) {
            this.res_doctor = res_doctor;
        }

        public String getDate_res() {
            return date_res;
        }

        public void setDate_res(String date_res) {
            this.date_res = date_res;
        }

        public DoctorBean getDoctor() {
            return doctor;
        }

        public void setDoctor(DoctorBean doctor) {
            this.doctor = doctor;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public static class DoctorBean {
            /**
             * id : 1
             * title : اختصاصية أمراض نساء وإخصاب
             * photo : eman.jpg
             * phone : +96895597021
             * fb :
             * twitter :
             * name_en : Dr.Eman El-Aezaway
             * title_en : FERTILITY DEPARTMENT
             * name : الدكتورة إيمان العزاوي
             * ratings : [{"doctor_id":1,"stars":3,"count":1}]
             * department : {"name_en":"Fertility Department","name":"قسم أطفال الأنابيب وعلدج العقم"}
             */

            private int id;
            private String title;
            private String photo;
            private String phone;
            private String fb;
            private String twitter;
            private String name_en;
            private String title_en;
            private String name;
            private DepartmentBean department;
            private List<RatingsBean> ratings;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getFb() {
                return fb;
            }

            public void setFb(String fb) {
                this.fb = fb;
            }

            public String getTwitter() {
                return twitter;
            }

            public void setTwitter(String twitter) {
                this.twitter = twitter;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getTitle_en() {
                return title_en;
            }

            public void setTitle_en(String title_en) {
                this.title_en = title_en;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public DepartmentBean getDepartment() {
                return department;
            }

            public void setDepartment(DepartmentBean department) {
                this.department = department;
            }

            public List<RatingsBean> getRatings() {
                return ratings;
            }

            public void setRatings(List<RatingsBean> ratings) {
                this.ratings = ratings;
            }

            public static class DepartmentBean {
                /**
                 * name_en : Fertility Department
                 * name : قسم أطفال الأنابيب وعلدج العقم
                 */

                private String name_en;
                private String name;

                public String getName_en() {
                    return name_en;
                }

                public void setName_en(String name_en) {
                    this.name_en = name_en;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            public static class RatingsBean {
                /**
                 * doctor_id : 1
                 * stars : 3
                 * count : 1
                 */

                private int doctor_id;
                private int stars;
                private int count;

                public int getDoctor_id() {
                    return doctor_id;
                }

                public void setDoctor_id(int doctor_id) {
                    this.doctor_id = doctor_id;
                }

                public int getStars() {
                    return stars;
                }

                public void setStars(int stars) {
                    this.stars = stars;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }
            }
        }
    }
}
