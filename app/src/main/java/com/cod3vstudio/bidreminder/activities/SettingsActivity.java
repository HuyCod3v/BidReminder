package com.cod3vstudio.bidreminder.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.cod3vstudio.bidreminder.App;
import com.cod3vstudio.bidreminder.BR;
import com.cod3vstudio.bidreminder.R;
import com.cod3vstudio.bidreminder.databinding.ActivitySettingsBinding;
import com.cod3vstudio.core.util.Configuration;
import com.cod3vstudio.core.view.BaseActivity;
import com.cod3vstudio.core.viewmodel.SettingsViewModel;

import org.androidannotations.annotations.EActivity;

public class SettingsActivity extends BaseActivity<ActivitySettingsBinding, SettingsViewModel> {

    private SharedPreferences mSharedPreferences;
    private RadioButton mRBVietnamese;
    private RadioButton mRBEnglish;
    private CheckBox mCBNotification;
    private TextView mTVLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.sharedComponent().inject(this);

        super.onCreate(savedInstanceState);

        setBindingContentView(R.layout.activity_settings, BR.viewModel);

        setToolbar(R.id.toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getString(R.string.settings));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        mSharedPreferences = getSharedPreferences(Configuration.APP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        RadioGroup rdgLanguage = (RadioGroup) findViewById(R.id.rdgLanguage);
        mRBVietnamese = (RadioButton) findViewById(R.id.rbVietnamese);
        mRBEnglish = (RadioButton) findViewById(R.id.rbEnglish);
        mCBNotification = (CheckBox) findViewById(R.id.cbNotification);
        mTVLanguage = (TextView) findViewById(R.id.txtLanguage);

        String language = mSharedPreferences.getString(Configuration.LANGUAGE, "en");
        if (language.equals("en")) {
            mRBEnglish.setChecked(true);
        } else if (language.equals("vi")) {
            mRBVietnamese.setChecked(true);
        }

        rdgLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbEnglish) {
                    setLanguage("en");
                    mSharedPreferences.edit()
                            .putString(Configuration.LANGUAGE, "en")
                            .apply();
                } else if (checkedId == R.id.rbVietnamese) {
                    setLanguage("vi");
                    mSharedPreferences.edit()
                            .putString(Configuration.LANGUAGE, "vi")
                            .apply();
                }
                mRBVietnamese.setText(getString(R.string.vietnamese));
                mRBEnglish.setText(getString(R.string.english));
                mCBNotification.setText(getString(R.string.settings_notification));
                mTVLanguage.setText(getString(R.string.language));
                setTitle(getString(R.string.settings));

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
