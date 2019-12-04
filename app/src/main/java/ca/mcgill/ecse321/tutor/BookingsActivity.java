package ca.mcgill.ecse321.tutor;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

/**
 * Class that contains the main activity (first activity when the application starts)
 */
public class BookingsActivity extends AppCompatActivity {

    /**
     * Variable that keeps track of error message.
     */
    private String error = null;
    /**
     * The currently logged in tutor ID.
     */
    public String tutorId;
    /**
     * A JSONArray of all bookings associated with the tutor.
     */
    public JSONArray bookings;
    /**
     * A string for the currently selected bookingId.
     */
    public String bookingId;

    /**
     * method that gets all bookings associated with a tutor
     *
     * @param tutorId : the ID of the tutor (should be retrieved on login)
     */
    private void getBookings(String tutorId) {
        Log.d("booking", "sending a request with tutorId" + tutorId);
        Log.d("booking", "booking/tutor/" + tutorId);
        HttpUtils.get("booking/tutor/" + tutorId, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("Bookings", "onSuccess");
                bookings = response;
                init(response);
                try {
                    JSONObject booking1 = response.getJSONObject(0);
                    String bookingId = booking1.getString("bookingId");
                    Log.d("booking", response.toString());
                    Log.d("booking", booking1.toString());
                    Log.d("booking", bookingId);
                } catch (JSONException e) {
                    Log.d("booking", "unable to parse json");
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
            }
        });
    }

    /**
     * Method specifying tasks on application initialization.
     *
     * @param savedInstanceState : last state of the activity, will be null if first started
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Grab the activity main layout and generate it.
        setContentView(R.layout.activity_booking);

        try {
            tutorId = getIntent().getStringExtra("tutorId");
            getBookings(tutorId);
        } catch (Exception e) {
            Log.d("unable to grab tutor id", e.toString());
        }

        // Determine what to do when settings button is clicked
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

        // Determine what to do when time slot button is clicked
        final Button timeSlotsButton = findViewById(R.id.timeslots);
        timeSlotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeSlotsPage(tutorId);
            }
        });

        // Determine what to do when signOutButton is pressed.
        final Button signOutButton = findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
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

    }

    /**
     * Initialize all bookings and place them in a table
     * Need the table to have id: "bookingTable".
     *
     * @param response : JSONArray of all bookings to display
     */
    public void init(JSONArray response) {
        TableLayout stk = findViewById(R.id.bookingTable);
        //Clear all views in the table
        stk.removeAllViewsInLayout();
        // Create a new row, this will be all column titles
        TableRow tbrow0 = new TableRow(this);

        //Add a text view for each column we want.
        TextView tv0 = new TextView(this);
        tv0.setText(" Course ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Student Name ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Date ");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Start ");
        tv3.setTextColor(Color.BLACK);
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText(" End ");
        tv4.setTextColor(Color.BLACK);
        tbrow0.addView(tv4);
        stk.addView(tbrow0);
        //Iterate through the list of bookings.
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject booking1 = response.getJSONObject(i);
                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(booking1.getString("course"));
                t1v.setTextColor(Color.BLACK);
                t1v.setGravity(Gravity.CENTER);
                tbrow.addView(t1v);
                TextView t2v = new TextView(this);
                t2v.setText(booking1.getString("studentName"));
                t2v.setTextColor(Color.BLACK);
                t2v.setGravity(Gravity.CENTER);
                tbrow.addView(t2v);
                TextView t3v = new TextView(this);
                t3v.setText(booking1.getString("specificDate"));
                t3v.setTextColor(Color.BLACK);
                t3v.setGravity(Gravity.CENTER);
                tbrow.addView(t3v);
                TextView t4v = new TextView(this);
                String startTime = booking1.getJSONObject("timeSlot").getString("startTime");
                t4v.setText(startTime.substring(0, startTime.length() - 3));
                t4v.setTextColor(Color.BLACK);
                t4v.setGravity(Gravity.CENTER);
                tbrow.addView(t4v);
                TextView t5v = new TextView(this);
                String endTime = booking1.getJSONObject("timeSlot").getString("endTime");
                t5v.setText(endTime.substring(0, endTime.length() - 3));
                t5v.setTextColor(Color.BLACK);
                t5v.setGravity(Gravity.CENTER);
                tbrow.addView(t5v);
                tbrow.setId(i);
                tbrow.setPadding(0, 48, 0, 0);
                // Adding an onclick listener for when the row is clicked.
                tbrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openPopUp(v.getId());
                    }
                });
                stk.addView(tbrow);

            } catch (JSONException e) {
                Log.d("booking", e.toString());
            }
        }
    }

    /**
     * Open a pop up alert if a booking is pressed.
     *
     * @param bookingNumber : the index that the booking appears in the table.
     */
    private void openPopUp(final int bookingNumber) {
        String course = null;
        String student = null;
        String date = null;
        String startTime = null;
        String endTime = null;
        try {
            JSONObject specificBooking = bookings.getJSONObject(bookingNumber);
            course = specificBooking.getString("course");
            student = specificBooking.getString("studentName");
            date = specificBooking.getString("specificDate");
            startTime = specificBooking.getJSONObject("timeSlot").getString("startTime");
            startTime = startTime.substring(0, startTime.length() - 3);
            endTime = specificBooking.getJSONObject("timeSlot").getString("endTime");
            endTime = endTime.substring(0, endTime.length() - 3);
            bookingId = specificBooking.getString("bookingId");
        } catch (JSONException e) {
            error += e.toString();
        }

        // Create a new alert dialog with options for the booking.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Booking Information");
        builder.setMessage(
                "Course: " + course + "\n" +
                        "Student Name: " + student + "\n" +
                        "Date: " + date + "\n" +
                        "Time: " + startTime + "-" + endTime
        );

        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("booking", Integer.toString(bookingNumber));

                        acceptBooking(bookingId);

                    }
                });
        builder.setNeutralButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.d("booking", Integer.toString(bookingNumber));
                declineBooking(bookingId);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Method to delete a booking.
     * Refreshes the list of bookings after deleting.
     *
     * @param bookingId : the ID of the booking to be deleted.
     */
    private void declineBooking(String bookingId) {
        try {
            HttpUtils.delete("booking/decline/" + bookingId, new RequestParams(), new JsonHttpResponseHandler() {
            });
            getBookings(tutorId);
        } catch (Exception e) {
            getBookings(tutorId);
        }
    }

    /**
     * Method that signs out and returns to the login page.
     */
    private void signOut() {
        Intent mainIntent = new Intent(this, LoginActivity.class);
        startActivity(mainIntent);
    }

    /**
     * Method to accept a booking
     * Refreshes a list of bookings after accepting.
     *
     * @param bookingId : the ID of the booking to be accepted
     */
    private void acceptBooking(String bookingId) {
        HttpUtils.delete("booking/accept/" + bookingId, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                getBookings(tutorId);

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
     * Method to transition to sessions page.
     *
     * @param tutorId : the ID of the currently logged in tutor.
     */
    private void openSessionsPage(String tutorId) {
        Intent mainIntent = new Intent(this, SessionsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }

    /**
     * Method to transition to courses page.
     *
     * @param tutorId : the ID of the currently logged in tutor.
     */
    private void openCoursesPage(String tutorId) {
        Intent mainIntent = new Intent(this, CoursesActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }

    /**
     * Method to transition to time slots page.
     *
     * @param tutorId : the ID of the currently logged in tutor.
     */
    private void openTimeSlotsPage(String tutorId) {
        Intent mainIntent = new Intent(this, TimeSlotsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }

    /**
     * Method to transition to settings page.
     *
     * @param tutorId : the ID of the currently logged in tutor.
     */
    private void openSettingsPage(String tutorId) {
        Intent mainIntent = new Intent(this, SettingsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }

}