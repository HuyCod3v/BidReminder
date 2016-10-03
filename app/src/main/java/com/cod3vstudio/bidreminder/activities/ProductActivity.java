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
import com.cod3vstudio.core.view.BaseActivity;
import com.cod3vstudio.core.viewmodel.ProductViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;

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
                return true;
            case R.id.action_bid:
                return true;
            case R.id.action_favorite:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product, menu);
        return true;
    }

}
