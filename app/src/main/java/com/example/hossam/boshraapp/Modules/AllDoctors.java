package com.example.hossam.boshraapp.Modules;

import java.util.List;

/**
 * Created by hossam on 22/07/2018.
 */

public class AllDoctors  {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : الدكتورة إيمان العزاوي
         * name_en : Dr.Eman El-Aezaway
         * email : info@sfc-oman.com
         * phone : +96895597021
         * address : سلطنة عمان - مسقط
         * title : اختصاصية أمراض نساء وإخصاب
         * res_department : 1
         * longs : 58.35644960000002
         * lat : 23.597211
         * description :
         * likes : 0
         * created : 2016-12-24T03:02:46+0000
         * modified : 2017-01-11T01:37:57+0000
         * accept : 1
         * favorit : 25  ر.ع
         * photo : eman.jpg
         * webdesc :
         * license_pic :
         * title_en : FERTILITY DEPARTMENT
         * fb :
         * twitter :
         * ratings : [{"doctor_id":1,"stars":3,"count":1}]
         */

        private int id;
        private String name;
        private String name_en;
        private String email;
        private String phone;
        private String address;
        private String title;
        private int res_department;
        private String longs;
        private String lat;
        private String description;
        private int likes;
        private String created;
        private String modified;
        private int accept;
        private String favorit;
        private String photo;
        private String webdesc;
        private String license_pic;
        private String title_en;
        private String fb;
        private String twitter;
        private List<RatingsBean> ratings;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getRes_department() {
            return res_department;
        }

        public void setRes_department(int res_department) {
            this.res_department = res_department;
        }

        public String getLongs() {
            return longs;
        }

        public void setLongs(String longs) {
            this.longs = longs;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
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

        public int getAccept() {
            return accept;
        }

        public void setAccept(int accept) {
            this.accept = accept;
        }

        public String getFavorit() {
            return favorit;
        }

        public void setFavorit(String favorit) {
            this.favorit = favorit;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getWebdesc() {
            return webdesc;
        }

        public void setWebdesc(String webdesc) {
            this.webdesc = webdesc;
        }

        public String getLicense_pic() {
            return license_pic;
        }

        public void setLicense_pic(String license_pic) {
            this.license_pic = license_pic;
        }

        public String getTitle_en() {
            return title_en;
        }

        public void setTitle_en(String title_en) {
            this.title_en = title_en;
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

        public List<RatingsBean> getRatings() {
            return ratings;
        }

        public void setRatings(List<RatingsBean> ratings) {
            this.ratings = ratings;
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
