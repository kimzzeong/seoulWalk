package com.example.seoulwalk;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class YoutubeDialog extends AppCompatDialogFragment {
    EditText link_input;
    YoutubeDialogInterface youtubeInterface;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.youtube_dialog_background,null);
        builder.setView(view)
                .setTitle("링크 입력")
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String link = link_input.getText().toString();
                        youtubeInterface.applyText(link);
                    }
                });

        link_input = view.findViewById(R.id.put_text);
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        youtubeInterface = (YoutubeDialogInterface) context;
    }

    public interface YoutubeDialogInterface{
        void applyText(String link);
    }
}
