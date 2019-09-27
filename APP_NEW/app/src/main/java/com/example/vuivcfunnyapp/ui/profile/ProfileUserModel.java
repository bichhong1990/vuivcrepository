package com.example.vuivcfunnyapp.ui.profile;



import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.vuivcfunnyapp.ui.profile.profile_interface.InterfaceProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProfileUserModel implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("numVideo")
    @Expose
    private long numVideo;

    @SerializedName("numFollower")
    @Expose
    private long numFollower;

    @SerializedName("numFollowing")
    @Expose
    private long numFollowing;

    @SerializedName("sex")
    @Expose
    private String sex;

    @SerializedName("nameUser")
    @Expose
    private String nameUser;

    @SerializedName("ngaySinh")
    @Expose
    private String ngaySinh;

    @SerializedName("photo")
    @Expose
    private String photo;

    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    public ProfileUserModel() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ProfileUserModel");
    }

    public ProfileUserModel(String id, long numVideo, long numFollower, long numFollowing, String sex, String nameUser, String ngaySinh, String photo) {
        this.id = id;
        this.numVideo = numVideo;
        this.numFollower = numFollower;
        this.numFollowing = numFollowing;
        this.sex = sex;
        this.nameUser = nameUser;
        this.ngaySinh = ngaySinh;
        this.photo = photo;

    }

    protected ProfileUserModel(Parcel in) {
        id = in.readString();
        numVideo = in.readLong();
        numFollower = in.readLong();
        numFollowing = in.readLong();
        sex = in.readString();
        nameUser = in.readString();
        ngaySinh = in.readString();
        photo = in.readString();
        firebaseUser = in.readParcelable(FirebaseUser.class.getClassLoader());
    }

    public static final Creator<ProfileUserModel> CREATOR = new Creator<ProfileUserModel>() {
        @Override
        public ProfileUserModel createFromParcel(Parcel in) {
            return new ProfileUserModel(in);
        }

        @Override
        public ProfileUserModel[] newArray(int size) {
            return new ProfileUserModel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getNumVideo() {
        return numVideo;
    }

    public void setNumVideo(long numVideo) {
        this.numVideo = numVideo;
    }

    public long getNumFollower() {
        return numFollower;
    }

    public void setNumFollower(long numFollower) {
        this.numFollower = numFollower;
    }

    public long getNumFollowing() {
        return numFollowing;
    }

    public void setNumFollowing(long numFollowing) {
        this.numFollowing = numFollowing;
    }

    public ProfileUserModel(String id, String sex, String nameUser, String photo) {
        this.id = id;
        this.sex = sex;
        this.nameUser = nameUser;
        this.photo = photo;
    }

    public String isSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void getDataProfile(final InterfaceProfile interfaceProfile)
    {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot userdata = dataSnapshot.child(firebaseUser.getUid());
                ProfileUserModel profileUserModel = userdata.getValue(ProfileUserModel.class);
                    interfaceProfile.getDataProfileInterface(profileUserModel);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        databaseReference.addValueEventListener(valueEventListener);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeLong(numFollower);
        parcel.writeLong(numVideo);
        parcel.writeLong(numFollowing);
        parcel.writeString(nameUser);
        parcel.writeString(sex);
        parcel.writeString(ngaySinh);
        parcel.writeString(photo);
    }
}
