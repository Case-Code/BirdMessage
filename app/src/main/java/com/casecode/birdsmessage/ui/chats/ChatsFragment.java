package com.casecode.birdsmessage.ui.chats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.casecode.birdsmessage.R;
import com.mikhaellopez.circularimageview.CircularImageView;

public class ChatsFragment extends Fragment {

    private ChatsViewModel chatsViewModel;
    private Toolbar mChatsToolbar;
    private CircularImageView mProfilePictureCircularImageView;
    private ImageButton mChatNewMessageImageButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_chats, container, false);

        mChatsToolbar = root.findViewById(R.id.toolbar_chats);
        mProfilePictureCircularImageView = root.findViewById(R.id.circular_chats_profilepicture);
        mChatNewMessageImageButton = root.findViewById(R.id.imagebutton_chats_newmessage);

        mProfilePictureCircularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Profile picture", Toast.LENGTH_SHORT).show();
            }
        });

        mChatNewMessageImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "New message", Toast.LENGTH_SHORT).show();
            }
        });

        chatsViewModel = ViewModelProviders.of(this).get(ChatsViewModel.class);
        chatsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppCompatActivity) getActivity()).setSupportActionBar(mChatsToolbar);

    }
}
