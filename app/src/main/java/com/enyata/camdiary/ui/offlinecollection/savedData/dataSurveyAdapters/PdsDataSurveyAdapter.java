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
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.PdsDataCollection;

import java.util.List;

public class PdsDataSurveyAdapter extends RecyclerView.Adapter<PdsDataSurveyAdapter.PdsViewHolder> {

    private Context context;
    private List<PdsDataCollection> pdsDataCollection;
    private onPdsDataListener onPdsDataListener;
    public String[] mColors = {"#F4F4F4","#00000000"};

    public PdsDataSurveyAdapter(Context context, List<PdsDataCollection> pdsDataCollection,onPdsDataListener onPdsDataListener) {
        this.context = context;
        this.pdsDataCollection = pdsDataCollection;
        this.onPdsDataListener = onPdsDataListener;
    }

    @NonNull
    @Override
    public PdsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cds_offline_data_item_layout,parent,false);
        return new PdsViewHolder(view,onPdsDataListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PdsViewHolder holder, int position) {
        PdsDataCollection pdsData = pdsDataCollection.get(position);
        holder.name.setText(String.format("%s  %s", pdsData.getFirstName(), pdsData.getLastName()));
        holder.phoneNumber.setText(pdsData.getPhone_no());
        holder.gender.setText(pdsData.getGender());
        holder.parent.setBackgroundColor(Color.parseColor(mColors[position % 2]));

    }

    @Override
    public int getItemCount() {
        return pdsDataCollection.size();
    }

    public class  PdsViewHolder extends RecyclerView.ViewHolder{
        TextView name, phoneNumber,gender;
        Button delete,edit;
        onPdsDataListener onPdsDataListener;
        LinearLayout parent;


        public PdsViewHolder(@NonNull View itemView, onPdsDataListener onPdsDataListener) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            gender = itemView.findViewById(R.id.gender);
            delete = itemView.findViewById(R.id.deleteBtn);
//            edit = itemView.findViewById(R.id.editBtn);
            parent = itemView.findViewById(R.id.parent);
            this.onPdsDataListener = onPdsDataListener;

//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onPdsDataListener.onPdsEditClick(getAdapterPosition());
//
//                }
//            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPdsDataListener.onPdsDeleteClick(getAdapterPosition());
                }
            });
        }




    }

    public  void  deletePdsData(int position){
        pdsDataCollection.remove(position);
        notifyItemRemoved(position);
    }

    public interface onPdsDataListener {
        void onPdsDeleteClick(int position);
        void onPdsEditClick(int position);

    }

    public List<PdsDataCollection> getData() {
        return pdsDataCollection;
    }

    public int getSize(){
        return pdsDataCollection.size();
    }
}
