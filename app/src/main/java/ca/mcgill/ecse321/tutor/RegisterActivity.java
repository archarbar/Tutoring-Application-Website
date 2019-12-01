package ca.mcgill.ecse321.tutor;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.tutor.utils.HttpUtils;
import cz.msebera.android.httpclient.Header;

public class RegisterActivity extends AppCompatActivity {

    private String error = null;

    /**
     * Method that is executed when the page is launched.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final Button register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                register(v);
            }
        });
//        Toolbar toolbar = findViewById(R.id.toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

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
     * @param v
     */
    public void register(View v) {

        error = "";

        final TextView firstNameView = (TextView) findViewById(R.id.registerTutorFirstName);
        final TextView lastNameView = (TextView) findViewById(R.id.registerTutorLastName);
        final TextView passwordView = (TextView) findViewById(R.id.registerTutorPassword);
        final TextView emailView = (TextView) findViewById(R.id.registerTutorEmail);
        final TextView successfulMessage = (TextView) findViewById(R.id.registrationMessage);

        RequestParams params = new RequestParams();
        params.put("tutorFirstName", firstNameView.getText().toString());
        params.put("tutorLastName", lastNameView.getText().toString());
        params.put("tutorEmail", passwordView.getText().toString());
        params.put("tutorPassword", emailView.getText().toString());

        HttpUtils.post("tutor/new", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                refreshErrorMessage();
                successfulMessage.setText("Successfully Registered!");
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


}
