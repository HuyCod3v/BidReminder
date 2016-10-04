package com.cod3vstudio.bidreminder.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cod3vstudio.bidreminder.App;
import com.cod3vstudio.bidreminder.BR;
import com.cod3vstudio.bidreminder.R;
import com.cod3vstudio.bidreminder.databinding.ActivityMainBinding;
import com.cod3vstudio.bidreminder.fragments.BiddingFragment;
import com.cod3vstudio.bidreminder.fragments.CartFragment;
import com.cod3vstudio.bidreminder.fragments.HomeFragment;
import com.cod3vstudio.bidreminder.fragments.SavedFragment;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.BaseActivity;
import com.cod3vstudio.core.viewmodel.MainViewModel;

import java.util.Locale;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.sharedComponent().inject(this);
        super.onCreate(savedInstanceState);

        setBindingContentView(R.layout.activity_main, BR.viewModel);

        setToolbar(R.id.toolbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);
        changeFragment(new HomeFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(com.cod3vstudio.core.util.Configuration.APP_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String language = sharedPreferences.getString(com.cod3vstudio.core.util.Configuration.LANGUAGE, "en");
        if (!language.equals(mCurrentLocale)) {
            recreate();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            mViewModel.getNavigator().navigateTo(Constants.FILTER_PAGE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        if (item.isChecked()) {
            closeDrawer();
            return true;
        }

        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                changeFragment(new HomeFragment());
                break;
            case R.id.nav_saved:
                changeFragment(new SavedFragment());
                break;
            case R.id.nav_bidding:
                if (mViewModel.isSignedUserAvailable()) {
                    changeFragment(new BiddingFragment());
                    break;
                } else {
                    mViewModel.getNavigator().navigateTo(Constants.SIGN_IN_PAGE);
                    return false;
                }
            case R.id.nav_cart:
                if (mViewModel.isSignedUserAvailable()) {
                    changeFragment(new CartFragment());
                    break;
                }
                else {
                    mViewModel.getNavigator().navigateTo(Constants.SIGN_IN_PAGE);
                    return false;
                }
            case R.id.nav_settings:
                mViewModel.getNavigator().navigateTo(Constants.SETTINGS_PAGE);
                break;
            case R.id.nav_profile:
                mViewModel.getNavigator().navigateTo(Constants.PROFILE_PAGE);
                break;
            case R.id.nav_sign_out:
                if (mViewModel.isSignedUserAvailable()) {
                    mViewModel.signOutCommand();
                    break;
                }
                else {
                    mViewModel.getNavigator().navigateTo(Constants.SIGN_IN_PAGE);
                    return false;
                }
        }

        closeDrawer();
        return true;
    }

    private void closeDrawer() {
        mDrawer.closeDrawer(GravityCompat.START);
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commitNow();
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_main, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    private void showDialog(DialogFragment dialog) {
        dialog.show(getSupportFragmentManager(), dialog.getClass().getSimpleName());
    }

}
