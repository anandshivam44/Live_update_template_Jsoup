package com.example.jsoup_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    TextView textView;
    TextView blue;
    Button bt;
    Button bt2;
    String url="https://www.worldometers.info/coronavirus/";
    String array[];
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView =findViewById(R.id.textView);
        blue=findViewById(R.id.blue);
        bt=findViewById(R.id.button);
        bt2 = findViewById(R.id.button_2);


//        try {
//            Document doc = Jsoup.connect("http://example.com/").get();
//            String title = doc.title();
//            Log.d(TAG, "TITLE  "+title);
//            text.setText(title);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.d(TAG, " Inside Catch"+ e.getMessage());


        //}
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Content content=new Content();
                content.execute();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button 2 Clicked");
                int j=0;
                    Log.d(TAG, "while ran "+j+" times");

                    new CountDownTimer(3000000, 10000) {

                        public void onTick(long millisUntilFinished) {
                            blue.setText("seconds remaining: " + millisUntilFinished / 1000);
                            Content content=new Content();
                            content.execute();
                        }

                        public void onFinish() {
                            Content content=new Content();
                            content.execute();
                        }
                    }.start();


            }
        });

//

    }
    private class Content extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressDialog=new ProgressDialog(MainActivity.this);
            //progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            String A="";
            A+=" Total Case    "+ array[0]+"\n Death       "+ array[1]+"\n Recovered      "+ array[2];
            textView.setText(A);
            //progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect(url).get();
                array =doc.getElementsByClass("maincounter-number").text().split(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
