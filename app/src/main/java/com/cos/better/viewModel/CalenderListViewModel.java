package com.cos.better.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cos.better.dto.CalenderDTO;
import com.cos.better.model.Diary;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

public class CalenderListViewModel extends ViewModel {

    private static final String TAG = "CalenderListViewModel";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("schedule");

    private MutableLiveData<List<CalenderDTO>> mdCalenderList = new MutableLiveData<>();

    public MutableLiveData<List<CalenderDTO>> getMdCalenderList() {
        return mdCalenderList;
    }

    public void findSelected(CalendarDay today){
        Log.d(TAG, "findAllSchedule: today : " + today.getDate());
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        ref.whereArrayContains("calendarDayList", today)
                .whereEqualTo("user", user.getProviderId()+user.getUid())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.getResult().size() != 0){
                        List<CalenderDTO> calenderDTOS = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            //Log.d(TAG, document.getId() + " => " + document.getData());
                            CalenderDTO calenderDTO = document.toObject(CalenderDTO.class);
                            Log.d(TAG, "findAllScheduleTest: "+ calenderDTO.getTitle());
                            calenderDTOS.add(calenderDTO);
                        }
                        mdCalenderList.setValue(calenderDTOS);
                    } else {
                        mdCalenderList.setValue(null);
                    }

                })
                .addOnFailureListener(task -> {
                    task.printStackTrace();
                });
    }



}
