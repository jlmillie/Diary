package com.on.diary.bean;

/**
 * 妹子的实体类
 *
 * Created by developerHaoz on 2017/5/3.
 */

public class MeiziBean {

    private String id;
    private String imageUrl;
    private String who;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public MeiziBean(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public MeiziBean(){

    }
}
