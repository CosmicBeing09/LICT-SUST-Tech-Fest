package com.example.raihan.sharefoods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class Show_foodRequest_Adapter extends RecyclerView.Adapter<Show_foodRequest_Adapter.MyViewHolder> {
    private ArrayList<FoodRequestObject> obj;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  name,location,quantity,briefDescription,expiaryTime;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.template_food_request_name);
            this.location = itemView.findViewById(R.id.template_food_request_location);
            this.quantity = itemView.findViewById(R.id.template_food_request_quantity);
            this.briefDescription = itemView.findViewById(R.id.template_food_request_briefDescription);
            this.expiaryTime = itemView.findViewById(R.id.template_food_request_expiaryTime);
        }


    }

    public Show_foodRequest_Adapter(ArrayList<FoodRequestObject> obj, Context context)
    {
        this.obj = obj;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.template_food_request,viewGroup,false);
        Show_foodRequest_Adapter.MyViewHolder viewHolder = new Show_foodRequest_Adapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) throws NullPointerException{

        TextView name = holder.name;
        TextView location = holder.location;
        TextView quantity = holder.quantity;
        TextView briefDescription = holder.briefDescription;
        TextView expiaryTime = holder.expiaryTime;

        name.setText(obj.get(position).getName());
        location.setText(obj.get(position).getLocation());
        quantity.setText(obj.get(position).getQuantity());
        briefDescription.setText(obj.get(position).getExpiary());
        expiaryTime.setText(obj.get(position).getExpiary());

    }

    @Override
    public int getItemCount() {
        return obj.size();
    }


}

