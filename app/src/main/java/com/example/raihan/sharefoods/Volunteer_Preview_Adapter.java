package com.example.raihan.sharefoods;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Volunteer_Preview_Adapter  extends RecyclerView.Adapter<Volunteer_Preview_Adapter.MyViewHolder> {

    private List<Profile_Object> obj;
    String myjson;
    private Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,location,phone_no;


        public MyViewHolder(View itemView) {
            super(itemView);
//            this.name = itemView.findViewById(R.id.template_food_request_name);
            this.location = itemView.findViewById(R.id.volunteerPreviewLocation);
            this.name = itemView.findViewById(R.id.volunteerPreviewName);
            phone_no = itemView.findViewById(R.id.volunteerPreviewPhoneNo);


        }


    }

    public Volunteer_Preview_Adapter(List<Profile_Object> obj, Context context)
    {
        this.obj = obj;
        this.context = context;
    }


    @NonNull
    @Override
    public Volunteer_Preview_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.volunteer_preview,viewGroup,false);
        Volunteer_Preview_Adapter.MyViewHolder viewHolder = new Volunteer_Preview_Adapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Volunteer_Preview_Adapter.MyViewHolder holder, int position) throws NullPointerException{

        holder.location.setText(obj.get(position).getAddress());
        holder.name.setText(obj.get(position).getUser().getUsername());
        holder.phone_no.setText(obj.get(position).getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return obj.size();
    }


}
