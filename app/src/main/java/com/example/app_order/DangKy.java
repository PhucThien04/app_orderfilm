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

public class DangKy extends AppCompatActivity {
    EditText email, pass, verifyPass;
    boolean passVisible;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_ky);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    AnhXa();

    pass.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            final int Right = 2;
            if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                if(motionEvent.getRawX()>=pass.getRight()-pass.getCompoundDrawables()[Right].getBounds().width()){
                    int select = pass.getSelectionEnd();
                    if(passVisible){
                        pass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.hide, 0);
                        pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passVisible = false;
                    }else {
                        pass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.view, 0);
                        pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passVisible = true;
                    }
                    pass.setSelection(select);
                    return true;
                }
            }
            return false;
        }

    });

        verifyPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX()>=verifyPass.getRight()-verifyPass.getCompoundDrawables()[Right].getBounds().width()){
                        int select = verifyPass.getSelectionEnd();
                        if(passVisible){
                            verifyPass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.hide, 0);
                            verifyPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passVisible = false;
                        }else {
                            verifyPass.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.view, 0);
                            verifyPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passVisible = true;
                        }
                        verifyPass.setSelection(select);
                        return true;
                    }
                }
                return false;
            }

        });


    }
    private void AnhXa(){
        pass = (EditText) findViewById(R.id.editText_pass);
        verifyPass = (EditText) findViewById(R.id.editText_verifyPass);
    }
}