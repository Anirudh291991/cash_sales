package com.assignment.cashsales.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.assignment.cashsales.R;
import com.assignment.cashsales.model.PaymentList_getter_setter;
import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.MyViewHolder> {
    private final ArrayList<PaymentList_getter_setter> modelArrayList;
    Activity activity;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_amount;
        public TextView txt_date_time;

        public MyViewHolder(View view) {
            super(view);
            txt_amount =  view.findViewById(R.id.txt_amount);
            txt_date_time =  view.findViewById(R.id.txt_date_time);
        }
    }

    public PaymentAdapter(Activity activity, ArrayList<PaymentList_getter_setter> modelArrayList) {
        this.activity = activity;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public PaymentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reported_symptom_item, parent, false);
        return new MyViewHolder(itemView);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(PaymentAdapter.MyViewHolder holder, int position) {
        holder.txt_amount.setText("\u20B9" + modelArrayList.get(position).getPrice() + ".0");
        holder.txt_date_time.setText(modelArrayList.get(position).getTimestamp());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }
}
