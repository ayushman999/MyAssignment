package com.ayushman999.miskaaassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ayushman999.miskaaassignment.R;
import com.ayushman999.miskaaassignment.SvgUtils;
import com.ayushman999.miskaaassignment.roomDB.Region;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RegionAdapter extends RecyclerView.Adapter<RegionHolder> {
    ArrayList<Region> data=new ArrayList<>();
    Context context;

    public RegionAdapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public RegionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView= LayoutInflater.from(parent.getContext()).inflate(R.layout.region_element,parent,false);
        RegionHolder holder=new RegionHolder(myView);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull RegionHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.region.setText(data.get(position).getRegion());
        holder.subregion.setText(data.get(position).getSubregion());
        SvgUtils.fetchSvg(context,data.get(position).getFlag(),holder.flag);
        String bord="Borders:"+"\n";
        String languages="Languages:\n";
        for(int i=0;i<data.get(position).getBorders().size();i++)
        {
            bord=bord+data.get(position).getBorders().get(i)+"\n";
        }
        for(int i=0;i<data.get(position).getLanguages().size();i++)
        {
            languages=languages+data.get(position).getLanguages().get(i)+"\n";
        }
        holder.languages.setText(languages);
        holder.borders.setText(bord);
        holder.population.setText(data.get(position).getPopulation()+"");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void update(List<Region> data1)
    {
        data.clear();
        data.addAll(data1);
        notifyDataSetChanged();
    }

}
class RegionHolder extends RecyclerView.ViewHolder {
    ImageView flag;
    TextView name,region,subregion,borders,languages,population;
    public RegionHolder(@NonNull View itemView) {
        super(itemView);
        name=(TextView) itemView.findViewById(R.id.name);
        region=(TextView) itemView.findViewById(R.id.region);
        subregion=(TextView) itemView.findViewById(R.id.subregion);
        flag=(ImageView) itemView.findViewById(R.id.flag);
        borders=(TextView) itemView.findViewById(R.id.borders);
        languages=(TextView) itemView.findViewById(R.id.languages);
        population=(TextView) itemView.findViewById(R.id.population);


    }
}
