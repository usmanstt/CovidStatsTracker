package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<CountryHelper> {

    private Context context;
    private List<CountryHelper> countryLs;
    private List<CountryHelper> countryLsFiltered;

    public CustomAdapter(@NonNull Context context, List<CountryHelper> countryLs) {
        super(context, R.layout.list_items_custom, countryLs);

        this.context = context;
        this.countryLs = countryLs;
        this.countryLsFiltered = countryLs;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items_custom, null, true);
        TextView country = view.findViewById(R.id.countryText);
        ImageView flags = view.findViewById(R.id.imageFlag);

        country.setText(countryLsFiltered.get(position).getCountry());
        Glide.with(context).load(countryLsFiltered.get(position).getFlag()).into(flags);

        return view;
    }

    @Override
    public int getCount() {
        return countryLsFiltered.size();
    }

    @Nullable
    @Override
    public CountryHelper getItem(int position) {
        return countryLsFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults =  new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryLs.size();
                    filterResults.values = countryLs;
                }
                else{
                    List<CountryHelper> resultModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(CountryHelper itemsHelper: countryLs){
                        if(itemsHelper.getCountry().toLowerCase().contains(searchStr)){
                            resultModel.add(itemsHelper);
                        }
                        filterResults.count = resultModel.size();
                        filterResults.values = resultModel;
                    }
                }
                return  filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                countryLsFiltered = (List<CountryHelper>) results.values;
                AffectedCountries.countryHelperList = (List<CountryHelper>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }
}
