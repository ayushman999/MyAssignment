package com.ayushman999.miskaaassignment.roomDB;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private RegionDAO dao;
    private LiveData<List<Region>> data;
    Repository(Application application)
    {

        ConnectivityManager cm =
                (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        boolean isOnline= netInfo != null && netInfo.isConnected();

        if(isOnline)
        {
            RegionDB db = RegionDB.getDatabase(application);
            dao = db.regionDAO();
            data = dao.getAll();
            Toast.makeText(application, "Online", Toast.LENGTH_SHORT).show();
        }
        else {
            RegionDB db = RegionDB.getDatabase(application);
            dao = db.regionDAO();
            data = dao.getAll();
            Toast.makeText(application, "Offline", Toast.LENGTH_SHORT).show();

        }
    }

    LiveData<List<Region>> getAllRegion()
    {
        return data;
    }
    void insert(Region region)
    {
        RegionDB.databaseWriteExecutor.execute(()->{
            dao.insert(region);
        });
    }
    void deleteAll()
    {
        RegionDB.databaseWriteExecutor.execute(()->{
            dao.deleteAll();
        });
    }
}

