package com.cos.better.config;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {

    private static final String TAG = "MyDialogFragment";
    @Override
    // create 붙은건 대부분 new 된다는 의미
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // bulider 패턴(객체생성). 이렇게 new 시켰다는 건 xml로 만들었단게 아님.
        // inflate 안했으니까 xml 아녀
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("입력을 취소할까요?")
                .setPositiveButton("네",
                        (DialogInterface dialog, int id) -> {
                            Log.d(TAG, "onCreateDialog: setPositiveButton");
                            getActivity().finish();

                        }
                )
                .setNegativeButton("아니오",
                        (DialogInterface dialog, int id) -> {
                            Log.d(TAG, "onCreateDialog: setNegativeButton");
                        }
                );
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
