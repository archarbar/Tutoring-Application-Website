package ca.mcgill.ecse321.tutor;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Class that contains the main activity (first activity when the application starts)
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Variable that keeps track of error message
     */
    private String error = null;


    /**
     * Method that displays the error message on the screen.
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
     * Method specifying tasks on application initialization.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        refreshErrorMessage();
    }

//    /**
//     * Method that handles the login button.
//     * @param v
//     */
//    public void login(View username, View password) {
//        error = "";
//        final TextView tv = (TextView) findViewById(R.id.newperson_name);
//        final TextView tv = (TextView) findViewById(R.id.newperson_name);
//        final TextView tv = (TextView) findViewById(R.id.newperson_name);
//        final TextView tv = (TextView) findViewById(R.id.newperson_name);
//        final TextView tv = (TextView) findViewById(R.id.newperson_name);
//        final TextView tv = (TextView) findViewById(R.id.newperson_name);
//        HttpUtils.post("persons/" + tv.getText().toString(), new RequestParams(), new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                refreshErrorMessage();
//                tv.setText("");
//            }
//            @Override
//            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
//                try {
//                    error += errorResponse.get("message").toString();
//                } catch (JSONException e) {
//                    error += e.getMessage();
//                }
//                refreshErrorMessage();
//            }
//        });
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
