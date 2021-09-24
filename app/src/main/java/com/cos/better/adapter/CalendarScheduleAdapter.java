package com.cos.better.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.better.R;
import com.cos.better.dto.CalenderDTO;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CalendarScheduleAdapter extends RecyclerView.Adapter<CalendarScheduleAdapter.MyViewHolder>{

    private static final String TAG = "CalendarScheduleAdapter";

    private List<CalenderDTO> calenderDTOList = new ArrayList<>();


    // 컬렉션 데이터 셋팅 해야함
    public void addItems(List<CalenderDTO> calenderDTOList){
        Log.d(TAG, "addItems: " + calenderDTOList.size());
        this.calenderDTOList = calenderDTOList;
        notifyDataSetChanged();
    }

    public void addItem(CalenderDTO calenderDTO){
        //Log.d(TAG, "addItems: " + diaryList.size());
        this.calenderDTOList.add(calenderDTO);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.calendar_schedule_item, parent, false);

        return new MyViewHolder(view);
    }



    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + calenderDTOList.size());
        return calenderDTOList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarScheduleAdapter.MyViewHolder holder, int position) {
        CalenderDTO calenderDTO = calenderDTOList.get(position);
        holder.setItem(calenderDTO);

    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG = "MyViewHolder";

        private TextView tvScheduleTitle, tvStartDate, tvEndDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvScheduleTitle = itemView.findViewById(R.id.tvScheduleTitle);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            tvEndDate = itemView.findViewById(R.id.tvEndDate);

            initLr();
        }

        public void setItem(CalenderDTO calenderDTO){


            SimpleDateFormat sdf = new SimpleDateFormat("HH시 mm분");
            String startDate = sdf.format(calenderDTO.getStartDate());
            String endDate = sdf.format(calenderDTO.getEndDate());

            Log.d(TAG, "setItem: startDate :" + startDate);
            Log.d(TAG, "setItem: endDate :" + endDate);

            tvScheduleTitle.setText(calenderDTO.getTitle());
            tvStartDate.setText(startDate + " ~");
            tvEndDate.setText(endDate);

        }

        private void initLr(){
            itemView.setOnClickListener(v->{
                Log.d(TAG, "initLr: 클릭됨 " + getAdapterPosition());
            });
        }
    }



}
