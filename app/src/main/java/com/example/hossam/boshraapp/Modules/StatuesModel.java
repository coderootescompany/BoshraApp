package com.example.hossam.boshraapp.Modules;

import java.util.List;

/**
 * Created by hossam on 23/07/2018.
 */

public class StatuesModel {


    /**
     * success : true
     * data : [{"id":1,"status":"doctors","created":null,"modified":"2018-06-20T23:52:31+0000"}]
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * status : doctors
         * created : null
         * modified : 2018-06-20T23:52:31+0000
         */

        private int id;
        private String status;
        private Object created;
        private String modified;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getCreated() {
            return created;
        }

        public void setCreated(Object created) {
            this.created = created;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }
    }
}
