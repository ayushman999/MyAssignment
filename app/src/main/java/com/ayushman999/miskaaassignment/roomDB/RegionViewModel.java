package com.ayushman999.miskaaassignment.roomDB;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RegionViewModel extends AndroidViewModel {
    private Repository regionRepo;
    private final LiveData<List<Region>> data;
    public RegionViewModel(@NonNull Application application) {
        super(application);
        regionRepo=new Repository(application);
        data=regionRepo.getAllRegion();

    }
    public LiveData<List<Region>> getData(){return data;}
    public void insert(Region region)
    {
        regionRepo.insert(region);
    }
    public void deleteAll()
    {
        regionRepo.deleteAll();
    }
}
