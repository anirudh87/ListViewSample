package com.test.listviewsample.activity;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.test.listviewsample.R;
import com.test.listviewsample.adapters.CustomItemsAdapter;
import com.test.listviewsample.interfaces.CallBack;
import com.test.listviewsample.managers.CommunicationManager;
import com.test.listviewsample.models.FactData;
import com.test.listviewsample.utility.Constants;
import com.test.listviewsample.databinding.ActivityMainBinding;
import com.test.listviewsample.utility.SimpleDividerItemDecoration;


public class MainActivity extends AppCompatActivity implements CallBack,SwipeRefreshLayout.OnRefreshListener {

    private ProgressDialog pd;
    private CommunicationManager commObj;
    private RecyclerView itemsRecyclerView;
    ActivityMainBinding binding;
    private CustomItemsAdapter objAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isFirstCall = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        commObj = new CommunicationManager(this);

        itemsRecyclerView = (RecyclerView) findViewById(R.id.itemsRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        itemsRecyclerView.setLayoutManager(layoutManager);
        itemsRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        isFirstCall = true;
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
        if (isFirstCall) {
            commObj.callWebService(this, Constants.API_URL, this, Constants.TASK_FETCH_DATA);
        } else {
            commObj.callWebService(this, Constants.API_URL, this, Constants.TASK_REFRESH_DATA);
        }
    }

    @Override
    public void onResult(String data, int tasksID) {
        if (pd.isShowing()) {
            pd.dismiss();
            pd.cancel();
        }
        Gson objgson = new Gson();
        try {
            FactData objFacts = objgson.fromJson(data, FactData.class);
            binding.setFactdata(objFacts);
            Log.e("data", ">"+data);
            if (tasksID == Constants.TASK_FETCH_DATA) {
                if (binding.getFactdata().getRows().size() > 0) {
                    isFirstCall = false;
                    objAdapter = new CustomItemsAdapter(this, objFacts.getRows());
                    itemsRecyclerView.setAdapter(objAdapter);
                }
            } else if (tasksID == Constants.TASK_REFRESH_DATA) {
                objAdapter.addAll(objFacts.getRows());
                swipeRefreshLayout.setRefreshing(false);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Some error has occurred. Please try again.", Toast.LENGTH_SHORT).show();
            Log.e("Exception", ">" + e);
            e.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        callWebService();
    }
}
