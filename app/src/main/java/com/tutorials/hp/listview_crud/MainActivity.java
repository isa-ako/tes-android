package com.tutorials.hp.listview_crud;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<Country> adapter;
    CRUD crud=new CRUD();
    Dialog d;
    Context ini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ini=this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv= (ListView) findViewById(R.id.lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(d != null) {
                    if(!d.isShowing())
                    {
                        displayInputDialog(i);
                    }else
                    {
                        d.dismiss();
                    }
                }
            }
        });

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                displayInputDialog(-1);
            }
        });
    }
    private void displayInputDialog(final int pos)
    {
        d=new Dialog(this);
        d.setTitle("LISTVIEW CRUD");
        d.setContentView(R.layout.input_dialog);

        final EditText nameEditTxt= (EditText) d.findViewById(R.id.nameEditText);
        final EditText capitalEditTxt= (EditText) d.findViewById(R.id.capitalEditText);

        Button addBtn= (Button) d.findViewById(R.id.addBtn);
        Button updateBtn= (Button) d.findViewById(R.id.updateBtn);
        Button deleteBtn= (Button) d.findViewById(R.id.deleteBtn);

        if(pos== -1)
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }else
        {
            addBtn.setEnabled(true);
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            nameEditTxt.setText(crud.getCountries().get(pos).getCountry());
            capitalEditTxt.setText(crud.getCountries().get(pos).getCapital());
        }

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String name=nameEditTxt.getText().toString();
                String capital=capitalEditTxt.getText().toString();

                //VALIDATE
                if(name.length()>0 && name != null && capital.length()>0 && capital != null)
                {
                    //save
                    crud.save(name, capital);
                    nameEditTxt.setText("");
                    capitalEditTxt.setText("");
                    adapter=new CountryAdapter(ini,crud.getCountries());
                    lv.setAdapter(adapter);

                }else
                {
                    Toast.makeText(MainActivity.this, "Semua kolom harus di isi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                String newName=nameEditTxt.getText().toString();
                String newCapital=capitalEditTxt.getText().toString();

                //VALIDATE
                if(newName.length()>0 && newName != null)
                {
                    //save
                    if(crud.update(pos,newName,newCapital))
                    {
                        nameEditTxt.setText(newName);
                        capitalEditTxt.setText(newCapital);
                        adapter=new CountryAdapter(ini,crud.getCountries());
                        lv.setAdapter(adapter);
                    }

                }else
                {
                    Toast.makeText(MainActivity.this, "emua kolom harus di isi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //DELETE
                if( crud.delete(pos))
                {
                    nameEditTxt.setText("");
                    adapter=new CountryAdapter(ini,crud.getCountries());
                    lv.setAdapter(adapter);
                }
            }
        });

        d.show();
    }
}
