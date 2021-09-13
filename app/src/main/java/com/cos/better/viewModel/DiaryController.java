package com.cos.better.viewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cos.better.model.Diary;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.HashMap;
import java.util.Map;

public class DiaryController {

    private static final String TAG = "DiaryViewModel";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private MutableLiveData<Diary> diary = new MutableLiveData<>();

    public void init(){
        //diary.setValue();
    }

    public MutableLiveData<Diary> getDiary() {
        return diary;
    }



    public void insertDiary(Context mContext, Diary diary){
        CollectionReference diaryData = db.collection("diary");
        diaryData.document().set(diary)
            .addOnCompleteListener(runnable -> {
                Log.d(TAG, "insertDiary: success");
                Toast.makeText(mContext, "일기가 등록되었습니다", Toast.LENGTH_SHORT).show();
            })
            .addOnFailureListener(runnable -> {
                Log.d(TAG, "insertDiary: fail");
                runnable.printStackTrace();
                Toast.makeText(mContext, "일기 등록에 실패했습니다", Toast.LENGTH_SHORT).show();
            });
    }

    public void findAllDiary(){
        db.collection("diary")
                .get()
                .addOnCompleteListener(runnable -> {
                    //Log.d(TAG, "findAllDiary: success:  " + runnable.getResult().size());
                    if(runnable.isSuccessful()){
                        for (QueryDocumentSnapshot document : runnable.getResult()){
                            //Log.d(TAG, "findAllDiary: " + document.getId() + " => " + document.getData());
                        }
                    }
                })
                .addOnFailureListener(runnable -> {
                    runnable.printStackTrace();
                });
    }

    public void findOne(CalendarDay today){ // 사실은 한건이 아님. 몇건일지 모름
        db.collection("diary")
                .whereEqualTo("today", today) // 쿼리는 이런식으로 작성하면 될듯
                .get()
                .addOnCompleteListener(runnable -> {
                    Log.d(TAG, "findAllDiary: success:  " + runnable.getResult().size());
                    Diary newDiary = runnable.getResult().toObjects(Diary.class).get(0);
                    //Log.d(TAG, "findAllDiary: success:  " + newDiary.toString());
                    //Log.d(TAG, "findAllDiary: success:  " + newDiary.getContent());

                })
                .addOnFailureListener(runnable -> {
                    runnable.printStackTrace();
                });
    }
}