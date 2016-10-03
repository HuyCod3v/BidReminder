package com.cod3vstudio.bidreminder.activities;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.widget.EditText;

import com.cod3vstudio.bidreminder.App;
import com.cod3vstudio.bidreminder.BR;
import com.cod3vstudio.bidreminder.R;
import com.cod3vstudio.bidreminder.databinding.ActivitySignUpBinding;
import com.cod3vstudio.core.view.BaseActivity;
import com.cod3vstudio.core.viewmodel.SignUpViewModel;

public class SignUpActivity extends BaseActivity<ActivitySignUpBinding, SignUpViewModel> {

    //region Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.sharedComponent().inject(this);

        super.onCreate(savedInstanceState);

        setBindingContentView(R.layout.activity_sign_up, BR.viewModel);

        setToolbar(R.id.toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtPassword.setTypeface(Typeface.DEFAULT);

        EditText txtRetypePassword = (EditText) findViewById(R.id.txtRetypePassword);
        txtPassword.setTypeface(Typeface.DEFAULT);

        setTitle(getString(R.string.sign_up));

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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

    //endregion
}
