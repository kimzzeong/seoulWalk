package com.example.seoulwalk.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seoulwalk.Activity.ActivityTheme_Nature;
import com.example.seoulwalk.Activity.ActivityTheme_Person;
import com.example.seoulwalk.Activity.ActivityTheme_Story;
import com.example.seoulwalk.Activity.ActivityTheme_Walk;
import com.example.seoulwalk.R;
import com.example.seoulwalk.data.Dulle_theme_Data;

import java.util.ArrayList;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ViewHolder> {
    Context context;


    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private OnItemClickListener mListener = null ;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    private ArrayList<Dulle_theme_Data> mData = null;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.dulle_theme_item, parent,false);
        ThemeAdapter.ViewHolder vh = new ThemeAdapter.ViewHolder(view);
        return vh;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dulle_theme_Data dulle_data = mData.get(position);
        holder.textView.setText(dulle_data.getDulle_theme_name());
//
        holder.constraintLayout.setBackground(dulle_data.getDulle_theme_background());




    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ThemeAdapter(ArrayList<Dulle_theme_Data> list) {
        mData = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ConstraintLayout constraintLayout;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text_forWhat);
            constraintLayout = itemView.findViewById(R.id.theme_picture);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //

                    if (getAdapterPosition() == 0){
                        //액티비티로 이동
                        Intent intent = new Intent(context, ActivityTheme_Person.class);
                        context.startActivity(intent);


                    }else if (getAdapterPosition() == 1){
                        //액티비티로 이동
                        Intent intent = new Intent(context, ActivityTheme_Nature.class);
                        context.startActivity(intent);

                    }else if (getAdapterPosition() == 2){
                        //액티비티로 이동
                        Intent intent = new Intent(context, ActivityTheme_Walk.class);
                        context.startActivity(intent);

                    }else {
                        //액티비티로 이동
                        Intent intent = new Intent(context, ActivityTheme_Story.class);
                        context.startActivity(intent);

                    }
                }
            });
        }
    }
}
