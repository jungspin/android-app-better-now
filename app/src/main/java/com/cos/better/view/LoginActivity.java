package com.cos.better.view;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.airbnb.lottie.Lottie;
import com.cos.better.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity  {

    private static final String TAG = "LoginActivity";
    private Context mContext = LoginActivity.this;




    // [START auth_fui_create_launcher]
    // See: https://developer.android.com/training/basics/intents/result
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            result -> onSignInResult(result)
    );
    // [END auth_fui_create_launcher]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //createSignInIntent();

        setContentView(R.layout.activity_login);


        showProgressDialog();
            login();





    }

    private void showProgressDialog(){
        new LoadingFragment().newInstance().
                show(getSupportFragmentManager(),"");
    }



    // [START auth_fui_result]
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        IdpResponse response = result.getIdpResponse();
        if (result.getResultCode() == RESULT_OK) {
            // Successfully signed in
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//            Log.d(TAG, "getMetadata: " + user.getMetadata());
//            Log.d(TAG, "getPhotoUrl: " + user.getPhotoUrl());
//            Log.d(TAG, "getIdToken(true): " + user.getIdToken(true));
//            Log.d(TAG, "getDisplayName: " + user.getDisplayName());
//            Log.d(TAG, "getEmail: " + user.getEmail());
//            Log.d(TAG, "getProviderId: " + user.getProviderId());
//            Log.d(TAG, "getUid: " + user.getUid());


            Intent intent = new Intent(mContext, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Log.d(TAG, "onSignInResult: 로그인 실패 : " + response.getError());
        }
    }
    // [END auth_fui_result]

    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> {
                    Log.d(TAG, "signOut: ");
                });
        // [END auth_fui_signout]
    }

    public void delete() {
        // [START auth_fui_delete]
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(task -> {
                    Log.d(TAG, "delete: ");
                });
        // [END auth_fui_delete]
    }


    public void login() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.TwitterBuilder().build());

        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.better_logo)
                .setTheme(R.style.Theme_Better)
                .build();
        signInLauncher.launch(signInIntent);
        // [END auth_fui_create_intent]
    }






}