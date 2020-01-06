package com.example.khadamat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.khadamat.Model.Poste;

import java.util.ArrayList;

public class PostesAdapter extends  RecyclerView.Adapter<PostesAdapter.MyViewHolder> {
      Context context;
      ArrayList<Poste> postes;


      public PostesAdapter(Context c, ArrayList<Poste> u){

          context = c;
          postes = u;
      }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.postes_items_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

          holder.poste_desc.setText(postes.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return postes.size() ;
    }

    class  MyViewHolder extends RecyclerView.ViewHolder {
        TextView poste_desc;

        public MyViewHolder(View itemView) {
            super(itemView);
            poste_desc = itemView.findViewById(R.id.poste_desc);
        }
    }
}
