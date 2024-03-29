package com.example.vuivcfunnyapp.ui.upload;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.vuivcfunnyapp.MainActivity;
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

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class UploadPhotoFragment extends Fragment {

    private int GALLERY = 1, CAMERA = 2;
    ImageView imvPhotoUpload;
    EditText edtCaptionUploadPhoto;
    FirebaseStorage storage;
    StorageReference storageReference;
    Uri filePath;
    Bitmap bm;
    Intent pictureIntent;
    DatabaseReference url;
    ArrayList<PhotoModel> photoList = new ArrayList<>();
    FirebaseDatabase database;
    long totalCount = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upload_photo,container,false);

          imvPhotoUpload = root.findViewById(R.id.imvPhotoUpload);
          Button btnTakePhotoFromGallery = root.findViewById(R.id.btnTakePhotoFromGallery);
          Button btnPhotoUploadGallery = root.findViewById(R.id.btnPhotoUploadGallery);
          Button btnTakePhotoFromCamera = root.findViewById(R.id.btnTakePhotoFromCamera);

            edtCaptionUploadPhoto = root.findViewById(R.id.edtCaptionUploadPhoto);
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();
            database  = FirebaseDatabase.getInstance();
            url = database.getInstance().getReference().child("PhotoModel");

        btnTakePhotoFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtCaptionUploadPhoto.getText().toString() != null && !edtCaptionUploadPhoto.getText().toString().isEmpty())
                {
                    Intent intentGallery = new Intent();
                    intentGallery.setType("image/*");
                    intentGallery.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intentGallery, "Take a photo"), GALLERY);
                }
                else
                {
                    Toast.makeText(getContext(), "Chưa nhập tiêu đề cho ảnh chế", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnTakePhotoFromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 if(edtCaptionUploadPhoto.getText().toString() != null && !edtCaptionUploadPhoto.getText().toString().isEmpty())
                {
                    pictureIntent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE
                    );
                    if(pictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                        if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
                        {
                            requestPermissions(new String[]{Manifest.permission.CAMERA},
                                    CAMERA);
                        }
                        else {
                            startActivityForResult(pictureIntent, CAMERA);
                        }
                    }
                }
                else
                {
                    Toast.makeText(getContext(), "Chưa nhập tiêu đề cho ảnh chế", Toast.LENGTH_SHORT).show();
                }
            }
        });

            btnPhotoUploadGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(filePath == null && (edtCaptionUploadPhoto.getText().toString().isEmpty() || edtCaptionUploadPhoto.getText().toString() == null))
                    {
                        Toast.makeText(getContext(), "Chưa có tiêu đề hoặc chưa chọn ảnh", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        uploadBtmapImage(bm);
                    }
                }
            });
        return root;
    }

    private void DownloadImage(String downloadUrl, final String caption)
    {
        storageReference.child(downloadUrl)
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                // Got the download URL for 'photos/profile.png'
                final String getURL = uri.toString();
                url.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        totalCount = dataSnapshot.getChildrenCount();
                        //int id = (int) System.currentTimeMillis() / 1000;
                        url.child("" + totalCount).setValue(new PhotoModel((int)totalCount + 1,caption,getURL,1));
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Toast.makeText(getContext()
                        , "Failed downloaded"+exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        if (requestCode == GALLERY && resultCode == RESULT_OK ) {

            filePath = data.getData();
            String firstText = edtCaptionUploadPhoto.getText().toString();
            bm = DrawTextToImage(data.getData(),edtCaptionUploadPhoto);
            imvPhotoUpload.setImageBitmap(bm);
        }
        else if(requestCode == CAMERA && resultCode == RESULT_OK)
        {
            if (data != null && data.getExtras() != null) {
                filePath = data.getData();
                String firstText = edtCaptionUploadPhoto.getText().toString();
                bm = DrawTextToImage((Bitmap) data.getExtras().get("data"),firstText);
                imvPhotoUpload.setImageBitmap(bm);
            }
        }
    }

    private Bitmap DrawTextToImage(Uri source1, EditText edt) {
        Bitmap bm1 = null;
        Bitmap newBitmap = null;

        try {
            bm1 = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(source1));

            Bitmap.Config config = bm1.getConfig();
            if (config == null) {
                config = Bitmap.Config.ARGB_8888;
            }

            newBitmap = Bitmap.createBitmap(bm1.getWidth(), bm1.getHeight(), config);
            Canvas newCanvas = new Canvas(newBitmap);

            newCanvas.drawBitmap(bm1, 0, 0, null);
            newCanvas.translate(0, 600);
            String captionString = edt.getText().toString();
            if (captionString != null) {

                Paint paintText = new Paint(Paint.ANTI_ALIAS_FLAG);
                paintText.setColor(Color.GREEN);
                paintText.setTextSize(50);
                paintText.setStyle(Paint.Style.FILL);
                paintText.setShadowLayer(10f, 10f, 10f, Color.BLACK);


                Rect rectText = new Rect();
                paintText.getTextBounds(captionString, 0, captionString.length(), rectText);

                newCanvas.drawText(captionString,
                        0, rectText.height(), paintText);

            } else {
                Toast.makeText(getContext().getApplicationContext(),
                        "caption empty!",
                        Toast.LENGTH_LONG).show();
            }
            newCanvas.save();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return newBitmap;
    }

    private Bitmap DrawTextToImage(Bitmap toEdit, String text){

        Bitmap dest = toEdit.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(dest);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);  //set the look
        //paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        paint.setTextSize(50);
        paint.setStyle(Paint.Style.FILL);
        paint.setShadowLayer(2.0f, 1.0f, 1.0f, Color.BLACK);

        int pictureHeight = dest.getHeight();
        paint.setTextSize(pictureHeight * .04629f);

        canvas.drawText(text , dest.getWidth()/2,  100, paint);
        return dest;
    }

    private void uploadImage(Uri filePath) {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            String imgDownloadUrl = "vuivcimages/"+ UUID.randomUUID().toString() + ".jpg";
            StorageReference ref = storageReference.child(imgDownloadUrl);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext()
                                    , "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    public void uploadBtmapImage(Bitmap bitmap) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

       final String imgDownloadUrl = "vuivcimages/"+ UUID.randomUUID().toString() + ".jpg";
       StorageReference imagesRef = storageReference.child(imgDownloadUrl);

        UploadTask uploadTask = imagesRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                progressDialog.dismiss();
                Toast.makeText(getContext()
                        , "Failed uploaded"+exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                        .getTotalByteCount());
                progressDialog.setMessage("Uploading  "+(int)progress+"%");
            }
        })
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                DownloadImage(imgDownloadUrl,edtCaptionUploadPhoto.getText().toString());
                Toast.makeText(getContext(), "Uploaded successfull", Toast.LENGTH_SHORT).show();
                edtCaptionUploadPhoto.setText("");
                imvPhotoUpload.setImageResource(R.drawable.vuivc_no_image_available);
                filePath = null;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                startActivityForResult(pictureIntent, CAMERA);
            }
        }
    }
}
