package com.example.final_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> userList;
    public Adapter (List<ModelClass>userList) {
        this.userList=userList;
    }



    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.electritain_recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        int resource = userList.get(position).getImageView1();
        String name = userList.get(position).getName();
        String location = userList.get(position).getLocation();
        String line = userList.get(position).getDivider();

        holder.setData(resource, name, location,line);
   }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       private ImageView pimageView;
       private TextView pname ,plocation,pdivider;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pimageView = (ImageView) itemView.findViewById(R.id.image1);
            pname = (TextView) itemView.findViewById(R.id.eleName);
            plocation =(TextView) itemView.findViewById(R.id.elecLoc);
            pdivider =(TextView) itemView.findViewById(R.id.elecUnderLine);

        }

        public void setData(int resource, String name, String location, String line) {
        
            pimageView.setImageResource(resource);
            pname.setText(name);
            plocation.setText(location);
            pdivider.setText(line);

        }
    }
}
