package com.example.dsm2001.e_gorski.common.fragments;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;

public class LoadingFragment extends Fragment {
    private Context context;
    private ProgressDialog progressDialog;
    private String loadingMessage;

    public LoadingFragment() {
        // Required empty public constructor
    }

    public static LoadingFragment create(Context context) {
        return LoadingFragment.create(context, "Please wait...");
    }

    public static LoadingFragment create(Context context, String loadingMessage) {
        LoadingFragment loadingFragment = new LoadingFragment();
        loadingFragment.setContext(context);
        loadingFragment.setLoadingMessage(loadingMessage);
        return loadingFragment;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setLoadingMessage(String loadingMessage) {
        this.loadingMessage = loadingMessage;
    }

    public void show() {
        if (this.progressDialog == null) {
            this.progressDialog = new ProgressDialog(this.context);
            this.progressDialog.setMessage(this.loadingMessage);
            this.progressDialog.setCancelable(false);
        }

        this.progressDialog.show();
    }

    public void hide() {
        if (this.progressDialog != null) {
            this.progressDialog.hide();
        }
    }
}
