package com.example.seoulwalk.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.seoulwalk.R;
import com.example.seoulwalk.retrofit.ApiClient;
import com.example.seoulwalk.retrofit.ApiInterface;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCourseAdapter extends RecyclerView.Adapter<DetailCourseAdapter.DetailCourseViewHolder> {

    private final String TAG = this.getClass().getSimpleName();
    private final Context context;
    private final List<Video> lists;
    private final ItemClickListener itemClickListener;
    private DetailCourseAdapter.onClickListener onClickListener;

    int idInt, yearFormatInt, viewInt;
    String titleString, descriptionString, dateString, videoPathString, locationString, userString, monthDateString, thumbnailString;
    Uri videoUri;

    public interface onClickListener {
        void onAddWatchLaterClick(Video video, int pos);
    }

    public void setOnClickListener(DetailCourseAdapter.onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public DetailCourseAdapter(Context context, List<Video> lists, ItemClickListener itemClickListener) {
        this.context = context;
        this.lists = lists;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public DetailCourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_detail_item, parent, false);
        return new DetailCourseViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailCourseViewHolder holder, int position) {
        Video video = lists.get(position);
        holder.textViewTitle.setText(video.getTitle());
        holder.textViewDatetime.setText(video.getDate());
        holder.textViewDuration.setText(video.getDuration());
        holder.textViewViews.setText(String.valueOf(video.getViews()).concat("회"));

        // 영상 업로드 할 때 썸네일 이미지도 같이 업로드하는 걸로 바꿈
        Glide.with(context).load(ApiClient.BASE_URL.concat(video.getThumbnail())).into(holder.thumbnailImageView);

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class DetailCourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ConstraintLayout constraintLayout;
        public TextView textViewOriginalCourseName, textViewDetailCourseName, textViewDuration, textViewViews;
        public ImageView thumbnailImageView, moreIcon;
        ItemClickListener itemClickListener;

        public DetailCourseViewHolder(@NonNull View view, ItemClickListener itemClickListener) {
            super(view);
            constraintLayout = view.findViewById(R.id.constraint_course_detail_item);
            thumbnailImageView = view.findViewById(R.id.course_detail_item_image);
            textViewOriginalCourseName = view.findViewById(R.id.course_detail_item_original_course_name);
            textViewDetailCourseName = view.findViewById(R.id.course_detail_item_name);

            moreIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickMoreIcon();
                }
            });

            this.itemClickListener = itemClickListener;
            constraintLayout.setOnClickListener(this);
        }

        private void clickMoreIcon() {
            showMoreBottomDialog();
        }

        private void showMoreBottomDialog() {
            final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
            bottomSheetDialog.setContentView(R.layout.channel_video_more_bottom_sheet_dialog_layout);

            CardView later = bottomSheetDialog.findViewById(R.id.cardview_bottom_sheet_dialog_channel_later);
            CardView addPlaylist = bottomSheetDialog.findViewById(R.id.cardview_bottom_sheet_dialog_channel_add_playlist);
            CardView share = bottomSheetDialog.findViewById(R.id.cardview_bottom_sheet_dialog_channel_share);
            CardView close = bottomSheetDialog.findViewById(R.id.cardview_bottom_sheet_dialog_channel_close);

            // 나중에 볼 동영상에 저장
            later.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, "나중에 볼 동영상에 저장 클릭: " + lists.get(getBindingAdapterPosition()).getTitle() + " / id: " + lists.get(getBindingAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();

                    onClickListener.onAddWatchLaterClick(lists.get(getBindingAdapterPosition()), getBindingAdapterPosition());

                    bottomSheetDialog.dismiss();
                }
            });

            // 재생목록에 저장
            addPlaylist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "재생목록에 저장 클릭: " + lists.get(getBindingAdapterPosition()).getTitle() + " / id: " + lists.get(getBindingAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                }
            });

            // 공유
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "공유 클릭: " + lists.get(getBindingAdapterPosition()).getTitle() + " / id: " + lists.get(getBindingAdapterPosition()).getId(), Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                }
            });

            // 닫기
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomSheetDialog.dismiss();
                }
            });

            bottomSheetDialog.show();
        }

        @Override
        public void onClick(View view) {
//            itemClickListener.onItemClick(view, getAdapterPosition());

            // getAdapterPosition() is deprecated
            int pos = getBindingAdapterPosition();
            idInt = lists.get(getBindingAdapterPosition()).getId();
            titleString = lists.get(getBindingAdapterPosition()).getTitle();
            descriptionString = lists.get(getBindingAdapterPosition()).getDescription();
            dateString = lists.get(getBindingAdapterPosition()).getDate();
            locationString = lists.get(getBindingAdapterPosition()).getLocation();
            userString = lists.get(getBindingAdapterPosition()).getUser();
            monthDateString = lists.get(getBindingAdapterPosition()).getMonthDate();
            thumbnailString = lists.get(getBindingAdapterPosition()).getThumbnail();
            yearFormatInt = lists.get(getBindingAdapterPosition()).getYearFormat();
            viewInt = lists.get(getBindingAdapterPosition()).getViews();
            Log.d(TAG, "userString: " + userString);
            videoPathString = ApiClient.BASE_URL.concat(lists.get(getBindingAdapterPosition()).getPath());
            videoUri = Uri.parse(videoPathString);

            if (pos != RecyclerView.NO_POSITION) {
//                Toast.makeText(view.getContext(œ), "pos: " + pos + " / title: " + titleString, Toast.LENGTH_SHORT).show();
                Log.d("MyVideoAdapter", "onClick: videoPathString: " + videoPathString);
                Context context = view.getContext();
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("id", idInt);
                intent.putExtra("title", titleString);
                intent.putExtra("description", descriptionString);
                intent.putExtra("date", dateString);
                intent.putExtra("videoUri", videoUri);
                intent.putExtra("location", locationString);
                intent.putExtra("email", userString);
                intent.putExtra("yearFormat", yearFormatInt);
                intent.putExtra("monthDate", monthDateString);
                intent.putExtra("views", viewInt);
                Log.d("MyVideoAdapter", "intent id: " + idInt);
                Log.d("MyVideoAdapter", "intent titleString: " + titleString);
                Log.d("MyVideoAdapter", "intent descriptionString: " + descriptionString);
                Log.d("MyVideoAdapter", "intent dateString: " + dateString);
                Log.d("MyVideoAdapter", "intent videoUri: " + videoUri);
                Log.d("MyVideoAdapter", "intent locationString: " + locationString);
                Log.d("MyVideoAdapter", "intent email: " + userString);
                Log.d("MyVideoAdapter", "intent yearFormatInt: " + yearFormatInt);
                Log.d("MyVideoAdapter", "intent monthDateString: " + monthDateString);
                Log.d("MyVideoAdapter", "intent viewInt: " + viewInt);
                context.startActivity(intent);
            }
        }
    }

    private void setMainVideo(int videoId, String email, String title, String date, int views, String thumbnail, Context context) {
        Log.d(TAG, "setMainVideo()");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<String> call = apiInterface.setChannelMainVideo(videoId, email);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d(TAG, "setMainVideo(): onResponse(): successful");
                    parseSetMainVideoResult(response.body(), videoId, title, date, views, thumbnail, context);
                } else {
                    Log.d(TAG, "setMainVideo(): onResponse(): not successful");
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.d(TAG, "setMainVideo(): onFailure : " + t.getMessage());
            }
        });
    }

    private void parseSetMainVideoResult(String response, int videoId, String title, String date, int views, String thumbnail, Context context) {
        Log.d(TAG, "parseSetMainVideoResult()");
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString("status").equals("true")) {
                Log.d(TAG, "parseSetMainVideoResult(): message: " + jsonObject.getString("message"));

                Intent intent = new Intent(context, MyProfileActivity.class);
                intent.putExtra("id", videoId);
                intent.putExtra("title", title);
                intent.putExtra("date", date);
                intent.putExtra("views", views);
                intent.putExtra("thumbnail", thumbnail);
                Log.d(TAG, "intent id: " + videoId);
                Log.d(TAG, "intent titleString: " + title);
                Log.d(TAG, "intent dateString: " + date);
                Log.d(TAG, "intent viewInt: " + views);
                Log.d(TAG, "intent thumbnail: " + thumbnail);
                context.startActivity(intent);

            } else {
                Log.d(TAG, "parseSetMainVideoResult(): " + jsonObject.getString("message"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void displaySnackBar() {
        Log.d(TAG, "///////////////////////////");
        Log.d(TAG, "displaySnackBar()");
        Log.d(TAG, "///////////////////////////");
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("display_deleted_my_video_snack_bar"));
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}