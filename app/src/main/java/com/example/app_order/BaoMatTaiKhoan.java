package com.example.app_order;

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

public class BaoMatTaiKhoan extends AppCompatActivity {
    private EditText edt_pass;
    private boolean passVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bao_mat_tai_khoan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edt_pass = (EditText) findViewById(R.id.editText_Pass);

        edt_pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX()>=edt_pass.getRight()-edt_pass.getCompoundDrawables()[Right].getBounds().width()){
                        int select = edt_pass.getSelectionEnd();
                        if(passVisible){
                            edt_pass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.hide, 0);
                            edt_pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisible = false;
                        }else {
                            edt_pass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.view, 0);
                            edt_pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisible = true;
                        }
                        edt_pass.setSelection(select);
                        return true;
                    }
                }
                return false;
            }

        });
    }
}