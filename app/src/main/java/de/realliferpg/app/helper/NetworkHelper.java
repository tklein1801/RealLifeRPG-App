package de.realliferpg.app.helper;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import de.realliferpg.app.Singleton;
import de.realliferpg.app.interfaces.RequestCallbackInterface;
import de.realliferpg.app.objects.Changelog;

public class NetworkHelper {

    public void doJSONRequest(String url, final RequestCallbackInterface callback, final Class type) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onResponse(response, type);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("NetworkHelper","Error in response");

                    }
                });

        Singleton.getInstance().addToRequestQueue(jsonObjectRequest);
        Log.d("","");
    }
}