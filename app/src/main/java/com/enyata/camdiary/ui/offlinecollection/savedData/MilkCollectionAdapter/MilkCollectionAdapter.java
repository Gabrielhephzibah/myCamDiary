package com.enyata.camdiary.ui.offlinecollection.savedData.MilkCollectionAdapter;

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
import com.enyata.camdiary.data.model.db.MilkCollection;

import java.util.List;

public class MilkCollectionAdapter extends RecyclerView.Adapter<MilkCollectionAdapter.MilkCollectionViewHolder> {
    private Context context;
    private List<MilkCollection> milkCollections;
    public String[] mColors = {"#F4F4F4","#00000000"};
    onMilkListener onMilkListener;
//    private BdsDataSurveyAdapter.onBdsDataListener onBdsDataListener;

    public MilkCollectionAdapter(Context context, List<MilkCollection> milkCollections, onMilkListener onMilkListener ){
        this.context = context;
        this.milkCollections = milkCollections;
        this.onMilkListener = onMilkListener;

    }



    @NonNull
    @Override
    public MilkCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offline_saved_milk_collection_layout,parent,false);
        return new MilkCollectionViewHolder(view,onMilkListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MilkCollectionViewHolder holder, int position) {
        MilkCollection milkCollection = milkCollections.get(position);
        holder.farmerId.setText(milkCollection.getFarmerId());
        holder.status.setText(milkCollection.getStatusOfCollection());
        holder.parent.setBackgroundColor(Color.parseColor(mColors[position % 2]));

    }

    @Override
    public int getItemCount() {
        return milkCollections.size();
    }

    public class  MilkCollectionViewHolder  extends  RecyclerView.ViewHolder {
        TextView farmerId, churnNo,volume,status;
        Button delete,details;
        LinearLayout parent;
        onMilkListener onMilkListener;
        public MilkCollectionViewHolder(@NonNull View itemView, onMilkListener onMilkListener) {
            super(itemView);
            farmerId = itemView.findViewById(R.id.farmer_id);
            volume = itemView.findViewById(R.id.volume);
            status = itemView.findViewById(R.id.status);
            delete = itemView.findViewById(R.id.deleteBtn);
            details = itemView.findViewById(R.id.detailBtn);

            parent = itemView.findViewById(R.id.parent);
            this.onMilkListener = onMilkListener;

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onMilkListener.deleteMilk(getAdapterPosition());
                }
            });

            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onMilkListener.detailMilk(getAdapterPosition());
                }
            });


        }
    }

    public  interface onMilkListener{
        void deleteMilk(int position);
        void detailMilk(int position);
    }

    public List<MilkCollection> getData() {
        return milkCollections;
    }

    public  void  deleteMilkData(int position){
        milkCollections.remove(position);
        notifyItemRemoved(position);
    }
}
