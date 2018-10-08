package com.example.admin.parsing;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Context context;
    List<DataShowWithURL> arrayList = new ArrayList<>();

    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_data_from_json);
        progressBar = findViewById(R.id.progressbar);
        listView = findViewById(R.id.listView);
        String url = "https://www.androidbegin.com/tutorial/jsonparsetutorial.txt";

        new ShowImageWithJSON().execute(url);
    }
    public class ShowImageWithJSON extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(100);
        }

        @Override
        protected String doInBackground(String... url) {
            String urlparse = url[0];
            try {
                URL urlll = new URL(urlparse);
                // URLConnection connection = urlll.openConnection();
                InputStream inputStream = urlll.openConnection().getInputStream();

                StringBuffer stringBuffer = new StringBuffer();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = bufferedReader.readLine())!=null)
                {
                    stringBuffer.append(line+ "\n");

                }
                return stringBuffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//           Log.i("String",s);
            progressBar.setVisibility(View.GONE);
            JSONImageCustomAdapter jsonImageCustomAdapter = new JSONImageCustomAdapter(context,arrayList);

            try {
                JSONObject object =new JSONObject(s);
                JSONArray array = object.getJSONArray("worldpopulation");
                for (int i=0;i<array.length();i++)
                {
                    Log.i("Value of i", String.valueOf(i));
                    DataShowWithURL secondDataClass = new DataShowWithURL();
                    JSONObject jsonObject = array.getJSONObject(i);
                    secondDataClass.setRank(jsonObject.getString("rank"));
                    Log.i("Rank", String.valueOf(secondDataClass));
                    secondDataClass.setPopulation(jsonObject.getString("population"));
                    Log.i("Population", String.valueOf(secondDataClass));
                    secondDataClass.setFlag(jsonObject.getString("flag"));

                    arrayList.add(secondDataClass);
                }
                listView.setAdapter(new JSONImageCustomAdapter(getApplicationContext(),arrayList));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}