package com.cos.better.viewModel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cos.better.model.Habit;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HabitViewModel extends ViewModel {
    private static final String TAG = "HabitViewModel";
    private MutableLiveData<List<Habit>> habitList = new MutableLiveData<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Habit> lists = new ArrayList<>();

    public void findAllHabit() {
        db.collection("habits")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              //  Log.d(TAG, document.getId() + " : " + document.getData());
                                lists.add(document.toObject(Habit.class));
                            }
                            habitList.setValue(lists);
                        } else {
                            Log.d(TAG, "Error getting documents: " + task.getException());
                        }
                    }
                });
    }

    public MutableLiveData<List<Habit>> getHabit() {
        return habitList;
    }
}

