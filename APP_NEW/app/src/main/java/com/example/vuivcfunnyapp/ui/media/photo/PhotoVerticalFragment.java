package com.example.vuivcfunnyapp.ui.media.photo;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vuivcfunnyapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoVerticalFragment extends Fragment {

    List<PhotoModel> listPhoto = new ArrayList<>();
    PhotoVerticalPagerAdapter photoVerticalPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vertical_viewpager,container,false);
        final VerticalViewPager verticalviewpager = root.findViewById(R.id.verticalviewpager);

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference =    mFirebaseDatabase.getReference().child("PhotoModel");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    int id = Integer.parseInt(singleSnapshot.child("id").getValue().toString());
                    String title = singleSnapshot.child("imageTitle").getValue().toString();
                    String imageUrl = singleSnapshot.child("imageUrl").getValue().toString();
                    int userId = Integer.parseInt(singleSnapshot.child("userId").getValue().toString());
                    listPhoto.add(new PhotoModel(id,title,imageUrl,userId));
                }

                if(listPhoto != null && listPhoto.size() > 0)
                {
                    photoVerticalPagerAdapter  = new PhotoVerticalPagerAdapter(getChildFragmentManager(),listPhoto);
                    verticalviewpager.setAdapter(photoVerticalPagerAdapter);
                    photoVerticalPagerAdapter.notifyDataSetChanged();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        ApiUtils.GetMediaService().GetPhotoList().enqueue(new Callback<List<PhotoModel>>() {
//            @Override
//            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
//                if(response.isSuccessful()){
//                    listPhoto.addAll(response.body());
//                    if(listPhoto != null && listPhoto.size() > 0)
//                    {
//                        photoVerticalPagerAdapter  = new PhotoVerticalPagerAdapter(getChildFragmentManager(),listPhoto);
//                        verticalviewpager.setAdapter(photoVerticalPagerAdapter);
//                        photoVerticalPagerAdapter.notifyDataSetChanged();
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
//                Log.d("Error","" + t.getMessage());
//            }
//        });



        return root;
    }


}
