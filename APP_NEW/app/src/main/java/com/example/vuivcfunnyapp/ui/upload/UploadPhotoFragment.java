package com.example.vuivcfunnyapp.ui.upload;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vuivcfunnyapp.R;

public class UploadPhotoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upload_photo,container,false);

        ImageView imvPhotoUpload = root.findViewById(R.id.imvPhotoUpload);
        Button btnPhotoUploadGallery = root.findViewById(R.id.btnPhotoUploadGallery);
        Button btnPhotoUploadCamera = root.findViewById(R.id.btnPhotoUploadCamera);
        EditText edtCaptionUploadPhoto = root.findViewById(R.id.edtCaptionUploadPhoto);


        return root;
    }
}
