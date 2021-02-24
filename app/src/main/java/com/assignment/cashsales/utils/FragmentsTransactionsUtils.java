package com.assignment.cashsales.utils;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentsTransactionsUtils {

    public static void addFragment(Context context, Fragment fragment, int layout, String Tag) {
        FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        boolean fragmentPopped = fm.popBackStackImmediate(Tag, 0);
        if (!fragmentPopped) {
            ft.addToBackStack(Tag);
            ft.add(layout, fragment, Tag).commit();
        }
    }

    public static void replaceFragmentKeepPrevious(Context context, Fragment fragment, int layout, String Tag) {
        FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        boolean fragmentPopped = fm.popBackStackImmediate(Tag, 0);
        if (!fragmentPopped) {
            ft.replace(layout, fragment, Tag);
            ft.addToBackStack(Tag);
            ft.commit();
        }
    }

    public static void replaceFragmentRemovePrevious(Context context, Fragment fragment, int layout, String Tag) {
        FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        fm.popBackStack();
        ft.replace(layout, fragment, Tag);
        ft.addToBackStack(Tag);
        ft.commit();
    }

    public static void popFragment(Context context) {
        FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
        fm.popBackStack();
    }
}

