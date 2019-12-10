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

package com.enyata.camdiary.di.builder;

import com.enyata.camdiary.ui.about.AboutFragmentProvider;
import com.enyata.camdiary.ui.collections.barcode.BarcodeActivity;
import com.enyata.camdiary.ui.collections.dashboard.DashboardActivity;
import com.enyata.camdiary.ui.collections.entervolume.EnterVolumeActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerDetails.FarmerDetailsActivity;
import com.enyata.camdiary.ui.collections.farmer.farmerId.FarmerIdActivity;
import com.enyata.camdiary.ui.collections.successfulcollection.SuccessfulActivity;
import com.enyata.camdiary.ui.feed.FeedActivity;
import com.enyata.camdiary.ui.feed.FeedActivityModule;
import com.enyata.camdiary.ui.feed.blogs.BlogFragmentProvider;
import com.enyata.camdiary.ui.feed.opensource.OpenSourceFragmentProvider;
import com.enyata.camdiary.ui.login.LoginActivity;
import com.enyata.camdiary.ui.main.MainActivity;
import com.enyata.camdiary.ui.main.rating.RateUsDialogProvider;
import com.enyata.camdiary.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by amitshekhar on 14/09/17.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            FeedActivityModule.class,
            BlogFragmentProvider.class,
            OpenSourceFragmentProvider.class})
    abstract FeedActivity bindFeedActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector
    abstract BarcodeActivity bindBarcodeActivity();

    @ContributesAndroidInjector
    abstract FarmerDetailsActivity bindFarmerDetailsActivity();

    @ContributesAndroidInjector
    abstract FarmerIdActivity bindFarmerIdActivity();

    @ContributesAndroidInjector
    abstract SuccessfulActivity bindSuccessfulActivity();

    @ContributesAndroidInjector
    abstract EnterVolumeActivity bindEnterVolumeActivity();

    @ContributesAndroidInjector
    abstract DashboardActivity bindDashboardActivity();

    @ContributesAndroidInjector(modules = {
            AboutFragmentProvider.class,
            RateUsDialogProvider.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();
}
