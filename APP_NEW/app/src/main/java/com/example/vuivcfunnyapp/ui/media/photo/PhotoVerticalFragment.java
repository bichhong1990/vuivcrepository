package com.example.vuivcfunnyapp.ui.media.photo;

import android.os.Bundle;
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


        ApiUtils.GetMediaService().GetPhotoList().enqueue(new Callback<List<PhotoModel>>() {
            @Override
            public void onResponse(Call<List<PhotoModel>> call, Response<List<PhotoModel>> response) {
                if(response.isSuccessful()){
                    listPhoto = response.body();
                    if(listPhoto != null && listPhoto.size() > 0)
                    {
                        photoVerticalPagerAdapter  = new PhotoVerticalPagerAdapter(getChildFragmentManager(),listPhoto);
                        verticalviewpager.setAdapter(photoVerticalPagerAdapter);
                        photoVerticalPagerAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<PhotoModel>> call, Throwable t) {
                Log.d("Error","" + t.getMessage());
            }
        });

//        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
//        DatabaseReference databaseReference =    mFirebaseDatabase.getReference().child("PhotoModel");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
//                   // PhotoModel photoModelFirebase = childDataSnapshot.getValue(PhotoModel.class);
//                    //listPhoto.add(photoModelFirebase);
//                    int id = Integer.parseInt(childDataSnapshot.child("id").getValue().toString());
//                    String title = childDataSnapshot.child("imageTitle").getValue().toString();
//                    String imageUrl = childDataSnapshot.child("imageUrl").getValue().toString();
//                    int userId = Integer.parseInt(childDataSnapshot.child("userId").getValue().toString());
//                    listPhoto.add(new PhotoModel(id,title,imageUrl,userId));
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });



        return root;
    }


}
