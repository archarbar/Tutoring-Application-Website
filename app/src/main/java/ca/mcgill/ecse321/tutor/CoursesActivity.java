package ca.mcgill.ecse321.tutor;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
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
import org.w3c.dom.Text;

import ca.mcgill.ecse321.tutor.utils.HttpUtils;
import cz.msebera.android.httpclient.Header;

public class CoursesActivity extends AppCompatActivity {

    public String tutorId;
    public JSONArray courses;
    private String error;

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


    private void getCourses(String tutorId) {
        HttpUtils.get("course/" + tutorId + "/get" , new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//                refreshErrorMessage();
                Log.d("Courses", "onSuccess");
                courses = response;
                initializeCourses(courses);
                try {
                    JSONObject booking1 = response.getJSONObject(0);
                    String bookingId = booking1.getString("bookingId");

                } catch (JSONException e) {
                    Log.d("Courses","unable to parse json");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, errorResponse.toString(), throwable);
                Log.d("courses", "fail");
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
//                refreshErrorMessage();
            }
        });
    }

    private void initializeCourses(JSONArray courses) {
        TableLayout stk =  findViewById(R.id.coursesTable);
        stk.removeAllViewsInLayout();
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Course Name ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Course Level ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        stk.addView(tbrow0);
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
    private void openSessionsPage(String tutorId) {
        Intent mainIntent = new Intent(this, SessionsActivity.class);
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
