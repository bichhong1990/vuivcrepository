package com.example.vuivcfunnyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity implements View.OnClickListener {

    EditText edtEmailForgot;
    Button btn_SendEmail;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        firebaseAuth = firebaseAuth.getInstance();
        edtEmailForgot = findViewById(R.id.edtEmailForgot);
        btn_SendEmail = findViewById(R.id.btn_SendEmail);

        btn_SendEmail.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String email = edtEmailForgot.getText().toString();
        switch (view.getId())
        {
            case R.id.btn_SendEmail:
                if(!SignInEmail.KTEmail(email))
                {
                    Toast.makeText(ForgotPassword.this,"Email nhap sai",Toast.LENGTH_LONG).show();
                }
                else if(email.isEmpty())
                {
                    Toast.makeText(ForgotPassword.this,getString(R.string.thongbaoloi)+"Email",Toast.LENGTH_LONG).show();
                }
                else
                {
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgotPassword.this,"Da gui email",Toast.LENGTH_LONG).show();
                                Intent itentdn = new Intent(ForgotPassword.this,SignInEmail.class);
                                startActivity(itentdn);
                            }
                        }
                    });

                }
                break;
        }
    }
}
