package com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveyAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.db.BdsDataCollections;

import java.util.List;

public class BdsDataSurveyAdapter extends RecyclerView.Adapter<BdsDataSurveyAdapter.BdsViewHolder> {

    private Context context;
    private List<BdsDataCollections> bdsDataCollection;
    public String[] mColors = {"#F4F4F4","#00000000"};
    private  onBdsDataListener onBdsDataListener;


    public BdsDataSurveyAdapter(Context context, List<BdsDataCollections> bdsDataCollection, onBdsDataListener onBdsDataListener) {
        this.context = context;
        this.bdsDataCollection = bdsDataCollection;
        this.onBdsDataListener = onBdsDataListener;
    }

    @NonNull
    @Override
    public BdsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cds_offline_data_item_layout,parent,false);
        return new BdsDataSurveyAdapter.BdsViewHolder(view,onBdsDataListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BdsViewHolder holder, int position) {
        BdsDataCollections pdsData = bdsDataCollection.get(position);
        holder.name.setText(String.format("%s  %s", pdsData.getFirstName(), pdsData.getLastName()));
        holder.phoneNumber.setText(pdsData.getPhoneNo());
        holder.gender.setText(pdsData.getGender());
        holder.parent.setBackgroundColor(Color.parseColor(mColors[position % 2]));
    }

    @Override
    public int getItemCount() {
        return bdsDataCollection.size();
    }

    public  class BdsViewHolder extends  RecyclerView.ViewHolder{
        TextView name, phoneNumber,gender;
        Button delete,edit;
        LinearLayout parent;
        onBdsDataListener onBdsDataListener;

        public BdsViewHolder(@NonNull View itemView, onBdsDataListener onBdsDataListener) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            gender = itemView.findViewById(R.id.gender);
            delete = itemView.findViewById(R.id.deleteBtn);
//            edit = itemView.findViewById(R.id.editBtn);
            parent = itemView.findViewById(R.id.parent);
            this.onBdsDataListener = onBdsDataListener;


//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onBdsDataListener.onBdsEditClick(getAdapterPosition());
//                }
//            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBdsDataListener.onBdsDeleteClick(getAdapterPosition());
                }
            });
        }
    }

    public  interface onBdsDataListener{
        void onBdsDeleteClick(int position);
        void onBdsEditClick(int position);
    }

    public  void  deleteBdsData(int position){
        bdsDataCollection.remove(position);
        notifyItemRemoved(position);
    }

    public List<BdsDataCollections> getData() {
        return bdsDataCollection;
    }

    public int getSize(){
        return bdsDataCollection.size();
    }
}
