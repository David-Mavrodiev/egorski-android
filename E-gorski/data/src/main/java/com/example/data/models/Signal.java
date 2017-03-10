package com.example.data.models;

public class Signal {
    private String registeredNumber;
    private String firstname;
    private String lastname;
    private String description;
    private String number;
    private String lat;
    private String lng;
    private String answer;
    private String error;
    private String cookieText;

    public Signal(){

    }

    public Signal(String error, String registeredNumber, String firstname, String lastname, String description, String number, String lat, String lng, String answer){
        this.error = error;
        this.registeredNumber = registeredNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.number = number;
        this.lat = lat;
        this.lng = lng;
        this.answer = answer;
    }

    public Signal(String firstname, String lastname, String description, String number, String lat, String lng, String answer){
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.number = number;
        this.lat = lat;
        this.lng = lng;
        this.answer = answer;
    }

    public Signal(String firstname, String lastname, String description, String number, String lat, String lng){
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.number = number;
        this.lat = lat;
        this.lng = lng;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getRegisteredNumber() {
        return registeredNumber;
    }

    public void setRegisteredNumber(String registeredNumber) {
        this.registeredNumber = registeredNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCookieText() {
        return cookieText;
    }

    public void setCookieText(String cookieText) {
        this.cookieText = cookieText;
    }
}
