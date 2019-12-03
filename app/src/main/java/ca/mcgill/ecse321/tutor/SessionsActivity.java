package ca.mcgill.ecse321.tutor;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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


public class SessionsActivity extends AppCompatActivity {

    public String tutorId;
    public JSONArray sessions;
    public String error;

    /**
     * Method specifying tasks on application initialization.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Grab the activity main layout and generate it.
        setContentView(R.layout.activity_sessions);

        try{
            tutorId = getIntent().getStringExtra("tutorId");
            getSessions(tutorId);

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
        final Button coursesButton = findViewById(R.id.courses);
        coursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCoursesPage(tutorId);
            }
        });

        // Determine what to do when signUpButton is clicked
        final Button timeSlotsButton = findViewById(R.id.timeslots);
        timeSlotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeSlotsPage(tutorId);
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


//        refreshErrorMessage();
    }

    private void getSessions(String tutorId) {
        HttpUtils.get("tutoringsession/tutor/" + tutorId , new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//                refreshErrorMessage();
                Log.d("Bookings", "onSuccess");
                sessions = response;
//                init(response);
                initializeTable(sessions);
                try {
                    JSONObject booking1 = response.getJSONObject(0);
                    String bookingId = booking1.getString("bookingId");

                } catch (JSONException e) {
                    Log.d("sessions","unable to parse json");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, errorResponse.toString(), throwable);
                Log.d("booking", "fail");
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
//                refreshErrorMessage();
            }
        });
    }

    private void initializeTable(JSONArray sessions) {
        TableLayout stk =  findViewById(R.id.sessionTable);
        stk.removeAllViewsInLayout();
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Date ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Room ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Start ");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" End ");
        tv3.setTextColor(Color.BLACK);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);
        for (int i=0; i < sessions.length(); i++) {
            try{
                JSONObject session = sessions.getJSONObject(i);
                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(session.getString("sessionDate"));
                t1v.setTextColor(Color.BLACK);
                t1v.setGravity(Gravity.CENTER);
                tbrow.addView(t1v);
                TextView t2v = new TextView(this);
                t2v.setText(session.getString("room"));
                t2v.setTextColor(Color.BLACK);
                t2v.setGravity(Gravity.CENTER);
                tbrow.addView(t2v);
                TextView t3v = new TextView(this);
                String startTime = session.getJSONObject("timeSlot").getString("startTime");
                t3v.setText(startTime.substring(0, startTime.length() - 3 ));
                t3v.setTextColor(Color.BLACK);
                t3v.setGravity(Gravity.CENTER);
                tbrow.addView(t3v);
                TextView t4v = new TextView(this);
                String endTime = session.getJSONObject("timeSlot").getString("endTime");
                t4v.setText(endTime.substring(0, endTime.length() - 3 ));
                t4v.setTextColor(Color.BLACK);
                t4v.setGravity(Gravity.CENTER);
                tbrow.addView(t4v);
                tbrow.setId(i);
                tbrow.setPadding(0,48,0,0);
//                tbrow.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v){
//                        openPopUp(v.getId());
//                    }
//                });
                stk.addView(tbrow);

            }catch(JSONException e){
                Log.d("booking", e.toString());
            }

        }
    }


    private void openBookingsPage(String tutorId) {
        Intent mainIntent = new Intent(this, BookingsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }
    private void openCoursesPage(String tutorId) {
        Intent mainIntent = new Intent(this, CoursesActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }
    private void openTimeSlotsPage(String tutorId) {
        Intent mainIntent = new Intent(this, TimeSlotsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }
    private void openSettingsPage(String tutorId) {
        Intent mainIntent = new Intent(this, SettingsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }
}
