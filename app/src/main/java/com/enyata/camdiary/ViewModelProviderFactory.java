package com.enyata.camdiary;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.enyata.camdiary.data.DataManager;
import com.enyata.camdiary.ui.aggregations.aggregatorEditProfile.AggregatorEditProfileViewModel;
import com.enyata.camdiary.ui.aggregations.barcode.collectorID.ColectorIdViewModel;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanViewModel;
import com.enyata.camdiary.ui.aggregations.collection.last.LastCollectionViewModel;
import com.enyata.camdiary.ui.aggregations.collection.success.CollectionSuccessViewModel;
import com.enyata.camdiary.ui.aggregations.collectorCollection.CollectorCollectionViewModel;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardViewModel;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailViewModel;
import com.enyata.camdiary.ui.aggregations.entervolume.VolumeViewModel;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHistoryViewModel;
import com.enyata.camdiary.ui.aggregations.milkcollection.MilkCollectionViewModel;
import com.enyata.camdiary.ui.aggregations.product.ProductViewModel;
import com.enyata.camdiary.ui.collections.collectorEditProfile.CollectorEditProfileViewModel;
import com.enyata.camdiary.ui.collections.data.bdsData.BdsViewModel;
import com.enyata.camdiary.ui.collections.data.cdsData.CdsDataViewModel;
import com.enyata.camdiary.ui.collections.data.dataFarmerDetail.DataFarmerDetailViewModel;
import com.enyata.camdiary.ui.collections.data.dataFarmerId.DataFarmerIdViewModel;
import com.enyata.camdiary.ui.collections.data.dataScanBarcode.DataScanCodeViewModel;
import com.enyata.camdiary.ui.collections.data.pdsData.PdsDataViewModel;
import com.enyata.camdiary.ui.collections.newentervolume.NewEnterVolumeViewModel;
import com.enyata.camdiary.ui.collections.rejection.newreason.NewReasonViewModel;
import com.enyata.camdiary.ui.collections.statusofcollection.StatusOfCollectionViewModel;
import com.enyata.camdiary.ui.collections.barcode.BarcodeViewModel;
import com.enyata.camdiary.ui.collections.constant.ConstantLayoutViewModel;
import com.enyata.camdiary.ui.collections.dashboard.DashboardViewModel;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionViewModel;
import com.enyata.camdiary.ui.collections.data.dataSubmission.SubmissionViewModel;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeViewModel;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsViewModel;
import com.enyata.camdiary.ui.collections.farmer.farmerId.FarmerIdViewModel;
import com.enyata.camdiary.ui.collections.history.HistoryViewModel;
import com.enyata.camdiary.ui.collections.rejection.reason.ReasonViewModel;
import com.enyata.camdiary.ui.collections.rejection.rejectsuccess.RejectsuccessViewModel;
import com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard.DataCollectorDashboardViewModel;
import com.enyata.camdiary.ui.datacollector.dataCollectorEditProfile.DataCollectorEditProfileViewModel;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.deliverysuccess.FinishViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.details.DetailsViewModel;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.feedback.FeedbackViewModel;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardViewModel;
import com.enyata.camdiary.ui.deliveries.deliveryEditProfile.DeliveryEditProfileViewModel;
import com.enyata.camdiary.ui.deliveries.history.DeliveryHistoryViewModel;
import com.enyata.camdiary.ui.deliveries.signcustomer.confirmation.ConfirmationViewModel;
import com.enyata.camdiary.ui.deliveries.signcustomer.confirmsuccess.SignsuccessViewModel;
import com.enyata.camdiary.ui.deliveries.signcustomer.signup.SignupViewModel;
import com.enyata.camdiary.ui.editProfile.EditProfileViewModel;
import com.enyata.camdiary.ui.login.LoginViewModel;
import com.enyata.camdiary.ui.offlinecollection.offlineDashBoard.OfflineDashboardViewModel;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.bdsoffline.BdsOfflineViewModel;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.cdsoffline.CdsOfflineViewModel;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editBdsOffline.EditBdsOfflineViewModel;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editCdsOffline.EditCdsOfflineActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editCdsOffline.EditCdsOfflineViewModel;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.editPdsOffline.EditPdsOfflineViewModel;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.pdsoffline.PdsOfflineViewModel;
import com.enyata.camdiary.ui.offlinecollection.offlineMilkCollection.milkRejectionOffline.MilkRejectionOfflineViewModel;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataActivity;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataViewModel;
import com.enyata.camdiary.ui.password.ResetPasswordViewModel;
import com.enyata.camdiary.ui.scanbarcode.aggregatorScanBarCode.AggregatorBarcodeViewModel;
import com.enyata.camdiary.ui.scanbarcode.collectorScanBarcode.CollectorBarcodeViewModel;
import com.enyata.camdiary.ui.scanbarcode.collectorScanBarcode.CollectorScanBarCode;
import com.enyata.camdiary.ui.scanbarcode.dataCollectorScanBarcode.DataCollectorBarcodeViewModel;
import com.enyata.camdiary.ui.splash.SplashViewModel;
import com.enyata.camdiary.utils.rx.SchedulerProvider;
import javax.inject.Inject;
import javax.inject.Singleton;
/**
 * Created by Sanni Michael Tomiwa and Gabriel Hephzibah 3/12/2019.
 */
@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;


    @Inject
    public ViewModelProviderFactory( DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(BarcodeViewModel.class)) {
            //noinspection unchecked
            return (T) new BarcodeViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(FarmerDetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new FarmerDetailsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(FarmerIdViewModel.class)) {
            //noinspection unchecked
            return (T) new FarmerIdViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SubmissionViewModel.class)) {
            //noinspection unchecked
            return (T) new SubmissionViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(RejectsuccessViewModel.class)) {
            //noinspection unchecked
            return (T) new RejectsuccessViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(ConstantLayoutViewModel.class)) {
            //noinspection unchecked
            return (T) new ConstantLayoutViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(ReasonViewModel.class)) {
            //noinspection unchecked
            return (T) new ReasonViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(StatusOfCollectionViewModel.class)) {
            //noinspection unchecked
            return (T) new StatusOfCollectionViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(ScanViewModel.class)) {
            //noinspection unchecked
            return (T) new ScanViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(ColectorIdViewModel.class)) {
            //noinspection unchecked
            return (T) new ColectorIdViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(CollectorBarcodeViewModel.class)) {
            //noinspection unchecked
            return (T) new CollectorBarcodeViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(VolumeViewModel.class)) {
            //noinspection unchecked
            return (T) new VolumeViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(AggregatorHistoryViewModel.class)) {
            //noinspection unchecked
            return (T) new AggregatorHistoryViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(AggregatorBarcodeViewModel.class)) {
            //noinspection unchecked
            return (T) new AggregatorBarcodeViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(DeliveryDashboardViewModel.class)) {
            //noinspection unchecked
            return (T) new DeliveryDashboardViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(CollectorDetailViewModel.class)) {
            //noinspection unchecked
            return (T) new CollectorDetailViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(ProductViewModel.class)) {
            //noinspection unchecked
            return (T) new ProductViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(CollectionSuccessViewModel.class)) {
            //noinspection unchecked
            return (T) new CollectionSuccessViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(DeliveryViewModel.class)) {
            //noinspection unchecked
            return (T) new DeliveryViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(SignupViewModel.class)) {
            //noinspection unchecked
            return (T) new SignupViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(FinishViewModel.class)) {
            //noinspection unchecked
            return (T) new FinishViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(SignsuccessViewModel.class)) {
            //noinspection unchecked
            return (T) new SignsuccessViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(ConfirmationViewModel.class)) {
            //noinspection unchecked
            return (T) new ConfirmationViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(DeliveryHistoryViewModel.class)) {
            //noinspection unchecked
            return (T) new DeliveryHistoryViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(ResetPasswordViewModel.class)) {
            //noinspection unchecked
            return (T) new ResetPasswordViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(HistoryViewModel.class)) {
            //noinspection unchecked
            return (T) new HistoryViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(DetailsViewModel.class)) {
            //noinspection unchecked
            return (T) new DetailsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(FeedbackViewModel.class)) {
            //noinspection unchecked
            return (T) new FeedbackViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(BottlesViewModel.class)) {
            //noinspection unchecked
            return (T) new BottlesViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(LastCollectionViewModel.class)) {
            //noinspection unchecked
            return (T) new LastCollectionViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(AggregatorDashboardViewModel.class)) {
            //noinspection unchecked
            return (T) new AggregatorDashboardViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(DataCollectionViewModel.class)) {
            //noinspection unchecked
            return (T) new DataCollectionViewModel(dataManager, schedulerProvider);

        } else if (modelClass.isAssignableFrom(DashboardViewModel.class)) {
            //noinspection unchecked
            return (T) new DashboardViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(EnterVolumeViewModel.class)) {
            //noinspection unchecked
            return (T) new EnterVolumeViewModel(dataManager, schedulerProvider);
        }  else if (modelClass.isAssignableFrom(MilkCollectionViewModel.class)) {
            //noinspection unchecked
            return (T) new MilkCollectionViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(CdsDataViewModel.class)) {
            //noinspection unchecked
            return (T) new CdsDataViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(BdsViewModel.class)) {
            //noinspection unchecked
            return (T) new BdsViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(DataScanCodeViewModel.class)) {
            //noinspection unchecked
            return (T) new DataScanCodeViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(DataFarmerIdViewModel.class)) {
            //noinspection unchecked
            return (T) new DataFarmerIdViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(DataFarmerDetailViewModel.class)) {
            //noinspection unchecked
            return (T) new DataFarmerDetailViewModel(dataManager, schedulerProvider);
        }

        else if (modelClass.isAssignableFrom(PdsDataViewModel.class)) {
            //noinspection unchecked
            return (T) new PdsDataViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(EditProfileViewModel.class)) {
            //noinspection unchecked
            return (T) new EditProfileViewModel(dataManager, schedulerProvider);
        }

        else if (modelClass.isAssignableFrom(CollectorEditProfileViewModel.class)) {
            //noinspection unchecked
            return (T) new CollectorEditProfileViewModel(dataManager, schedulerProvider);
        }

        else if (modelClass.isAssignableFrom(AggregatorEditProfileViewModel.class)) {
            //noinspection unchecked
            return (T) new AggregatorEditProfileViewModel(dataManager, schedulerProvider);
        }

        else if (modelClass.isAssignableFrom(DeliveryEditProfileViewModel.class)) {
            //noinspection unchecked
            return (T) new DeliveryEditProfileViewModel(dataManager, schedulerProvider);
        }

        else if (modelClass.isAssignableFrom(DataCollectorDashboardViewModel.class)) {
            //noinspection unchecked
            return (T) new DataCollectorDashboardViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(DataCollectorEditProfileViewModel.class)) {
            //noinspection unchecked
            return (T) new DataCollectorEditProfileViewModel(dataManager, schedulerProvider);
        }

        else if (modelClass.isAssignableFrom(DataCollectorBarcodeViewModel.class)) {
            //noinspection unchecked
            return (T) new DataCollectorBarcodeViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(OfflineDashboardViewModel.class)) {
            //noinspection unchecked
            return (T) new OfflineDashboardViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(CdsOfflineViewModel.class)) {
            //noinspection unchecked
            return (T) new CdsOfflineViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(BdsOfflineViewModel.class)) {
            //noinspection unchecked
            return (T) new BdsOfflineViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(PdsOfflineViewModel.class)) {
            //noinspection unchecked
            return (T) new PdsOfflineViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(OfflineSavedDataViewModel.class)) {
            //noinspection unchecked
            return (T) new OfflineSavedDataViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(EditCdsOfflineViewModel.class)) {
            //noinspection unchecked
            return (T) new EditCdsOfflineViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(EditPdsOfflineViewModel.class)) {
            //noinspection unchecked
            return (T) new EditPdsOfflineViewModel(dataManager, schedulerProvider);
        }else if (modelClass.isAssignableFrom(EditBdsOfflineViewModel.class)) {
            //noinspection unchecked
            return (T) new EditBdsOfflineViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(CollectorCollectionViewModel.class)) {
            //noinspection unchecked
            return (T) new CollectorCollectionViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(MilkRejectionOfflineViewModel.class)) {
            //noinspection unchecked
            return (T) new MilkRejectionOfflineViewModel(dataManager, schedulerProvider);

        }else if (modelClass.isAssignableFrom(NewEnterVolumeViewModel.class)) {
            //noinspection unchecked
            return (T) new NewEnterVolumeViewModel(dataManager, schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(NewReasonViewModel.class)) {
            //noinspection unchecked
            return (T) new NewReasonViewModel(dataManager, schedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}