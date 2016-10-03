package com.cod3vstudio.bidreminder.util;

import com.cod3vstudio.core.model.entities.Change;
import com.cod3vstudio.core.util.Configuration;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by quanghuymr403 on 25/09/2016.
 */
public class ChartValueFormatter implements ValueFormatter {
    private DecimalFormat mDecimalFormat;
    private SimpleDateFormat mDateFormat;

    public ChartValueFormatter() {
        mDecimalFormat = new DecimalFormat("###,###,##0.0");
        mDateFormat = new SimpleDateFormat(Configuration.CHART_DATE_FORMAT);
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        Change change = (Change) entry.getData();
        return mDecimalFormat.format(value) + "\r" + mDateFormat.format(change.getChangedAt());
    }
}
