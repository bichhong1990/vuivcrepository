package com.example.vuivcfunnyapp.ui.upload;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.vuivcfunnyapp.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import static android.app.Activity.RESULT_OK;

public class UploadPhotoFragment extends Fragment {

    private static final String IMAGE_DIRECTORY = "/vuivc";
    private int GALLERY = 1, CAMERA = 2;
    String photoName = new Date().toString();
    ImageView imvPhotoUpload;
    EditText edtCaptionUploadPhoto;
    TextView tvKQ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upload_photo,container,false);

          imvPhotoUpload = root.findViewById(R.id.imvPhotoUpload);
              Button btnPhotoUploadGallery = root.findViewById(R.id.btnPhotoUploadGallery);
          edtCaptionUploadPhoto = root.findViewById(R.id.edtCaptionUploadPhoto);
        tvKQ = root.findViewById(R.id.tvKQ);

        imvPhotoUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY);
            }
        });

        btnPhotoUploadGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




//               Bitmap bm = drawTextToBitmap(getContext(),imvPhotoUpload.getId(),edtCaptionUploadPhoto.getText().toString());
//               imvPhotoUpload.setImageBitmap(bm);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super method removed
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY) {
                Bitmap bm = DrawTextToImage(data.getData(),edtCaptionUploadPhoto);
                SaveImage(bm);
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
                paintText.setColor(Color.WHITE);
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

    private void SaveImage(Bitmap bmImg)
    {
        String root = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();

        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bmImg.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
// Tell the media scanner about the new file so that it is
// immediately available to the user.
        MediaScannerConnection.scanFile(getContext(), new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
    }



    }
