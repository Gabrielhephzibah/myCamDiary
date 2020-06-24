package com.enyata.camdiary.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

public class FragmentUtils {
    private static ProgressDialog mProgressDialog;

    public static boolean isNetworkConnected(Context context) {
        return NetworkUtils.isNetworkConnected(context);
    }


    public static void showLoading(Context context) {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(context);
    }


    public static void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }
}
