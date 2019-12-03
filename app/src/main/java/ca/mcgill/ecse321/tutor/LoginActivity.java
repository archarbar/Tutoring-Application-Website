package ca.mcgill.ecse321.tutor;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

import ca.mcgill.ecse321.tutor.utils.HttpUtils;
import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {

    /**
     * String representing the error from requests
     */
    private String error = "";

    /**
     * Method called on creation of activity
     * @param savedInstanceState : previous instance of the activity
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.LoginToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login");


        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.Login);
        final ProgressBar loadingProgressBar = findViewById(R.id.PasswordProgress);

        // Determine what to do when signUpButton is clicked
        final Button signUpButton = findViewById(R.id.signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterPage();
            }
        });

        // Determine what to do when login is clicked
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                login();
            }
        });
    }

    /**
     * Transition between login activity and registration activity
     */
    private void openRegisterPage(){
        Intent newIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(newIntent);
    }

    /**
     * Transition between login activity to booking activity.
     * @param tutorId
     */
    private void openBookingsPage(String tutorId) {
        Intent mainIntent = new Intent(LoginActivity.this, BookingsActivity.class);
        mainIntent.putExtra("tutorId", tutorId);
        startActivity(mainIntent);
    }

    /**
     * Method that attempts to login the tutor
     * If the information is wrong it will be displayed in the loginMessage Text View.
     * If successful, the tutor will be redirected to his bookings.
     */
    public void login() {
        error = "";
        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);
        final TextView loginMessage = (TextView) findViewById(R.id.loginMessage);
        // Create parameters for the request.
        RequestParams params = new RequestParams();
        params.put("Email", username.getText().toString());
        params.put("Password", password.getText().toString());

        // Send the login request
        HttpUtils.get("login/" + username.getText().toString() + '/' + password.getText().toString(), null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                super.onSuccess(statusCode, headers, response);
                Log.v("Login", "onSuccess" +  response.toString());
                refreshErrorMessage();
                try {
                    response.get("tutorId");
                    String tutorId = response.getString("tutorId");
                    loginMessage.setText("Successfully logged in!");
                    // Transition to bookings and pass the tutorId for future calls.
                    openBookingsPage(tutorId);
                }catch (JSONException e){
                    Log.d("json", e.toString());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, errorResponse.toString(), throwable);
                Log.v("Login", "onFailure" +  errorResponse.toString());
                // update the login message for the tutor to see.
                loginMessage.setText("Invalid Email / Password");
                try {
                    error += errorResponse.get("message").toString();
                } catch (JSONException e) {
                    error += e.getMessage();
                }
                refreshErrorMessage();
            }
        });
    }

    /**
     * Method that shows the error message.
     */
    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = (TextView) findViewById(R.id.registrationError);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }

}
