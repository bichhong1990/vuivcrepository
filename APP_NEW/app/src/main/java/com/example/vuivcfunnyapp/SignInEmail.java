package com.example.vuivcfunnyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.*;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class SignInEmail extends AppCompatActivity implements View.OnClickListener, FirebaseAuth.AuthStateListener{

    EditText edt_SignInEmail,edt_Password;
    Button btnSignIn;
    TextView txtQuenMK;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_email);

        edt_SignInEmail = findViewById(R.id.edtEmail);
        edt_Password = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btn_SignIn);
        txtQuenMK = findViewById(R.id.txtQuenMK);

        firebaseAuth = firebaseAuth.getInstance();

        btnSignIn.setOnClickListener(this);
        txtQuenMK.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id= view.getId();
        String email = edt_SignInEmail.getText().toString();
        String password = edt_Password.getText().toString();
        String thongbaoloi = getString(R.string.thongbaoloi);
        switch (id)
        {
            case R.id.btn_SignIn:
                if(email.isEmpty())
                {
                    thongbaoloi += " email";
                    Toast.makeText(SignInEmail.this,thongbaoloi,Toast.LENGTH_LONG).show();
                }
                else if(password.isEmpty())
                {
                    thongbaoloi += " password";
                    Toast.makeText(SignInEmail.this,thongbaoloi,Toast.LENGTH_LONG).show();
                }
                else if(!KTEmail(email))
                {
                    Toast.makeText(SignInEmail.this,"Email nhap sai",Toast.LENGTH_LONG).show();
                }
                else
                {
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignInEmail.this,"Failed",Toast.LENGTH_LONG).show();
                        }
                    });
                }
                break;
            case R.id.txtQuenMK:
                Intent intentQuenMK = new Intent(SignInEmail.this,ForgotPassword.class);
                startActivity(intentQuenMK);
                break;
        }
    }
    public static boolean KTEmail(String email)
    {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null)
        {
            Intent intentHome = new Intent(SignInEmail.this,MainActivity.class);
            startActivity(intentHome);
        }
    }
}
