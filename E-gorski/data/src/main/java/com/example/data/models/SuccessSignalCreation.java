package com.example.data.models;

public class SuccessSignalCreation {
    private String count;
    private String status;
    private String error;

    public SuccessSignalCreation(){

    }

    public SuccessSignalCreation(String count, String status){
        this.count = count;
        this.status = status;
    }

    public SuccessSignalCreation(String error, String count, String status){
        this.error = error;
        this.count = count;
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
