package com.example.kafkaDemo.event;

public class OrderCreatedEvent {
    private String orderId;
    private String DoctorSurname;
    private String PayWay;

    public OrderCreatedEvent() {}

    public OrderCreatedEvent(String orderId, String doctorSurname, String payWay) {
        this.orderId = orderId;
        this.DoctorSurname = doctorSurname;
        this.PayWay = payWay;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

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
