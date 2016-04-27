package com.test.listviewsample.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.test.listviewsample.R;
import com.test.listviewsample.interfaces.CallBack;
import com.test.listviewsample.managers.CommunicationManager;
import com.test.listviewsample.utility.Constants;

public class MainActivity extends AppCompatActivity implements CallBack{

    private ProgressDialog pd;
    private CommunicationManager commObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        commObj = new CommunicationManager(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadItems();
    }

    public void loadItems() {
        pd = new ProgressDialog(this);
        pd.setMessage("Fetching Items..");
        pd.setCancelable(false);
        pd.show();
        callWebService();
    }

    public void callWebService() {
        commObj.callWebService(this, Constants.API_URL, this, Constants.TASK_FETCH_DATA);
    }

    @Override
    public void onResult(String data, int tasksID) {
        if (pd.isShowing()) {
            pd.dismiss();
            pd.cancel();
        }
        if (tasksID == Constants.TASK_FETCH_DATA) {
            try {
                Log.e("Response Data", ">" + data);
            } catch (Exception e) {
                Log.e("Exception", ">" + e);
                e.printStackTrace();
            }
        }


    }
}
