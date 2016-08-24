package hsm.yeng.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import hsm.yeng.R;
import hsm.yeng.util.Util;


/**
 * Created by rahul on 16/11/15.
 */
public class CalendarView extends LinearLayout
{
    private static final String LOGTAG = "Calendar View";
    private static final int DAYS_COUNT = 42;
    private static final String DATE_FORMAT = "MMM yyyy";
    private String mDateFormat;
    private Calendar mCurrentDate = Calendar.getInstance();
    private EventHandler mEventHandler = null;
    private LinearLayout mHeader;
    private ImageView btnPrev;
    private ImageView btnNext;
    private TextView txtDate;
    private GridView mGridView;
    int[] rainbow = new int[] {
            R.color.summer,
            R.color.fall,
            R.color.winter,
            R.color.spring
    };

    // month-season association (northern hemisphere, sorry australia :)
    int[] monthSeason = new int[] {2, 2, 3, 3, 3, 0, 0, 0, 1, 1, 1, 2};

    public CalendarView(Context context)
    {
        super(context);
    }

    public CalendarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initControl(context, attrs);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        initControl(context, attrs);
    }

    private void initControl(Context context, AttributeSet attrs)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.control_calendar, this);

        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();

        updateCalendar();
    }

    private void loadDateFormat(AttributeSet attrs)
    {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.CalendarView);

        try
        {
            // try to load provided date format, and fallback to default otherwise
            mDateFormat = ta.getString(R.styleable.CalendarView_dateFormat);
            if (mDateFormat == null)
                mDateFormat = DATE_FORMAT;
        }
        finally
        {
            ta.recycle();
        }
    }
    private void assignUiElements()
    {
        // layout is inflated, assign local variables to components
        mHeader = (LinearLayout)findViewById(R.id.calendar_header);
        btnPrev = (ImageView)findViewById(R.id.calendar_prev_button);
        btnNext = (ImageView)findViewById(R.id.calendar_next_button);
        txtDate = (TextView)findViewById(R.id.calendar_date_display);
        mGridView = (GridView)findViewById(R.id.calendar_grid);
    }

    private void assignClickHandlers()
    {
        btnNext.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mCurrentDate.add(Calendar.MONTH, 1);
                updateCalendar();
            }
        });

        btnPrev.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mCurrentDate.add(Calendar.MONTH, -1);
                updateCalendar();
            }
        });

        // long-pressing a day
        mGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id) {
                if (mEventHandler == null)
                    return false;

                mEventHandler.onDayLongPress((Date) view.getItemAtPosition(position));
                return true;
            }
        });
    }

    /**
     * Display dates correctly in mGridView
     */
    public void updateCalendar()
    {
        updateCalendar(null);
    }

    /**
     * Display dates correctly in mGridView
     */
    public void updateCalendar(HashSet<Date> events)
    {
        ArrayList<Date> cells = new ArrayList<>();
        Calendar calendar = (Calendar) mCurrentDate.clone();

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);

        while (cells.size() < DAYS_COUNT)
        {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        mGridView.setAdapter(new CalendarAdapter(getContext(), cells, events));

        SimpleDateFormat sdf = new SimpleDateFormat(mDateFormat);
        txtDate.setText(sdf.format(mCurrentDate.getTime()));

        int month = mCurrentDate.get(Calendar.MONTH);
        int season = monthSeason[month];
        int color = rainbow[season];

        mHeader.setBackgroundColor(getResources().getColor(color));
    }


    private class CalendarAdapter extends ArrayAdapter<Date>
    {
        Util util;
        private HashSet<Date> eventDays;

        private LayoutInflater inflater;
        ArrayList<DateDataModel> list;
        public CalendarAdapter(Context context, ArrayList<Date> days, HashSet<Date> eventDays)
        {

            super(context, R.layout.control_calendar_day, days);
            util=new Util();
            this.eventDays = eventDays;
            inflater = LayoutInflater.from(context);
            try {
                list=new ArrayList<>();
                JSONArray array=new JSONArray(util.loadJSONFromAsset(context,"calend.json"));
                for (int i=0;i<array.length();i++){
                    JSONObject object=array.getJSONObject(i);
                    DateDataModel dateDataModel=new DateDataModel();
                    dateDataModel.setDate(object.optString("date"));
                    dateDataModel.setEvent(object.optString("event"));
                    dateDataModel.setFlag(object.optString("flag"));
                    list.add(dateDataModel);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            Date date = getItem(position);
            int day = date.getDate();
            int month = date.getMonth();
            int year = date.getYear();
            Date today = new Date();

            if (view == null)
                view = inflater.inflate(R.layout.control_calendar_day, parent, false);

            view.setBackgroundResource(0);
            if (eventDays != null)
            {
                for (Date eventDate : eventDays)
                {
                    if (eventDate.getDate() == day &&
                            eventDate.getMonth() == month &&
                            eventDate.getYear() == year)
                    {
                        view.setBackgroundResource(R.mipmap.reminder);
                        break;
                    }
                }
            }

            ((TextView)view).setTypeface(null, Typeface.NORMAL);
            ((TextView)view).setTextColor(Color.BLACK);

            if (month != today.getMonth() || year != today.getYear())
            {
               // ((TextView)view).setTextColor(getResources().getColor(R.color.greyed_out));
            }
            else if (day == today.getDate())
            {
                ((TextView)view).setTypeface(null, Typeface.BOLD);
                ((TextView)view).setBackgroundColor(getResources().getColor(R.color.today));
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy"); // Set your date format
            String currentData = sdf.format(date);
            Log.e(list.size()+"date"+String.valueOf(date.getMonth()),String.valueOf(date.getDate())+currentData);
            for (int j=0;j<list.size();j++){
                Log.e("list Date"+list.get(j).getDate(),"calen"+currentData);
                if (list.get(j).getDate().equals(currentData)){
                    if (list.get(j).getFlag().equals("1")){
                        ((TextView)view).setTextColor(Color.RED);
                        ((TextView)view).setTypeface(null, Typeface.BOLD);
                    }
                    else{
                        ((TextView)view).setTextColor(Color.CYAN);
                        ((TextView)view).setTypeface(null, Typeface.BOLD);

                    }
                    ((TextView)view).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                }
            }
            ((TextView)view).setText(String.valueOf(date.getDate()));

            return view;
        }
    }

    /**
     * Assign event handler to be passed needed events
     */
    public void setmEventHandler(EventHandler mEventHandler)
    {
        this.mEventHandler = mEventHandler;
    }

    /**
     * This interface defines what events to be reported to
     * the outside world
     */
    public interface EventHandler
    {
        void onDayLongPress(Date date);
    }
}
