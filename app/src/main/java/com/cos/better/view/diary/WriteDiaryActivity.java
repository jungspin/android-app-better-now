package com.cos.better.view.diary;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;


import com.cos.better.R;
import com.cos.better.config.InitSetting;
import com.cos.better.config.MyDate;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.InputStream;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

public class WriteDiaryActivity extends AppCompatActivity implements InitSetting {

    private static final String TAG = "WriteDiaryActivity";
    private WriteDiaryActivity mContext = this;

    private ImageView ivSave, ivCancel, ivInsertImage, ivPhoto;

    private RichEditor mEditor;

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
        ivInsertImage = findViewById(R.id.ivInsertImage);

    }

    @Override
    public void initLr() {
        ivCancel.setOnClickListener(v->{
            finish();
        });
        ivSave.setOnClickListener(v->{
            Log.d(TAG, "initLr: " + mEditor.getHtml());
            String diary = mEditor.getHtml();
            Intent intent = new Intent(mContext, IntentTestActivity.class);
            intent.putExtra("diary", diary);
            startActivity(intent);
        });





    }






    @Override
    public void initSetting() {

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbarDiaryEditor);
        setSupportActionBar(mToolbar);

        mEditor = findViewById(R.id.editor);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setPadding(10, 10, 10, 10);

        String initData = getIntent().getStringExtra("initData");
        mEditor.setPlaceholder(initData);



    }

    @Override
    public void initData() {

    }

    private void setPermission(){
        Log.d(TAG, "setPermission: ");
        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 허용 시
                Log.d(TAG, "onPermissionGranted: 권한 허용");

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                // 권한 거부시
                Log.d(TAG, "onPermissionDenied: 권한 거부");
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage("갤러리 접근 권한 설정이 필요합니다")
                .setDeniedMessage("거부 하셨습니다")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();
    }


}