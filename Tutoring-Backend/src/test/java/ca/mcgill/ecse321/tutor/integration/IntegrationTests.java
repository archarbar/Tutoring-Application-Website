package ca.mcgill.ecse321.tutor.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


public class IntegrationTests {

	
	private final String BASE_URL = "https://ecse321-tutor-backend.herokuapp.com";
//	private final String BASE_URL = "https://localhost:8080";
	
    private JSONObject response;
    
    private JSONObject requestURL(String typeOfRequest, String url, String path, String parameters) {
        try {    
        	URL urll = new URL(url + path + ((parameters==null)?"":("?" + parameters)));
            System.out.println("Sending: "+urll.toString());
            HttpURLConnection connection = (HttpURLConnection) urll.openConnection();
            connection.setRequestMethod(typeOfRequest);
            connection.setRequestProperty("Accept", "application/json");
            if (connection.getResponseCode() != 200) {
                throw new RuntimeException(url.toString() + " failed : HTTP error code : "
                        + connection.getResponseCode());
            }
            JSONObject r = new JSONObject(new BufferedReader(new InputStreamReader(
                    (connection.getInputStream()))).readLine());
            assertEquals(200, connection.getResponseCode());
            connection.disconnect();
            return r;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    
    @Test
    public void TestCreatingTutor() {
        String managerId = "1030";
        try {
            response = requestURL("POST", BASE_URL, "/Tutor/newTutor", "firstName=bob&lastName=bog&"
            														+ "email=bob@email.com&password=password1"
            														+ "&manager=" + managerId
            														);
            System.out.println("Received: "+response.toString());
            System.out.println(response.getString("firstName"));
            assertEquals("bob", response.getString("firstName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	 private JSONObject requestURL(String requestType, String baseUrl, String path) {
	        return requestURL(requestType,baseUrl,path,null);
	    }
    
    

}
