package com.casecode.birdsmessage.ui.friends;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FriendsViewModel extends ViewModel {
    MutableLiveData<String> mText;

    public FriendsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is friends fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
