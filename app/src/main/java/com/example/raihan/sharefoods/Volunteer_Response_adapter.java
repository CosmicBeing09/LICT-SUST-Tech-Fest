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

public class Volunteer_Response_adapter extends RecyclerView.Adapter<Volunteer_Response_adapter.MyViewHolder> {
    private List<FoodRequestObject> obj;
    String myjson;
    private Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,location,quantity,briefDescription,expiaryTime,volunteer;


        public MyViewHolder(View itemView) {
            super(itemView);


            this.location = itemView.findViewById(R.id.location);
            this.quantity = itemView.findViewById(R.id.person);
            this.volunteer = itemView.findViewById(R.id.volunteer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myactivity = new Intent(context.getApplicationContext(),Post_Food_FeedBack.class);

                    myactivity.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    FoodRequestObject f = obj.get(getAdapterPosition());
                    myactivity.putExtra("object",f);
                    context.getApplicationContext().startActivity(myactivity);
//                    Toast.makeText(context,MainActivity.myprofile.getAddress(),Toast.LENGTH_SHORT).show();
                }
            });


        }


    }

    public Volunteer_Response_adapter(List<FoodRequestObject> obj, Context context)
    {
        this.obj = obj;
        this.context = context;
    }


    @NonNull
    @Override
    public Volunteer_Response_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.template_food_request,viewGroup,false);
        Volunteer_Response_adapter.MyViewHolder viewHolder = new Volunteer_Response_adapter.MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Volunteer_Response_adapter.MyViewHolder holder, int position) throws NullPointerException{


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

