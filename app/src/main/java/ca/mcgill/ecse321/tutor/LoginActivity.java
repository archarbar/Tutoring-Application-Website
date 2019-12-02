package ca.mcgill.ecse321.tutor;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

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
    private TextView successfulMessage;
    private String error = "";

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

        // Determine what to do when signUpButton is clicked
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                login();
            }
        });
    }

    private void openRegisterPage(){
        Intent newIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(newIntent);
    }

    private void openMainPage() {
        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }

    /**
     * Action handler for login button.
     */
    public void login() {
        Log.d("ok","ok");
        error = "";

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.password);

        RequestParams params = new RequestParams();
        params.put("Email", username.getText().toString());
        params.put("Password", password.getText().toString());
        Log.d("email", username.getText().toString());
        Log.d("password",password.getText().toString() );
        String url = "login/" + username.getText().toString() + '/' + password.getText().toString();
        Log.d("url", url);

        HttpUtils.get("login/" + username.getText().toString() + '/' + password.getText().toString(), null, new JsonHttpResponseHandler() {
//        HttpUtils.get("login?Email=" + username.getText().toString() + "&Password=" + password.getText().toString(),  new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                refreshErrorMessage();
                successfulMessage.setText("Successfully logged in!");
                Log.v("Login", "onSuccess" +  response.toString());
                openMainPage();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, errorResponse.toString(), throwable);
                Log.v("Login", "onFailure" +  errorResponse.toString());

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
