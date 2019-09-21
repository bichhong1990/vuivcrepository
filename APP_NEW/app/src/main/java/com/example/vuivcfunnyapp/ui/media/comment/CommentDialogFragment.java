package com.example.vuivcfunnyapp.ui.media.comment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuivcfunnyapp.R;

import java.util.ArrayList;

public class CommentDialogFragment extends DialogFragment
{

    public static int resourceLayout;
    public static String video_link, video_name;

    public static CommentDialogFragment newInstance(String data, int resource_Layout,
                                                       String videoLink, String videoName) {
        CommentDialogFragment dialog = new CommentDialogFragment();
        Bundle args = new Bundle();
        args.putString("data", data);
        dialog.setArguments(args);
        resourceLayout = resource_Layout;
        video_link = videoLink;
        video_name = videoName;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView= inflater.inflate(resourceLayout,null, false);

        TextView tvCommentTitle = dialogView.findViewById(R.id.tvCommentTitle);
        RecyclerView recyclerViewCommentList = dialogView.findViewById(R.id.recyclerViewCommentList);
        EditText edtInputComment = dialogView.findViewById(R.id.edtInputComment);

        tvCommentTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ArrayList<CommentModel> listComment = new ArrayList<>();
        listComment.clear();
        listComment.add(new CommentModel(1,1,"","Administrator",
                "abc", null,100));
        listComment.add(new CommentModel(1,1,"","Administrator",
                "abc", null,100));
        listComment.add(new CommentModel(1,1,"","Administrator",
                "abc", null,100));
        listComment.add(new CommentModel(1,1,"","Administrator",
                "abc", null,100));
        listComment.add(new CommentModel(1,1,"","Administrator",
                "abc", null,100));
        listComment.add(new CommentModel(1,1,"","Administrator",
                "abc", null,100));
        listComment.add(new CommentModel(1,1,"","Administrator",
                "abc", null,100));
        recyclerViewCommentList.setAdapter(new CommentAdapter(listComment));
        recyclerViewCommentList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        tvCommentTitle.setText("" + listComment.size() + " comments");
        builder.setView(dialogView);
        return builder.create();
    }
}
