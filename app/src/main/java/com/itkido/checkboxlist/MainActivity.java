package com.itkido.checkboxlist;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button mList;
    Button mClear;
    TextView mItemSelect;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mList = findViewById(R.id.btnList);
        mItemSelect = findViewById(R.id.txtItemSelect);
        mClear = findViewById(R.id.btnClear);
        mClear.setVisibility(View.INVISIBLE);
        listItems = getResources().getStringArray(R.array.Life);
        checkedItems = new boolean[listItems.length];

        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setTitle("Countries");
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            if (!mUserItems.contains(position)){
                                mUserItems.add(position);
                            }else {
                                mUserItems.remove(position);
                            }
                        }

                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = "";

                        for (int i = 0; i< mUserItems.size(); i++){
                            mClear.setVisibility(View.VISIBLE);
                            item = item + listItems[mUserItems.get(i)];
                            if (i != mUserItems.size() -1){
                                item = item + ", ";
                            }
                            mItemSelect.setText(item);
                        }
                    }
                });

                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mClear.setVisibility(View.INVISIBLE);

                    }
                });

//                mBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        for (int i = 0; i < checkedItems.length; i++){
//                            checkedItems[i] = false;
//                            mUserItems.clear();
//                            mItemSelect.setText("");
//                        }
//                    }
//                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });

        mClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i = 0; i < checkedItems.length; i++){
                            checkedItems[i] = false;
                            mUserItems.clear();
                            mItemSelect.setText("");
                        }
                        mClear.setVisibility(View.INVISIBLE);
//                mUserItems.clear();
//                mItemSelect.setText("");
            }
        });
    }

    public void open_dialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_item,null);
        ListView l1 = row.findViewById(R.id.listView);
        l1.setAdapter(new CustomAdapter(this));
        builder.setView(row);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
