package com.cod3vstudio.bidreminder;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.cod3vstudio.bidreminder.util.ChartValueFormatter;
import com.cod3vstudio.core.model.entities.Change;
import com.cod3vstudio.core.view.BaseRecyclerViewAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 7/30/2016.
 */

public class AppBindingAdapter {

    //region Binding Adapters

    @BindingAdapter(value = {"items"})
    public static <T> void setAdapter(RecyclerView recyclerView, List<T> items) {
        if (recyclerView.getAdapter() instanceof BaseRecyclerViewAdapter) {
            BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.setData(items);
            }
            return;
        }
    }

    @BindingAdapter(value = {"items"})
    public static void setLineData(LineChart lineChart, List<Change> items) {
        if (items == null) {
            return;
        }

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            entries.add(new Entry(i, (float) items.get(i).getPrice(), items.get(i)));
        }
        LineDataSet dataSet = new LineDataSet(entries, null);

        dataSet.setColor(R.color.colorChart);
        dataSet.setCircleColor(R.color.colorCircleChart);
        dataSet.setValueTextColor(R.color.colorTextPrice);
        dataSet.setValueTextSize(13);
        dataSet.setValueFormatter(new ChartValueFormatter());

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    @BindingAdapter(value = {"imageUrl", "placeHolder"})
    public static void loadImage(ImageView view, String url, Drawable error) {
        Picasso.with(view.getContext())
                .load(url)
                .error(error)
                .into(view);
    }

    @BindingAdapter(value = {"imageUrl"})
    public static void loadImage(ImageView view, String url) {
        if (url == null || url.equals("")) {
            return;
        }
        Picasso.with(view.getContext())
                .load(url)
                .into(view);
    }

    //endregion

}
