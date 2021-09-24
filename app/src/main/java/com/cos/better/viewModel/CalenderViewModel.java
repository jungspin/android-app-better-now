package com.cos.better.viewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cos.better.dto.CalenderDTO;
import com.cos.better.model.Diary;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.prolificinteractive.materialcalendarview.CalendarDay;

public class CalenderViewModel extends ViewModel {

    private static final String TAG = "CalenderViewModel";


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // 변할 수 있는 데이터
    private MutableLiveData<CalenderDTO> mdCalenderDTO = new MutableLiveData<>();


    public MutableLiveData<CalenderDTO> getCalenderDTO() {
        return mdCalenderDTO;
    }

        public void insertSchedule(Context mContext, CalenderDTO calenderDTO){
        CollectionReference diaryData = db.collection("schedule");
        diaryData.document().set(calenderDTO)
            .addOnCompleteListener(runnable -> {
                Log.d(TAG, "insertSchedule: success");
                Log.d(TAG, "insertSchedule: " + runnable.getResult());
                Toast.makeText(mContext, "일정이 추가되었습니다", Toast.LENGTH_SHORT).show();
            })
            .addOnFailureListener(runnable -> {
                Log.d(TAG, "insertSchedule: fail");
                runnable.printStackTrace();
                Toast.makeText(mContext, "일정 추가에 실패했습니다", Toast.LENGTH_SHORT).show();
            });
    }

}
