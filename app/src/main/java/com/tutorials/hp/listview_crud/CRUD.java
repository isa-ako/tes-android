package com.tutorials.hp.listview_crud;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oclemy for ProgrammingWizards TV Channel and https://www.camposha.info.
 */
public class CRUD {

    private Context countryContext;
    private ArrayList<Country> countries =new ArrayList<>();
    private List<Country> countryList = new ArrayList<>();

    public void save(String name, String capital)
    {
        Country negara = new Country(name,capital);
        countries.add(negara);
    }

    public ArrayList<Country> getCountries()
    {

        return countries;
    }

    public Boolean update(int position,String newName,String newCapital)
    {
       try {
           countries.remove(position);
           Country coun = new Country(newName, newCapital);
           countries.add(position, coun);

           return true;
       }catch (Exception e)
       {
           e.printStackTrace();
          return false;
        }
    }

    public Boolean delete(int position)
    {
        try {
            countries.remove(position);

            return true;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;

        }
    }
}
