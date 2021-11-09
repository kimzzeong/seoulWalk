package com.example.seoulwalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seoulwalk.R;
import com.example.seoulwalk.data.Dulle_Data;
import com.example.seoulwalk.data.Review_Data;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private ReviewAdapter.OnItemClickListener mListener = null ;
    public void setOnItemClickListener(ReviewAdapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    private ArrayList<Review_Data> mData = null;

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.review_item, parent,false);
        ReviewAdapter.ViewHolder vh = new ReviewAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {

        Review_Data review_data = mData.get(position);
        holder.review_item_nickname.setText(review_data.getReview());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public ReviewAdapter(ArrayList<Review_Data> list) {
        mData = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView review_item_nickname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            review_item_nickname = itemView.findViewById(R.id.review_item_nickname);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if (mListener != null) {
                            mListener.onItemClick(v, pos) ;
                        }
                    }
                }
            });
        }
    }
}
