package com.assignment.cashsales.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.assignment.cashsales.R;
import com.assignment.cashsales.activities.PaymentStatus;
import com.assignment.cashsales.adapter.PaymentAdapter;
import com.assignment.cashsales.database.CashSaleModel;
import com.assignment.cashsales.database.DatabaseHelper;
import com.assignment.cashsales.databinding.FragmentDashboardBinding;
import com.assignment.cashsales.model.PaymentList_getter_setter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class FragmentDashboard extends Fragment  {
    private FragmentDashboardBinding dashboardbinding;
    private DatabaseHelper db;
    public static final String DATE_FORMAT1 = "dd MMM yyyy";
    private ArrayList<PaymentList_getter_setter> payment_list = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        View myView = dashboardbinding.getRoot();
        ((InputMethodManager) Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(myView.getWindowToken(), 0);
        db = new DatabaseHelper(getActivity());
        initView();
        return myView;
    }

    private void initView() {
        Typeface fontAwesomeFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/fontawesome-webfont.ttf");
        dashboardbinding.iconcard.setTypeface(fontAwesomeFont);
        dashboardbinding.iconqr.setTypeface(fontAwesomeFont);
        dashboardbinding.iconcash.setTypeface(fontAwesomeFont);
        dashboardbinding.iconinsurance.setTypeface(fontAwesomeFont);

        dashboardbinding.layoutCash.setOnClickListener(view -> {
            if (dashboardbinding.edtAmount.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), getResources().getString(R.string.please_enter_amount), Toast.LENGTH_SHORT).show();
            } else {
                dashboardbinding.layoutButton.setVisibility(View.GONE);
                dashboardbinding.layoutPhoneNumber.setVisibility(View.VISIBLE);
            }
        });

        dashboardbinding.btnSubmit.setOnClickListener(view -> {
            if (dashboardbinding.edtPhone.getText().toString().isEmpty()) {
                Toast.makeText(getActivity(), getResources().getString(R.string.please_enter_mobile_number), Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(getActivity(), PaymentStatus.class);
                i.putExtra("bill_amount", dashboardbinding.edtAmount.getText().toString());
                i.putExtra("mobile_number", dashboardbinding.edtPhone.getText().toString());
                getActivity().startActivity(i);
                getActivity().finish();
            }
        });

        dashboardbinding.btnBack.setOnClickListener(view -> {
            dashboardbinding.layoutButton.setVisibility(View.VISIBLE);
            dashboardbinding.layoutPhoneNumber.setVisibility(View.GONE);
        });

        getPrice();
        getPayments();
    }

    private void getPayments() {
        List<CashSaleModel> paymentList = new ArrayList<>();
        paymentList.addAll(db.getAllPayments());
        JSONArray jsonArray = new JSONArray();
        for (CashSaleModel cn : paymentList) {
            JSONObject payment = new JSONObject();
            try {
                payment.put("price", cn.getPrice());
                payment.put("timestamp", cn.getTimestamp());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jsonArray.put(payment);
        }
        JSONObject paymentObj = new JSONObject();
        try {
            paymentObj.put("payment", jsonArray);
            paymentObj.put("message", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray arr1;
        if (!payment_list.isEmpty())
            payment_list.clear();
        try {
            arr1 = paymentObj.getJSONArray("payment");
            for (int i = 0; i < arr1.length(); i++) {
                JSONObject obj = arr1.getJSONObject(i);
                payment_list.add(new PaymentList_getter_setter(obj.getString("price"),obj.getString("timestamp")));
            }
            dashboardbinding.recyclerviewTransaction.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            PaymentAdapter paymentAdapter = new PaymentAdapter(getActivity(), payment_list);
            dashboardbinding.recyclerviewTransaction.setAdapter(paymentAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getPrice() {
        List<CashSaleModel> priceList = new ArrayList<>();
        priceList.addAll(db.getAllCashSale(getDate()));
        JSONArray jsonArray = new JSONArray();
        for (CashSaleModel cn : priceList) {
            JSONObject price = new JSONObject();
            try {
                price.put("price", cn.getPrice());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            jsonArray.put(price);
        }
        JSONObject priceObj = new JSONObject();
        try {
            priceObj.put("price", jsonArray);
            priceObj.put("message", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray arr1;
        int total = 0;
        try {
            arr1 = priceObj.getJSONArray("price");
            for (int i = 0; i < arr1.length(); i++) {
                JSONObject obj = arr1.getJSONObject(i);
                total = total + Integer.parseInt(obj.getString("price"));
            }
            dashboardbinding.todayAmount.setText("\u20B9" + total + ".0");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDate() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT1);
        Date today = Calendar.getInstance().getTime();
        return dateFormat.format(today);
    }
}
