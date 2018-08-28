package com.example.hossam.boshraapp.Modules;

import java.util.List;

/**
 * Created by hossam on 23/07/2018.
 */

public class SliderModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * doctor_id : 1
         * photo : 15203505901920177050.jpg
         * doctor : {"name":"الدكتورة إيمان العزاوي","id":1,"phone":"+96895597021","fb":"","twitter":"","title":"اختصاصية أمراض نساء وإخصاب","name_en":"Dr.Eman El-Aezaway","title_en":"FERTILITY DEPARTMENT","ratings":[{"doctor_id":1,"stars":3,"count":1}],"department":{"name":"قسم أطفال الأنابيب وعلدج العقم","name_en":"Fertility Department"}}
         */

        private int doctor_id;
        private String photo;
        private DoctorBean doctor;

        public int getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(int doctor_id) {
            this.doctor_id = doctor_id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public DoctorBean getDoctor() {
            return doctor;
        }

        public void setDoctor(DoctorBean doctor) {
            this.doctor = doctor;
        }

        public static class DoctorBean {
            /**
             * name : الدكتورة إيمان العزاوي
             * id : 1
             * phone : +96895597021
             * fb :
             * twitter :
             * title : اختصاصية أمراض نساء وإخصاب
             * name_en : Dr.Eman El-Aezaway
             * title_en : FERTILITY DEPARTMENT
             * ratings : [{"doctor_id":1,"stars":3,"count":1}]
             * department : {"name":"قسم أطفال الأنابيب وعلدج العقم","name_en":"Fertility Department"}
             */

            private String name;
            private int id;
            private String phone;
            private String fb;
            private String twitter;
            private String title;
            private String name_en;
            private String title_en;
            private DepartmentBean department;
            private List<RatingsBean> ratings;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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
                 * name : قسم أطفال الأنابيب وعلدج العقم
                 * name_en : Fertility Department
                 */

                private String name;
                private String name_en;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getName_en() {
                    return name_en;
                }

                public void setName_en(String name_en) {
                    this.name_en = name_en;
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
