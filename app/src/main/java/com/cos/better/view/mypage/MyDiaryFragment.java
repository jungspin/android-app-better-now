package com.cos.better.view.mypage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.config.MypageDiaryAdapter;
import com.cos.better.model.Diary;
import com.cos.better.view.HomeActivity;
import com.cos.better.viewModel.DiaryListViewModel;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.prolificinteractive.materialcalendarview.CalendarDay;


public class MyDiaryFragment extends Fragment implements InitSetting {

    private static final String TAG = "MyDiaryFragment";
    private HomeActivity mContext;
    private MypageFragment mFragment;

    private RecyclerView rvDiaries;
    private MypageDiaryAdapter mypageDiaryAdapter;

    DiaryListViewModel vm;


    public MyDiaryFragment(HomeActivity mContext) {
        // Required empty public constructor
    }


    public MyDiaryFragment(MypageFragment mFragment) {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        View view = inflater.inflate(R.layout.fragment_my_diary, container, false);

        rvDiaries = (RecyclerView) view.findViewById(R.id.rvDiaries);

        initAdapter();
        initSetting();
        initData();

        return view;
    }

    @Override
    public void init() {

    }

    @Override
    public void initLr() {

    }

    @Override
    public void initAdapter() {
        Log.d(TAG, "initAdapter: ");

        rvDiaries.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        mypageDiaryAdapter = new MypageDiaryAdapter();
        rvDiaries.setAdapter(mypageDiaryAdapter);
//        mypageDiaryAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            public void onItemRangeInserted(int positionStart, int itemCount) {
//                rvDiaries.smoothScrollToPosition(mypageDiaryAdapter.getItemCount());
//            }
//        });


    }

    @Override
    public void initSetting() {
        vm = new ViewModelProvider(requireActivity()).get(DiaryListViewModel.class);

    }

    @Override
    public void initData() {

        //DiaryTestProvider diaryTestProvider = new DiaryTestProvider();
        //mypageDiaryAdapter.addItems(vm.findAllDiary());
        vm.findAllDiary();
        vm.getDiaryList().observe(requireActivity(), data ->{
            if (data.size() != 0){
                Log.d(TAG, "initData: " + data.get(0).toString());
                mypageDiaryAdapter.addItems(data);
            } else {
                Log.d(TAG, "initData: 데이터 없음");
            }
            
        });

    }
    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

}