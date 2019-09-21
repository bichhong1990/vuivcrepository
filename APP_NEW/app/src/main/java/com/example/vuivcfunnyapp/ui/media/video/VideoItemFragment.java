package com.example.vuivcfunnyapp.ui.media.video;

import android.content.Intent;
import android.net.Uri;
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
import com.example.vuivcfunnyapp.ui.media.comment.CommentDialogFragment;

public class VideoItemFragment extends Fragment {

    private VideoModel videoModelItem;
    static VideoItemFragment newInstance(VideoModel videoModel, int position) {
        VideoItemFragment f = new VideoItemFragment();
        f.videoModelItem = videoModel;
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_video_item,container,false);

        TextView tvVideoTitle = root.findViewById(R.id.tvVideoTitle);
        ImageView imvVideo = root.findViewById(R.id.imvVideo);
        TextView tvLikeVideo = root.findViewById(R.id.tvLikeVideo);
        final TextView tvCommentVideo = root.findViewById(R.id.tvCommentVideo);
        final TextView tvShareVideo = root.findViewById(R.id.tvShareVideo);

        if(videoModelItem != null)
        {
            final String videoTitle = videoModelItem.getVideoName();
            String videoImageUrl = videoModelItem.getImageUrl();
            final String videoUrl = videoModelItem.getVideoUrl();
            String videoLike = "" + videoModelItem.getNumberOfLike();
            String videoComment = "" + videoModelItem.getNumberOfComment();
            String videoShare = "" + videoModelItem.getNumberOfShare();

            tvVideoTitle.setText(videoTitle);
            tvLikeVideo.setText(videoLike);
            tvCommentVideo.setText(videoComment);
            tvShareVideo.setText(videoShare);

            Glide.with(root).load(videoImageUrl).fitCenter().into(imvVideo);
            imvVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(videoUrl);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setDataAndType(uri, "video/mp4");
                    startActivity(intent);
                }
            });

            tvShareVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VideoShareDialogFragment videoShareDialogFragment = VideoShareDialogFragment.newInstance("Share video with",
                            R.layout.fragment_video_sharing,videoUrl,videoTitle);
                    videoShareDialogFragment.show(getChildFragmentManager(), "Share video with");
                }
            });

            tvCommentVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CommentDialogFragment commentDialogFragment = CommentDialogFragment.newInstance("Show comments",
                            R.layout.fragment_comment,videoUrl,videoTitle);
                    commentDialogFragment.show(getChildFragmentManager(), "");
                }
            });
        }
        return root;
    }
}
