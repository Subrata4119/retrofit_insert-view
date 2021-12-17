package com.subrata.retrofit_2021_with_email;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.subrata.retrofit_2021_with_email.API.ApiRequestBiodata;
import com.subrata.retrofit_2021_with_email.API.RetroServer;
import com.subrata.retrofit_2021_with_email.adapter.AdapterData;
import com.subrata.retrofit_2021_with_email.model.DataModel;
import com.subrata.retrofit_2021_with_email.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ShowData extends AppCompatActivity {
    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private List<DataModel> mItems = new ArrayList<>();
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        pd = new ProgressDialog(this);
        mRecycler = (RecyclerView) findViewById(R.id.recyclerTemp);
        mManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(mManager);

        pd.setMessage("Loading ...");
        pd.setCancelable(false);
        pd.show();

        ApiRequestBiodata api = RetroServer.getClient().create(ApiRequestBiodata.class);
        Call<ResponseModel> getdata = api.getBiodata();
        getdata.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                pd.hide();
                Log.d("RETRO", "RESPONSE : " + response.body().getCode());
                mItems = response.body().getResult();

                mAdapter = new AdapterData(ShowData.this,mItems);
                mRecycler.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.hide();
                Log.d("RETRO", "FAILED : respon gagal");

            }
        });

    }
}
