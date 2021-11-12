package com.example.seoulwalk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.seoulwalk.R;
import com.example.seoulwalk.data.CommunityData;
import com.example.seoulwalk.data.PostData;

import java.util.ArrayList;

public class CommunityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TEXT_ITEM = 0;
    public static final int IMAGE_ITEM = 1;
    Context context;
    public ArrayList<CommunityData> post_list = null;
    private ArrayList<PostData> viewtype = new ArrayList<>();
    private int position;
    private OnItemClickListener mListener = null ;
    public CommunityAdapter(ArrayList<CommunityData> post_list,Context context) {
        this.post_list = post_list;
        this.context = context;
    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }
    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(viewType == TEXT_ITEM)
        {
            view = inflater.inflate(R.layout.community_item, parent, false);
            return new CommunityViewHolder_text(view);
        }
        else if(viewType == IMAGE_ITEM)
        {
            view = inflater.inflate(R.layout.community_image_item, parent, false);
            return new CommunityViewHolder_image(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CommunityData item = post_list.get(position) ;

        if(holder instanceof CommunityViewHolder_text){
            ((CommunityViewHolder_text) holder).post_tilte.setText(item.getTitleStr());
            ((CommunityViewHolder_text) holder).post_nickname.setText(item.getNickname());
            ((CommunityViewHolder_text) holder).post_date.setText(item.getDate());

        }

        else if(holder instanceof CommunityViewHolder_image) {
            ((CommunityViewHolder_image) holder).post_tilte.setText(item.getTitleStr());
            Glide.with(holder.itemView.getContext()).load(item.getImageStr()).centerCrop().into(((CommunityViewHolder_image) holder).post_image);
            ((CommunityViewHolder_image) holder).post_nickname.setText(item.getNickname());
            ((CommunityViewHolder_image) holder).post_date.setText(item.getDate());
        }
    }

    @Override
    public int getItemCount() {
        return post_list.size();
    }

    @Override
    public int getItemViewType(int position) {
        CommunityData item = post_list.get(position);

        if(item.getImageStr() == null){
            return TEXT_ITEM;
        }
        else{
            return IMAGE_ITEM;
        }
    }

    public class CommunityViewHolder_text extends RecyclerView.ViewHolder{
        protected TextView post_tilte;
        protected TextView post_nickname;
        protected TextView post_date;

        CommunityViewHolder_text(View itemView) {
            super(itemView) ;

            // 뷰 객체에 대한 참조. (hold strong reference)
            this.post_tilte = (TextView) itemView.findViewById(R.id.write_post_title) ;
            this.post_nickname = (TextView) itemView.findViewById(R.id.nickname_item);
            this.post_date = (TextView) itemView.findViewById(R.id.date_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        if(mListener!=null){
                            //setPosition(pos);
                            mListener.onItemClick(v,pos);
                            notifyItemChanged(pos);
                        }
                    }
                }
            });
        }
    }

    public class CommunityViewHolder_image extends RecyclerView.ViewHolder{

        protected TextView post_tilte;
        protected ImageView post_image;
        protected TextView post_nickname;
        protected TextView post_date;
        public CommunityViewHolder_image(@NonNull View itemView) {
            super(itemView);

            this.post_tilte = (TextView) itemView.findViewById(R.id.write_post_title) ;
            this.post_image = (ImageView) itemView.findViewById(R.id.write_post_image_item);
            this.post_nickname = (TextView) itemView.findViewById(R.id.nickname_item);
            this.post_date = (TextView) itemView.findViewById(R.id.date_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        if(mListener!=null){
                            //setPosition(pos);
                            mListener.onItemClick(v,pos);
                            notifyItemChanged(pos);
                        }
                    }
                }
            });
        }
    }

    public int getPostion(){
        return position;
    }

    public void setPosition(int position){
        this.position = position;
    }
}
