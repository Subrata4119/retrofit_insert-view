package com.subrata.retrofit_2021_with_email;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.subrata.retrofit_2021_with_email.R;
import com.subrata.retrofit_2021_with_email.API.ApiRequestBiodata;
import com.subrata.retrofit_2021_with_email.API.RetroServer;
import com.subrata.retrofit_2021_with_email.model.ResponseModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText name,position,salary,email;
    Button savebtn,showbtn;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.nameid);
        position=findViewById(R.id.positionid);
        salary=findViewById(R.id.salaryid);
        savebtn=findViewById(R.id.savebtnId);
        showbtn=findViewById(R.id.showbtn);
        email=findViewById(R.id.emailid);
        progressDialog=new ProgressDialog(this);
        showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),ShowData.class);
                startActivity(intent);
            }
        });


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("send data ... ");
                progressDialog.setCancelable(false);
                progressDialog.show();

                String Sname = name.getText().toString();
                String Sposition = position.getText().toString();
                String ssalary = salary.getText().toString();
                String Semail=email.getText().toString();
                ApiRequestBiodata api = RetroServer.getClient().create(ApiRequestBiodata.class);

                Call<ResponseModel> sendbio = api.sendBiodata(Sname,Sposition,ssalary,Semail);
                sendbio.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        progressDialog.hide();
                        Log.d("RETRO", "response : " + response.body().toString());
                        String code = response.body().getCode();

                        if(code.equals("1"))
                        {
                            Toast.makeText(MainActivity.this, "Data save successfully", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(MainActivity.this, "Data Error pls entry correctly", Toast.LENGTH_SHORT).show();

                        }
                    }



                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        progressDialog.hide();
                        Log.d("RETRO", "Falure : " + "Gagal Mengirim Request");
                    }
                });

            }
        });

    }
}