package com.ayushman999.miskaaassignment.roomDB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Region.class},version = 1,exportSchema = false)
@TypeConverters(Converters.class)
public abstract class RegionDB extends RoomDatabase {
    public abstract RegionDAO regionDAO();
    public static volatile RegionDB INSTANCE;
    public static final int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseWriteExecutor=
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static RegionDB getDatabase(Context context)
    {
        if(INSTANCE==null)
        {
            synchronized (RegionDB.class)
            {
                if(INSTANCE==null)
                {
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            RegionDB.class,"region_table")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                RegionDAO dao = INSTANCE.regionDAO();
                dao.deleteAll();

            });
        }
    };
}
