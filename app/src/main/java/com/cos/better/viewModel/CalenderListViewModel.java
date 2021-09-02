package com.cos.better.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cos.better.dto.CalenderDTO;
import com.cos.better.model.Diary;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

public class CalenderListViewModel extends ViewModel {

    private static final String TAG = "CalenderListViewModel";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private MutableLiveData<List<CalenderDTO>> mdCalenderList = new MutableLiveData<>();

    public void init(){
        //diary.setValue();
    }

    public MutableLiveData<List<CalenderDTO>> getMdCalenderList() {
        return mdCalenderList;
    }


    public void findAllSchedule(CalendarDay today){
        Log.d(TAG, "findAllSchedule: today : " + today);
        db.collection("schedule")
                .whereEqualTo("startCalenderDay", today) // 쿼리는 이런식으로 작성하면 될듯
                .get()
                .addOnCompleteListener(runnable -> {
                    if(runnable.getResult().size() == 0){
                        Log.d(TAG, "findAllSchedule: size 0 ");
                        mdCalenderList.setValue(null);
                    }else {
                        Log.d(TAG, "findAllDiary: success:  " + runnable.getResult().size());
                        List<CalenderDTO> calenderDTOS = new ArrayList<>();
                        if(runnable.isSuccessful()){
                            for (QueryDocumentSnapshot document : runnable.getResult()){
                                Log.d(TAG, "findAllDiary: " + document.getId() + " => " + document.getData());
                                CalenderDTO cal = document.toObject(CalenderDTO.class);
                                calenderDTOS.add(cal);
                                for (int i=0; i<cal.getCalendarDayList().size();i++){
                                    if(cal.getCalendarDayList().contains(today)){

                                    }
                                }


                            }
                            Log.d(TAG, "diaryList: " + calenderDTOS.get(0).getTitle());
                            mdCalenderList.setValue(calenderDTOS);
                        }
                    }

                })
                .addOnFailureListener(runnable -> {
                    runnable.printStackTrace();
                });
    }

    public void findAllScheduleTest(CalendarDay today){
        Log.d(TAG, "findAllSchedule: today : " + today.getDate());
        db.collection("schedule")
                .get()
                .addOnCompleteListener(runnable -> {
                    if (runnable.isSuccessful()) {
                        List<CalendarDay > calendarDays = new ArrayList<>();
                        Log.d(TAG, "findAllScheduleTest: " + runnable.getResult().getDocuments());
                        List<DocumentSnapshot> documents = runnable.getResult().getDocuments();
                        for(int i=0;i<documents.size();i++){
                            //Log.d(TAG, "findAllScheduleTest: " + documents.get((i)).getData().containsKey("calendarDayList"));

                        }


//                        for(int i=0; i<runnable.getResult().size();i++){
//                            Log.d(TAG, "findAllScheduleTest: " + runnable.getResult().getDocuments());
//                            List<DocumentSnapshot> documents = runnable.getResult().getDocuments();
//                            CalendarDay cal = runnable.getResult().getDocuments().get(i).toObject(CalendarDay.class);
//                            calendarDays.add(cal);

//                            if(cal.equals(today)){
//                                Log.d(TAG, "findAllScheduleTest: 있어");
//                            }
                        }
//                        for (QueryDocumentSnapshot document : runnable.getResult()) {
//                            Log.d(TAG, document.getId() + " => " + document.getData());
//                            calendarDays.add(document.toObject(CalendarDay.class));
//
//
//                        }
//                        Log.d(TAG, "findAllScheduleTest: 리스트 : " + calendarDays.get(0).getMonth());
//                        Log.d(TAG, "findAllScheduleTest: " + CalendarDay.from(2021, 8, 5));
//                        Log.d(TAG, "calendarDays.get(0).getDate(): " + calendarDays.get(0).getDate());
//                        Log.d(TAG, "CalendarDay.from(2021, 8, 19).getDate(): " + CalendarDay.from(2021, 8, 2).getDate());
//                        if(calendarDays.get(0).getDate().equals((CalendarDay.from(2021, 8, 2)).getDate())){
//                            Log.d(TAG, "findAllScheduleTest: 있어");
//                        }else {
//                            Log.d(TAG, "findAllScheduleTest: 없어");
//                        }
//                    } else {
//                        Log.d(TAG, "Error getting documents: ", runnable.getException());
//                    }

                })
                .addOnFailureListener(runnable -> {
                    runnable.printStackTrace();
                });
    }

//    private void findAll(){
//        db.collection("schedule")
//                .get()
//
//    }

}
