package com.aspiresys.makemyride.ModelClass;

public class BookingDetails {
    String boardingPoint,droppingPoint,time,date,price;

    public BookingDetails(String sp, String dst, String dt, String time, String rate) {
        this.boardingPoint=sp;
        this.droppingPoint=dst;
        this.time=time;
        this.date=dt;
        this.price=rate;
    }

    public BookingDetails() {
    }

    public String getBoardingPoint() {
        return boardingPoint;
    }

    public void setBoardingPoint(String boardingPoint) {
        this.boardingPoint = boardingPoint;
    }

    public String getDroppingPoint() {
        return droppingPoint;
    }

    public void setDroppingPoint(String dropppingPoint) {
        this.droppingPoint = dropppingPoint;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
