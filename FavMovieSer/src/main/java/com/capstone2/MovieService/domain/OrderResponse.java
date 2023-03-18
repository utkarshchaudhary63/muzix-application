package com.capstone2.MovieService.domain;

public class OrderResponse {
    String secretKey;
    String razorpayOrderId;
    double applicationFee;
    String secretId;
    String pgName;

    public OrderResponse(String secretKey, String razorpayOrderId, int applicationFee, String secretId, String pgName) {
        this.secretKey = secretKey;
        this.razorpayOrderId = razorpayOrderId;
        this.applicationFee = applicationFee;
        this.secretId = secretId;
        this.pgName = pgName;
    }

    public OrderResponse() {
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public double getApplicationFee() {
        return applicationFee;
    }

    public void setApplicationFee(double applicationFee) {
        this.applicationFee = applicationFee;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getPgName() {
        return pgName;
    }

    public void setPgName(String pgName) {
        this.pgName = pgName;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "secretKey='" + secretKey + '\'' +
                ", razorpayOrderId='" + razorpayOrderId + '\'' +
                ", applicationFee='" + applicationFee + '\'' +
                ", secretId='" + secretId + '\'' +
                ", pgName='" + pgName + '\'' +
                '}';
    }
}
