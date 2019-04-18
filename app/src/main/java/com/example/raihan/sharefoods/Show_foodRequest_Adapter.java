package com.example.raihan.sharefoods;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class Show_foodRequest_Adapter extends RecyclerView.Adapter<Show_foodRequest_Adapter.MyViewHolder> {
    private List<FoodRequestObject> obj;
    private Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  name,location,quantity,briefDescription,expiaryTime,volunteer;


        public MyViewHolder(View itemView) {
            super(itemView);
//            this.name = itemView.findViewById(R.id.template_food_request_name);
            this.location = itemView.findViewById(R.id.location);
            this.quantity = itemView.findViewById(R.id.person);
            this.volunteer = itemView.findViewById(R.id.volunteer);
//            this.briefDescription = itemView.findViewById(R.id.template_food_request_briefDescription);
//            this.expiaryTime = itemView.findViewById(R.id.template_food_request_expiaryTime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent myactivity = new Intent(context.getApplicationContext(), Show_Profile.class);
//                    Gson gson = new Gson();
//
//
//
//                    myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
//                    Profile p = class_list.get(getAdapterPosition());
//                    String myJson1 = gson.toJson(p);
//                    myjson = myJson1;
//
//                    myactivity.putExtra("myjson", myjson);
//                    //                    myactivity.putExtra("Position",p);
                    FoodRequestObject f = obj.get(getAdapterPosition());
//                    cn.getApplicationContext().startActivity(myactivity);
                    Toast.makeText(context,f.getLocation(),Toast.LENGTH_SHORT).show();
                }
            });


        }


    }

    public Show_foodRequest_Adapter(List<FoodRequestObject> obj, Context context)
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

//        TextView name = holder.;
//        TextView location = holder.location;
//        TextView quantity = holder.quantity;
//        TextView volunteer = holder.voluteer;
//        TextView expiaryTime = holder.expiaryTime;

//        name.setText(obj.get(position).getName());
        holder.location.setText(obj.get(position).getLocation());
        holder.quantity.setText("Food For "+ obj.get(position).getQuantity()+"");
        if(obj.get(position).getQuantity()/4 >0) {
            holder.volunteer.setText(obj.get(position).getQuantity() / 4+" Volunteer Needed");
        }else{
            holder.volunteer.setText("1 volunteer needed");
        }
//        briefDescription.setText(obj.get(position).getExpiary());
//        expiaryTime.setText(obj.get(position).getExpiary());

    }

    @Override
    public int getItemCount() {
        return obj.size();
    }


}