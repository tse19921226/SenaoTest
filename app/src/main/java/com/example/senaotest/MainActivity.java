package com.example.senaotest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    private ExecutorService executorService;
    private DataSync dataSync;
    private TopBarView topBarView;
    private RecyclerView rvList;
    private StoreItemAdapter storeItemAdapter;
    private ArrayList<StoreItem> currentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataSync();
        initView();
    }

    private void initView(){
        topBarView = findViewById(R.id.topBar);
        rvList = findViewById(R.id.rv_list);
        topBarView.setViewType(TopBarView.SERACH_TYPE);
        topBarView.setup();
        topBarView.registerSearchCallBack(searchCallBack);
        storeItemAdapter = new StoreItemAdapter(getApplicationContext());
        storeItemAdapter.registerAdapterCallBack(adapterCallBack);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(storeItemAdapter);
    }

    private void dataSync(){
        dataSync = new DataSync();
        dataSync.setmCtx(getApplicationContext());
        dataSync.execute(DataManager.senaoUrl);
        dataSync.registerDataSyncCallBack(dataSyncCallBack);
    }

    private DataSync.DataSyncCallBack dataSyncCallBack = new DataSync.DataSyncCallBack() {
        @Override
        public void syncFinish() {
            currentList = DataManager.getInstance(getApplicationContext()).getStoreData().getData();
            storeItemAdapter.updateList(currentList);
            storeItemAdapter.notifyDataSetChanged();
        }
    };

    private StoreItemAdapter.AdapterCallBack adapterCallBack = new StoreItemAdapter.AdapterCallBack() {
        @Override
        public void onClick(StoreItem storeItem) {
            Log.d(TAG, "storeItem.martName = " + storeItem.martName);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            intent.setClass(MainActivity.this, DetailActivity.class);
            bundle.putSerializable(DataManager.SelectItem_Key, storeItem);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };

    private TopBarView.TopBarSearchCallBack searchCallBack = new TopBarView.TopBarSearchCallBack() {
        @Override
        public void onTextSubmit(String query) {

        }

        @Override
        public void onTextChange(String text) {
            storeItemAdapter.getFilter().filter(text);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        storeItemAdapter.unregisterAdapterCallBack();
        dataSync.unregisterDataSyncCallBack();
        topBarView.unregisterSearchCallBack();
    }
}