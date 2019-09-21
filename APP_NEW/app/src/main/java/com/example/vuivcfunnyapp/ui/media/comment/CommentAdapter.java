package com.example.vuivcfunnyapp.ui.media.comment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuivcfunnyapp.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    ArrayList<CommentModel> commentModelArrayList =  new ArrayList<>();
    public CommentAdapter(ArrayList<CommentModel> commentModelArrayList) {
        this.commentModelArrayList = commentModelArrayList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_comment_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.SetData(commentModelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return commentModelArrayList.size();
    }
}
