package com.casecode.birdsmessage.ui.settings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.casecode.birdsmessage.R;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsFragment extends Fragment implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {

    private static final String TAG = SettingsFragment.class.getSimpleName();
    private SettingsViewModel settingsViewModel;

    private Toolbar mSettingsToolbar;

    private TextView mSubMainTitleTextView;

    private ImageButton mBackImageButton;


    private static String mBack = "Settings";
    private static String mSubMainTitle = "Settings";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        mSettingsToolbar = root.findViewById(R.id.toolbar_submain);
        mSubMainTitleTextView = root.findViewById(R.id.text_submain_title);
        mBackImageButton = root.findViewById(R.id.imagebutton_chats_back);

        mBackImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSubMainTitleAndActionBack(mBack);
                mSubMainTitleTextView.setText(mSubMainTitle);
            }
        });

        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBack = getString(R.string.title_settings);
        setSubMainTitleAndActionBack(mBack);
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        // Instantiate the new Fragment
        final Bundle args = pref.getExtras();
        final Fragment fragment = getActivity().getSupportFragmentManager().getFragmentFactory().instantiate(
                getActivity().getClassLoader(),
                pref.getFragment());
        fragment.setArguments(args);
        fragment.setTargetFragment(caller, 0);
        // Replace the existing Fragment with the new Fragment
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.settings, fragment)
                .addToBackStack(null)
                .commit();
        return true;
    }

    // Setting preference fragment
    public static class SettingPreferenceFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            mBack = getString(R.string.title_settings);
            mSubMainTitle = getString(R.string.title_settings);
        }
    }

    // Setting preference 'Account' fragment
    public static class SettingPreferenceAccountFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.pref_account, rootKey);

            mBack = getString(R.string.title_settings);
            mSubMainTitle = getString(R.string.title_account);
        }
    }

    // Setting preference 'Privacy' fragment
    public static class SettingPreferencePrivacyFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.pref_privacy, rootKey);

            mBack = getString(R.string.title_settings);
        }
    }

    // Setting preference 'Block List' fragment
    public static class SettingPreferenceBlockListFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.pref_block_list, rootKey);

            mBack = getString(R.string.title_block_list);
        }
    }

    // Setting preference 'Hidden Chats' fragment
    public static class SettingPreferenceHiddenChatsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.pref_hidden_chats, rootKey);

            mBack = getString(R.string.title_hidden_chats);
        }
    }

    // Setting preference 'Personal Data' fragment
    public static class SettingPreferencePersonalDataFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.pref_personal_data, rootKey);

            mBack = getString(R.string.title_personal_data);
        }
    }

    // Setting preference 'Request Your Data' fragment
    public static class SettingPreferenceRequestYourDataFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.pref_request_your_data, rootKey);

            mBack = getString(R.string.title_request_your_data);
        }
    }

    // Setting preference 'Delete Your Date' fragment
    public static class SettingPreferenceDeleteYourDateFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.pref_delete_your_data, rootKey);

            mBack = getString(R.string.title_delete_your_date);
        }
    }

    // Setting preference 'Notifications' fragment
    public static class SettingPreferenceNotificationsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.pref_notifications, rootKey);

            mBack = getString(R.string.title_settings);
        }
    }

    // Setting preference 'Call and Message' fragment
    public static class SettingPreferenceCallAndMessageFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.pref_call_and_message, rootKey);

            mBack = getString(R.string.title_settings);
        }
    }

    // Setting preference 'Media' fragment
    public static class SettingPreferenceMediaFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.pref_media, rootKey);

            mBack = getString(R.string.title_settings);
        }
    }

    // Setting preference 'Change Phone Number' fragment
    public static class SettingPreferenceChangePhoneNumberFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.pref_change_phone_number, rootKey);

            mBack = getString(R.string.title_change_phone_number);
        }
    }

    // Set sub man title and action back
    public void setSubMainTitleAndActionBack(String subMainTitle) {
        if (subMainTitle.equals(getString(R.string.title_settings))) {
            mBack = getString(R.string.title_settings);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingPreferenceFragment())
                    .commit();
        } else if (subMainTitle.equals(getString(R.string.title_account))) {
            mBack = getString(R.string.title_account);

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingPreferenceAccountFragment())
                    .commit();
        } else if (subMainTitle.equals(getString(R.string.title_privacy))) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingPreferencePrivacyFragment())
                    .commit();

            ((AppCompatActivity) getActivity()).setSupportActionBar(mSettingsToolbar);

            mSubMainTitleTextView.setText(getText(R.string.title_privacy).toString());
        } else if (subMainTitle.equals(getString(R.string.title_notifications))) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingPreferenceNotificationsFragment())
                    .commit();

            ((AppCompatActivity) getActivity()).setSupportActionBar(mSettingsToolbar);

            mSubMainTitleTextView.setText(getText(R.string.title_notifications).toString());
        } else if (subMainTitle.equals(getString(R.string.title_call_and_message))) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingPreferenceCallAndMessageFragment())
                    .commit();

            ((AppCompatActivity) getActivity()).setSupportActionBar(mSettingsToolbar);

            mSubMainTitleTextView.setText(getText(R.string.title_call_and_message).toString());
        } else if (subMainTitle.equals(getString(R.string.title_media))) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingPreferenceMediaFragment())
                    .commit();

            ((AppCompatActivity) getActivity()).setSupportActionBar(mSettingsToolbar);

            mSubMainTitleTextView.setText(getText(R.string.title_media).toString());
        }
    }
}
