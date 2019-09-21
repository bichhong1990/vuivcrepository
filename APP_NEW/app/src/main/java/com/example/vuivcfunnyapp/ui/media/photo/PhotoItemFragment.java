package com.example.vuivcfunnyapp.ui.media.photo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.vuivcfunnyapp.R;

public class PhotoItemFragment extends Fragment {

    private PhotoModel photoModelItem;
    static PhotoItemFragment newInstance(PhotoModel photoModel, int position) {
        PhotoItemFragment f = new PhotoItemFragment();
        f.photoModelItem = photoModel;
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_photo_item,container,false);

        TextView tvPhotoTitle = root.findViewById(R.id.tvPhotoTitle);
        ImageView imvPhoto = root.findViewById(R.id.imvPhoto);

        if(photoModelItem != null)
        {
            tvPhotoTitle.setText(photoModelItem.getImageTitle());
            Glide
                    .with(root)
                    .load(photoModelItem.getImageUrl())
                    .fitCenter()
                    .into(imvPhoto);
        }
        return root;
    }
}
