package com.damianvela.nytimeslistviewexample;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.damianvela.nytimeslistviewexample.model.NYTItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private static final String url = "http://api.nytimes.com/svc/topstories/v1/home.json?api-key=15629235341919a7b4b8b6e9344f9bca:6:72095783";
    private ProgressDialog pDialog;
    private List<NYTItem> nytItems = new ArrayList<NYTItem>();
    private ListView listView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomAdapter(this, nytItems);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest NYTTopStoriesRequest = new JsonObjectRequest(Request.Method.GET,url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Doing the request", response.toString());
                            hidePDialog();
                            JSONArray list = response.getJSONArray("results");
                            for (int i = 0; i < list.length(); i++) {
                                JSONObject obj = list.getJSONObject(i);
                                NYTItem nycElement = new NYTItem();
                                String thumb;
                                try {
                                    thumb = new JSONArray(URLDecoder.decode(obj.getString("multimedia"), "UTF-8")).getJSONObject(0).getString("url");
                                }catch (Exception e){
                                    thumb = "";
                                }
                                Log.i ("El thumb es:", thumb);
                                nycElement.setTitle(obj.getString("title"));
                                nycElement.setThumbnailUrl(thumb);
                                nytItems.add(nycElement);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d("Request failed because of: ", "Error: " + error.getMessage());
                            hidePDialog();
                        }
                });
        AppController.getInstance().addToRequestQueue(NYTTopStoriesRequest);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

}
