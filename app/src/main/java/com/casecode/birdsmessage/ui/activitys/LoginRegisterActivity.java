package com.casecode.birdsmessage.ui.activitys;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.casecode.birdsmessage.R;
import com.casecode.birdsmessage.databinding.ActivityLoginRegisterBinding;
import com.casecode.birdsmessage.ui.main.MainActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class LoginRegisterActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 10001;
    private static final String TAG = LoginRegisterActivity.class.getSimpleName();

    private ActivityLoginRegisterBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_register);

        mBinding.buttonLoginregisterLoginregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle login register
                handleLoginRegister();
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            // Start main activity intent
            startMainActivityIntent();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                // We have signed in the user or we have a new user
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                Log.d(TAG, "onActivityResult: " + currentUser.getEmail());

                long lastSignInTimestamp = currentUser.getMetadata().getLastSignInTimestamp();
                long creationTimestamp = currentUser.getMetadata().getCreationTimestamp();
                if (lastSignInTimestamp == creationTimestamp) {
                    Toast.makeText(this, "Welcome new user", Toast.LENGTH_SHORT).show();
                } else {
                    // This is returning user
                    Toast.makeText(this, "Welcome back again", Toast.LENGTH_SHORT).show();
                }

                // Start main activity intent
                startMainActivityIntent();
            } else {
                // Signing in failed
                IdpResponse idpResponse = IdpResponse.fromResultIntent(data);
                if (idpResponse == null) {
                    Log.d(TAG, "onActivityResult: the user has cancelled the sign in request");
                } else {
                    Log.e(TAG, "onActivityResult: " + idpResponse.getError());
                }
            }
        }
    }

    // Handle login register
    public void handleLoginRegister() {
        List<AuthUI.IdpConfig> availableProviders = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        String tosUrl = "https://www.example.com";
        String privacyPolicyUrl = "https://www.example.com";

        Intent intent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(availableProviders)
                .setTosAndPrivacyPolicyUrls(tosUrl, privacyPolicyUrl)
                .setLogo(R.drawable.authui_logo)
                .setIsSmartLockEnabled(true)
                .build();

        startActivityForResult(intent, RC_SIGN_IN);
    }

    // Start main activity intent
    private void startMainActivityIntent() {
        Context context = LoginRegisterActivity.this;
        Class destinationActivity = MainActivity.class;
        Intent startMainActivityIntent = new Intent(context, destinationActivity);
        startActivity(startMainActivityIntent);
        finish();
    }
}