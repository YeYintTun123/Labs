package com.example.lab3.ui.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3.R;
import com.example.lab3.ui.Country.Country;
import com.example.lab3.ui.Country.CountryDetail;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.ListIterator;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private int listItemLayout;
    private ArrayList<Country> CountryList;

    public MyAdapter(int layoutId, ArrayList<Country> countrylist) {
        listItemLayout = layoutId;
        this.CountryList = countrylist;


    }

    @Override
    public int getItemCount() {
        return CountryList == null ? 0 : CountryList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView name = holder.Name;
        TextView captial = holder.Captial;
        TextView region = holder.Region;
        name.setText(CountryList.get(listPosition).GetName()); //Getting the name from list array
        captial.setText(CountryList.get(listPosition).GetCapital());
        region.setText(CountryList.get(listPosition).GetRegion());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CountryDetail.class);
                Country country = CountryList.get(listPosition);
                intent.putExtra("Name", CountryList.get(listPosition).GetName());
                intent.putExtra("Captial", CountryList.get(listPosition).GetCapital());
                intent.putExtra("Region", CountryList.get(listPosition).GetRegion());

                v.getContext().startActivity(intent);
            }
        });


    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Name;
        public TextView Captial;
        public TextView Region;
        View mView;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Name = itemView.findViewById(R.id.textViewName);
            Captial = itemView.findViewById(R.id.textViewCapital);
            Region = itemView.findViewById(R.id.textViewRegion);


            mView = itemView;





        }

        @Override
        public void onClick(View view) {

        }

    }

}
