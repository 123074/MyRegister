package com.example.user.myregister;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.myregister.httpconnection.ServerRequest;
import com.example.user.myregister.utilities.Pref;
import com.example.user.myregister.utilities.Utilities;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Button button,button1;
    EditText editText1,editText2;
    String name,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText1=(EditText)findViewById(R.id.editTextNamee);
        editText2=(EditText)findViewById(R.id.editTextPass);
        
        name=editText1.getText().toString();
       pass= editText2.getText().toString();


        button=(Button)findViewById(R.id.btn2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);

            }
        });
        button1=(Button)findViewById(R.id.btn1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent= new Intent(getApplicationContext(),WelcomeActivity.class);
//                startActivity(intent);
                new UserLoginTask(name,pass);


            }
        });
    }

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
    public class UserLoginTask extends AsyncTask<Void,Void,String>{
        private final String username,password;

        public UserLoginTask(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected String doInBackground(Void... voids) {
            HashMap<String,String>userData=new HashMap<>();
            userData.put("username",username);
            userData.put("password",password);
            userData.put("submit","submit");
            String response=null;
            if(Utilities.isNetworkAvailable(MainActivity.this)){
                response=new ServerRequest(MainActivity.this).httpPostData("http://192.168.1.28/login_api/?action=login",userData);


            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                JSONObject object=new JSONObject(s);
                boolean result=Boolean.valueOf(object.getString("success"));
                if (result){
                    JSONObject data=object.getJSONObject("msg");
                    new Pref(MainActivity.this).setPreferences(Pref.KEY_NAME,data.optString("name"));
                    startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));

                }
                else {
                    Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_SHORT).show();

                }

            }catch (Exception e){

            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}
