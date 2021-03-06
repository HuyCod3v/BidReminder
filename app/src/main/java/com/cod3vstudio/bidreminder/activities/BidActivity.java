package com.cod3vstudio.bidreminder.activities;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.cod3vstudio.bidreminder.App;
import com.cod3vstudio.bidreminder.BR;
import com.cod3vstudio.bidreminder.R;
import com.cod3vstudio.bidreminder.databinding.ActivityBidBinding;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.BaseActivity;
import com.cod3vstudio.core.viewmodel.BidViewModel;

public class BidActivity extends BaseActivity<ActivityBidBinding, BidViewModel> {

    private CheckBox mCBIsBuyAutomatically;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.sharedComponent().inject(this);

        super.onCreate(savedInstanceState);

        setBindingContentView(R.layout.activity_bid, BR.viewModel);

        setToolbar(R.id.toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle(getString(R.string.bid));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        mCBIsBuyAutomatically = (CheckBox) findViewById(R.id.cbIsBuyAutomatically);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_bid:
                if (mViewModel.isSignedUserAvailable()) {
                    mViewModel.bid(mCBIsBuyAutomatically.isChecked());
                } else {
                    mViewModel.getNavigator().navigateTo(Constants.SIGN_IN_PAGE);
                }

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bid, menu);

        return true;
    }
}
