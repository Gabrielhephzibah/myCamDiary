package com.enyata.camdiary.ui.aggregations.collectorCollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enyata.camdiary.R;

import java.util.List;

public class CollectorCollectionAdapter extends RecyclerView.Adapter<CollectorCollectionAdapter.CollectionViewHolder> {
    private Context context;
//    private  CollectorCollectionListItem collectionListItem;
    private List<CollectorCollectionListItem>collectionListItems;
    onListItem onListItem;


    public CollectorCollectionAdapter(Context context, List<CollectorCollectionListItem>collectionListItems, onListItem onListItem ){
        this.context = context;
        this.collectionListItems = collectionListItems;
        this.onListItem = onListItem;
    }




    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collector_collection_layout,parent,false);
        return new CollectionViewHolder(view,onListItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder, int position) {
        CollectorCollectionListItem listItem = collectionListItems.get(position);
        holder.churnId.setText(listItem.getChurnId());
        holder.volume.setText(String.format("%s Litres", listItem.getVolume()));



    }

    @Override
    public int getItemCount() {
        return collectionListItems.size();
    }

    public class CollectionViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        TextView churnId,volume;
        LinearLayout parent;
        Button viewMoreBtn;
        onListItem onListItem;

        public CollectionViewHolder(@NonNull View itemView, onListItem onListItem)  {
            super(itemView);
            churnId = itemView.findViewById(R.id.churnId);
            volume = itemView.findViewById(R.id.volume);
            parent = itemView.findViewById(R.id.parentWrapper);
            viewMoreBtn = itemView.findViewById(R.id.viewMoreBtn);
            this.onListItem = onListItem;
            itemView.setOnClickListener(this);

            viewMoreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onListItem.onViewMore(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View view) {
            parent.setElevation(8.0f);
            onListItem.onItemClick(getAdapterPosition());

        }
    }

    public interface onListItem{
        void onViewMore(int position);
        void onItemClick(int position);
    }

    public List<CollectorCollectionListItem> getData(){
        return collectionListItems;
    }

    public  void  removeCollectionData(int position){
        collectionListItems.remove(position);
        notifyItemRemoved(position);
    }
}
