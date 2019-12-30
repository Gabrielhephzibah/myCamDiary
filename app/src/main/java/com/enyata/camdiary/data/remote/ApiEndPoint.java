/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.enyata.camdiary.data.remote;

import com.enyata.camdiary.BuildConfig;

/**
 * Created by Sanni Michael and Gabriel Hephzibah on 10/12/2019
 */

public final class ApiEndPoint {

    public static final String LOGIN_URL = BuildConfig.BASE_URL + "/";

    public static final String ACCEPTED_VOLUME_URL = BuildConfig.INVENTORY_BASE_URL + "/collection/accepted";

    public static final String REJECTED_VOLUME_URL = BuildConfig.INVENTORY_BASE_URL + "/collection/rejected";

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

    public  static  final  String AGGREGATOR_HISTORY = BuildConfig.INVENTORY_BASE_URL + "/aggregation";



    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
