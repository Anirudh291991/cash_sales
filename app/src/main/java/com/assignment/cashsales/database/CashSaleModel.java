package com.assignment.cashsales.database;

public class CashSaleModel {
    public static final String TABLE_NAME = "cash_sale";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MOBILE_NUMBER = "mobile_number";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String mobile_number;
    private String price;
    private String date;
    private String timestamp;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_MOBILE_NUMBER + " TEXT,"
                    + COLUMN_PRICE + " TEXT,"
                    + COLUMN_DATE + " TEXT,"
                    + COLUMN_TIMESTAMP + " TEXT"
                    + ")";

    public CashSaleModel(){}

    public CashSaleModel(int id, String mobile_number, String price, String date, String timestamp){
        this.id = id;
        this.mobile_number = mobile_number;
        this.price = price;
        this.date = date;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobile_number;
    }

    public void setMobileNumber(String mobile_number) {
        this.mobile_number = mobile_number;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
