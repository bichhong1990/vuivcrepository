package com.example.vuivcfunnyapp.ui.profile;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.vuivcfunnyapp.ActivityLogin;
import com.example.vuivcfunnyapp.R;
import com.example.vuivcfunnyapp.ui.profile.profile_interface.InterfaceProfile;
import com.example.vuivcfunnyapp.ui.profile.profile_interface.NhanDuLieuTuUserMain;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class ProfileFragment extends Fragment
{
    LinearLayout line1, line2, line3;
    ImageButton imgbtn_setting;
    ImageView imgAnhdaidien;
    public TextView txt_SoLuongVideo, txt_SoluongFollower, txt_SoluongFollowing, txtTenUser, txtID,txtSex, txtDate;
    long SoluongFollower,SoLuongFollowing,SoLuongVideo;
    FirebaseAuth firebaseAuth;
    ProfileUserModel dataProfile;
    InterfaceProfile interfaceProfile;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile,container,false);

        imgbtn_setting = root.findViewById(R.id.imgbtn_setting);
        txt_SoLuongVideo = root.findViewById(R.id.txt_SoLuongVideo);
        txt_SoluongFollower = root.findViewById(R.id.txt_SoLuongFollowers);
        txt_SoluongFollowing =root. findViewById(R.id.txt_SoLuongFollowing);
        txtSex = root.findViewById(R.id.txtSex);
        txtDate = root.findViewById(R.id.txtDate);
        imgAnhdaidien = root.findViewById(R.id.imgAnhUser);
        txtTenUser = root.findViewById(R.id.txtTenUser);
        txtID = root.findViewById(R.id.txtID);
        line1 = root.findViewById(R.id.line1);
        line2 = root.findViewById(R.id.line2);
        line3 = root.findViewById(R.id.line3);

        firebaseAuth = firebaseAuth.getInstance();
        dataProfile = new ProfileUserModel();

        //fragmentEditProfile.FragmentEditProfile(this);//lắng nghe du lieu tu Edit profile
        txt_SoluongFollower.setText(SoluongFollower+"");
        txt_SoluongFollowing.setText(SoLuongFollowing+"");
        txt_SoLuongVideo.setText(SoLuongVideo+"");

        imgbtn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(getContext(), imgbtn_setting);
                popupMenu.getMenuInflater().inflate(R.menu.menu_setting, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.iteditprofile:
                                Intent activityedit = new Intent(getActivity(),FragmentEditProfile.class);
                                startActivity(activityedit);
                                break;
                          /*  case R.id.itfavourite:
                                ChuyenFragment(new FragmentDraw());
                                break;*/
                            case R.id.logout:
                                AlertDialog.Builder logout_dialog = new AlertDialog.Builder(new ContextThemeWrapper(getContext(),R.style.Theme_AppCompat_DayNight_NoActionBar));
                                logout_dialog.setMessage("Do you want to log out ?");
                                logout_dialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                    }
                                });
                                logout_dialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        firebaseAuth.signOut();
                                        Intent intentlogin = new Intent(getActivity(), ActivityLogin.class);
                                        startActivity(intentlogin);
                                    }
                                });
                                AlertDialog dialog = logout_dialog.create();
                                dialog.show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_button_setting);
                imgbtn_setting.startAnimation(animation);
            }
        });
        return  root;
    }

    @Override
    public void onStart() {
        super.onStart();
            interfaceProfile = new InterfaceProfile() {
                @Override
                public void getDataProfileInterface(final ProfileUserModel profileUserModel) {
                    txtTenUser.setText(profileUserModel.getNameUser() + "");
                    txtID.setText(profileUserModel.getId() + "");
                    txtSex.setText(profileUserModel.isSex() + "");
                    txtDate.setText(profileUserModel.getNgaySinh() + "");
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("imagedaidien").child(profileUserModel.getId() + ".jpg");
                    storageReference.getBytes(1024*1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                        @Override
                        public void onSuccess(byte[] bytes) {
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            imgAnhdaidien.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Glide.with(getContext()).load(profileUserModel.getPhoto() + "/*.jpeg?height=500").into(imgAnhdaidien);
                        }
                    });

                }
            };
            dataProfile.getDataProfile(interfaceProfile);

        }

}





