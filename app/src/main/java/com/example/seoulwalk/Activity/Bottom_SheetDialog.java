package com.example.seoulwalk.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.seoulwalk.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Bottom_SheetDialog extends BottomSheetDialogFragment {
    View view;

    Button start_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.bottom_sheet_dialog, container, false);
        view.findViewById(R.id.end_btn).setOnClickListener(v -> Toast.makeText(getContext(), "확인", Toast.LENGTH_SHORT).show());
        //view.findViewById(R.id.close_btn).setOnClickListener(v -> dismiss());
        //start_btn = view.findViewById(R.id.start_btn);
        //start_btn.setOnClickListener((View.OnClickListener) this);

        return view;
    }

}
