package com.example.app_order;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DoiMatKhau extends AppCompatActivity {
    private EditText edt_newpass, edt_oldpass, edt_verifyPass;
    boolean passVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doi_mat_khau);

        findViewById(R.id.btnBack).setOnClickListener(v -> {
            Intent intent = new Intent(DoiMatKhau.this, Information_Account.class);
            startActivity(intent);
        });

        AnhXa();

        edt_newpass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX()>=edt_newpass.getRight()-edt_newpass.getCompoundDrawables()[Right].getBounds().width()){
                        int select = edt_newpass.getSelectionEnd();
                        if(passVisible){
                            edt_newpass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.hide, 0);
                            edt_newpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisible = false;
                        }else {
                            edt_newpass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.view, 0);
                            edt_newpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisible = true;
                        }
                        edt_newpass.setSelection(select);
                        return true;
                    }
                }
                return false;
            }

        });

        edt_oldpass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX()>=edt_oldpass.getRight()-edt_oldpass.getCompoundDrawables()[Right].getBounds().width()){
                        int select = edt_oldpass.getSelectionEnd();
                        if(passVisible){
                            edt_oldpass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.hide, 0);
                            edt_oldpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisible = false;
                        }else {
                            edt_oldpass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.view, 0);
                            edt_oldpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisible = true;
                        }
                        edt_oldpass.setSelection(select);
                        return true;
                    }
                }
                return false;
            }

        });

        edt_verifyPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX()>=edt_verifyPass.getRight()-edt_verifyPass.getCompoundDrawables()[Right].getBounds().width()){
                        int select = edt_verifyPass.getSelectionEnd();
                        if(passVisible){
                            edt_verifyPass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.hide, 0);
                            edt_verifyPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisible = false;
                        }else {
                            edt_verifyPass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.view, 0);
                            edt_verifyPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisible = true;
                        }
                        edt_verifyPass.setSelection(select);
                        return true;
                    }
                }
                return false;
            }

        });
    }
    private void AnhXa(){
        edt_newpass = (EditText) findViewById(R.id.editText_NewPass);
        edt_oldpass = (EditText) findViewById(R.id.editText_OldPass);
        edt_verifyPass = (EditText) findViewById(R.id.editText_VerifyPass);
    }
}