package com.cos.better.viewModel;

import android.content.pm.PackageInfo;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cos.better.dto.CalenderDTO;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

public class CalenderDayListViewModel extends ViewModel {

    private static final String TAG = "CalenderDayListVM";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ref = db.collection("schedule");

    private MutableLiveData<List<CalendarDay>> mdCldList = new MutableLiveData<>();


    public MutableLiveData<List<CalendarDay>> getMdCldList() {
        return mdCldList;
    }


     public void findAllCalendar(){
         FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            ref.whereEqualTo("user", user.getProviderId()+user.getUid())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.getResult().size() != 0) {
                        List<CalendarDay> calendarDays = new ArrayList<>();
                        for(DocumentSnapshot doc : task.getResult()){
                            //Log.d(TAG, "findAllCalendar: " + doc.getId() + " => " + doc.getData());
                            CalenderDTO calenderDTO = doc.toObject(CalenderDTO.class);
                            //Log.d(TAG, "findAllCalendar: calenderDTO: " + calenderDTO.getCalendarDayList());
                            for(int i=0; i<calenderDTO.getCalendarDayList().size();i++){
                                calendarDays.add(calenderDTO.getCalendarDayList().get(i));
                            }
                        }
                        Log.d(TAG, "findAllCalendar: calendarDays: " + calendarDays.size());
                        mdCldList.setValue(calendarDays);
                    }else {
                        mdCldList.setValue(null);
                    }
                })
                .addOnFailureListener(e -> {
                    e.printStackTrace();
                });
        }



}
