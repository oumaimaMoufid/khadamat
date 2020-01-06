package com.example.khadamat;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.khadamat.Model.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.MyViewHolder> {
      Context context;
      ArrayList<Users> users;


      public MyAdapter(Context c, ArrayList<Users> u){

          context = c;
          users = u;
         // Toast.makeText(c, "image 2"+users.get(0).getImageUrl(), Toast.LENGTH_LONG).show();
      }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.product_items_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

          holder.name.setText(users.get(position).getName());
          holder.phone.setText(users.get(position).getPhone());
          Picasso.get().load(users.get(position).getImageUrl()).into(holder.user_image);

        //Toast.makeText(context, "image"+users.get(position).getImageUrl(), Toast.LENGTH_LONG).show();
          //  Log.v("image",users.get(position).getImageUrl());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MyAdapter.this, Detail.class);
//                intent.putExtra("phone", users.get(position).getPhone());
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return users.size() ;
    }

    class  MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name,phone;
        ImageView user_image;
        Button detail;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            phone = itemView.findViewById(R.id.user_phone);
            detail = itemView.findViewById(R.id.detail);
            user_image = itemView.findViewById(R.id.user_image);

            detail.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.detail){
                //String userPhone = phone.toString();
                Intent intent = new Intent(context, Detail.class);
                intent.putExtra("num",phone.getText().toString());
                context.startActivity(intent);
            }
        }
    }
}
