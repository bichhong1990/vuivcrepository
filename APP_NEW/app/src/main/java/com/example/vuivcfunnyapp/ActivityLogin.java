package com.example.vuivcfunnyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.android.gms.auth.api.*;

import java.util.Arrays;
import java.util.List;

public class ActivityLogin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, FirebaseAuth.AuthStateListener {
    TextView txt_LoginGoogle,txt_SignUp;
    Button btn_LoginFacebook,btn_LoginEmail;
    GoogleApiClient apiClient;
    FirebaseAuth firebaseAuth;
    LoginManager loginManager;
    CallbackManager mCallBackFacebook;
    List<String> permissionFacebook = Arrays.asList("email","public_profile");
    public static int CODE_LOGIN_GOOGLE = 100;
    public static int CHECK_AUTH_PROVIDER_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        txt_LoginGoogle = findViewById(R.id.txt_GoogLe_Home);
        btn_LoginEmail = findViewById(R.id.btn_Login_Home);
        btn_LoginFacebook = findViewById(R.id.btn_login_facebook);
        txt_SignUp = findViewById(R.id.tvTextRegisterBottom);

        mCallBackFacebook = CallbackManager.Factory.create();
        loginManager = loginManager.getInstance();
        firebaseAuth = firebaseAuth.getInstance();
        firebaseAuth.signOut();
        txt_LoginGoogle.setOnClickListener(this);
        btn_LoginFacebook.setOnClickListener(this);
        btn_LoginEmail.setOnClickListener(this);
        txt_SignUp.setOnClickListener(this);

        CreateClientLoginGoogle();


    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    private void CreateClientLoginGoogle(){
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions)
                .build();
    }

    private void LoginGoogle(GoogleApiClient apiClient){
        Intent intentGoogle = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(intentGoogle,CODE_LOGIN_GOOGLE);
    }

    private void LoginFacebook(){
        loginManager.logInWithReadPermissions(this,permissionFacebook);
        loginManager.registerCallback(mCallBackFacebook, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                CHECK_AUTH_PROVIDER_SIGN_IN = 2;
                String tokenID = loginResult.getAccessToken().getToken();
                ChungThucDangNhapFireBase(tokenID);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }


    private void ChungThucDangNhapFireBase(String tokenID) {

        switch (CHECK_AUTH_PROVIDER_SIGN_IN) {
            case 1:
                AuthCredential authCredential = GoogleAuthProvider.getCredential(tokenID, null);//mo chung thuc google
                firebaseAuth.signInWithCredential(authCredential);
                break;
            case 2:
                AuthCredential authCredential1 = FacebookAuthProvider.getCredential(tokenID);
                firebaseAuth.signInWithCredential(authCredential1);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id)
        {
            case R.id.txt_GoogLe_Home:
                LoginGoogle(apiClient);
                break;
            case R.id.btn_login_facebook:
                LoginFacebook();
                break;
            case R.id.btn_Login_Home:
                Intent itentSignIn = new Intent(ActivityLogin.this,SignInEmail.class);
                startActivity(itentSignIn);
                break;
            case R.id.tvTextRegisterBottom:
                Intent itentRegister = new Intent(ActivityLogin.this,SignUp.class);
                startActivity(itentRegister);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_LOGIN_GOOGLE)
        {
            if(resultCode == RESULT_OK)
            {
                GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount account = signInResult.getSignInAccount();//laasy account
                String tokenID = account.getIdToken();//lay token tu account
                ChungThucDangNhapFireBase(tokenID);//chung thuc cai token dang nhap
            }
        }
        else
        {
            if(resultCode == RESULT_OK)
            {
                mCallBackFacebook.onActivityResult(requestCode,resultCode,data);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    //Kiem tra trang thai login
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user != null)
        {
            Intent Home = new Intent(ActivityLogin.this, MainActivity.class);
            startActivity(Home);
        }

    }

}
