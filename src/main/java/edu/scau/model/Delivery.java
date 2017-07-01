package edu.scau.model;

import java.util.Date;

public class Delivery extends DeliveryKey {
    private Integer orderid;

    private Date time;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}