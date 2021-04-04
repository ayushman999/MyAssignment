package com.ayushman999.miskaaassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ayushman999.miskaaassignment.adapter.RegionAdapter;
import com.ayushman999.miskaaassignment.roomDB.Region;
import com.ayushman999.miskaaassignment.roomDB.RegionViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Region> regionData;
    Button deleteAll;
    public RegionViewModel viewModel;
    boolean isOnline;
    RegionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView) findViewById(R.id.region_recycler);
        deleteAll=(Button) findViewById(R.id.delete_data);
        adapter=new RegionAdapter(getApplicationContext());
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        boolean isOnline= netInfo != null && netInfo.isConnected();

        viewModel=new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(RegionViewModel.class);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        if(isOnline)
        {
            fetchData();
        }
        else
        {
            viewModel.getData().observe(MainActivity.this, new Observer<List<Region>>() {
                @Override
                public void onChanged(List<Region> regions) {
                    adapter.update(regions);
                }
            });
            recyclerView.setAdapter(adapter);
        }
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteAll();
            }
        });

    }

    private void fetchData() {
        String url="https://restcountries.eu/rest/v2/region/Asia";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.i("Inside:","onResponse");
                for(int i=0;i<response.length();i++)
                {
                    try {
                        Log.i("Inside:","inside for loop");
                        JSONObject object=response.getJSONObject(i);
                        String name=object.getString("name");
                        String capital=object.getString("capital");
                        String region=object.getString("region");
                        String subregion=object.getString("subregion");
                        String flag=object.getString("flag");
                        int population=object.getInt("population");
                        JSONArray borders=object.getJSONArray("borders");
                        JSONArray languages=object.getJSONArray("languages");
                        ArrayList<String> borderArray=new ArrayList<>();
                        ArrayList<String> languagesArray=new ArrayList<>();
                        for(int j=0;j<borders.length();j++)
                        {
                            borderArray.add(borders.getString(j));
                        }
                        for(int j=0;j<languages.length();j++)
                        {
                            languagesArray.add(languages.getJSONObject(j).getString("name"));
                        }
                        Region regions=new Region(name,capital,region,subregion,flag,population,borderArray,languagesArray);
                        viewModel.insert(regions);
                        viewModel.getData().observe(MainActivity.this, new Observer<List<Region>>() {
                            @Override
                            public void onChanged(List<Region> regions) {
                                adapter.update(regions);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(arrayRequest);

    }
}