package ca.mcgill.ecse321.tutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.tutor.utils.HttpUtils;
import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppCompatActivity {
    /**
     * Contains all from requests sent from this activity.
     */
    private String error = null;

    /**
     * Method that is executed when the page is launched.
     *
     * @param savedInstanceState : previous state of activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Button register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                register();
            }
        });
        Toolbar toolbar = findViewById(R.id.RegistrationToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registration");

        refreshErrorMessage();
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

    /**
     * Action handler for registration button
     */
    public void register() {

        error = "";

        final TextView firstNameView = (TextView) findViewById(R.id.registerTutorFirstName);
        final TextView lastNameView = (TextView) findViewById(R.id.registerTutorLastName);
        final TextView passwordView = (TextView) findViewById(R.id.registerTutorPassword);
        final TextView emailView = (TextView) findViewById(R.id.registerTutorEmail);
        final TextView successfulMessage = (TextView) findViewById(R.id.registrationMessage);

        // Set up parameters.
        RequestParams params = new RequestParams();
        params.put("tutorFirstName", firstNameView.getText().toString());
        params.put("tutorLastName", lastNameView.getText().toString());
        params.put("tutorEmail", emailView.getText().toString());
        params.put("tutorPassword", passwordView.getText().toString());
        // Send post request to sign up a new tutor
        HttpUtils.post("tutor/new", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                // If successful, display a message and transition to login page.
                successfulMessage.setText("Successfully Registered!\nRedirecting to Login page");
                openLoginPage();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
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
     * Return to Login page from sign up page.
     */
    private void openLoginPage(){
            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(loginIntent);
    }


}
