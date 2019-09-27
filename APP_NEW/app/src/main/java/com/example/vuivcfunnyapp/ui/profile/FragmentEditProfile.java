package com.example.vuivcfunnyapp.ui.profile;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.example.vuivcfunnyapp.R;
import com.example.vuivcfunnyapp.ui.dialog.DialogChangeImage;
import com.example.vuivcfunnyapp.ui.profile.profile_interface.InterfaceProfile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;



public class FragmentEditProfile extends AppCompatActivity implements View.OnClickListener {
    ImageView img_Quaylai, imgDoiAnh, imgAnhDaiDien;
    EditText edt_ten, edt_ngaysinh;
    RadioButton rbNam, rbNu;
    TextView txtLuu, txt_ID;
    String gioitinh;
    Bitmap image;
    String urlimagedaidien;
    byte[] imagedata;
    boolean sex = true;
    DatabaseReference databaseReference;
    ProfileUserModel profileUser;
    StorageReference storageRef;


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
        storageRef = FirebaseStorage.getInstance().getReference().child("imagedaidien").child(profileUser.getId() + ".jpg");

    }

    @Override
    public void onStart() {
        super.onStart();

        img_Quaylai.setOnClickListener(this);
        rbNu.setOnClickListener(this);
        rbNam.setOnClickListener(this);
        txtLuu.setOnClickListener(this);
        imgAnhDaiDien.setOnClickListener(this);
        //Nhan du lieu tu User Main
        InterfaceProfile interfaceProfile = new InterfaceProfile() {
            @Override
            public void getDataProfileInterface(final ProfileUserModel profileUserModel) {
                profileUser = profileUserModel;
                txt_ID.setText(profileUser.getId() + "");
                edt_ten.setText(profileUser.getNameUser());
                edt_ngaysinh.setText(profileUser.getNgaySinh());
                storageRef = FirebaseStorage.getInstance().getReference().child("imagedaidien").child(profileUser.getId() + ".jpg");
                storageRef.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        imgAnhDaiDien.setImageBitmap(bitmap);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Glide.with(getApplicationContext()).load(profileUserModel.getPhoto() + "/*.jpeg?height=500").into(imgAnhDaiDien);
                    }
                });

                if (profileUser.isSex().equalsIgnoreCase("male")) {
                    CheckBuild(rbNam);
                    rbNam.setChecked(true);
                    CheckBuild(rbNu);
                } else {
                    CheckBuild(rbNu);
                    rbNu.setChecked(true);
                    CheckBuild(rbNam);
                }
            }
        };
        profileUser.getDataProfile(interfaceProfile);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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


                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("nameUser", profileUser.getNameUser());
                hashMap.put("id", profileUser.getId());
                hashMap.put("ngaySinh", profileUser.getNgaySinh());
                hashMap.put("numFollower", profileUser.getNumFollower());
                hashMap.put("numFollowing", profileUser.getNumFollowing());
                hashMap.put("numVideo", profileUser.getNumVideo());
                profileUser.setPhoto(urlimagedaidien);
                hashMap.put("photo", profileUser.getPhoto());
                hashMap.put("sex", profileUser.isSex());

                HashMap<String, Object> key = new HashMap<>();
                key.put(profileUser.getId(), hashMap);
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
        dialog.show(getSupportFragmentManager(), null);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 300) {
            if (resultCode == RESULT_OK) {
                image = (Bitmap) data.getExtras().get("data");
                //profileUser.setPhoto(image);
            }
        } else if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                Uri imageUri = data.getData();
                try {
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(image == null)
        {
            if(storageRef.child("imagedaidien").child(profileUser.getId() + ".jpg").getDownloadUrl() == null) {
                Glide.with(getApplicationContext()).load(profileUser.getPhoto() + "/*.jpeg?height=500").into(imgAnhDaiDien);
            }
            else
            {
                long ONEMEGABYTE = 1024 * 1024;
                storageRef.getBytes(ONEMEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        imgAnhDaiDien.setImageBitmap(bitmap);
                    }
                });
            }
        }
        else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            imagedata = byteArrayOutputStream.toByteArray();
            storageRef = FirebaseStorage.getInstance().getReference().child("imagedaidien").child(profileUser.getId() + ".jpg");
            storageRef.putBytes(imagedata);
            urlimagedaidien = profileUser.getId() + ".jpg";
            //lay anh
            long ONEMEGABYTE = 1024 * 1024;
            storageRef.getBytes(ONEMEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    imgAnhDaiDien.setImageBitmap(bitmap);
                }
            });
        }



    }

    private void CheckBuild(RadioButton radioButton) {
        if (Build.VERSION.SDK_INT >= 21) {

            ColorStateList colorStateList = new ColorStateList(
                    new int[][]{

                            new int[]{-android.R.attr.state_enabled}, //disabled
                            new int[]{android.R.attr.state_enabled} //enabled
                    },
                    new int[]{

                            Color.WHITE //disabled
                            , Color.YELLOW //enabled

                    }
            );


            radioButton.setButtonTintList(colorStateList);//set the color tint list
            radioButton.invalidate(); //could not be necessary
        }
    }


}

