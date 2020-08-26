package com.casecode.birdsmessage.ui.friends;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.casecode.birdsmessage.R;
import com.casecode.birdsmessage.databinding.ActivityMainBinding;

public class FriendsFragment extends Fragment {

    private FriendsViewModel friendsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_friends, container, false);

        friendsViewModel = ViewModelProviders.of(this).get(FriendsViewModel.class);
        friendsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
            }
        });

        return root;
    }
}
