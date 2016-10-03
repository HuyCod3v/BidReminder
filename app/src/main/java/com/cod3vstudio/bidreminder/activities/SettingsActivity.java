package com.cod3vstudio.bidreminder.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;

import com.cod3vstudio.bidreminder.App;
import com.cod3vstudio.bidreminder.BR;
import com.cod3vstudio.bidreminder.R;
import com.cod3vstudio.bidreminder.databinding.ActivityEditPasswordBinding;
import com.cod3vstudio.bidreminder.databinding.ActivitySettingsBinding;
import com.cod3vstudio.core.view.BaseActivity;
import com.cod3vstudio.core.viewmodel.EditPasswordViewModel;
import com.cod3vstudio.core.viewmodel.SettingsViewModel;

public class SettingsActivity extends BaseActivity<ActivitySettingsBinding, SettingsViewModel> {

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
