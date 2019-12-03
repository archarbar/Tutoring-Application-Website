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

    /**
     * ID of the tutor currently signed in.
     */
    public String tutorId;
    /**
     * JSONArray of all time slots associated with the tutor.
     */
    public JSONArray timeSlots;
    /**
     * contains errors from requests made in this activity
     */
    private String error;
    /**
     * Day of the week currently being selected in spinner.
     */
    public String dayOfTheWeek;
    /**
     * ID of the time select that was clicked on.
     */
    public int selectedTimeSlotId;

    /**
     * Called upon creation of activity.
     * @param savedInstanceState : previous state of activity if there was one.
     */
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
        // Determine what to do when bookings button is clicked
        final Button bookingsButton = findViewById(R.id.bookings);
        bookingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookingsPage(tutorId);
            }
        });

        // Determine what to do when sessions button is clicked
        final Button sessionsButton = findViewById(R.id.sessions);
        sessionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSessionsPage(tutorId);
            }
        });

        // Determine what to do when courses button is clicked
        final Button coursesButton = findViewById(R.id.courses);
        coursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCoursesPage(tutorId);
            }
        });

        // Determine what to do when settings button is clicked
        final Button settingsButton = findViewById(R.id.settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettingsPage(tutorId);
            }
        });

        // All fields necessary to create a new time slot.
        // Button that will add new time slots if other fields are entered
        final Button addTimeSlotButton = findViewById(R.id.addTimeSlotButton);
        // Starting time.
        final TextView startTime = findViewById(R.id.startTimeTimeSlots);
        // Ending time.
        final TextView endTime = findViewById(R.id.endTimeTimeSlots);
        // Spinner for the day of the week.
        final Spinner dayOfTheWeekSpinner = findViewById(R.id.spinnerTimeSlots);
        //Method that will correctly update dayOfTheWeek everytime the spinner changers.
        dayOfTheWeekSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                try {
                    dayOfTheWeek =parentView.getItemAtPosition(position).toString();
                }
                catch (Exception e) {
                    error = error + e.toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        // Event listener for when the add time slot button is pressed.
        addTimeSlotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTimeSlot(startTime.getText().toString(), endTime.getText().toString(), dayOfTheWeek);
            }
        });
    }

    /**
     * Method to add a new time slot for the tutor.
     * @param startTime : start time of time slot.
     * @param endTime : end time of time slot.
     * @param dayOfTheWeek : day of the week associated with the time slot.
     */
    private void addTimeSlot(String startTime, String endTime, String dayOfTheWeek) {
        // If both start and end time are correctly formatted, send the request
        if(verifyTime(startTime) && verifyTime(endTime)){
            RequestParams params = new RequestParams();
            params.put("startTime", startTime);
            params.put("endTime", endTime);
            params.put("dayOfTheWeek", dayOfTheWeek);
            params.put("tutorId", tutorId);
            // Generate a new request and update the current table of time slots.
            try{
                HttpUtils.post("timeslot/tutor/new", params, new JsonHttpResponseHandler() {});
                getTimeSlots(tutorId);
            }catch (Exception e){
                getTimeSlots(tutorId);
            }

        }else{
            refreshErrorMessage("Please enter time with format HH:MM:SS");
        }
    }

    /**
     * Updates the error message in case time slots are entered incorrectly.
     * @param s : error message
     */
    private void refreshErrorMessage(String s) {
        final TextView errorField = findViewById(R.id.timeSlotError);
        errorField.setText(s);
    }

    /**
     * Method to verify that the time entered is formatted correctly.
     * @param startTime : time to be verified for valid formatting
     * @return true if time is valid, false otherwise.
     */
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

    /**
     * Method that retrieves all time slots associated with the tutor.
     * Generates a table of all time slots after.
     * @param tutorId : ID of the tutor currently signed in.
     */
    private void getTimeSlots(String tutorId) {
        HttpUtils.get("timeslot/tutor/" + tutorId , new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
                timeSlots = response;
                initializeTimeSlots(timeSlots);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                super.onFailure(statusCode, headers, errorResponse.toString(), throwable);
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
            }
        });
    }

    /**
     * Initializes a table of all time slots associated with the tutor
     * Need table id to be "timeSlotsTable".
     * @param timeSlots : JSONArray of all time slots associated with tutor.
     */
    private void initializeTimeSlots(JSONArray timeSlots) {
        TableLayout stk =  findViewById(R.id.timeSlotsTable);
        // Removes all previous views in table
        stk.removeAllViewsInLayout();
        //Generates all column names
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
        // Add column names to the table
        stk.addView(tbrow0);
        // Iterate through all time slots.
        for (int i=0; i < timeSlots.length(); i++) {
            try{
                JSONObject timeSlot = timeSlots.getJSONObject(i);
                //Generate a new row for each time slot.
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
                // Create an on click listener when a time slot is clicked on.
                tbrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        // Calls this method every time a time slot is clicked.
                        openPopUp(v.getId());
                    }
                });
                stk.addView(tbrow);
            }catch(JSONException e){
                error += e.toString();
            }
        }
    }

    /**
     * Method to generate an alert when a time slot is clicked.
     * @param id : index of the time slot being pressed.
     */
    private void openPopUp(int id) {
        String startTime = null;
        String endTime = null;
        String dayOfTheWeek = null;
        try{
            // Parse though the time slot to retrieve the time slot information.
            JSONObject timeslot = timeSlots.getJSONObject(id);
            dayOfTheWeek = timeslot.getString("dayOfTheWeek");
            startTime = timeslot.getString("startTime");
            endTime = timeslot.getString("endTime");
            selectedTimeSlotId = timeslot.getInt("timeSlotId");
        }catch(JSONException e){
            error += e.toString();
        }
        //Create a new alert dialog
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
                deleteTimeSlot(selectedTimeSlotId, tutorId);

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Method to delete a selected time slot
     * @param selectedTimeSlotId : ID of the time slot to be deleted.
     * @param tutorId : ID of the tutor associated with the time slot.
     */
    private void deleteTimeSlot(int selectedTimeSlotId, String tutorId) {
        // Create the parameters for the request.
        RequestParams params = new RequestParams();
        params.put("timeSlotId", Integer.toString(selectedTimeSlotId));
        params.put("tutorId", tutorId);
        try{
            HttpUtils.delete("timeslot/tutor/delete", params, new JsonHttpResponseHandler(){
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                    super.onFailure(statusCode, headers, errorResponse.toString(), throwable);
                    try {
                        if(errorResponse.get("message").toString().contains("SQL")){
                            refreshErrorMessage("There is a booking associated with this time slot!");
                        }
                        error += errorResponse.get("message").toString();
                    } catch (JSONException e) {
                        error += e.getMessage();
                    }
                }
            });
            getTimeSlots(tutorId);
        }catch(Exception e){
            getTimeSlots(tutorId);
        }

        // After deleting the time slot, generate a new table with all time slots.

    }

    /**
     * Method to transition to bookings page.
     * @param tutorId : the ID of the currently logged in tutor.
     */
    private void openBookingsPage(String tutorId) {
        Intent mainIntent = new Intent(this, BookingsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }
    /**
     * Method to transition to sessions page.
     * @param tutorId : the ID of the currently logged in tutor.
     */
    private void openSessionsPage(String tutorId) {
        Intent mainIntent = new Intent(this, SessionsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }
    /**
     * Method to transition to courses page.
     * @param tutorId : the ID of the currently logged in tutor.
     */
    private void openCoursesPage(String tutorId) {
        Intent mainIntent = new Intent(this, CoursesActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }
    /**
     * Method to transition to settings page.
     * @param tutorId : the ID of the currently logged in tutor.
     */
    private void openSettingsPage(String tutorId) {
        Intent mainIntent = new Intent(this, SettingsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }
}
