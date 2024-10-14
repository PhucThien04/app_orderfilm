package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ThanhtoanActivity extends AppCompatActivity {

    private ImageView btnBack;

    private RadioGroup radioGroupPayment;
    private CheckBox checkBoxTerms;
    private Button buttonPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thanhtoan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Back = new Intent(ThanhtoanActivity.this, DatgheActivity.class);
                startActivity(Back);
            }
        });

        radioGroupPayment = findViewById(R.id.radioGroupPayment);
        checkBoxTerms = findViewById(R.id.checkBoxTerms);
        buttonPay = findViewById(R.id.buttonPay);

        // Kiểm tra trạng thái của các thành phần
        radioGroupPayment.setOnCheckedChangeListener((group, checkedId) -> updatePayButtonState());
        checkBoxTerms.setOnCheckedChangeListener((buttonView, isChecked) -> updatePayButtonState());

        buttonPay.setOnClickListener(v -> {
            Intent intent = new Intent(ThanhtoanActivity.this, ThanhcongActivity.class);
            startActivity(intent);
        });
    }

    private void updatePayButtonState() {
        boolean isPaymentSelected = radioGroupPayment.getCheckedRadioButtonId() != -1;
        boolean isTermsAccepted = checkBoxTerms.isChecked();
        buttonPay.setEnabled(isPaymentSelected && isTermsAccepted);
    }
}