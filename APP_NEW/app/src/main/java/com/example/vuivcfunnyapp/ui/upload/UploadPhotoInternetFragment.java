package com.example.vuivcfunnyapp.ui.upload;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.vuivcfunnyapp.R;
import com.example.vuivcfunnyapp.ui.media.photo.PhotoModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.UUID;

public class UploadPhotoInternetFragment extends Fragment {

    String firstText, urlUpload = "";
    DatabaseReference url;
    FirebaseDatabase database;
    long totalCount = 0;
    EditText edtPhotoUrlInternet,edtCaptionUploadPhotoInternet;
    ImageView imvPhotoUploadInternet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       final View root = inflater.inflate(R.layout.fragment_upload_photo_internet,container,false);
         edtPhotoUrlInternet = root.findViewById(R.id.edtPhotoUrlInternet);
         edtCaptionUploadPhotoInternet = root.findViewById(R.id.edtCaptionUploadPhotoInternet);
          imvPhotoUploadInternet = root.findViewById(R.id.imvPhotoUploadInternet);
        Button btnPreviewPhotoInternet = root.findViewById(R.id.btnPreviewPhotoInternet);
        Button btnPhotoUploadInternet = root.findViewById(R.id.btnPhotoUploadInternet);

        database  = FirebaseDatabase.getInstance();
        url = database.getInstance().getReference().child("PhotoModel");

        btnPreviewPhotoInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urlUpload = edtPhotoUrlInternet.getText().toString();
                firstText = edtCaptionUploadPhotoInternet.getText().toString();

                if(urlUpload != null && !urlUpload.isEmpty())
                {
                    Glide.with(root).load(urlUpload).into(imvPhotoUploadInternet);
                }
            }
        });

        btnPhotoUploadInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadImage(urlUpload);
            }
        });
       return root;
    }

    private void DownloadImage(final String downloadUrl)
    {
        if(!downloadUrl.isEmpty() && downloadUrl != null)
        {
            url.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    totalCount = dataSnapshot.getChildrenCount();
                    url.child("" + totalCount).setValue(new PhotoModel((int)totalCount + 1,firstText,downloadUrl,1));
                    Toast.makeText(getContext(), "Upload successfull !!!", Toast.LENGTH_SHORT).show();
                    edtCaptionUploadPhotoInternet.setText("");
                    edtPhotoUrlInternet.setText("");
                    imvPhotoUploadInternet.setImageResource(R.drawable.vuivc_no_image_available);

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getContext(), "Cancel !!!", Toast.LENGTH_SHORT).show();
                    edtCaptionUploadPhotoInternet.setText("");
                    edtPhotoUrlInternet.setText("");
                    imvPhotoUploadInternet.setImageResource(R.drawable.vuivc_no_image_available);
                }
            });
        }
        else
        {
            Toast.makeText(getContext()
                    , "Not found link download !!!", Toast.LENGTH_SHORT).show();
            edtCaptionUploadPhotoInternet.setText("");
            edtPhotoUrlInternet.setText("");
            imvPhotoUploadInternet.setImageResource(R.drawable.vuivc_no_image_available);
        }
    }

}
