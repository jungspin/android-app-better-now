package com.cos.better.view.mypage.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.better.R;
import com.cos.better.dto.HabitDto;
import com.cos.better.dto.StatusDto;
import com.cos.better.model.Habit;
import com.cos.better.view.status.StatusDetailActivity;
import com.cos.better.view.status.adapter.StatusAdapter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class MyHabitAdapter extends RecyclerView.Adapter<MyHabitAdapter.MyViewHolder>{

    private static final String TAG = "MyHabitAdapter";
    private MyHabitAdapter myHabitAdapter = this;
    private Context mContext;
    private List<HabitDto> habitList = new ArrayList<>();

    public MyHabitAdapter(Context context){
        this.mContext = context;
    }

    public void addItems(List<StatusDto> statusList){
        this.habitList.addAll(habitList);
        notifyDataSetChanged();
    }

    public void addItem(HabitDto habitDto){
        this.habitList.add(habitDto);
        notifyDataSetChanged();
    }
    public List<HabitDto> getItems(){
        return habitList;
    }
    public void removeItem(int index){
        this.habitList.remove(index);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHabitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                (LayoutInflater)parent.getContext().getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        View view = layoutInflater.inflate(R.layout.staus_item,parent,false);
        view.setOnClickListener(v ->{
            Log.d(TAG, "onCreateViewHolder: "+v);
        });
        return new MyHabitAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HabitDto habitDto = habitList.get(position);
        holder.setItem(habitDto);
    }

    @Override
    public int getItemCount() {
        return habitList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private MaterialTextView tvHabitTitle, tvCheckHabit;
        private MaterialButton btnComplete;
        private ImageView ivIcon;

        //앱 구동 시에 발동
        public MyViewHolder(View itemView){
            super(itemView);
            tvHabitTitle = itemView.findViewById(R.id.tvHabitTitle);
            tvCheckHabit = itemView.findViewById(R.id.tvCheckHabit);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            btnComplete =itemView.findViewById(R.id.btnComplete);

            initLr();
        }
        public void setItem(HabitDto habitDto){
            tvHabitTitle.setText(habitDto.getTitle());
            ivIcon.setImageResource(habitDto.getIcon());

        }
        public void initLr(){

        }

    }
}
