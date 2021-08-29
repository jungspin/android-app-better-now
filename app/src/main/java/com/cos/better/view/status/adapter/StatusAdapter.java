package com.cos.better.view.status.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.cos.better.R;
import com.cos.better.dto.StatusDto;
import com.cos.better.view.status.StatusFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyViewHolder> {

    private StatusAdapter statusAdapter = this;
    private static final String TAG = "StatusAdapter";
    private Context context;
    private List<StatusDto> statusList = new ArrayList<>();

    public StatusAdapter(Context context){
        this.context = context;
    }

    public void addItems(List<StatusDto> statusList){
        this.statusList = statusList;
        notifyDataSetChanged();
    }

    public void addItem(StatusDto statusDto){
        this.statusList.add(statusDto);
        notifyDataSetChanged();
    }
    public List<StatusDto> getItems(){
        return statusList;
    }
    public void removeItem(int index){
        this.statusList.remove(index);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                (LayoutInflater)parent.getContext().getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        View view = layoutInflater.inflate(R.layout.staus_item,parent,false);
        view.setOnClickListener(v ->{
            Log.d(TAG, "onCreateViewHolder: "+v);
        });
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(StatusAdapter.MyViewHolder holder, int position) {
        StatusDto statusDto = statusList.get(position);
        holder.setItem(statusDto);
    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private MaterialTextView tvHabitTitle, tvProgress;
        private AppCompatButton btnCount, btnCycle;
        private ImageView ivIcon;

        //앱 구동 시에 발동
        public MyViewHolder(View itemView){
            super(itemView);
            tvHabitTitle = itemView.findViewById(R.id.tvHabitTitle);
            tvProgress = itemView.findViewById(R.id.tvProgress);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            btnCount =itemView.findViewById(R.id.btnCount);
            btnCycle = itemView.findViewById(R.id.btnCycle);

            initLr();
        }
        public void setItem(StatusDto statusDto){
            int progress = statusDto.getCount()/30; // 추가 연산 필요

            tvHabitTitle.setText(statusDto.getHabitTitle());
            tvProgress.setText(progress+"%");
            ivIcon.setImageResource(statusDto.getIcon());
            btnCount.setText(statusDto.getCount()+"회/30회");
            btnCycle.setText(statusDto.getCycle());
        }
        public void initLr(){
            itemView.setOnClickListener(v ->{
                Log.d(TAG, "initLr: "+getAdapterPosition());
                int index = getAdapterPosition();
                statusAdapter.getItems();
                Log.d(TAG, "initLr: "+statusAdapter.getItems().get(index).getHabitTitle());

            });
        }

    }
}
