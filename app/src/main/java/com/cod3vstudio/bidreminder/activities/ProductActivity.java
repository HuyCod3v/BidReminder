package com.cod3vstudio.bidreminder.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;

import com.cod3vstudio.bidreminder.App;
import com.cod3vstudio.bidreminder.BR;
import com.cod3vstudio.bidreminder.R;
import com.cod3vstudio.bidreminder.databinding.ActivityProductBinding;
import com.cod3vstudio.core.util.Constants;
import com.cod3vstudio.core.view.BaseActivity;
import com.cod3vstudio.core.viewmodel.ProductViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;

@EActivity
public class ProductActivity extends BaseActivity<ActivityProductBinding, ProductViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.sharedComponent().inject(this);

        super.onCreate(savedInstanceState);

        setBindingContentView(R.layout.activity_product, BR.viewModel);
        setToolbar(R.id.toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }


        LineChart chart = (LineChart) findViewById(R.id.chart);
        chart.setDescription("Price chart (" + mViewModel.getProduct().getCurrencyUnit() + ")");
        chart.setDescriptionTextSize(14);

        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(false);

        chart.getAxisRight().setEnabled(false);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setDrawGridLines(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_bid:
                mViewModel.showBidPageCommand();
                break;
            case R.id.action_favorite:
                if (item.isChecked()) {
                    item.setIcon(R.drawable.ic_favorite_normal);
                    item.setChecked(false);
                } else {
                    item.setIcon(R.drawable.ic_favorite_active);
                    item.setChecked(true);
                }

                setSaved(!item.isChecked());

                break;
            case R.id.action_buy:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product, menu);

        if (mViewModel.isSaved()) {
            menu.getItem(2).setChecked(true);
            menu.getItem(2).setIcon(R.drawable.ic_favorite_active);
        }

        return true;
    }

    @Background
    private void setSaved(boolean isSaved) {
        mViewModel.setSaved(isSaved);
    }

}
