package com.casecode.birdsmessage.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.casecode.birdsmessage.ui.activitys.LoginRegisterActivity;
import com.casecode.birdsmessage.R;
import com.casecode.birdsmessage.databinding.ActivityMainBinding;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private boolean isNavigationHide = false;
    private boolean isSearchBarHide = false;

    private View search_bar;


    private ActivityMainBinding mainBinding;

    private BottomNavigationView mNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Initialize references to views
        initReferencesToViews();

        // Initialize Firebase components
        initFirebaseComponents();

        // Initialize components
        initComponent();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.item_main_logout:
                AuthUI.getInstance().signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Start login register activity intent
                                    startLoginRegisterActivityIntent();
                                } else {
                                    Log.e(TAG, "onComplete: " + task.getException());
                                }
                            }
                        });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Initialize references to views
    private void initReferencesToViews() {
        // Bottom navigation view
        mNavView = findViewById(R.id.bottom_navigation);

        // View
//        search_bar = (View) findViewById(R.id.search_bar);

    }

    // Initialize Firebase components
    private void initFirebaseComponents() {

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start login register activity intent
            startLoginRegisterActivityIntent();
        }

    }

    // Initialize components
    private void initComponent() {
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_friends,
                R.id.navigation_calls,
                R.id.navigation_chats,
                R.id.navigation_camera,
                R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(mNavView, navController);

        NestedScrollView nested_content = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        nested_content.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY < oldScrollY) { // up
                    animateNavigation(false);
//                    animateSearchBar(false);
                }
                if (scrollY > oldScrollY) { // down
                    animateNavigation(true);
//                    animateSearchBar(true);
                }
            }
        });
    }

    // Initialize message RecyclerView and its adapter

    // Start login register activity intent
    private void startLoginRegisterActivityIntent() {
        Context context = MainActivity.this;
        Class destinationActivity = LoginRegisterActivity.class;
        Intent startLoginRegisterActivityIntent = new Intent(context, destinationActivity);
        startActivity(startLoginRegisterActivityIntent);
        finish();
    }

    // Animate navigation
    private void animateNavigation(final boolean hide) {
        if (isNavigationHide && hide || !isNavigationHide && !hide) return;
        isNavigationHide = hide;
        int moveY = hide ? (2 * mNavView.getHeight()) : 0;
        mNavView.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }

    // Animate search bar
    private void animateSearchBar(final boolean hide) {
        if (isSearchBarHide && hide || !isSearchBarHide && !hide) return;
        isSearchBarHide = hide;
        int moveY = hide ? -(2 * search_bar.getHeight()) : 0;
        search_bar.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }


}