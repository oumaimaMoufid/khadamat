package com.example.khadamat.Model;

public class Users {

    private String name;
    //private String lastname;
    private String mail;
    private String password;
    private String phone;
    private String address;
    private String city;
    private String image_url;
    private String categorie;

    public Users(){

    }

    public Users(String name, String mail, String password, String phone, String address, String city, String image_url, String categorie) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.image_url = image_url;
        this.categorie = categorie;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }

    public Users(String image_url) {
        this.image_url = image_url;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Users(String address, String city, String image_url, String mail, String name, String password, String phone) {
        this.name = name;
        //this.lastname = lastname;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.image_url = image_url;
    }

}
