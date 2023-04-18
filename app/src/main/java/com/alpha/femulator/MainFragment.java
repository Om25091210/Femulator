package com.alpha.femulator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alpha.femulator.BMI.main_screen_bmi;
import com.alpha.femulator.Cal.show_clendar;
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

import soup.neumorphism.NeumorphCardView;

public class MainFragment extends Fragment {

    View view;String val_pred,prev_date,lop;
    TextView phase,days_left;
    NeumorphCardView clean,shower,pads,ex,bmi,gentle,cook,listen,atten,pat;
    DatabaseReference users_ref;
    String deep_link_value,deep_link_uid_value,deep_link_name,deep_link_uid_value_profile;
    View lay_user,lay_patner;
    FirebaseAuth auth;
    FirebaseUser user;
    String uid;
    private Context contextNullSafe= null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_main, container, false);
        if (contextNullSafe == null) getContextNullSafety();
        phase=view.findViewById(R.id.textView11);
        lay_user=view.findViewById(R.id.add);
        lay_patner=view.findViewById(R.id.lay_patner);
        days_left=view.findViewById(R.id.textView);

        try {
            deep_link_value = getArguments().getString("deep_link_value");//deep link value
            deep_link_uid_value = getArguments().getString("deep_link_uid_value");//deep link uid value
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            deep_link_name=getArguments().getString("deep_link_name");
            deep_link_uid_value_profile=getArguments().getString("deep_link_uid_value_profile");
        } catch (Exception e) {
            e.printStackTrace();
        }
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        if(deep_link_uid_value==null) {
            uid=user.getUid();
            Log.e("uid",uid);
            lay_user.setVisibility(View.VISIBLE);
            lay_patner.setVisibility(View.GONE);
            users_ref = FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        }
        else{
            Log.e("deep_link_uid_value",deep_link_uid_value);
            lay_user.setVisibility(View.GONE);
            lay_patner.setVisibility(View.VISIBLE);
            uid=deep_link_uid_value;
            users_ref = FirebaseDatabase.getInstance().getReference().child("users").child(deep_link_uid_value);
        }
        int c=0;
        if(getArguments()!=null)
        {
            if(getArguments().getString("val_pred")!=null) {
                val_pred = getArguments().getString("val_pred");
                prev_date = getArguments().getString("prev_date");
                lop = getArguments().getString("lop");
                Log.e("prediction", val_pred + "");
                Log.e("prediction", prev_date + "");
                Log.e("prediction", lop + "");
            }else{
                c=1;
            }
        }
        else{
            c=1;
        }
        if(c==1){
            users_ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    prev_date=snapshot.child("prev_date").getValue(String.class);
                    val_pred=snapshot.child("pred_cycle").getValue(String.class);
                    lop=snapshot.child("lop").getValue(String.class);
                    set_data(prev_date,val_pred);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        }
        gentle=view.findViewById(R.id.gentle);
        gentle.setOnClickListener(v->{
            Bundle bundle=new Bundle();
            bundle.putString("content","gentle");
            precautions precautions=new precautions();
            precautions.setArguments(bundle);
            ((FragmentActivity) getContextNullSafety()).getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right)
                    .add(R.id.drawer,precautions)
                    .addToBackStack(null)
                    .commit();
        });

        cook=view.findViewById(R.id.cook);
        cook.setOnClickListener(v->{
            Bundle bundle=new Bundle();
            bundle.putString("content","cook");
            precautions precautions=new precautions();
            precautions.setArguments(bundle);
            ((FragmentActivity) getContextNullSafety()).getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right)
                    .add(R.id.drawer,precautions)
                    .addToBackStack(null)
                    .commit();
        });

        listen=view.findViewById(R.id.listen);
        listen.setOnClickListener(v->{
            Bundle bundle=new Bundle();
            bundle.putString("content","listen");
            precautions precautions=new precautions();
            precautions.setArguments(bundle);
            ((FragmentActivity) getContextNullSafety()).getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right)
                    .add(R.id.drawer,precautions)
                    .addToBackStack(null)
                    .commit();
        });

        atten=view.findViewById(R.id.atten);
        atten.setOnClickListener(v->{
            Bundle bundle=new Bundle();
            bundle.putString("content","atten");
            precautions precautions=new precautions();
            precautions.setArguments(bundle);
            ((FragmentActivity) getContextNullSafety()).getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right)
                    .add(R.id.drawer,precautions)
                    .addToBackStack(null)
                    .commit();
        });

        pat=view.findViewById(R.id.pat);
        pat.setOnClickListener(v->{
            Bundle bundle=new Bundle();
            bundle.putString("content","pat");
            precautions precautions=new precautions();
            precautions.setArguments(bundle);
            ((FragmentActivity) getContextNullSafety()).getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right)
                    .add(R.id.drawer,precautions)
                    .addToBackStack(null)
                    .commit();
        });

        clean=view.findViewById(R.id.clean);
        clean.setOnClickListener(v->{
            Bundle bundle=new Bundle();
            bundle.putString("content","clean");
            precautions precautions=new precautions();
            precautions.setArguments(bundle);
            ((FragmentActivity) getContextNullSafety()).getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right)
                    .add(R.id.drawer,precautions)
                    .addToBackStack(null)
                    .commit();
        });
        bmi=view.findViewById(R.id.bmi);
        bmi.setOnClickListener(v->{
            Intent intent=new Intent(getContextNullSafety(), main_screen_bmi.class);
            startActivity(intent);
        });
        shower=view.findViewById(R.id.shower);
        shower.setOnClickListener(v->{
            Bundle bundle=new Bundle();
            bundle.putString("content","shower");
            precautions precautions=new precautions();
            precautions.setArguments(bundle);
            ((FragmentActivity) getContextNullSafety()).getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right)
                    .add(R.id.drawer,precautions)
                    .addToBackStack(null)
                    .commit();
        });
        pads=view.findViewById(R.id.pads);
        pads.setOnClickListener(v->{
            Bundle bundle=new Bundle();
            bundle.putString("content","pads");
            precautions precautions=new precautions();
            precautions.setArguments(bundle);
            ((FragmentActivity) getContextNullSafety()).getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right)
                    .add(R.id.drawer,precautions)
                    .addToBackStack(null)
                    .commit();
        });
        ex=view.findViewById(R.id.ex);
        ex.setOnClickListener(v->{
            Bundle bundle=new Bundle();
            bundle.putString("content","pads");
            precautions precautions=new precautions();
            precautions.setArguments(bundle);
            ((FragmentActivity) getContextNullSafety()).getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations( R.anim.enter_from_right, R.anim.exit_to_left,R.anim.enter_from_left, R.anim.exit_to_right)
                    .add(R.id.drawer,precautions)
                    .addToBackStack(null)
                    .commit();
        });
        if(c==0) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            // Parse the date string into a Date object
            Date date = null;
            try {
                date = dateFormat.parse(prev_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // Create a Calendar object and set it to the parsed date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // Subtract 10 days from the date
            int menstrual_days = calendar.get(Calendar.DAY_OF_MONTH);
            Log.e("prediction", menstrual_days + "");

            CircularDaysLeftView circular = view.findViewById(R.id.circular_days_left);
            circular.setProgress(0, 30, 30 - Integer.parseInt(lop) + 2); // Set the number of days left
            if(menstrual_days<=10) {
                circular.setStartAngle(-90f + 9f * menstrual_days);
            }
            else if(menstrual_days>10) {
                circular.setProgress(0,30,30-Integer.parseInt(lop)+1);
                circular.setStartAngle(-90f + 12f * menstrual_days);
            }

            circular.setOnClickListener(v -> {
                Intent intent = new Intent(getContextNullSafety(), show_clendar.class);
                startActivity(intent);
            });

            int Follicular_phase_length = (int) (Double.parseDouble(val_pred) - 14);
            int Luteal_phase_length = (int) (Double.parseDouble(val_pred) - Follicular_phase_length);

            Calendar firstDayOfPeriod = Calendar.getInstance();
            firstDayOfPeriod.set(Calendar.YEAR, Calendar.MONTH, menstrual_days); // replace with actual date of first day of menstrual period

//////////////////////////////////////////////////////////////////////////////////////get predicted date
            Date da = null;
            try {
                da = dateFormat.parse(prev_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(da);
            cal1.add(Calendar.DAY_OF_MONTH, (int) Double.parseDouble(val_pred));
            ;

            Calendar today1 = Calendar.getInstance();
            Calendar targetDate = Calendar.getInstance();
            targetDate.set(Calendar.MONTH, cal1.get(Calendar.MONTH));
            targetDate.set(Calendar.DAY_OF_MONTH, cal1.get(Calendar.DAY_OF_MONTH)); // set target day

            // calculate the difference between the two dates in milliseconds
            long timeDiffInMillis = targetDate.getTimeInMillis() - today1.getTimeInMillis();

            // convert the difference to days
            long daysLeft = timeDiffInMillis / (24 * 60 * 60 * 1000);
            Log.e("DaysLEft", daysLeft + "");
            days_left.setText(daysLeft + " Days till next period arrives.");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
            String formattedDate = sdf.format(targetDate.getTime());
            users_ref.child("data").child(formattedDate+"").setValue(targetDate.getTime()+"");
/////////////////////////////////////////////////////////////////////////////////////////////////////

            Date date2 = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date2);
            int today = cal.get(Calendar.DAY_OF_MONTH);
            Log.e("today", today + "");

            Date date1 = null;
            try {
                date1 = dateFormat.parse(prev_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);
            calendar1.add(Calendar.DAY_OF_MONTH, -14);
            int day = calendar1.get(Calendar.DAY_OF_MONTH);

            Calendar ovulationDay = Calendar.getInstance();
            ovulationDay.set(Calendar.YEAR, Calendar.MONTH, day); // replace with actual date of ovulation day
            Log.e("Ovulation", "The ovulation day is " + ovulationDay.get(Calendar.DAY_OF_MONTH) + "th day");
            // Calculate the ovulation phase
            int ovulationPhase = ovulationDay.get(Calendar.DAY_OF_MONTH)
                    - (firstDayOfPeriod.get(Calendar.DAY_OF_MONTH) + Follicular_phase_length);

            Log.e("Ovulation", "The ovulation phase is " + ovulationPhase + " days");

            if (today == ovulationDay.get(Calendar.DAY_OF_MONTH)) {
                phase.setText("Ovulation Phase");
            }
            users_ref.child("prev_date").setValue(prev_date);
            users_ref.child("pred_cycle").setValue(val_pred);
            users_ref.child("lop").setValue(lop);
        }
        //TODO: ovulation date se less matlb follicle phase otherwise greater toh Luteal phase.
        //TODO: pull back to form.
        //TODO: Calendar optimize.
        //TODO: Logs
        //TODO: Patner login and view.
        //TODO: Notify when patner is in period stage.
        //saving data for next run computation.
        return view;
    }

    private void set_data(String prev_date,String val_pred) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        // Parse the date string into a Date object
        Date date = null;
        try {
            date = dateFormat.parse(prev_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Create a Calendar object and set it to the parsed date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // Subtract 10 days from the date
        int menstrual_days = calendar.get(Calendar.DAY_OF_MONTH);
        Log.e("prediction", menstrual_days + "");

        CircularDaysLeftView circular = view.findViewById(R.id.circular_days_left);
        circular.setProgress(0,30,30-Integer.parseInt(lop)+2); // Set the number of days left
        if(menstrual_days<=10) {
            circular.setStartAngle(-90f + 9f * menstrual_days);
        }
        else if(menstrual_days>10) {
            circular.setProgress(0,30,30-Integer.parseInt(lop)+1);
            circular.setStartAngle(-90f + 12f * menstrual_days);
        }

        circular.setOnClickListener(v->{
            Intent intent=new Intent(getContextNullSafety(), show_clendar.class);
            startActivity(intent);
        });

        int Follicular_phase_length = (int) (Double.parseDouble(val_pred)-14);
        int Luteal_phase_length = (int) (Double.parseDouble(val_pred)- Follicular_phase_length);

        Calendar firstDayOfPeriod = Calendar.getInstance();
        firstDayOfPeriod.set(Calendar.YEAR, Calendar.MONTH, menstrual_days); // replace with actual date of first day of menstrual period

//////////////////////////////////////////////////////////////////////////////////////get predicted date
        Date da=null;
        try {
            da = dateFormat.parse(prev_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(da);
        cal1.add(Calendar.DAY_OF_MONTH, (int)Double.parseDouble(val_pred));;

        Calendar today1 = Calendar.getInstance();
        Calendar targetDate = Calendar.getInstance();
        targetDate.set(Calendar.MONTH, cal1.get(Calendar.MONTH));
        targetDate.set(Calendar.DAY_OF_MONTH, cal1.get(Calendar.DAY_OF_MONTH)); // set target day

        // calculate the difference between the two dates in milliseconds
        long timeDiffInMillis = targetDate.getTimeInMillis() - today1.getTimeInMillis();

        // convert the difference to days
        long daysLeft = timeDiffInMillis / (24 * 60 * 60 * 1000);
        Log.e("DaysLEft",daysLeft+"");
        days_left.setText(daysLeft+" Days till next period arrives.");
        Log.e("Next date",targetDate.getTime()+"");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String formattedDate = sdf.format(targetDate.getTime());
        users_ref.child("data").child(formattedDate+"").setValue(targetDate.getTime()+"");
/////////////////////////////////////////////////////////////////////////////////////////////////////

        Date date2=new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date2);
        int today = cal.get(Calendar.DAY_OF_MONTH);
        Log.e("today",today+"");


        Date date1=null;
        try {
            date1 = dateFormat.parse(prev_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar1.add(Calendar.DAY_OF_MONTH, -14);
        int day = calendar1.get(Calendar.DAY_OF_MONTH);

        Calendar ovulationDay = Calendar.getInstance();
        ovulationDay.set(Calendar.YEAR, Calendar.MONTH, day); // replace with actual date of ovulation day
        Log.e("Ovulation","The ovulation day is " + ovulationDay.get(Calendar.DAY_OF_MONTH) + "th day");
        // Calculate the ovulation phase
        int ovulationPhase = ovulationDay.get(Calendar.DAY_OF_MONTH)
                - (firstDayOfPeriod.get(Calendar.DAY_OF_MONTH) + Follicular_phase_length);

        Log.e("Ovulation","The ovulation phase is " + ovulationPhase + " days");

        if(today==ovulationDay.get(Calendar.DAY_OF_MONTH)){
            phase.setText("Ovulation Phase");
        }
    }

    /**CALL THIS IF YOU NEED CONTEXT*/
    public Context getContextNullSafety() {
        if (getContext() != null) return getContext();
        if (getActivity() != null) return getActivity();
        if (contextNullSafe != null) return contextNullSafe;
        if (getView() != null && getView().getContext() != null) return getView().getContext();
        if (requireContext() != null) return requireContext();
        if (requireActivity() != null) return requireActivity();
        if (requireView() != null && requireView().getContext() != null)
            return requireView().getContext();

        return null;

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        contextNullSafe = context;
    }
}