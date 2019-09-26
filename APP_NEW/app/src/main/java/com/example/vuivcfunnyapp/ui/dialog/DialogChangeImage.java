package com.example.vuivcfunnyapp.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import com.example.vuivcfunnyapp.R;
import com.example.vuivcfunnyapp.ui.profile.FragmentEditProfile;


public class DialogChangeImage extends DialogFragment {
    Button btn_CameraShot,btn_takefromphone;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater =getActivity().getLayoutInflater();
        View view =layoutInflater.inflate(R.layout.dialog_doianh,null);
        builder.setView(view).setTitle("Option");
        btn_CameraShot = view.findViewById(R.id.btn_CameraShot);
        btn_takefromphone = view.findViewById(R.id.btn_takefromphone);
        btn_CameraShot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                captureImage();
                dismiss();
            }
        });
        btn_takefromphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
                dismiss();
            }
        });

        return builder.create();
    }

    private void choosePicture() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        getActivity().startActivityForResult(pickPhoto , 200);//one can be replaced with any action code
    }

    private void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getActivity().startActivityForResult(intent,300);
    }

}
