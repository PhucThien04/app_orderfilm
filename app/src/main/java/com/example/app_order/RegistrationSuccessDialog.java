package com.example.app_order;

import androidx.fragment.app.DialogFragment;import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
public class RegistrationSuccessDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông báo")
                .setMessage("Đăng ký thành công")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Chuyển sang layout khác
                    Intent intent = new Intent(getActivity(), TrangChu.class);
                    startActivity(intent);
                });
        return builder.create();
    }
}
