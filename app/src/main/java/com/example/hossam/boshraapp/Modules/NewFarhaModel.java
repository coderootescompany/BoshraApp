package com.example.hossam.boshraapp.Modules;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hossam on 29/07/2018.
 */

public class NewFarhaModel  implements Parcelable{

    private ArrayList<PhotosBean> photos;

    protected NewFarhaModel(Parcel in) {
    }


    public static final Creator<NewFarhaModel> CREATOR = new Creator<NewFarhaModel>() {
        @Override
        public NewFarhaModel createFromParcel(Parcel in) {
            return new NewFarhaModel(in);
        }

        @Override
        public NewFarhaModel[] newArray(int size) {
            return new NewFarhaModel[size];
        }
    };

    public ArrayList<PhotosBean> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<PhotosBean> photos) {
        this.photos = photos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static class PhotosBean {
        /**
         * id : 236
         * photo : 15174683801456966022.jpg
         * created : 2018-02-01T01:59:40+0000
         * modified : 2018-02-01T01:59:40+0000
         * galary_cat_id : 2
         * name_ar : فرحة جديدة
         * description_ar : فرحة جديدة
         * name_en : newhappy
         * description_en : newhappy
         */

        private int id;
        private String photo;
        private String created;
        private String modified;
        private int galary_cat_id;
        private String name_ar;
        private String description_ar;
        private String name_en;
        private String description_en;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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

        public int getGalary_cat_id() {
            return galary_cat_id;
        }

        public void setGalary_cat_id(int galary_cat_id) {
            this.galary_cat_id = galary_cat_id;
        }

        public String getName_ar() {
            return name_ar;
        }

        public void setName_ar(String name_ar) {
            this.name_ar = name_ar;
        }

        public String getDescription_ar() {
            return description_ar;
        }

        public void setDescription_ar(String description_ar) {
            this.description_ar = description_ar;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getDescription_en() {
            return description_en;
        }

        public void setDescription_en(String description_en) {
            this.description_en = description_en;
        }
    }



}
