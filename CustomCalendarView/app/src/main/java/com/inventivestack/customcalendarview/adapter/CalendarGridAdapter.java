package com.inventivestack.customcalendarview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.inventivestack.customcalendarview.R;
import com.inventivestack.customcalendarview.listeners.OnCalenderDayClickListener;
import com.inventivestack.customcalendarview.model.CalenderEventObjects;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by akumar1 on 11/22/2017.
 */

public class CalendarGridAdapter extends ArrayAdapter {
    private static final String TAG = CalendarGridAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private List<Date> monthlyDates;
    private Calendar currentDate;
    private List<CalenderEventObjects> allEvents;
    private OnCalenderDayClickListener listener;
    SimpleDateFormat format = new SimpleDateFormat("MMM dd,yyyy");
    public CalendarGridAdapter(Context context, List<Date> monthlyDates, Calendar currentDate, List<CalenderEventObjects> allEvents, OnCalenderDayClickListener listener) {
        super(context, R.layout.calendar_single_cell_layout);
        this.monthlyDates = monthlyDates;
        this.currentDate = currentDate;
        this.allEvents = allEvents;
        this.listener = listener;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Date mDate = monthlyDates.get(position);
        final Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.calendar_single_cell_layout, parent, false);
        }
        //Add day to calendar
        TextView cellNumber = (TextView) view.findViewById(R.id.calendar_date_id);
        cellNumber.setText(String.valueOf(dayValue));


        if (displayMonth == currentMonth && displayYear == currentYear) {
            view.setBackgroundColor(Color.parseColor("#f5f5f5"));
        } else {
            view.setBackgroundColor(Color.parseColor("#f5f5f5"));
            cellNumber.setTextColor(Color.parseColor("#d7d7d7"));
        }

        Calendar currentData = Calendar.getInstance();
        if (dayValue == currentData.get(Calendar.DAY_OF_MONTH) && displayMonth == currentData.get(Calendar.MONTH) + 1 && displayYear == currentData.get(Calendar.YEAR)) {
            cellNumber.setBackgroundResource(R.drawable.current_date_bg);
            cellNumber.setTextColor(Color.parseColor("#FF0000"));
        }
        //Add events to the calendar
        TextView eventIndicator = (TextView) view.findViewById(R.id.event_id);
        Calendar eventCalendar = Calendar.getInstance();
        CalenderEventObjects eventObjects = null;
        for (int i = 0; i < allEvents.size(); i++) {
            eventCalendar.setTime(allEvents.get(i).getDate());
            if (dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                    && displayYear == eventCalendar.get(Calendar.YEAR)) {
                eventIndicator.setBackgroundColor(Color.parseColor("#FF4081"));
                cellNumber.setTextColor(Color.parseColor("#303F9F"));
                eventObjects = allEvents.get(i);
            }
        }

        final CalenderEventObjects finalEventObjects = eventObjects;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onDayClick(position, "" + format.format(dateCal.getTime()), finalEventObjects);
                }
            }
        });

        return view;
    }

    @Override
    public int getCount() {
        return monthlyDates.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return monthlyDates.get(position);
    }

    @Override
    public int getPosition(Object item) {
        return monthlyDates.indexOf(item);
    }


}