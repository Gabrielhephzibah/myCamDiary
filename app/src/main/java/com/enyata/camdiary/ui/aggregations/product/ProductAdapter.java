package com.enyata.camdiary.ui.aggregations.product;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.enyata.camdiary.R;
import com.enyata.camdiary.ui.collections.dashboard.DashboardCollectorList;

import java.util.ArrayList;

public class ProductAdapter  extends ArrayAdapter {
    private Context mcontext;


    public ArrayList<ProductList> productLists;

    public ProductAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<ProductList>list) {
        super(context, 0 , list);
        mcontext = context;
        productLists = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mcontext).inflate(R.layout.product_list_layout,parent,false);

        ProductList product = productLists.get(position);
        TextView name = listItem.findViewById(R.id.name);
        name.setText(product.getFullName());

        TextView litres = listItem.findViewById(R.id.litres);
        litres.setText(product.getMyLitres());

        TextView companyName  = listItem.findViewById(R.id.companyname);
        companyName.setText(product.getCompanyName());

        TextView farmerId  = listItem.findViewById(R.id.farmerId);
        farmerId.setText(product.getCompanyId());



        return listItem;
    }

    @Override
    public int getCount() {
        if(productLists.size() > 10){
            return 5;
        }else{
            return productLists.size();
        }
    }


}
