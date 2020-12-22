package com.shinta.idn.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.shinta.idn.absensi.adapter.AbsensiAdapter;
import com.shinta.idn.absensi.model.SiswaItem;
import com.shinta.idn.absensi.remote.APIUtils;
import com.shinta.idn.absensi.remote.AbsesnsiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnGet;
    ListView rv;
    AbsesnsiService absesnsiService;
    List<SiswaItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnGet = findViewById(R.id.btnGet);
        rv = findViewById(R.id.rv);

        absesnsiService = APIUtils.getAttendanceService();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                        AbsensiActivity.class);
                intent.putExtra("date", "");
                intent.putExtra("name", "");
                intent.putExtra("class", "");
                intent.putExtra("subject", "");
                intent.putExtra("attendance", "");
                startActivity(intent);
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserList();
            }
        });

    }

    public void getUserList() {
        Call<List<SiswaItem>> call = absesnsiService.getAttendance();
        call.enqueue(new Callback<List<SiswaItem>>() {
            @Override
            public void onResponse(Call<List<SiswaItem>> call, Response<List<StudentItem>> response) {
                if (response.isSuccessful()){
                    list = response.body();
                    rv.setAdapter(new AbsensiAdapter(MainActivity.this,
                            R.layout.list_item, list));
                }
            }
            @Override
            public void onFailure(Call<List<SiswaItem>> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }
}