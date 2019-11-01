package com.example.myapplication;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<Country> {

    private Context countryContext;
    private List<Country> countryList = new ArrayList<>();

    public CountryAdapter(@NonNull Context context,ArrayList<Country> list) {
        super(context, 0 , list);
        countryContext = context;
        countryList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(countryContext).inflate(R.layout.list_item,parent,false);

        Country currentCountry = countryList.get(position);


        TextView countryName = (TextView) listItem.findViewById(R.id.textView_country);
        countryName.setText(currentCountry.getCountry());

        TextView capitalName = (TextView) listItem.findViewById(R.id.textView_capital);
        capitalName.setText(currentCountry.getCapital());

        return listItem;
    }
}