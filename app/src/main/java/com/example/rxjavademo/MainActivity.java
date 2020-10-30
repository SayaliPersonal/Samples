package com.example.rxjavademo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavademo.network.APIClient;
import com.example.rxjavademo.network.APIInterface;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
public class MainActivity extends AppCompatActivity {

    private RecyclerAdapter mAdapter;
    private RecyclerView recycler_view;
    private APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=(Button) findViewById(R.id.btn_click);
         recycler_view =(RecyclerView) findViewById(R.id.recycler_view);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });

    }

    private void getData() {
        final ProgressDialog proDialog = ProgressDialog.show(this, "Loading", "Please wait");




            apiInterface = APIClient.getClient().create(APIInterface.class);
            final Call<ResponseBody> call1 = apiInterface.getEmployee();

            Log.e("&&URL", call1.request().url() + "");







        call1.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                proDialog.dismiss();
                Log.w("**onResponse", call1.request().url() + "");
                if (response.isSuccessful()) {
                    try {
                        String res = response.body().string();
                        Log.w("**res", res + "");
                        Gson gson = new Gson();
                        Reader reader = new StringReader(res);
                        Employee employee = gson.fromJson(reader, Employee.class);

                        mAdapter = new RecyclerAdapter(getApplicationContext(),employee.getData());
                        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recycler_view.setLayoutManager(linearLayoutManager);
                        recycler_view.setAdapter(mAdapter);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                proDialog.dismiss();
            }
        });


    }
}