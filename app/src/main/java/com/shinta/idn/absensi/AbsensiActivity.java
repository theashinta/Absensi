package com.shinta.idn.absensi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shinta.idn.absensi.model.SiswaItem;
import com.shinta.idn.absensi.remote.APIUtils;
import com.shinta.idn.absensi.remote.AbsesnsiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsensiActivity extends AppCompatActivity {

    AbsesnsiService absesnsiService;
    EditText edtDate, edtName, edtClass, edtSubject, edtAttendance, edtId;
    Button btnSave, btnDel;
    TextView txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtDate = findViewById(R.id.edt_ttanggal);
        edtName = findViewById(R.id.edt_nama);
        edtClass = findViewById(R.id.edt_kelas);
        edtSubject = findViewById(R.id.edt_keterangan);
        edtAttendance = findViewById(R.id.edt_kehadiran);
        btnSave = findViewById(R.id.btn_simpan);
        btnDel = findViewById(R.id.btn_hapus);
        edtId = findViewById(R.id.edt_id);
        txtId = findViewById(R.id.txt_id);

        absesnsiService = APIUtils.getAttendanceService();

        Bundle extras = getIntent().getExtras();
        String attendanceDate = extras.getString("date");
        String attendanceName = extras.getString("name");
        String attendanceClass = extras.getString("class");
        String attendanceSubject = extras.getString("subject");
        String attendanceAttendance = extras.getString("attendance");
        final String attendanceID = extras.getString("id");

        edtId.setText(attendanceID);
        edtDate.setText(attendanceDate);
        edtName.setText(attendanceName);
        edtClass.setText(attendanceClass);
        edtSubject.setText(attendanceSubject);
        edtAttendance.setText(attendanceAttendance);

        if (attendanceID != null && attendanceID.trim().length() > 0){
            edtId.setFocusable(false);
        } else {
            txtId.setVisibility(View.INVISIBLE);
            edtId.setVisibility(View.INVISIBLE);
            btnDel.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = edtDate.getText().toString();
                String name = edtName.getText().toString();
                String classs = edtClass.getText().toString();
                String subject = edtSubject.getText().toString();
                String attendance = edtAttendance.getText().toString();

                if (attendanceID != null && attendanceID.trim().length() > 0){
                    updateAttendance(Integer.parseInt(attendanceID), date, name, classs, subject, attendance);
                } else {
                    addAttendance(date, name, classs, subject, attendance);
                }
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAttendance(Integer.parseInt(attendanceID));
                Intent intent = new Intent(AbsensiActivity.this,
                        MainActivity.class);
                startActivity(intent);
            }
        });

//        @Override
//        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()){
//                case android.R.id.home:
//                    finish();
//                    return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }
    }

    private void deleteAttendance(int id) {
        Call<SiswaItem> call = absesnsiService.deleteAttendance(id);
        call.enqueue(new Callback<SiswaItem>() {
            @Override
            public void onResponse(Call<SiswaItem> call, Response<SiswaItem> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AbsensiActivity.this, "Attendance deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AbsensiActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<SiswaItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void addAttendance(String date, String name, String classs, String subject, String attendance) {
        Call<SiswaItem> call = AbsesnsiService.addAbsensi(date, name, classs, subject, attendance);
        call.enqueue(new Callback<SiswaItem>() {
            @Override
            public void onResponse(Call<SiswaItem> call, Response<SiswaItem> response) {
                Toast.makeText(AbsensiActivity.this, "attendance added succesfully",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AbsensiActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SiswaItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });
    }

    private void updateAttendance(int id, String date, String name, String classs, String subject, String attendance) {
        Call<SiswaItem> call = AbsesnsiService.updateAttendance(id, date, name, classs, subject, attendance);
        call.enqueue(new Callback<AbsesnsiService>() {
            @Override
            public void onResponse(Call<SiswaItem> call, Response<SiswaItem> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AbsensiActivity.this, "Attendance Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AbsensiActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<SiswaItem> call, Throwable t) {
                Log.e("ERROR: ", t.getMessage());
            }
        });

    }
}