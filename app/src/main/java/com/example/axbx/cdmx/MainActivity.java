package com.example.axbx.cdmx;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;

import static android.R.attr.value;
import static java.net.Proxy.Type.HTTP;

public class MainActivity extends AppCompatActivity {
    LoginButton loginButton;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        Button validar=(Button)findViewById(R.id.button4);

        validar.setOnClickListener(MyListener);

        loginButton=(LoginButton)findViewById(R.id.login_button);
        callbackManager=CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this,"Garantizado"+loginResult.getAccessToken().getUserId()+"\n"+loginResult.getAccessToken().getToken(),Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(MainActivity.this, Promociones.class);

                MainActivity.this.startActivity(myIntent);



            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Cancelado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private View.OnClickListener MyListener = new View.OnClickListener(){
        public void onClick(View v){



            //WEB SERVICIE

            //quitar
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            InputStream is = null;
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("pass", "password"));
            nameValuePairs.add(new BasicNameValuePair("user", "Luis"));




            try {


                JSONObject jsonObjData = new JSONObject();
                JSONObject json= new JSONObject();
                jsonObjData.put("pass", "password");
                jsonObjData.put("user", "Luis");
                json.put("data",jsonObjData);


                // Create the POST object and add the parameters
                HttpPost httpPost = new HttpPost("https://agentemovil.pagatodo.com/AgenteMovil.svc/agMov/login");
                StringEntity entity = new StringEntity(json.toString(), "UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
                HttpClient client = new DefaultHttpClient();
                HttpResponse response = client.execute(httpPost);
                HttpEntity entity2 = response.getEntity();

                //HttpClient httpclient = new DefaultHttpClient();
                // HttpPost httppost = new HttpPost("https://agentemovil.pagatodo.com/AgenteMovil.svc/agMov/login");
                //httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                //HttpResponse response = httpclient.execute(httppost);
                //HttpEntity entity = response.getEntity();
                //is = entity.getContent();

             /*=========*/

                StringBuilder sb = new StringBuilder();
                try {
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(entity2.getContent()), 65728);
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                }
                catch (IOException e) { e.printStackTrace(); }
                catch (Exception e) { e.printStackTrace(); }



                //
                Intent myIntent = new Intent(MainActivity.this, Promociones.class);

              MainActivity.this.startActivity(myIntent);


                System.out.println("finalResult " + sb.toString());
            /*==============================*/

                Log.d("HTTP", "HTTP: OK");
            } catch (Exception e) {
                Log.e("HTTP", "Error in http connection " + e.toString());
                
            }
        /*
        try{
            HttpClient client = new DefaultHttpClient();
            //HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();


            HttpPost request = new HttpPost();
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("key", "value"));
            request.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse response=  client.execute(request);

            HttpEntity entity = response.getEntity();
            System.out.println("Entity:"+entity);


        }
        catch (IOException e) {
            e.printStackTrace();
        }
*/

        }
    };
}
