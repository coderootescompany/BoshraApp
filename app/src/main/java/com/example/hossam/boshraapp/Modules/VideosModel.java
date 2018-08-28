package com.example.hossam.boshraapp.Modules;

import java.util.List;

/**
 * Created by hossam on 13/08/2018.
 */

public class VideosModel {

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
         * name : hazem
         * link : https://www.youtube.com/watch?v=7TF00hJI78Y
         * photo : 153374023259019156.jpg
         * Views : 20
         */

        private int id;
        private String name;
        private String link;
        private String photo;
        private int Views;

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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getViews() {
            return Views;
        }

        public void setViews(int Views) {
            this.Views = Views;
        }
    }
}
