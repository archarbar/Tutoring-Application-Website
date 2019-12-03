package ca.mcgill.ecse321.tutor;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.tutor.utils.HttpUtils;
import cz.msebera.android.httpclient.Header;


public class TimeSlotsActivity extends AppCompatActivity {

    public String tutorId;
    public JSONArray timeSlots;
    private String error;
    public String dayOfTheWeek;
    public int selectedTimeSlotId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slots);

        try{
            tutorId = getIntent().getStringExtra("tutorId");
            getTimeSlots(tutorId);
        }
        catch(Exception e){
            Log.d("unable to grab tutor id", e.toString());
        }
        // Determine what to do when signUpButton is clicked
        final Button bookingsButton = findViewById(R.id.bookings);
        bookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookingsPage(tutorId);
            }
        });

        // Determine what to do when signUpButton is clicked
        final Button sessionsButton = findViewById(R.id.sessions);
        sessionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSessionsPage(tutorId);
            }
        });

        // Determine what to do when signUpButton is clicked
        final Button coursesButton = findViewById(R.id.courses);
        coursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCoursesPage(tutorId);
            }
        });

        // Determine what to do when signUpButton is clicked
        final Button settingsButton = findViewById(R.id.settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingsPage(tutorId);
            }
        });

        final Button addTimeSlotButton = findViewById(R.id.addTimeSlotButton);
        final TextView startTime = findViewById(R.id.startTimeTimeSlots);
        final TextView endTime = findViewById(R.id.endTimeTimeSlots);
        final Spinner dayOftheWeekSpinner = findViewById(R.id.spinnerTimeSlots);
        dayOftheWeekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                try {

                    dayOfTheWeek =parentView.getItemAtPosition(position).toString();
                }
                catch (Exception e) {

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        addTimeSlotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTimeSlot(startTime.getText().toString(), endTime.getText().toString(), dayOfTheWeek);
            }
        });
    }

    private void addTimeSlot(String startTime, String endTime, String dayOfTheWeek) {
        Log.d("New TimeSlot", startTime + endTime + dayOfTheWeek);

        if(verifyTime(startTime) && verifyTime(endTime)){
            RequestParams params = new RequestParams();
            params.put("startTime", startTime);
            params.put("endTime", endTime);
            params.put("dayOfTheWeek", dayOfTheWeek);
            params.put("tutorId", tutorId);

            HttpUtils.post("timeslot/tutor/new", params, new JsonHttpResponseHandler() {});
            getTimeSlots(tutorId);
        }else{
            refreshErrorMessage("Please enter time with format HH:MM:SS");
        }


    }

    private void refreshErrorMessage(String s) {
        final TextView errorField = findViewById(R.id.timeSlotError);
        errorField.setText(s);
    }

    private boolean verifyTime(String startTime) {
        if(startTime.contains(":")){
            String[] times = startTime.split(":");
            if(times.length != 3){
                return false;
            }
            for(String time: times){
                if(!isNumeric(time) || time.length() != 2){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Boolean method that returns true if all values in the str are digits.
     * @param strNum : input string that must be only digits.
     * @return true if strNum is only digits, false otherwise.
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void getTimeSlots(String tutorId) {
        HttpUtils.get("timeslot/tutor/" + tutorId , new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//                refreshErrorMessage();
                Log.d("TimeSlots", "onSuccess");
                timeSlots = response;
                initializeTimeSlots(timeSlots);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, errorResponse.toString(), throwable);
                Log.d("TimeSlots", "fail");
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
//                refreshErrorMessage();
            }
        });
    }

    private void initializeTimeSlots(JSONArray timeSlots) {
        TableLayout stk =  findViewById(R.id.timeSlotsTable);
        stk.removeAllViewsInLayout();
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Day of Week ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Start Time ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2= new TextView(this);
        tv2.setText(" End Time ");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        stk.addView(tbrow0);
        for (int i=0; i < timeSlots.length(); i++) {
            try{
                JSONObject timeSlot = timeSlots.getJSONObject(i);
                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(timeSlot.getString("dayOfTheWeek"));
                t1v.setTextColor(Color.BLACK);
//                t1v.setGravity(Gravity.CENTER);
                tbrow.addView(t1v);
                TextView t2v = new TextView(this);
                t2v.setText(timeSlot.getString("startTime"));
                t2v.setTextColor(Color.BLACK);
//                t2v.setGravity(Gravity.CENTER);
                tbrow.addView(t2v);
                TextView t3v = new TextView(this);
                t3v.setText(timeSlot.getString("endTime"));
                t3v.setTextColor(Color.BLACK);
//                t3v.setGravity(Gravity.CENTER);
                tbrow.addView(t3v);
                tbrow.setId(i);
                tbrow.setPadding(0,48,0,0);
                tbrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        openPopUp(v.getId());
                    }
                });
                stk.addView(tbrow);

            }catch(JSONException e){
                Log.d("timeSlots", e.toString());
            }

        }
    }

    private void openPopUp(int id) {
        String startTime = null;
        String endTime = null;
        String dayOfTheWeek = null;
        try{
            JSONObject timeslot = timeSlots.getJSONObject(id);
            dayOfTheWeek = timeslot.getString("dayOfTheWeek");
            startTime = timeslot.getString("startTime");
            endTime = timeslot.getString("endTime");
            selectedTimeSlotId = timeslot.getInt("timeSlotId");

        }catch(JSONException e){

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Booking Information");
        builder.setMessage(
                "Day Of the Week: " + dayOfTheWeek + "\n" +
                        "Start Time: " + startTime + "\n" +
                        "End Time: " + endTime + "\n");

        builder.setNeutralButton("Ok",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

//                Log.d("timeSlot", Integer.toString(selectedTimeSlotId));
                deleteTimeSlot(selectedTimeSlotId, tutorId);

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteTimeSlot(int selectedTimeSlotId, String tutorId) {
        RequestParams params = new RequestParams();
        params.put("timeSlotId", Integer.toString(selectedTimeSlotId));
        params.put("tutorId", tutorId);
        HttpUtils.delete("timeslot/tutor/delete", params, new JsonHttpResponseHandler(){});
        getTimeSlots(tutorId);
    }


    private void openBookingsPage(String tutorId) {
        Intent mainIntent = new Intent(this, BookingsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }
    private void openSessionsPage(String tutorId) {
        Intent mainIntent = new Intent(this, SessionsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }
    private void openCoursesPage(String tutorId) {
        Intent mainIntent = new Intent(this, CoursesActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }
    private void openSettingsPage(String tutorId) {
        Intent mainIntent = new Intent(this, SettingsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }
}
