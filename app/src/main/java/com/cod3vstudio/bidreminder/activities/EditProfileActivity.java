package com.cod3vstudio.bidreminder.activities;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.cod3vstudio.bidreminder.App;
import com.cod3vstudio.bidreminder.BR;
import com.cod3vstudio.bidreminder.R;
import com.cod3vstudio.bidreminder.databinding.ActivityEditProfileBinding;
import com.cod3vstudio.core.view.BaseActivity;
import com.cod3vstudio.core.viewmodel.EditProfileViewModel;

public class EditProfileActivity extends BaseActivity<ActivityEditProfileBinding, EditProfileViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.sharedComponent().inject(this);

        super.onCreate(savedInstanceState);

        setBindingContentView(R.layout.activity_edit_profile, BR.viewModel);

        setToolbar(R.id.toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getString(R.string.edit_profile));

        AppCompatAutoCompleteTextView txtNation = (AppCompatAutoCompleteTextView) findViewById(R.id.txtNation);
        String[] countries  = getResources().getStringArray(R.array.countries);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        txtNation.setAdapter(adapter);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_update:
                mViewModel.updateProfileCommand();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_profile, menu);
        return true;
    }
}
