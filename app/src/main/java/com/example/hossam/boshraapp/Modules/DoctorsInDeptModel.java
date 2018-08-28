package com.example.hossam.boshraapp.Modules;

import java.util.List;

/**
 * Created by hossam on 07/08/2018.
 */

public class DoctorsInDeptModel {


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
         * department : {"id":1,"name_en":"Fertility Department","name":"قسم أطفال الأنابيب وعلدج العقم","created":null,"modified":null,"photo":"333.png","description":"\t\t\t\t\t\t\t\t\t<div class=\"vc_row wpb_row vc_row-fluid left_padding right_padding\"><div class=\"wpb_column vc_column_container vc_col-sm-12\">\n                                                                                <div class=\"vc_column-inner vc_custom_1471177587794\"><div class=\"wpb_wrapper\"> \t<div class=\"wpb_text_column wpb_content_element \"> \t\n                                                                                            <div class=\"wpb_wrapper\"> \t\t\n                                                                                                <p>In Virto Fertilization (IVF), which means fertilization in a test tube, hence the name \u201cTest Tube Baby\u201d,\n                                                                                                    is a process of taking one or more eggs from the ovary and fertilizing them outside the woman body,\n                                                                                                    and then returning the fertilized egg (embryo) to the uterus via the cervix. IVF has passed through several stages,\n                                                                                                    starting from the classical \u201cTest Tube Baby\u201d over 20 years ago,\n                                                                                                    and moving up to Intracytoplasmic Sperm Injection (ICSI) which is a process of injecting an individual sperm into an \n                                                                                                    egg using microscopic techniques. <\/p>  \t\t<\/div> \t<\/div>  \n                                                                                        <div class=\"wpb_text_column wpb_content_element  vc_custom_1471178082811\"> \t\n                                                                                            <div class=\"wpb_wrapper\"> \t\t\n                                                                                                <p>Our lab is considered as one of the most modern and developed labs in the region.\n                                                                                                    It is equipped with state-of-the-art equipment related to the field of fertility.<br /> \n                                                                                                    The basic functions of the lab are:<br /> 1. Semen analysis to determine sperm count,\n                                                                                                  \n                                                                                                    \n                                                                                                        <img src=\"http://sfc-oman.com/images/img03.png\"     \n                                                                                                             alt=\"img03\" width=\"278\" height=\"208\" \n                                                                                                             class=\"size-full wp-image-1661 alignright\"   /> \n                                                                                                        <br /> motility, viability and morphology.<br /> 2. Preparation of the seminal fluid for<br /> Intrauterine Insemination (IUI).<br /> 3. Conventional in Vitro-Fertilization (C.IVF).<br /> 4. Intracytoplasmic Sperm Injection (ICSI).<br /> 5. Testicular Sperm Extraction (TESE).<br /> 6. Assisted Hatching (AH).<\/p>  \t\t<\/div> \t<\/div>  \t<div class=\"wpb_text_column wpb_content_element  vc_custom_1471178281830\"> \t\t<div class=\"wpb_wrapper\"> \t\t\t<p>OUR SERVICES :<\/p>  \t\t<\/div> \t<\/div> <\/div><\/div><\/div><\/div>                                                     <div class=\"vc_row wpb_row vc_row-fluid left_padding right_padding\"><div class=\"wpb_column vc_column_container vc_col-sm-6\"><div class=\"vc_column-inner \"><div class=\"wpb_wrapper\"> \t<div class=\"wpb_text_column wpb_content_element \"> \t\t<div class=\"wpb_wrapper\"> \t\t\t<p>1. (IVF) In Vitro FertilizationConventional &amp; ICSI (Intra Cytoplasmic Sperm Injection)<br /> 2. (IUI) Intrauterine Insemination<br /> 3. Controlled Ovulation Induction<br /> 4. Embryo Freezing<br /> 5. Sperm Freezing<\/p> <p>6. Treatment of male infertility<br /> 7. Fine Needle Aspiration<\/p>  \t\t<\/div> \t<\/div> <\/div><\/div><\/div><div class=\"wpb_column vc_column_container vc_col-sm-6\"><div class=\"vc_column-inner \"><div class=\"wpb_wrapper\"> \t<div class=\"wpb_text_column wpb_content_element \"> \t\t<div class=\"wpb_wrapper\"> \t\t\t<p>8. Cyst Aspiration<br /> 9. Treatment of PCO<br /> 10. Treatment of Endometriosis<br /> 11. Treatment of Uterine &amp; Ovarian Tumor<br /> 12. Treatment of Symptoms of Menopause<br /> 13. Laparoscopic, Hysteroscopy Diagnosis &amp; Treatment of Gynecological Problems<\/p> <p>14. Testicular Biopsy, Extraction and Mapping of the Testis<br /> 15. Treatment of Varicocele<\/p>  \t\t<\/div> \t<\/div> <\/div><\/div><\/div><\/div>                                                                 <div class=\"vc_row wpb_row vc_row-fluid\">                <div class=\"wpb_column vc_column_container vc_col-sm-12\">                    <div class=\"vc_column-inner \">                        <div class=\"wpb_wrapper\"> \t<div class=\"wpb_text_column wpb_content_element \"> \n                                                                                            <div class=\"wpb_wrapper\"> \t\t\t \t\t<\/div> \t\n                                                                                        <\/div>"}
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
        private DepartmentBean department;
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
             * id : 1
             * name_en : Fertility Department
             * name : قسم أطفال الأنابيب وعلدج العقم
             * created : null
             * modified : null
             * photo : 333.png
             * description : 									<div class="vc_row wpb_row vc_row-fluid left_padding right_padding"><div class="wpb_column vc_column_container vc_col-sm-12">
             <div class="vc_column-inner vc_custom_1471177587794"><div class="wpb_wrapper"> 	<div class="wpb_text_column wpb_content_element ">
             <div class="wpb_wrapper">
             <p>In Virto Fertilization (IVF), which means fertilization in a test tube, hence the name “Test Tube Baby”,
             is a process of taking one or more eggs from the ovary and fertilizing them outside the woman body,
             and then returning the fertilized egg (embryo) to the uterus via the cervix. IVF has passed through several stages,
             starting from the classical “Test Tube Baby” over 20 years ago,
             and moving up to Intracytoplasmic Sperm Injection (ICSI) which is a process of injecting an individual sperm into an
             egg using microscopic techniques. </p>  		</div> 	</div>
             <div class="wpb_text_column wpb_content_element  vc_custom_1471178082811">
             <div class="wpb_wrapper">
             <p>Our lab is considered as one of the most modern and developed labs in the region.
             It is equipped with state-of-the-art equipment related to the field of fertility.<br />
             The basic functions of the lab are:<br /> 1. Semen analysis to determine sperm count,


             <img src="http://sfc-oman.com/images/img03.png"
             alt="img03" width="278" height="208"
             class="size-full wp-image-1661 alignright"   />
             <br /> motility, viability and morphology.<br /> 2. Preparation of the seminal fluid for<br /> Intrauterine Insemination (IUI).<br /> 3. Conventional in Vitro-Fertilization (C.IVF).<br /> 4. Intracytoplasmic Sperm Injection (ICSI).<br /> 5. Testicular Sperm Extraction (TESE).<br /> 6. Assisted Hatching (AH).</p>  		</div> 	</div>  	<div class="wpb_text_column wpb_content_element  vc_custom_1471178281830"> 		<div class="wpb_wrapper"> 			<p>OUR SERVICES :</p>  		</div> 	</div> </div></div></div></div>                                                     <div class="vc_row wpb_row vc_row-fluid left_padding right_padding"><div class="wpb_column vc_column_container vc_col-sm-6"><div class="vc_column-inner "><div class="wpb_wrapper"> 	<div class="wpb_text_column wpb_content_element "> 		<div class="wpb_wrapper"> 			<p>1. (IVF) In Vitro FertilizationConventional &amp; ICSI (Intra Cytoplasmic Sperm Injection)<br /> 2. (IUI) Intrauterine Insemination<br /> 3. Controlled Ovulation Induction<br /> 4. Embryo Freezing<br /> 5. Sperm Freezing</p> <p>6. Treatment of male infertility<br /> 7. Fine Needle Aspiration</p>  		</div> 	</div> </div></div></div><div class="wpb_column vc_column_container vc_col-sm-6"><div class="vc_column-inner "><div class="wpb_wrapper"> 	<div class="wpb_text_column wpb_content_element "> 		<div class="wpb_wrapper"> 			<p>8. Cyst Aspiration<br /> 9. Treatment of PCO<br /> 10. Treatment of Endometriosis<br /> 11. Treatment of Uterine &amp; Ovarian Tumor<br /> 12. Treatment of Symptoms of Menopause<br /> 13. Laparoscopic, Hysteroscopy Diagnosis &amp; Treatment of Gynecological Problems</p> <p>14. Testicular Biopsy, Extraction and Mapping of the Testis<br /> 15. Treatment of Varicocele</p>  		</div> 	</div> </div></div></div></div>                                                                 <div class="vc_row wpb_row vc_row-fluid">                <div class="wpb_column vc_column_container vc_col-sm-12">                    <div class="vc_column-inner ">                        <div class="wpb_wrapper"> 	<div class="wpb_text_column wpb_content_element ">
             <div class="wpb_wrapper"> 				</div>
             </div>
             */

            private int id;
            private String name_en;
            private String name;
            private Object created;
            private Object modified;
            private String photo;
            private String description;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public Object getCreated() {
                return created;
            }

            public void setCreated(Object created) {
                this.created = created;
            }

            public Object getModified() {
                return modified;
            }

            public void setModified(Object modified) {
                this.modified = modified;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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
