package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Information_Account  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_account);

        // Sự kiện cho các nút chuyển đổi giữa các layout
        findViewById(R.id.btnInformation).setOnClickListener(v -> {
            Intent intent = new Intent(Information_Account.this, InformationActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnTransactions).setOnClickListener(v -> {
            Intent intent = new Intent(Information_Account.this, TransactionsActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnNotifications).setOnClickListener(v -> {
            Intent intent = new Intent(Information_Account.this, NotificationsActivity.class);
            startActivity(intent);
        });

        // Thiết lập sự kiện click cho các nút
        findViewById(R.id.btnCompanyInfo).setOnClickListener(v -> {
            startActivity(new Intent(Information_Account.this, CompanyInfoActivity.class));
        });

        findViewById(R.id.btnPaymentPolicy).setOnClickListener(v -> {
            startActivity(new Intent(Information_Account.this, PaymentPolicyActivity.class));
        });

        findViewById(R.id.btnChangePassword).setOnClickListener(v -> {
            startActivity(new Intent(Information_Account.this, ChangePasswordActivity.class));
        });

        findViewById(R.id.btnDeleteAccount).setOnClickListener(v -> {
            showDeleteAccountDialog();
        });
    }

    // Method to show the delete account confirmation dialog
    private void showDeleteAccountDialog() {
        DeleteAccountDialog deleteAccountDialog = new DeleteAccountDialog();
        deleteAccountDialog.show(getSupportFragmentManager(), "deleteAccountDialog");
    }
}
