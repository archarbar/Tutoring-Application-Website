package ca.mcgill.ecse321.tutor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.tutor.utils.HttpUtils;
import cz.msebera.android.httpclient.Header;


public class SettingsActivity extends AppCompatActivity {

    /**
     * ID of the tutor currently logged in.
     */
    public String tutorId;
    /**
     * Name of the tutor currently signed in
     */
    public String tutorName;
    /**
     * Email of the tutor currently signed in.
     */
    public String tutorEmail;
    /**
     * Hourly rate of the tutor currently signed in.
     */
    public Double tutorHourlyRate;
    /**
     * contains errors from requests.
     */
    private String error;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        try{
            tutorId = getIntent().getStringExtra("tutorId");
            getSettings(tutorId);
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

        // Determine what to do when time slots button is clicked
        final Button timeSlotsButton = findViewById(R.id.timeslots);
        timeSlotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeSlotsPage(tutorId);
            }
        });

    }

    /**
     * Get all user information associated with the logged in tutor.
     * Generates the information into a table for the tutor to view
     * @param tutorId : ID of the tutor currently logged in.
     */
    private void getSettings(String tutorId) {
        HttpUtils.get("tutor/" + tutorId , new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
//                refreshErrorMessage();
                try {
                    tutorName = response.getString("firstName") + " " + response.getString("lastName");
                    tutorEmail = response.getString("email");
                    tutorHourlyRate = response.getDouble("hourlyRate");
                    initializeSettings();
                } catch (JSONException e) {
                    Log.d("settings","unable to parse json");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, errorResponse.toString(), throwable);
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
            }
        });
    }

    private void initializeSettings() {
        TableLayout settingsTable = findViewById(R.id.settingsTable);
        settingsTable.removeAllViewsInLayout();
        // Generate row of attribute + value
        //First one is Name: tutorName
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText("Name: " + tutorName);
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        settingsTable.addView(tbrow0);
        //Second one is Email: tutorEmail
        TableRow tbrow1 = new TableRow(this);
        TextView tv1 = new TextView(this);
        tv1.setText("Email: " + tutorEmail);
        tv1.setTextColor(Color.BLACK);
        tbrow1.addView(tv1);
        settingsTable.addView(tbrow1);
        //Third one is hourly rate.
        TableRow tbrow2 = new TableRow(this);
        TextView tv2 = new TextView(this);
        tv2.setText("Hourly Rate: " + Double.toString(tutorHourlyRate));
        tv2.setTextColor(Color.BLACK);
        tbrow2.addView(tv2);
        //Add them to the table.
        settingsTable.addView(tbrow2);

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
     * Method to transition to time slots page.
     * @param tutorId : the ID of the currently logged in tutor.
     */
    private void openTimeSlotsPage(String tutorId) {
        Intent mainIntent = new Intent(this, TimeSlotsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }

}
