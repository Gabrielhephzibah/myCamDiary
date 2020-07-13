package com.enyata.camdiary.ui.aggregations.collectorCollection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.enyata.camdiary.R;

import java.util.List;

import retrofit2.http.PUT;

public class ChurnDetailsAdapter extends RecyclerView.Adapter<ChurnDetailsAdapter.ChurnViewHolder> {

    private Context context;
    private List<ChurnDetailsItem>churnDetailsItems;

    public ChurnDetailsAdapter(Context context, List<ChurnDetailsItem>churnDetailsItems){
        this.context = context;
        this.churnDetailsItems = churnDetailsItems;
    }



    @NonNull
    @Override
    public ChurnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_more_item_layout,parent,false);
        return new ChurnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChurnViewHolder holder, int position) {
        ChurnDetailsItem item = churnDetailsItems.get(position);
        holder.name.setText(String.format("%s %s", item.getFirstName(), item.getLastName()));
        holder.volume.setText(String.format("%s Litres", item.getVolume()));
        holder.verificationId.setText(item.getVerificationId());

    }

    @Override
    public int getItemCount() {
        return churnDetailsItems.size();
    }

    public  class ChurnViewHolder extends  RecyclerView.ViewHolder {
        TextView name, verificationId, volume;

        public ChurnViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            verificationId = itemView.findViewById(R.id.verificationId);
            volume = itemView.findViewById(R.id.volume);
        }
    }
}
