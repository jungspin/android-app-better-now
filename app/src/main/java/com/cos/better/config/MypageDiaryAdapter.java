package com.cos.better.config;

import android.content.Context;
import android.content.Intent;
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
import com.cos.better.view.diary.DetailDiaryActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MypageDiaryAdapter extends RecyclerView.Adapter<MypageDiaryAdapter.MyViewHolder>{

    private static final String TAG = "MypageDiaryAdapter";
    private HomeActivity mContext;

    public MypageDiaryAdapter(HomeActivity mContext) {
        this.mContext = mContext;
    }

    private List<Diary> diaryList = new ArrayList<>();


    // 컬렉션 데이터 셋팅 해야함
    public void addItems(List<Diary> diaryList){
        Log.d(TAG, "addItems: " + diaryList.size());
        this.diaryList = diaryList;
        notifyDataSetChanged();
    }

    public void addItem(Diary diary){
        //Log.d(TAG, "addItems: " + diaryList.size());
        this.diaryList.add(diary);
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
        Log.d(TAG, "getItemCount: " + diaryList.size());
        return diaryList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull MypageDiaryAdapter.MyViewHolder holder, int position) {
        Diary diary = diaryList.get(position);
        holder.setItem(diary);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDate, tvTitle;
        private ImageView ivPhoto;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTitle = itemView.findViewById(R.id.tvTitle);

            initLr();
        }

        public void setItem(Diary diary){
            Log.d(TAG, "setItem: " + diary.getUser());
            CalendarDay cal = diary.getToday();
            String setTime = cal.getYear() + "년 " + (cal.getMonth()+1) + "월 " + cal.getDay() + "일";

            tvDate.setText(setTime);
            tvTitle.setText(diary.getTitle());


        }

        private void initLr(){
            itemView.setOnClickListener(v->{
                Log.d(TAG, "initLr: 클릭됨 " + getAdapterPosition());
                Diary diary = diaryList.get(getAdapterPosition());
                Log.d(TAG, "initLr: " + diary.toString());
                Intent intent = new Intent(mContext, DetailDiaryActivity.class);

                CalendarDay day = diary.getToday();
                Log.d(TAG, "initLr: day : " + day);
                CustomDate date = CustomDate.builder()
                        .month(day.getMonth()+1)
                        .year(day.getYear())
                        .day(day.getDay()).build();

                intent.putExtra("date", date);

                Log.d(TAG, "initLr: putExtra(\"date\", date): " + date.toString());

                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                mContext.startActivity(intent);
            });
        }
    }



}
