package com.example.vuivcfunnyapp.ui.profile;


import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.vuivcfunnyapp.R;
import com.example.vuivcfunnyapp.ui.dialog.DialogChangeImage;
import com.example.vuivcfunnyapp.ui.profile.profile_interface.InterfaceProfile;
import com.example.vuivcfunnyapp.ui.profile.profile_interface.TruyenThongTinUser;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;


import static android.app.Activity.RESULT_OK;

public class FragmentEditProfile extends AppCompatActivity implements View.OnClickListener {
    ImageView img_Quaylai,imgDoiAnh,imgAnhDaiDien;
    EditText edt_ten,edt_ngaysinh;
    RadioButton rbNam,rbNu;
    TextView txtLuu,txt_ID;
    String gioitinh;
    Bitmap image;
    String tenUser,ngaySinh;
    boolean sex=true;
    DatabaseReference databaseReference;
    InterfaceProfile interfaceProfile;
    ProfileUserModel profileUser;
    StorageReference storageRef;

    TruyenThongTinUser truyenThongTinUser;
    public void FragmentEditProfile(TruyenThongTinUser truyenDuLieu) { //Bắn dữ liệu cho UserMain
        this.truyenThongTinUser = truyenDuLieu;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit_user);
        img_Quaylai = findViewById(R.id.img_QuayLai);
        edt_ngaysinh = findViewById(R.id.edt_Ngaysinh);
        edt_ten = findViewById(R.id.edt_HovaTen);
        txtLuu = findViewById(R.id.txt_Luu);
        rbNam = findViewById(R.id.rb_Nam);
        rbNu = findViewById(R.id.rb_nu);
        imgDoiAnh = findViewById(R.id.circle_crop);
        imgAnhDaiDien = findViewById(R.id.img_DoiAnh);
        txt_ID = findViewById(R.id.txt_id);

        profileUser = new ProfileUserModel();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ProfileUserModel");

    }

    @Override
    public void onStart() {
        super.onStart();

        interfaceProfile = new InterfaceProfile() {
            @Override
            public void getDataProfileInterface(ProfileUserModel profileUserModel) {
                profileUser = profileUserModel;
                txt_ID.setText(profileUser.getId()+"");
                edt_ten.setText(profileUser.getNameUser());
                edt_ngaysinh.setText(profileUser.getNgaySinh());
                Glide.with(getApplicationContext()).load(profileUser.getPhoto()).into(imgAnhDaiDien);

                if(profileUser.isSex().equalsIgnoreCase("male"))
                {
                    CheckBuild(rbNam);
                    rbNam.setChecked(true);
                    CheckBuild(rbNu);
                }
                else {
                    CheckBuild(rbNu);
                    rbNu.setChecked(true);
                    CheckBuild(rbNam);
                }
            }
        };
        profileUser.getDataProfile(interfaceProfile);

        img_Quaylai.setOnClickListener(this);
        rbNu.setOnClickListener(this);
        rbNam.setOnClickListener(this);
        txtLuu.setOnClickListener(this);
        imgAnhDaiDien.setOnClickListener(this);
        //Nhan du lieu tu User Main


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.img_QuayLai:
                getFragmentManager().popBackStack();
                break;
            case R.id.rb_Nam:
                gioitinh = rbNam.getText().toString();
                sex = true;
                CheckBuild(rbNam);
                profileUser.setSex(rbNam.getText().toString());
                break;
            case R.id.rb_nu:
                gioitinh = rbNu.getText().toString();
                CheckBuild(rbNu);
                sex = false;
                profileUser.setSex(rbNu.getText().toString());
                break;
            case R.id.txt_Luu:
                databaseReference.child(profileUser.getId());
                profileUser.setNameUser(edt_ten.getText().toString());
                profileUser.setNgaySinh(edt_ngaysinh.getText().toString());


                HashMap<String,Object> hashMap = new HashMap<>();
                hashMap.put("nameUser",profileUser.getNameUser());
                hashMap.put("id",profileUser.getId());
                hashMap.put("ngaySinh",profileUser.getNgaySinh());
                hashMap.put("numFollower",profileUser.getNumFollower());
                hashMap.put("numFollowing",profileUser.getNumFollowing());
                hashMap.put("numVideo",profileUser.getNumVideo());
                hashMap.put("photo",profileUser.getPhoto());
                hashMap.put("sex",profileUser.isSex());

                HashMap<String,Object> key = new HashMap<>();
                key.put(profileUser.getId(),hashMap);

                databaseReference.updateChildren(key);

                finish();
                break;
            case R.id.img_DoiAnh:
                ChangeAnh();
                break;
        }
    }


    private void ChangeAnh() {
        DialogChangeImage dialog = new DialogChangeImage();
        dialog.show(getSupportFragmentManager(),null);
        }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 300)
        {
            if(resultCode == RESULT_OK)
            {
               image = (Bitmap) data.getExtras().get("data");
                //profileUser.setPhoto(image);
            }
        }
        else if(requestCode ==200)
        {
            if(resultCode==RESULT_OK) {
                Uri imageUri = data.getData();
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    private void CheckBuild(RadioButton radioButton) {
        if(Build.VERSION.SDK_INT>=21)
        {

            ColorStateList colorStateList = new ColorStateList(
                    new int[][]{

                            new int[]{-android.R.attr.state_enabled}, //disabled
                            new int[]{android.R.attr.state_enabled} //enabled
                    },
                    new int[] {

                            Color.WHITE //disabled
                            ,Color.YELLOW //enabled

                    }
            );


            radioButton.setButtonTintList(colorStateList);//set the color tint list
            radioButton.invalidate(); //could not be necessary
        }
    }


}

