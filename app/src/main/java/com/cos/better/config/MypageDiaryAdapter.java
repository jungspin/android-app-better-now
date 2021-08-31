package com.cos.better.config;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cos.better.R;
import com.cos.better.model.Diary;
import com.cos.better.view.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class MypageDiaryAdapter extends RecyclerView.Adapter<MypageDiaryAdapter.MyViewHolder>{

    private static final String TAG = "MypageDiaryAdapter";

    private List<Diary> diaryList = new ArrayList<>();


    // 컬렉션 데이터 셋팅 해야함
    public void addItems(List<Diary> diaryList){
        this.diaryList = diaryList;
        //notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.mypage_diary_item, parent, false);
        return new MyViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return diaryList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MypageDiaryAdapter.MyViewHolder holder, int position) {
        Diary diary = diaryList.get(position);
        holder.setItem(diary);

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDate, tvTitle;
        private ImageView ivPhoto;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);

            initLr();
        }

        public void setItem(Diary diary){
            tvDate.setText(diary.getCreated());
            tvTitle.setText(diary.getTitle());

            Glide
                    .with(itemView)
                    .load("https://picsum.photos/100/100")
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background) // 사진 없을 때
                    .into(ivPhoto);

        }

        private void initLr(){
            itemView.setOnClickListener(v->{
                Log.d(TAG, "initLr: 클릭됨 " + getAdapterPosition());
            });
        }
    }
}
