package com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveyAdapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.enyata.camdiary.R;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataActivity;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataViewModel;
import com.enyata.camdiary.ui.offlinecollection.savedData.dataSurveySavedData.DataSurveySavedDataFragement;

import java.util.List;

public class CdsDataSurveyAdapter extends RecyclerView.Adapter<CdsDataSurveyAdapter.CdsViewHolder> {

   private Context context;
    private List<CdsDataCollection> cdsDataCollection;
    private OfflineSavedDataViewModel offlineSavedDataViewModel;
    private  onDataListener onDataListener;

//    public int[] mColors = {R.color.adater_color,R.color.white};
    public String[] mColors = {"#F4F4F4","#00000000"};

    public CdsDataSurveyAdapter(Context context, List<CdsDataCollection> cdsDataCollection,onDataListener onDataListener) {
        this.context = context;
        this.cdsDataCollection = cdsDataCollection;
        this.onDataListener = onDataListener;
//        offlineSavedDataViewModel = ViewModelProviders.of((OfflineSavedDataActivity)context).get(OfflineSavedDataViewModel.class);

    }

    @NonNull
    @Override
    public CdsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cds_offline_data_item_layout,parent,false);
        return new CdsViewHolder(view,onDataListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CdsViewHolder holder, int position) {
        CdsDataCollection data = cdsDataCollection.get(position);
        holder.name.setText(String.format("%s  %s", data.getFirstName(), data.getLastName()));
        holder.phoneNumber.setText(data.getPhone_no());
        holder.gender.setText(data.getGender());
       holder.parent.setBackgroundColor(Color.parseColor(mColors[position % 2]));
//        int bgColor = ContextCompat.getColor(context, mColors[position % 2]);
//        holder.itemView.setBackgroundColor(bgColor);


    }



    @Override
    public int getItemCount() {
        return cdsDataCollection.size();
    }

    public  class CdsViewHolder extends  RecyclerView.ViewHolder{
        TextView name, phoneNumber,gender;
        Button delete,edit;
        onDataListener onDataListener;
       LinearLayout parent;

        public CdsViewHolder(@NonNull View itemView, onDataListener onDataListener) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            gender = itemView.findViewById(R.id.gender);
            delete = itemView.findViewById(R.id.deleteBtn);
//            edit = itemView.findViewById(R.id.editBtn);
            parent = itemView.findViewById(R.id.parent);
            this.onDataListener = onDataListener;

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDataListener.onDataClick(getAdapterPosition());
//                    int position = getAdapterPosition();
//                    Log.i("Delete Button","Delete Clicked");
//                    CdsDataCollection dataCollection = cdsDataCollection.get(position);
//                    Log.i("Delete Button","Delete at position"+ position);
//                  offlineSavedDataViewModel.deleteCdsData(dataCollection);
//                  cdsDataCollection.remove(dataCollection);
//                  notifyItemRemoved(position);

                }
            });

//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onDataListener.onEditClick(getAdapterPosition());
//                }
//            });

        }
    }

    public  void  deleteMyNote(int position){
        cdsDataCollection.remove(position);
        notifyItemRemoved(position);
    }

    public interface onDataListener {
        void onDataClick(int position);
        void onEditClick(int position);

    }

    public List<CdsDataCollection> getData() {
        return cdsDataCollection;
    }

}
