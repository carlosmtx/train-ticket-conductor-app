package com.railway.railwayconductor.activity.listener;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.railway.railwayconductor.activity.QRCodeReaderActivity;

/**
 * Created by cteixeira on 29-10-2015.
 * com.railway.railwayconductor.activity.listener
 */
public class QRCodeReaderOnVerifyClick implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        QRCodeReaderActivity activity = (QRCodeReaderActivity) v.getContext();
        IntentIntegrator a = new IntentIntegrator(activity);
        a.initiateScan();
    }
}
