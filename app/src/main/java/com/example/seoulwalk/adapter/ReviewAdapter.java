package com.example.seoulwalk.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.seoulwalk.R;
import com.example.seoulwalk.data.Review_Data;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    //뷰페이저
    private String[] images = new String[]{
            "https://gil.seoul.go.kr/walk/images/sub/sub1_1_img04.jpg", "https://gil.seoul.go.kr/walk/images/sub/sub3_img.jpg", "https://gil.seoul.go.kr/walk/images/sub/sub1_1_img01.jpg", "https://gil.seoul.go.kr/walk/images/sub/sub1_1_img02.jpg"
           };

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    private ReviewAdapter.OnItemClickListener mListener = null ;
    public void setOnItemClickListener(ReviewAdapter.OnItemClickListener listener) {
        this.mListener = listener ;
    }

    private ArrayList<Review_Data> mData = null;
    private Context context;

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //context = parent.getContext();
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

    public ReviewAdapter(ArrayList<Review_Data> list, Context context) {
        mData = list;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView review_item_nickname;
        ImageView[] indicators;
        private ViewPager2 sliderViewPager;
        private LinearLayout layoutIndicator;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            review_item_nickname = itemView.findViewById(R.id.review_item_nickname);

            sliderViewPager = itemView.findViewById(R.id.sliderViewPager_review);
            layoutIndicator = itemView.findViewById(R.id.layoutIndicators_review);

            sliderViewPager.setOffscreenPageLimit(1);
            sliderViewPager.setAdapter(new ImageSliderAdapter(context, images));

            sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    setCurrentIndicator(position,layoutIndicator);
                }
            });

            setupIndicators(images.length,layoutIndicator,indicators);

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

    private void setCurrentIndicator(int position,LinearLayout layoutIndicator) {
        Log.e("setCurrentIndicator","setCurrentIndicator");
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        context,
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        context,
                        R.drawable.bg_indicator_inactive
                ));
            }
        }
    }


    private void setupIndicators(int count,LinearLayout layoutIndicator, ImageView[] indicators) {
Log.e("setupIndicators","setupIndicators");
        indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(context);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(context,
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0,layoutIndicator);
    }
}
