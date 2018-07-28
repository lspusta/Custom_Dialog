package com.itkido.checkboxlist;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
    Context c;
    ArrayList<SingleRow> arrayList;
    CustomAdapter(Context c){
        this.c=c;
        arrayList = new ArrayList<>();
        Resources res = c.getResources();
        String[] names = res.getStringArray(R.array.country);
        int[] images = {R.drawable.unitedstatesflag};

        for (int i=0; i<names.length; i++){
            arrayList.add(new SingleRow(names[i], images[i]));
        }
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.custom_listview,parent, false);
        TextView t1 = (TextView)row.findViewById(R.id.textView);
        ImageView i1 = (ImageView)row.findViewById(R.id.imageView);
        SingleRow temp_obj = arrayList.get(position);
        t1.setText(temp_obj.name);
        i1.setImageResource(temp_obj.image);
        return row;
    }
}
