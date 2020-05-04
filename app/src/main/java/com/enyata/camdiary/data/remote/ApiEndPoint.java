

package com.enyata.camdiary.data.remote;

import com.enyata.camdiary.BuildConfig;

import java.util.StringTokenizer;

/**
 * Created by Sanni Michael and Gabriel Hephzibah on 10/12/2019
 */

public final class ApiEndPoint {

    public static final String LOGIN_URL = BuildConfig.BASE_URL + "/";

    public static final String ACCEPTED_VOLUME_URL = BuildConfig.INVENTORY_BASE_URL + "/collection?status=accepted";

    public static final String REJECTED_VOLUME_URL = BuildConfig.INVENTORY_BASE_URL + "/collection?status=rejected";

    public static final String TODAYS_COLLECTION= BuildConfig.INVENTORY_BASE_URL + "/collection/today";

    public static final String ALL_COLLECTIONS = BuildConfig.INVENTORY_BASE_URL + "/collection";

    public static final String ALL_ENTRIES = BuildConfig.INVENTORY_BASE_URL + "/collection/entries";

    public static final String NEW_COLLECTION = BuildConfig.INVENTORY_BASE_URL + "/collection";

    public static final String COLLECTED_VOLUME_URL = BuildConfig.INVENTORY_BASE_URL + "/aggregation/volume";

    public static final String NO_OF_COLLECTORS = BuildConfig.INVENTORY_BASE_URL + "/aggregation/collectors";

    public static final String AGGREGATOR_TODAY_COLLECTION = BuildConfig.INVENTORY_BASE_URL + "/aggregation/today";

    public static final String COLLECTORS_DETAILS = BuildConfig.BASE_URL + "/collectors/verify";

    public static final String COLLECTORS_COLLECTION = BuildConfig.INVENTORY_BASE_URL + "/collection";

    public static final String FARMER_INFO_URL = BuildConfig.BASE_URL + "/farmer";

    public  static  final  String SAVE_AGGREGATION = BuildConfig.INVENTORY_BASE_URL + "/aggregation";

    public  static  final String PENDING_DELIVERY = BuildConfig.TRANSACTION_BASE_URL + "/order/dispatcher/assigned";

    public  static  final  String DELIVERY_COMPLETED = BuildConfig.INVENTORY_BASE_URL + "/delivery/completed/today";

    public  static  final  String BOTTLE_INVENTORY = BuildConfig.INVENTORY_BASE_URL + "/delivery/bottles";

    public static final String CREATE_DELIVERY = BuildConfig.INVENTORY_BASE_URL + "/delivery";

    public  static  final  String RESET_PASSWORD = BuildConfig.BASE_URL + "/auth/reset-password-begin";

    public  static  final String COLLECTION_HISTORY = BuildConfig.INVENTORY_BASE_URL + "/collection/completed/history";

    public  static  final  String DELIVERY_HISTORY = BuildConfig.TRANSACTION_BASE_URL + "/order/dispatcher/history";

    public  static  final  String AGGREGATION_HISTORY = BuildConfig.INVENTORY_BASE_URL + "/aggregation/history";

    public static final String DISPATCHER_SIGNUP =BuildConfig.BASE_URL+ "/customer/signup/dispatcher";

    public  static final  String FARMER_DETAILS = BuildConfig.BASE_URL + "/farmer";

    public  static final  String CHANGE_PASSWORD = BuildConfig.BASE_URL + "/auth/change-password";

    public  static final  String EDIT_PROFILE = BuildConfig.BASE_URL + "/userprofile";

    public  static final  String CDS_DATA_COLLECTION = BuildConfig.INVENTORY_BASE_URL + "/collection/genSurveyCollection";

    public  static final  String PDS_DATA_COLLECTION = BuildConfig.INVENTORY_BASE_URL + "/collection/monthlyRecurringData";

    public  static final  String BDS_DATA_COLLECTION = BuildConfig.INVENTORY_BASE_URL + "/collection/farmerRegDataCollection";

    public  static final  String MILK_COLLECTION_DATA = BuildConfig.INVENTORY_BASE_URL + "/aggregation/collection";

    public  static final  String CREATE_AGGREGATION = BuildConfig.INVENTORY_BASE_URL + "/aggregation";

    public static final String GET_ELECTORAL_WARD = BuildConfig.INVENTORY_BASE_URL + "/collection/location/ward?area_council=";

    public  static final  String GET_COPERATIVE_NAME = BuildConfig.BASE_URL + "/cooperatives/name";

    public  static final  String GET_ORDER_DETAILS = BuildConfig.TRANSACTION_BASE_URL + "/order/dispatcher/assigned";



    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
