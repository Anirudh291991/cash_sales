package com.assignment.cashsales.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cash_sales_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CashSaleModel.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CashSaleModel.TABLE_NAME);
        onCreate(db);
    }

    public long insertCashSale(String mobile_number, String price, String date, String timestamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CashSaleModel.COLUMN_MOBILE_NUMBER, mobile_number);
        values.put(CashSaleModel.COLUMN_PRICE, price);
        values.put(CashSaleModel.COLUMN_DATE, date);
        values.put(CashSaleModel.COLUMN_TIMESTAMP, timestamp);
        long id = db.insert(CashSaleModel.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public List<CashSaleModel> getAllCashSale(String date) {
        List<CashSaleModel> cash_sale1 = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(CashSaleModel.TABLE_NAME,
                new String[]{CashSaleModel.COLUMN_PRICE},
                CashSaleModel.COLUMN_DATE + "=?",
                new String[]{String.valueOf(date)}, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                CashSaleModel cash_sale = new CashSaleModel();
                cash_sale.setPrice(cursor.getString(cursor.getColumnIndex(CashSaleModel.COLUMN_PRICE)));
                cash_sale1.add(cash_sale);
            } while (cursor.moveToNext());
        }
        db.close();
        return cash_sale1;
    }

    public List<CashSaleModel> getAllPayments() {
        List<CashSaleModel> payments = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + CashSaleModel.TABLE_NAME + " ORDER BY " +
                CashSaleModel.COLUMN_DATE + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                CashSaleModel payments1 = new CashSaleModel();
                payments1.setPrice(cursor.getString(cursor.getColumnIndex(CashSaleModel.COLUMN_PRICE)));
                payments1.setTimestamp(cursor.getString(cursor.getColumnIndex(CashSaleModel.COLUMN_TIMESTAMP)));
                payments.add(payments1);
            } while (cursor.moveToNext());
        }
        db.close();
        return payments;
    }
}
