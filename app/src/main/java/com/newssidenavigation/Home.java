package com.newssidenavigation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;


public class Home extends Fragment {

        int i;

    String url;
    ListView lv;
    ProgressDialog  pd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        Bundle bundle=getArguments();

        //here is your list array
        url=bundle.getString("url");
        return inflater.inflate(R.layout.fragment_home, container, false);



    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


         //val1=     getArguments().getString("value");

        lv = (ListView) getActivity().findViewById(R.id.listofnews);



        final ArrayList title=new ArrayList();
        final ArrayList des=new ArrayList();
        final ArrayList link=new ArrayList();

       pd = new ProgressDialog(getActivity());
        pd.setTitle("Loading Headlines");
        pd.setMessage("please wait");
        pd.setCancelable(false);
        pd.show();


            StringRequest sr = new StringRequest(Request.Method.GET, "http://fenkunews.com/getNewsData.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("response",response);
                    pd.dismiss();


          try
          {
                JSONObject obj = new JSONObject(response);
                        JSONArray dat = obj.getJSONArray("articles");
                        i=0;
                        while (i < dat.length()) {
                            title.add(dat.getJSONObject(i).getString("title"));
                            des.add(dat.getJSONObject(i).getString("description"));
                            link.add(dat.getJSONObject(i).getString("urlToImage"));
                            i++;
                        }




                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                   pd.dismiss();
                }
            });


            Volley.newRequestQueue(getActivity()).add(sr);







    }


}
