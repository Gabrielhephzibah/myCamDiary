package com.enyata.camdiary.ui.offlinecollection.offlineDashBoard;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.data.local.db.DataBaseClient;
import com.enyata.camdiary.data.model.db.CdsDataCollection;
import com.enyata.camdiary.data.model.db.MilkCollection;
import com.enyata.camdiary.ui.base.BaseViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;

import io.reactivex.Completable;

public class OfflineDashboardViewModel extends BaseViewModel<OfflineDashboardNavigator> {
    public OfflineDashboardViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    public void onBackToLogin(){
        getNavigator().onBackToLogin();

    }




//    Context context;
//
//    public void getContext(Context context){
//        this.context = context;
//    }
//
//
//
//
//    public void onBack(){
//        getNavigator().onBack();
//    }
//
//    public Completable addNote(MilkCollection milkCollection){
//       return DataBaseClient.getInstance(context)
//                .getNoteDataBase()
//                .dataObjectAccess()
//                .addMilkCollection(milkCollection);
//    }
}
