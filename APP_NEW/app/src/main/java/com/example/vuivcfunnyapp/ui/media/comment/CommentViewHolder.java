package com.example.vuivcfunnyapp.ui.media.comment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuivcfunnyapp.R;

public class CommentViewHolder extends RecyclerView.ViewHolder {

    TextView tvCommentItemUser,tvCommentItemContent;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);
        tvCommentItemUser = itemView.findViewById(R.id.tvCommentItemUser);
        tvCommentItemContent = itemView.findViewById(R.id.tvCommentItemContent);
    }

    public void SetData(CommentModel commentModel)
    {
        if(commentModel != null)
        {
            tvCommentItemUser.setText(commentModel.getUserName());
            tvCommentItemContent.setText(commentModel.getContent());
        }
    }

}
