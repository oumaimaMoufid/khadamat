package com.example.khadamat.Model;

public class Users {

    private String name;
    private String lastname;
    private String mail;
    private String password;
    private String phone;
    private String address;
    private String city;
    private String ImageUrl;


    public Users(){

    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Users(String imageUrl) {
        ImageUrl = imageUrl;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public Users(String name, String lastname, String mail, String password, String phone, String address, String city, String ImageUrl) {
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.ImageUrl = ImageUrl;
    }

}
