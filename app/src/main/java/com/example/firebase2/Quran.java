package com.example.firebase2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Quran extends AppCompatActivity {
    TextView output;
    String url = "http://api.alquran.cloud/v1/quran/quran-uthmani";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);
        output = findViewById(R.id.output);
    }

    public void Data(View view) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject data = response.getJSONObject("data");
                            JSONArray QuranCompelete =  data.getJSONArray("surahs");
                            JSONObject surahs =  QuranCompelete.getJSONObject(0);
                            String SurahNumber = surahs.getString("englishName");
                            output.setText(SurahNumber);

                        } catch (JSONException e) {

                            e.printStackTrace();
                            output.setText(e.getMessage());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("theH",error.getMessage());
                        output.setText(error.getMessage());
                    }
                }
        );
        objectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(objectRequest);
    }
}