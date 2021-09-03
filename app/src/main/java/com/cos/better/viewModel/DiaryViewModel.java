package com.cos.better.viewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cos.better.model.Diary;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

public class DiaryViewModel extends ViewModel {

    private static final String TAG = "DiaryViewModel";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private MutableLiveData<Diary> diary = new MutableLiveData<>();

    public void init(){
        //diary.setValue();
    }

    public MutableLiveData<Diary> getDiary() {
        return diary;
    }



//    public void insertDiary(Context mContext, Diary diary){
//        CollectionReference diaryData = db.collection("diary");
//        diaryData.document().set(diary)
//            .addOnCompleteListener(runnable -> {
//                Log.d(TAG, "insertDiary: success");
//                Toast.makeText(mContext, "일기가 등록되었습니다", Toast.LENGTH_SHORT).show();
//            })
//            .addOnFailureListener(runnable -> {
//                Log.d(TAG, "insertDiary: fail");
//                Toast.makeText(mContext, "일기 등록에 실패했습니다", Toast.LENGTH_SHORT).show();
//            });
//    }



    public void findOne(CalendarDay today){ // 사실은 한건이 아님. 몇건일지 모름
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(TAG, "findOne: " + today);
        db.collection("diary")
                .whereEqualTo("today", today).whereEqualTo("user", user.getEmail()) // 쿼리는 이런식으로 작성하면 될듯
                .get()
                .addOnCompleteListener(runnable -> {
                    if (runnable.getResult().size() == 0){
                        diary.setValue(null);
                    } else {
                        Log.d(TAG, "findAllDiary: success:  " + runnable.getResult().size());
                        Diary newDiary = runnable.getResult().toObjects(Diary.class).get(0);
                        Log.d(TAG, "findAllDiary: success:  " + newDiary.toString());
                        Log.d(TAG, "findAllDiary: success:  " + newDiary.getContent());
                        diary.setValue(newDiary);
                    }


                })
                .addOnFailureListener(runnable -> {
                    runnable.printStackTrace();
                    Log.d(TAG, "findOne: 에러");

                });
    }

}
