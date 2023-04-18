package com.alpha.femulator.Cal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.alpha.femulator.R;
import com.alpha.mylibrary2.RobotoCalendarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class show_clendar extends AppCompatActivity implements RobotoCalendarView.RobotoCalendarListener, LocationListener {

    RobotoCalendarView robotoCalendarView;
    String val_pred,prev_date;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference users_ref;
    String store_current=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_clendar);

        robotoCalendarView = findViewById(R.id.roboto);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        users_ref= FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
        Calendar current_date=Calendar.getInstance();
        int ch_current=0;
        int whiteend_current=0;
        String str_current=current_date.getTime()+"";
        String slice_current=str_current.substring(0,10);
        for(char c:str_current.toCharArray()){
            ch_current++;
            if(Character.isWhitespace(c)){
                whiteend_current++;
            }
            if(whiteend_current==5){
                String slice2=str_current.substring(ch_current);
                store_current=slice_current+" "+slice2;
                break;
            }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 5);

        // Set listener, in this case, the same activity
        robotoCalendarView.setRobotoCalendarListener(show_clendar.this);

        robotoCalendarView.setShortWeekDays(false);

        robotoCalendarView.showDateTitle(true);

        robotoCalendarView.setDate(new Date());

        users_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                prev_date=snapshot.child("prev_date").getValue(String.class);
                val_pred=snapshot.child("pred_cycle").getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        get_data();

    }

    private void get_data() {
        users_ref.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Log.e("dates",ds.getValue(String.class));
                    SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
                    try {
                        Date date = sdf.parse(ds.getValue(String.class));
                        //for getting date month
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        int given_month = cal.get(Calendar.MONTH);
                        //for getting current month
                        Calendar current_cal = Calendar.getInstance();
                        int current_month = current_cal.get(Calendar.MONTH);
                        if(given_month==current_month) {
                            robotoCalendarView.markCircleImage1(date);
                            for (int i = 0; i < 4; i++) {
                                // Add one day to the calendar
                                cal.add(Calendar.DAY_OF_MONTH, 1);

                                // Get the next date as a Date object
                                Date nextDate = cal.getTime();
                                robotoCalendarView.markCircleImage1(nextDate);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onDayClick(Date date) {
    }

    @Override
    public void onDayLongClick(Date date) {

    }

    @Override
    public void onRightButtonClick() {
        TextView textView=robotoCalendarView.findViewById(com.alpha.mylibrary2.R.id.monthText);
        String[] text_year =textView.getText().toString().split(" ");
        Calendar calendar = Calendar.getInstance();
        String extracted_year;
        if (text_year.length==2)
            extracted_year = text_year[1];
        else
            extracted_year = calendar.get(Calendar.YEAR)+"";
        String sub_text = textView.getText().toString().substring(0,3);
        if(sub_text.equals("Jan")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,0);
        }
        if(sub_text.equals("Feb")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,1);
        }
        if(sub_text.equals("Mar")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,2);
        }
        if(sub_text.equals("Apr")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,3);
        }
        if(sub_text.equals("May")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,4);
        }
        if(sub_text.equals("Jun")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,5);
        }
        if(sub_text.equals("Jul")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,6);
        }
        if(sub_text.equals("Aug")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,7);
        }
        if(sub_text.equals("Sep")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,8);
        }
        if(sub_text.equals("Oct")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,9);
        }
        if(sub_text.equals("Nov")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,10);
        }
        if(sub_text.equals("Dec")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,11);
        }
        users_ref.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Log.e("dates",ds.getValue(String.class));
                    SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
                    try {
                        Date date = sdf.parse(ds.getValue(String.class));
                        //for getting date month
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        int given_month = cal.get(Calendar.MONTH);
                        //for getting current month
                        int current_month = calendar.get(Calendar.MONTH);
                        if(given_month==current_month) {
                            robotoCalendarView.markCircleImage1(date);
                            for (int i = 0; i < 4; i++) {
                                // Add one day to the calendar
                                cal.add(Calendar.DAY_OF_MONTH, 1);

                                // Get the next date as a Date object
                                Date nextDate = cal.getTime();
                                robotoCalendarView.markCircleImage1(nextDate);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onLeftButtonClick() {
        TextView textView=robotoCalendarView.findViewById(com.alpha.mylibrary2.R.id.monthText);
        String[] text_year =textView.getText().toString().split(" ");
        Calendar calendar = Calendar.getInstance();
        String extracted_year;
        if (text_year.length==2)
            extracted_year = text_year[1];
        else
            extracted_year = calendar.get(Calendar.YEAR)+"";
        String sub_text = textView.getText().toString().substring(0,3);
        if(sub_text.equals("Jan")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,0);
        }
        if(sub_text.equals("Feb")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,1);
        }
        if(sub_text.equals("Mar")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,2);
        }
        if(sub_text.equals("Apr")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,3);
        }
        if(sub_text.equals("May")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,4);
        }
        if(sub_text.equals("Jun")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,5);
        }
        if(sub_text.equals("Jul")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,6);
        }
        if(sub_text.equals("Aug")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,7);
        }
        if(sub_text.equals("Sep")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,8);
        }
        if(sub_text.equals("Oct")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,9);
        }
        if(sub_text.equals("Nov")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,10);
        }
        if(sub_text.equals("Dec")){
            calendar.set(Calendar.YEAR,Integer.parseInt(extracted_year));
            calendar.set(Calendar.MONTH,11);
        }
        users_ref.child("data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    Log.e("dates",ds.getValue(String.class));
                    SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.getDefault());
                    try {
                        Date date = sdf.parse(ds.getValue(String.class));
                        //for getting date month
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        int given_month = cal.get(Calendar.MONTH);
                        //for getting current month
                        int current_month = calendar.get(Calendar.MONTH);
                        if(given_month==current_month) {
                            robotoCalendarView.markCircleImage1(date);
                            for (int i = 0; i < 4; i++) {
                                // Add one day to the calendar
                                cal.add(Calendar.DAY_OF_MONTH, 1);

                                // Get the next date as a Date object
                                Date nextDate = cal.getTime();
                                robotoCalendarView.markCircleImage1(nextDate);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}