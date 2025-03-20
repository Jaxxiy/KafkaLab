package com.example.kafkaDemo.dto;

public class OrderDTO {
    private String DoctorSurname;
    private String PayWay;

    public String getDoctorSurname() {
        return DoctorSurname;
    }

    public void setDoctorSurname(String doctorSurname) {
        DoctorSurname = doctorSurname;
    }

    public String getPayWay() {
        return PayWay;
    }

    public void setPayWay(String payWay) {
        PayWay = payWay;
    }
}
