package com.example.bikramkoju.barberfinalapp;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 5/29/2017.
 */

public class UpdateAdapter extends BaseAdapter {
    Context c;
    ArrayList<InfoModule> myList;
    LayoutInflater inflater;

    public UpdateAdapter(ShowForUpdate showForUpdate, ArrayList<InfoModule> list) {
        c = showForUpdate;
        myList = list;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolders hold;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listrecord, null);
            hold = new ViewHolders();
            hold.showUtitle = (TextView) convertView.findViewById(R.id.utitle);
            hold.showTitle = (TextView) convertView.findViewById(R.id.titles);
            convertView.setTag(hold);
        } else {
            hold = (ViewHolders) convertView.getTag();
        }
        hold.showUtitle.setText(myList.get(position).getUtitle());
        hold.showTitle.setText(myList.get(position).getTitle());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(c);
                dialog.setContentView(R.layout.update);

                final EditText updatename = (EditText) dialog.findViewById(R.id.uname);
                final EditText updateprice = (EditText) dialog.findViewById(R.id.uphone);


                Button updateall = (Button) dialog.findViewById(R.id.ubtn);
                Button deleteall = (Button) dialog.findViewById(R.id.dbtn);
                Button cancellall = (Button) dialog.findViewById(R.id.ucbtn);

                dialog.show();

                updateall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseHelper dp = new DatabaseHelper(c);
                        InfoModule infoModule = new InfoModule();
                        infoModule.setUtitle(updatename.getText().toString());
                        infoModule.setTitle(updateprice.getText().toString());
                        dp.updateData(infoModule);
                        dialog.dismiss();

                        ShowForUpdate showForUpdate= (ShowForUpdate) c;


                    }
                });

                deleteall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseHelper dp=new DatabaseHelper(c);
                        dp.removeData(myList.get(position).getUtitle());
                        dialog.dismiss();
                        ShowForUpdate showForUpdate= (ShowForUpdate) c;
                    }
                });

                cancellall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        return convertView;
    }

    private class ViewHolders {
        TextView showUtitle, showTitle;
    }
}
