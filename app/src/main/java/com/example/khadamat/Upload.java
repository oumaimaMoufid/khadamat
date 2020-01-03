package com.example.khadamat;

public class Upload {
    private String ImageUrl;

    public Upload(){

    }

    public Upload(String mImageUrl) {



        this.ImageUrl = mImageUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String mImageUrl) {
        this.ImageUrl = mImageUrl;
    }
}
