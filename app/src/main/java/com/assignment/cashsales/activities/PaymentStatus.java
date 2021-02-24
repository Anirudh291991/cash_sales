package com.assignment.cashsales.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.assignment.cashsales.R;
import com.assignment.cashsales.database.DatabaseHelper;
import com.assignment.cashsales.databinding.ActivityPaymentStatusBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PaymentStatus extends AppCompatActivity {
    private ActivityPaymentStatusBinding paymentstatusBinding;
    private String bill_amount;
    private String mobile_number;
    public static final String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm aa";
    public static final String DATE_FORMAT1 = "dd MMM yyyy";
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        paymentstatusBinding = DataBindingUtil.setContentView(PaymentStatus.this, R.layout.activity_payment_status);
        db = new DatabaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        bill_amount = bundle.getString("bill_amount");
        mobile_number = bundle.getString("mobile_number");

        initViews();
    }

    @SuppressLint("SetTextI18n")
    private void initViews() {
        paymentstatusBinding.txtAmount.setText("\u20B9" + bill_amount + ".00");

        String phonenumber = "XXXXX" + mobile_number.substring(5,10);
        paymentstatusBinding.txtPhone.setText(phonenumber);

        paymentstatusBinding.txtDateTime.setText(getCurrentDate());

        db.insertCashSale(mobile_number, bill_amount, getDate(), getCurrentDate());

        paymentstatusBinding.backHome.setOnClickListener(view -> callMainActivity());
    }

    @Override
    public void onBackPressed() {
        callMainActivity();
    }

    private void callMainActivity() {
        Intent i = new Intent(PaymentStatus.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private String getCurrentDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }

    private String getDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT1);
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }
}
