package com.example.app_order;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DangKyThongTinCaNhan extends AppCompatActivity {

    private EditText edTxt_Date;
    private DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_ky_thong_tin_ca_nhan);

        findViewById(R.id.button_signUp).setOnClickListener(v -> {
            Intent intent = new Intent(DangKyThongTinCaNhan.this, XacThucTaiKhoan.class);
            startActivity(intent);
        });
        AnhXa();
        edTxt_Date.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

           picker = new DatePickerDialog(DangKyThongTinCaNhan.this, new DatePickerDialog.OnDateSetListener() {
               @Override
               public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    edTxt_Date.setText(dayOfMonth + "/" + (month+1) + "/" + year);
               }
           }, year, month, day);
           picker.show();
        }
    });

    }
    private void AnhXa(){
        edTxt_Date = (EditText) findViewById(R.id.editText_dateOfBirth);

    }
}