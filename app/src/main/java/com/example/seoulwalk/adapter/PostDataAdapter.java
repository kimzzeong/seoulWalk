package com.example.seoulwalk.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.seoulwalk.R;
import com.example.seoulwalk.data.PostData;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class PostDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //이미지와 유튜브 뷰타입을 위한 상수 선언
    public static final int YOUTUBE_ITEM = 0;
    public static final int IMAGE_EDIT_ITEM = 1;

    String editLinkText;
    String editImageText;
    String cut = null;
    private OnLongClickListener mListener = null ;    private Context context;

    public ArrayList<PostData> getContentsList() {
        return contentsList;
    }

    private ArrayList<PostData> contentsList = null;

    public void setOnLongClickListener(OnLongClickListener listener) {
        this.mListener = listener ;
    }

    public String getEditImageText() {
        return editImageText;
    }

    public String getEditLinkText() {
        return editLinkText;
    }

    public interface OnLongClickListener {
        void onItemLongClick(View v, int position, boolean set) ;
    }

    public PostDataAdapter(Context context, ArrayList<PostData> contentsList){
        this.context = context;
        this.contentsList = contentsList;
    }

    @Override
    public int getItemViewType(int position) {
        if(contentsList.get(position).getYoutubeData() == null){
            return IMAGE_EDIT_ITEM;
        }else{
            return YOUTUBE_ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(viewType == YOUTUBE_ITEM){
            view = inflater.inflate(R.layout.write_post_youtube_item, parent, false);
            return new PostDataViewHolder_youtube(view);
        }else if(viewType == IMAGE_EDIT_ITEM){
            view = inflater.inflate(R.layout.write_post_image_edit_item, parent, false);
            return new PostDataViewHolder_image_edit(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PostData item = contentsList.get(position);

        if(holder instanceof PostDataViewHolder_image_edit){
            Glide.with(context).load(item.getImageData()).into(((PostDataViewHolder_image_edit) holder).postImage);
            editImageText = null;
            ((PostDataViewHolder_image_edit) holder).postEdit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    editImageText = s.toString();
                    item.setEditData(getEditImageText());

                }
            });
        }else if(holder instanceof PostDataViewHolder_youtube){
            ((PostDataViewHolder_youtube) holder).link.setText(item.getYoutubeData());
            if(item.getYoutubeData().contains(".be/")){
                cut = "be/";
            }else if(item.getYoutubeData().contains("v=")){
                cut = "v=";
            }

            String[] linkId = item.getYoutubeData().split(cut);
            String youtubeId = linkId[1];
            Log.e("링크아이디 확인",youtubeId);
            ((PostDataViewHolder_youtube) holder).youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(YouTubePlayer youTubePlayer) {
                    super.onReady(youTubePlayer);
                    youTubePlayer.cueVideo(youtubeId,0);
                }
            });
            editLinkText = null;
            ((PostDataViewHolder_youtube) holder).youtubeEdit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    editLinkText = s.toString();
                    item.setYoutubeEdit(getEditLinkText());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contentsList.size();
    }

    public class PostDataViewHolder_image_edit extends RecyclerView.ViewHolder {

        ImageView postImage;
        EditText postEdit;
        public PostDataViewHolder_image_edit(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() { //클릭시 삭제
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        image_remove_dialog(pos);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() { //롱클릭시 수정
                @Override
                public boolean onLongClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if (mListener != null) {
                            mListener.onItemLongClick(v, pos,true);
                            //image_set_dialog(pos);
                        }
                    }
                    return false;
                }
            });

            this.postImage = itemView.findViewById(R.id.post_image_data);
            this.postEdit = itemView.findViewById(R.id.post_edit_data);

        }
    }

    public class PostDataViewHolder_youtube extends RecyclerView.ViewHolder {

        YouTubePlayerView youTubePlayerView;
        TextView link;
        EditText youtubeEdit;

        public PostDataViewHolder_youtube(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() { //클릭시 삭제
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        youtube_remove_dialog(pos);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() { //롱클릭시 수정
                @Override
                public boolean onLongClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if (mListener != null) {
                            mListener.onItemLongClick(v, pos,false);
                            //youtube_set_dialog(pos);
                        }
                    }
                    return false;
                }
            });

            this.youTubePlayerView = itemView.findViewById(R.id.youtube_player_view);
            //((ActivityWritePost) context).getLifecycle().addObserver(youTubePlayerView);
            this.link = itemView.findViewById(R.id.linkStr);
            this.youtubeEdit = itemView.findViewById(R.id.editStr);
        }
    }

    public void image_remove_dialog(int pos){
        new AlertDialog.Builder(context) // TestActivity 부분에는 현재 Activity의 이름 입력.
                .setMessage("이미지를 삭제하시겠습니까?")     // 제목 부분 (직접 작성)
                .setCancelable(false)
                .setPositiveButton("네", new DialogInterface.OnClickListener() {      // 버튼1 (직접 작성)
                    public void onClick(DialogInterface dialog, int which){
                        contentsList.remove(pos);
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {     // 버튼2 (직접 작성)
                    public void onClick(DialogInterface dialog, int which){
                        return;
                    }
                })
                .show();
    }

    public void youtube_remove_dialog(int pos){
        new AlertDialog.Builder(context) // TestActivity 부분에는 현재 Activity의 이름 입력.
                .setMessage("링크를 삭제하시겠습니까?")     // 제목 부분 (직접 작성)
                .setCancelable(false)
                .setPositiveButton("네", new DialogInterface.OnClickListener() {      // 버튼1 (직접 작성)
                    public void onClick(DialogInterface dialog, int which){
                        contentsList.remove(pos);
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton("아니오", new DialogInterface.OnClickListener() {     // 버튼2 (직접 작성)
                    public void onClick(DialogInterface dialog, int which){
                        return;
                    }
                })
                .show();
    }
}
