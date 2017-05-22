package com.example.bikramkoju.barberfinalapp;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bikramkoju on 5/22/2017.
 */

public class TypeA extends Fragment {
    DatabaseHelper db;
    Module module;

    private GridView gridView;
    private GridViewAdapter gridViewAdapter;
    private TextView result;

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    private long sum;

    public TypeA() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_type, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        result = (TextView) view.findViewById(R.id.resulta);

        sharedPreferences = this.getActivity().getSharedPreferences("values", 0);
        editor = sharedPreferences.edit();

        gridView = (GridView) view.findViewById(R.id.gridView);
        gridViewAdapter = new GridViewAdapter(getActivity(), R.layout.grid_item_layout, getData());
        gridView.setAdapter(gridViewAdapter);
        gridView.setMinimumHeight(400);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                long value = Long.parseLong(item.getTitle());

                sum = sum + value;
                result.setText(String.valueOf(sum));
                db=new DatabaseHelper(getActivity());
                db.addIncome(String.valueOf(sum));
            }
        });
        module = new Module();
        module.setSum(sum);
    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        TypedArray nums = getResources().obtainTypedArray(R.array.numbers);
        TypedArray tits=getResources().obtainTypedArray(R.array.titles);

        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            String vlue = nums.getString(i);
            String title=tits.getString(i);
            imageItems.add(new ImageItem(bitmap,title, vlue));
        }
        return imageItems;
    }

}
