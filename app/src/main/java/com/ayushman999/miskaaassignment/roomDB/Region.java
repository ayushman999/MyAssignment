package com.ayushman999.miskaaassignment.roomDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity(tableName = "region_table")
public class Region {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "capital")
    public String capital;
    @ColumnInfo(name = "region")
    public String region;
    @ColumnInfo(name = "subregion")
    public String subregion;
    @ColumnInfo(name = "flag")
    public String flag;
    @ColumnInfo(name = "population")
    public int population;
    @ColumnInfo(name = "borders")
    public ArrayList<String> borders;
    @ColumnInfo(name = "languages")
    public ArrayList<String> languages;

    public Region(@NonNull String name,@NonNull String capital,@NonNull String region,@NonNull String subregion,@NonNull String flag,@NonNull int population,@NonNull ArrayList<String> borders,@NonNull ArrayList<String> languages) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.flag = flag;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public ArrayList<String> getBorders() {
        return borders;
    }

    public void setBorders(ArrayList<String> borders) {
        this.borders = borders;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }
}
