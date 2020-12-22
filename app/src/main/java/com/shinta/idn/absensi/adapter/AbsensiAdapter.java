package com.shinta.idn.absensi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shinta.idn.absensi.AbsensiActivity;
import com.shinta.idn.absensi.R;
import com.shinta.idn.absensi.model.SiswaItem;

import java.util.List;

public class AbsensiAdapter extends ArrayAdapter<SiswaItem> {

    private Context context;
    private List<SiswaItem> siswaItem;

    public AbsensiAdapter(@NonNull Context context,
                             int resources,
                             @NonNull List<SiswaItem> objects) {
        super(context, resources, objects);
        this.context = context;
        this.siswaItem = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_item,
                parent, false);

        TextView txtIdAttendance = v.findViewById(R.id.txt_attendance_id);
        TextView txtDateAttendance = v.findViewById(R.id.txt_attendance_date);
        TextView txtNameAttendance = v.findViewById(R.id.txt_attendance_name);
        TextView txtClassAttendance = v.findViewById(R.id.txt_attendance_class);
        TextView txtSubjectAttendance = v.findViewById(R.id.txt_attendance_subject);
        TextView txtStudentAttendance = v.findViewById(R.id.txt_attendance_student);

        txtIdAttendance.setText(String.valueOf(siswaItem.get(position).getId()));
        txtDateAttendance.setText(String.valueOf(siswaItem.get(position).getDate()));
        txtNameAttendance.setText(String.valueOf(siswaItem.get(position).getName()));
        txtClassAttendance.setText(String.valueOf(siswaItem.get(position).getClass()));
        txtSubjectAttendance.setText(String.valueOf(siswaItem.get(position).getSubject()));
        txtStudentAttendance.setText(String.valueOf(siswaItem.get(position).getAttendance()));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AbsensiActivity.class);
                intent.putExtra("id", String.valueOf(siswaItem.get(position).getId()));
                intent.putExtra("date", siswaItem.get(position).getName());
                intent.putExtra("name", siswaItem.get(position).getName());
                intent.putExtra("class", siswaItem.get(position).getClasss());
                intent.putExtra("subject", siswaItem.get(position).getSubject());
                intent.putExtra("attendance", siswaItem.get(position).getAttendance());
                context.startActivity(intent);
            }
        });

        return v;
    }

}
