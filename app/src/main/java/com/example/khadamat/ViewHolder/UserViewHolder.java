package com.example.khadamat.ViewHolder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khadamat.Interface.ItemClickListener;
import com.example.khadamat.R;
import com.example.khadamat.login;
import com.example.khadamat.register;

public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtUserName, txtUserPhone, txtUserDescription;
    public ImageView imageView;
    public ItemClickListener listner;


    public UserViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.user_image);
        txtUserName = itemView.findViewById(R.id.user_name);
        txtUserPhone = itemView.findViewById(R.id.user_phone);
        txtUserDescription = itemView.findViewById(R.id.user_description);

    }
    public void ItemClickListener(ItemClickListener listener)
    {
        this.listner = listner;
    }



    @Override
    public void onClick(View view) {
        listner.onClick(view, getAdapterPosition(), false);
    }
}
