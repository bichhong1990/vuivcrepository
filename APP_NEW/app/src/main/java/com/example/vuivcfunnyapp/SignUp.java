package com.example.vuivcfunnyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText edtEmailRegister,edtPasswordRegister,edtPasswordConfirm;
    Button btnRegister;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = firebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        edtEmailRegister = findViewById(R.id.edtEmailRegister);
        edtPasswordRegister = findViewById(R.id.edtPasswordRegister);
        edtPasswordConfirm  = findViewById(R.id.edtPasswordRegister);
        btnRegister = findViewById(R.id.btn_SignUp);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String email = edtEmailRegister.getText().toString();
        String password = edtPasswordRegister.getText().toString();
        String passwordconfirm = edtPasswordConfirm.getText().toString();
        String thongbaoloi = getString(R.string.thongbaoloi);
        progressDialog.setMessage("Chow trong giay lat");
        progressDialog.setIndeterminate(true);
        switch (view.getId())
        {
            case R.id.btn_SignUp:
                if(email.isEmpty())
                {
                    thongbaoloi += " email";
                    Toast.makeText(SignUp.this,thongbaoloi,Toast.LENGTH_LONG).show();
                }
                else if(password.isEmpty())
                {
                    thongbaoloi += " password";
                    Toast.makeText(SignUp.this,thongbaoloi,Toast.LENGTH_LONG).show();
                }
                else if(!SignInEmail.KTEmail(email))
                {
                    Toast.makeText(SignUp.this,"Email nhap sai",Toast.LENGTH_LONG).show();
                }
                else if(!password.equals(passwordconfirm))
                {
                    Toast.makeText(SignUp.this,"Password nhap lai khong dung",Toast.LENGTH_LONG).show();
                }
                else
                {
                    progressDialog.show();
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(SignUp.this,"Dang ky thanh cong",Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
                }

        }
    }
}
