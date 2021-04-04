package com.ayushman999.miskaaassignment.roomDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RegionDAO {
    @Query("SELECT * FROM region_table order by name ASC")
    LiveData<List<Region>> getAll();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Region> region);
    @Query("DELETE FROM region_table")
    void deleteAll();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Region region);
}
