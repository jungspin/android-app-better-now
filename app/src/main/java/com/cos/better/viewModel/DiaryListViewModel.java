package com.cos.better.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cos.better.model.Diary;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

public class DiaryListViewModel extends ViewModel {

    private static final String TAG = "DiaryListViewModel";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private MutableLiveData<List<Diary>> diaryList = new MutableLiveData<>();

    public void init(){
        //diary.setValue();
    }

    public MutableLiveData<List<Diary>> getDiaryList() {
        return diaryList;
    }


    public void findAllDiary(){

        db.collection("diary").whereEqualTo("user", user.getEmail())
                .get()
                .addOnCompleteListener(runnable -> {
                    Log.d(TAG, "findAllDiary: success:  " + runnable.getResult().size());
                    List<Diary> diaries = new ArrayList<>();
                    if(runnable.getResult().size()!=0){
                        for (QueryDocumentSnapshot document : runnable.getResult()){
                            Log.d(TAG, "findAllDiary: " + document.getId() + " => " + document.getData());

                            diaries.add(document.toObject(Diary.class));

                        }
                       // Log.d(TAG, "diaryList: " + diaries.get(0).getToday());
                        diaryList.setValue(diaries);
                    } else {
                        Log.d(TAG, "findAllDiary: 데이터 없음");
                    }
                })
                .addOnFailureListener(runnable -> {
                    runnable.printStackTrace();
                });
    }

}
