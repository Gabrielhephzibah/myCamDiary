

package com.enyata.camdiary.di.builder;

import com.enyata.camdiary.ui.aggregations.aggregatorEditProfile.AggregatorEditProfileActivity;
import com.enyata.camdiary.ui.aggregations.barcode.collectorID.CollectorIdActivity;
import com.enyata.camdiary.ui.aggregations.barcode.scanbarcode.ScanActivity;
import com.enyata.camdiary.ui.aggregations.collection.last.LastCollectionActivity;
import com.enyata.camdiary.ui.aggregations.collection.success.CollectionSuccessActivity;
import com.enyata.camdiary.ui.aggregations.dashboard.AggregatorDashboardActivity;
import com.enyata.camdiary.ui.aggregations.details.CollectorDetailActivity;
import com.enyata.camdiary.ui.aggregations.entervolume.VolumeActivity;
import com.enyata.camdiary.ui.aggregations.history.AggregatorHIstoryActivity;
import com.enyata.camdiary.ui.aggregations.milkcollection.MilkCollectionActivity;
import com.enyata.camdiary.ui.aggregations.product.ProductActivity;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.collectorEditProfile.CollectorEditProfileActivity;
import com.enyata.camdiary.ui.collections.collectorEditProfile.CollectorEditProfileViewModel;
import com.enyata.camdiary.ui.collections.constant.ConstantLayoutActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.data.bdsData.BdsDataActivity;
import com.enyata.camdiary.ui.collections.data.cdsData.CdsDataActivity;
import com.enyata.camdiary.ui.collections.data.dataCollection.DataCollectionActivity;
import com.enyata.camdiary.ui.collections.data.dataFarmerDetail.DataFarmerDetailActivity;
import com.enyata.camdiary.ui.collections.data.dataFarmerId.DataFarmerIdActivity;
import com.enyata.camdiary.ui.collections.data.dataScanBarcode.DataScanCodeActivity;
import com.enyata.camdiary.ui.collections.data.dataSubmission.SubmissionActivity;
import com.enyata.camdiary.ui.collections.data.pdsData.PdsDataActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerId.FarmerIdActivity;
import com.enyata.camdiary.ui.collections.history.HistoryActivity;
import com.enyata.camdiary.ui.collections.rejection.reason.ReasonActivity;
import com.enyata.camdiary.ui.collections.rejection.rejectsuccess.RejectsuccessActivity;
import com.enyata.camdiary.ui.collections.statusofcollection.StatusOfCollectionActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorDashBoard.DataCollectorDashboardActivity;
import com.enyata.camdiary.ui.datacollector.dataCollectorEditProfile.DataCollectorEditProfileActivity;
import com.enyata.camdiary.ui.deliveries.bottles.BottlesActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.delivery.DeliveryActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.deliverysuccess.FinishActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.details.DetailsActivity;
import com.enyata.camdiary.ui.deliveries.deliveries_delivery.feedback.FeedbackActivity;
import com.enyata.camdiary.ui.deliveries.deliveryDashboard.DeliveryDashboardActivity;
import com.enyata.camdiary.ui.deliveries.deliveryEditProfile.DeliveryEditProfileActivity;
import com.enyata.camdiary.ui.deliveries.history.DeliveryHistoryActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.confirmation.ConfirmationActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.confirmsuccess.SignsuccessActivity;
import com.enyata.camdiary.ui.deliveries.signcustomer.signup.SignupActivity;
import com.enyata.camdiary.ui.editProfile.EditProfileActivity;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDashBoard.OfflineDashboardActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.bdsoffline.BdsOfflineActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.cdsoffline.CdsOfflineActivity;
import com.enyata.camdiary.ui.offlinecollection.offlineDataSurvey.pdsoffline.PdsOfflineActivity;
import com.enyata.camdiary.ui.offlinecollection.savedData.OfflineSavedDataActivity;
import com.enyata.camdiary.ui.password.ResetPasswordActivity;
import com.enyata.camdiary.ui.scanbarcode.aggregatorScanBarCode.AggregatorScanBarCode;
import com.enyata.camdiary.ui.scanbarcode.collectorScanBarcode.CollectorScanBarCode;
import com.enyata.camdiary.ui.scanbarcode.dataCollectorScanBarcode.DataCollectorBarcodeActivity;
import com.enyata.camdiary.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Gabriel Hephzibah on 14/09/17.
 */
@Module
public abstract class ActivityBuilder {


    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract BarcodeActivity bindBarcodeActivity();

    @ContributesAndroidInjector
    abstract FarmerDetailsActivity bindFarmerDetailsActivity();

    @ContributesAndroidInjector
    abstract FarmerIdActivity bindFarmerIdActivity();

    @ContributesAndroidInjector
    abstract StatusOfCollectionActivity bindSuccessfulActivity();

    @ContributesAndroidInjector
    abstract EnterVolumeActivity bindEnterVolumeActivity();

    @ContributesAndroidInjector
    abstract DashboardActivity bindDashboardActivity();

    @ContributesAndroidInjector
    abstract DataCollectionActivity bindDataCollectionActivity();

    @ContributesAndroidInjector
    abstract HistoryActivity bindHistoryActivity();

    @ContributesAndroidInjector
    abstract ConstantLayoutActivity bindConstantLayoutActivity();

    @ContributesAndroidInjector
    abstract RejectsuccessActivity bindRejectsuccessActivity();

    @ContributesAndroidInjector
    abstract SubmissionActivity bindSubmissionActivity();

    @ContributesAndroidInjector
    abstract ReasonActivity bindReasonActivity();

    @ContributesAndroidInjector
    abstract ScanActivity bindScanActivity();

    @ContributesAndroidInjector
    abstract CollectorDetailActivity bindCollectorDetailActivity();

    @ContributesAndroidInjector
    abstract CollectorIdActivity bindCollectorIdActivity();

    @ContributesAndroidInjector
    abstract ProductActivity bindProductActivity();

    @ContributesAndroidInjector
    abstract VolumeActivity bindVolumeActivity();

    @ContributesAndroidInjector
    abstract AggregatorHIstoryActivity bindAggregatorHIstoryActivity();


    @ContributesAndroidInjector
    abstract CollectionSuccessActivity bindCollectionSuccessActivity();

    @ContributesAndroidInjector
    abstract LastCollectionActivity bindLastCollectionActivity();

    @ContributesAndroidInjector
    abstract DeliveryActivity bindDeliveryActivity();

    @ContributesAndroidInjector
    abstract DeliveryDashboardActivity bindDeliveryDashboardActivity();

    @ContributesAndroidInjector
    abstract SignupActivity bindSignupActivity();

    @ContributesAndroidInjector
    abstract ConfirmationActivity bindConfirmationActivity();

    @ContributesAndroidInjector
    abstract SignsuccessActivity bindSignsuccessActivity();

    @ContributesAndroidInjector
    abstract DeliveryHistoryActivity bindDeliveryHistoryActivity();

    @ContributesAndroidInjector
    abstract ResetPasswordActivity ResetPasswordActivity();

    @ContributesAndroidInjector
    abstract BottlesActivity bindBottlesActivity();

    @ContributesAndroidInjector
    abstract FinishActivity bindFinishActivity();

    @ContributesAndroidInjector
    abstract FeedbackActivity bindFeedbackActivity();

    @ContributesAndroidInjector
    abstract DetailsActivity bindDetailsActivity();

    @ContributesAndroidInjector
    abstract AggregatorDashboardActivity bindAggregatorDashboardActivity();

    @ContributesAndroidInjector
    abstract CollectorScanBarCode bindCollectorScanBarCode();

    @ContributesAndroidInjector
    abstract AggregatorScanBarCode bindAggregatorScanBarCode();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector
    abstract MilkCollectionActivity bindMilkCollectionActivity();

    @ContributesAndroidInjector
    abstract CdsDataActivity bindCdsDataActivity();

    @ContributesAndroidInjector
    abstract BdsDataActivity bindBdsDataActivity();

    @ContributesAndroidInjector
    abstract DataScanCodeActivity bindDataScanCodeActivity();

    @ContributesAndroidInjector
    abstract DataFarmerIdActivity bindDataFarmerIdActivity();

    @ContributesAndroidInjector
    abstract DataFarmerDetailActivity bindDataFarmerDetailActivity();

    @ContributesAndroidInjector
    abstract PdsDataActivity bindPdsDataActivity();

    @ContributesAndroidInjector
    abstract EditProfileActivity bindEditProfileActivity();

    @ContributesAndroidInjector
    abstract CollectorEditProfileActivity bindCollectorEditProfileActivity();

    @ContributesAndroidInjector
    abstract AggregatorEditProfileActivity bindAggregatorEditProfileActivity();

    @ContributesAndroidInjector
    abstract DeliveryEditProfileActivity bindDeliveryEditProfileActivity();

    @ContributesAndroidInjector
    abstract DataCollectorDashboardActivity bindDataCollectorDashboardActivity();

    @ContributesAndroidInjector
    abstract DataCollectorEditProfileActivity bindDataCollectorEditProfileActivity();

    @ContributesAndroidInjector
    abstract DataCollectorBarcodeActivity bindDataCollectorBarcodeActivity();

    @ContributesAndroidInjector
    abstract OfflineDashboardActivity bindOfflineDashboardActivity();

    @ContributesAndroidInjector
    abstract CdsOfflineActivity bindCdsOfflineActivity();

    @ContributesAndroidInjector
    abstract BdsOfflineActivity bindBdsOfflineActivity();

    @ContributesAndroidInjector
    abstract PdsOfflineActivity bindPdsOfflineActivity();

    @ContributesAndroidInjector
    abstract OfflineSavedDataActivity bindOfflineSavedDataActivity();

}
