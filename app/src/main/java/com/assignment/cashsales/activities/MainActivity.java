package com.assignment.cashsales.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.assignment.cashsales.R;
import com.assignment.cashsales.fragments.FragmentDashboard;
import com.assignment.cashsales.utils.FragmentsTransactionsUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView iconhome;
    private TextView tvhome;
    private TextView iconreport;
    private TextView tvreport;
    private TextView iconsupport;
    private TextView tvsupport;
    private TextView iconsettings;
    private TextView tvsettings;
    private long backTime;
    private LinearLayout footer;
    private int exitFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        setContentView(R.layout.activity_main);
        intView();
        final View activityRootView = findViewById(R.id.content_main);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
            if (heightDiff > dpToPx(MainActivity.this, 200)) { // if more than 200 dp, it's probably a keyboard...
                footer.setVisibility(View.GONE);
            } else {
                footer.setVisibility(View.VISIBLE);
            }
        });
    }

    private void intView() {
        footer =  findViewById(R.id.footer);
        LinearLayout footer_home = findViewById(R.id.footer_home);
        LinearLayout footer_report = findViewById(R.id.footer_report);
        LinearLayout footer_support = findViewById(R.id.footer_support);
        LinearLayout footer_settings = findViewById(R.id.footer_settings);

        iconhome = findViewById(R.id.iconhome);
        tvhome = findViewById(R.id.tvhome);
        iconreport = findViewById(R.id.iconreport);
        tvreport = findViewById(R.id.tvreport);
        iconsupport = findViewById(R.id.iconsupport);
        tvsupport = findViewById(R.id.tvsupport);
        iconsettings = findViewById(R.id.iconsettings);
        tvsettings = findViewById(R.id.tvsettings);

        Typeface fontAwesomeFont = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");
        iconhome.setTypeface(fontAwesomeFont);
        iconreport.setTypeface(fontAwesomeFont);
        iconsupport.setTypeface(fontAwesomeFont);
        iconsettings.setTypeface(fontAwesomeFont);

        init();

        footer_home.setOnClickListener(this);
        footer_report.setOnClickListener(this);
        footer_support.setOnClickListener(this);
        footer_settings.setOnClickListener(this);
    }

    public static float dpToPx(Context context, float valueInDp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics);
    }

    @Override
    public void onBackPressed() {
        getCurrentFragment();
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            tvhome.setTextColor(getResources().getColor(R.color.purple_500));
            iconhome.setTextColor(getResources().getColor(R.color.purple_500));
        }
    }

    void init() {
        FragmentsTransactionsUtils.addFragment(this, new FragmentDashboard(), R.id.main_frame, "Welcome");
        tvhome.setTextColor(getResources().getColor(R.color.purple_500));
        iconhome.setTextColor(getResources().getColor(R.color.purple_500));
    }

    private void getCurrentFragment() {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() == 1) {
                if (exitFlag == 0) {
                    exitFlag = 1;
                    backTime = System.currentTimeMillis();
                    Toast.makeText(this, "Press again to close the app", Toast.LENGTH_SHORT).show();
                } else {
                    if (System.currentTimeMillis() <= backTime + 3000) {
                        finish();
                    } else {
                        Toast.makeText(this, "Press again to close the app", Toast.LENGTH_SHORT).show();
                        exitFlag = 1;
                        backTime = System.currentTimeMillis();
                    }
                }
            } else {
                String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 2).getName();
                assert fragmentTag != null;
                invalidateOptionsMenu();
                super.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.footer_home:
                tvhome.setTextColor(getResources().getColor(R.color.purple_500));
                iconhome.setTextColor(getResources().getColor(R.color.purple_500));

                iconreport.setTextColor(getResources().getColor(R.color.text_color));
                tvreport.setTextColor(getResources().getColor(R.color.text_color));

                iconsupport.setTextColor(getResources().getColor(R.color.text_color));
                tvsupport.setTextColor(getResources().getColor(R.color.text_color));

                iconsettings.setTextColor(getResources().getColor(R.color.text_color));
                tvsettings.setTextColor(getResources().getColor(R.color.text_color));
                break;

            case R.id.footer_report:
                tvhome.setTextColor(getResources().getColor(R.color.text_color));
                iconhome.setTextColor(getResources().getColor(R.color.text_color));

                iconreport.setTextColor(getResources().getColor(R.color.purple_500));
                tvreport.setTextColor(getResources().getColor(R.color.purple_500));

                iconsupport.setTextColor(getResources().getColor(R.color.text_color));
                tvsupport.setTextColor(getResources().getColor(R.color.text_color));

                iconsettings.setTextColor(getResources().getColor(R.color.text_color));
                tvsettings.setTextColor(getResources().getColor(R.color.text_color));
                break;

            case R.id.footer_support:
                tvhome.setTextColor(getResources().getColor(R.color.text_color));
                iconhome.setTextColor(getResources().getColor(R.color.text_color));

                iconreport.setTextColor(getResources().getColor(R.color.text_color));
                tvreport.setTextColor(getResources().getColor(R.color.text_color));

                iconsupport.setTextColor(getResources().getColor(R.color.purple_500));
                tvsupport.setTextColor(getResources().getColor(R.color.purple_500));

                iconsettings.setTextColor(getResources().getColor(R.color.text_color));
                tvsettings.setTextColor(getResources().getColor(R.color.text_color));
                break;

            case R.id.footer_settings:
                tvhome.setTextColor(getResources().getColor(R.color.text_color));
                iconhome.setTextColor(getResources().getColor(R.color.text_color));

                iconreport.setTextColor(getResources().getColor(R.color.text_color));
                tvreport.setTextColor(getResources().getColor(R.color.text_color));

                iconsupport.setTextColor(getResources().getColor(R.color.text_color));
                tvsupport.setTextColor(getResources().getColor(R.color.text_color));

                iconsettings.setTextColor(getResources().getColor(R.color.purple_500));
                tvsettings.setTextColor(getResources().getColor(R.color.purple_500));
                break;

        }
    }
}