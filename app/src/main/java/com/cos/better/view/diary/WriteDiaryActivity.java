package com.cos.better.view.diary;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.dto.CalenderDayDTO;
import com.cos.better.model.Diary;
import com.cos.better.viewModel.DiaryController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.prolificinteractive.materialcalendarview.CalendarDay;


import java.io.InputStream;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

public class WriteDiaryActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "WriteDiaryActivity";
    private WriteDiaryActivity mContext = this;

    private ImageView ivSave, ivCancel, ivPhoto, ivInsertPhoto;
    private RichEditor mEditor;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);



        init();
        initLr();
        initSetting();
        initData();

    }


    @Override
    public void init() {
        ivSave = findViewById(R.id.ivSave);
        ivCancel = findViewById(R.id.ivCancel);
        mEditor = findViewById(R.id.editor);
        ivPhoto = findViewById(R.id.ivPhoto);
        ivInsertPhoto = findViewById(R.id.ivInsertPhoto);


    }

    @Override
    public void initLr() {
        ivCancel.setOnClickListener(v->{
            finish();
        });
        ivSave.setOnClickListener(v->{
            DiaryController vm = new DiaryController();

            CalenderDayDTO date = (CalenderDayDTO) getIntent().getSerializableExtra("date");

//            Log.d(TAG, "ivSave: " + ivPhoto.getDrawable());
//            BitmapDrawable bd = (BitmapDrawable) ivPhoto.getDrawable();
//            Bitmap bitmap = bd.getBitmap();

            Diary diary = Diary.builder()
                    .today(CalendarDay.from(date.getYear(), (date.getMonth()-1), date.getDay()))
                    .content(mEditor.getHtml())
                    .user(user.getEmail())
                    .build();
            vm.insertDiary(mContext, diary);
            Log.d(TAG, "ivSave: " + diary.toString());
            finish();
        });
        ivInsertPhoto.setOnClickListener(view -> {
//            // 구글 갤러리 접근
//            Intent intent = new Intent();
//            intent.setType("image/*");
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(intent, 1000);
            // 기본 갤러리 접근
//            Intent intent = new Intent();
//            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
//            startActivityForResult(intent, 1000);

        });


    }

    @Override
    public void initSetting() {
        ivPhoto.setVisibility(View.INVISIBLE);


        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("Insert text here...");


        Toolbar myToolbar = findViewById(R.id.writeDiaryToolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("");

    }

    @Override
    public void initData() {


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1000) {
//            if (resultCode == RESULT_OK){
//                InputStream in = null;
//                try {
//                    in = getContentResolver().openInputStream(data.getData());
//
//                Bitmap img = BitmapFactory.decodeStream(in);
//                in.close();
//                // 이미지뷰에 세팅
//                ivPhoto.setImageBitmap(img);
//                ivPhoto.setVisibility(View.VISIBLE);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//
//
//    private void setPermission(){
//        Log.d(TAG, "setPermission: ");
//        PermissionListener permissionListener = new PermissionListener() {
//            @Override
//            public void onPermissionGranted() {
//                // 권한 허용 시
//                Log.d(TAG, "onPermissionGranted: 권한 허용");
//            }
//
//            @Override
//            public void onPermissionDenied(List<String> deniedPermissions) {
//                // 권한 거부시
//                Log.d(TAG, "onPermissionDenied: 권한 거부");
//            }
//        };
//
//        TedPermission.with(this)
//                .setPermissionListener(permissionListener)
//                .setRationaleMessage("카메라 권한 설정이 필요합니다")
//                .setDeniedMessage("거부 하셨습니다")
//                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
//                .check();
//    }




}