package com.inventivestack.customcalendarview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.inventivestack.customcalendarview.adapter.CustomAdapter;
import com.inventivestack.customcalendarview.listeners.OnCalenderDayClickListener;
import com.inventivestack.customcalendarview.model.CalenderEventObjects;
import com.inventivestack.customcalendarview.widget.CalendarCustomView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnCalenderDayClickListener {
    List<CalenderEventObjects> mEvents;
    RecyclerView eventsView;
    CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarCustomView mView = (CalendarCustomView) findViewById(R.id.custom_calendar);

        mEvents = new ArrayList<>();
        mAdapter = new CustomAdapter(this);
        eventsView = findViewById(R.id.list_eventsView);
        eventsView.setHasFixedSize(true);
        eventsView.setLayoutManager(new LinearLayoutManager(this));
        eventsView.setAdapter(mAdapter);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String dtStart = "2017-11-25";
            String dtStart1 = "2017-11-26";
            Date date = format.parse(dtStart);
            Date date1 = format.parse(dtStart1);
            CalenderEventObjects event1 = new CalenderEventObjects("Hi 1", "aaaa", date);
            CalenderEventObjects event2 = new CalenderEventObjects("Hi 2", "dddddd", date1);
            mEvents.add(event1);
            mEvents.add(event2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        mView.loadCalender(mEvents, this);
    }

    @Override
    public void onDayClick(int position, String date, CalenderEventObjects eventObjects) {
        mAdapter.clearItem();
        if (eventObjects != null) {
            Log.e("eventObjects",""+eventObjects);
            mAdapter.addItem(eventObjects);
        } else {
            Toast.makeText(this, "" + date, Toast.LENGTH_SHORT).show();
        }
    }
}
