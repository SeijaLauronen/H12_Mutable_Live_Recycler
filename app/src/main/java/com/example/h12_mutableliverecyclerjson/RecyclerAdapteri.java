package com.example.h12_mutableliverecyclerjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapteri extends RecyclerView.Adapter<RecyclerAdapteri.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<String> lista;

    public void setItems(final ArrayList<String> _lista) {
        this.lista =_lista;
        notifyDataSetChanged();//"ruma tapa toteuttaa...mutta pitikös tämä nyt jättää vai kommentoida

        //Parempi: lasketaan kahden listan ero
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return lista.size();
            }

            @Override
            public int getNewListSize() {
                return _lista.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return lista.get(oldItemPosition).equals(_lista.get(newItemPosition));
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return false;
            }
        });
        result.dispatchUpdatesTo(this);
        notifyDataSetChanged();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textiView);
        }
    }

    //Tehdään itse konstruktori, jolle parametrinä konteksti
    public RecyclerAdapteri(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.listanrivi,parent);
        return new ViewHolder(v);
        // return null;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(lista.get(position));
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
