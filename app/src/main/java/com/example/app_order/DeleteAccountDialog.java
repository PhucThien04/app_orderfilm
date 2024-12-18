package com.example.app_order;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DeleteAccountDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Xóa tài khoản")
                .setMessage("Bạn có chắc chắn muốn xóa tài khoản không?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    // Logic xóa tài khoản
                })
                .setNegativeButton("Không", (dialog, which) -> dialog.dismiss());
        return builder.create();
    }
}
