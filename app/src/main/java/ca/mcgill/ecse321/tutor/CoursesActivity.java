package ca.mcgill.ecse321.tutor;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;

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

public class CoursesActivity extends AppCompatActivity {

    /**
     * ID of the currently signed in tutor.
     */
    public String tutorId;
    /**
     * JSONArray of all courses associated with the tutor.
     */
    public JSONArray courses;
    /**
     * error from any requests will be added to this string
     */
    private String error;

    /**
     * Method that will be called on creation of activity
     * @param savedInstanceState : previous state of activity, will be null if first initialized.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        try{
            tutorId = getIntent().getStringExtra("tutorId");
            getCourses(tutorId);
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


        // Determine what to do when time slots button is clicked
        final Button timeSlotsButton = findViewById(R.id.timeslots);
        timeSlotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeSlotsPage(tutorId);
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

        // Determine what to do when the apply button is pressed.
        final Button applyButton = findViewById(R.id.courseApplyButton);
        final TextView applyText = findViewById(R.id.courseApply);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyCourse(applyText.getEditableText());
                applyText.setText("");
            }
        });

    }

    private void applyCourse(Editable editableText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Application Sent");
        builder.setMessage("Your application to teach " + editableText + " has been received.");

        builder.setNeutralButton("Sweet!",
                new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    /**
     * Method to get all courses associated with the tutor
     * Generates the table to display all courses
     * @param tutorId : ID of the currently signed in tutor.
     */
    private void getCourses(String tutorId) {
        HttpUtils.get("course/" + tutorId + "/get" , new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                courses = response;
                initializeCourses(courses);
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

    /**
     * Initialize table with all courses
     * Table ID needs to be "coursesTable"
     * @param courses : JSONArray of all courses associated with a tutor.
     */
    private void initializeCourses(JSONArray courses) {
        TableLayout stk =  findViewById(R.id.coursesTable);
        // Remove all previous views in the table
        stk.removeAllViewsInLayout();
        //Create a new table row
        TableRow tbrow0 = new TableRow(this);
        //Insert a text view for each column.
        TextView tv0 = new TextView(this);
        tv0.setText(" Course Name ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Course Level ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        stk.addView(tbrow0);
        //Iterate through all courses and assign them with the same logic outlined above.
        for (int i=0; i < courses.length(); i++) {
            try{
                JSONObject course = courses.getJSONObject(i);
                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(course.getString("courseName"));
                t1v.setTextColor(Color.BLACK);
                t1v.setGravity(Gravity.CENTER);
                tbrow.addView(t1v);
                TextView t2v = new TextView(this);
                t2v.setText(course.getString("level"));
                t2v.setTextColor(Color.BLACK);
                t2v.setGravity(Gravity.CENTER);
                tbrow.addView(t2v);
                tbrow.setId(i);
                tbrow.setPadding(0,48,0,0);
                stk.addView(tbrow);

            }catch(JSONException e){
                Log.d("courses", e.toString());
            }

        }
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
     * Method to transition to time slots page.
     * @param tutorId : the ID of the currently logged in tutor.
     */
    private void openTimeSlotsPage(String tutorId) {
        Intent mainIntent = new Intent(this, TimeSlotsActivity.class);
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