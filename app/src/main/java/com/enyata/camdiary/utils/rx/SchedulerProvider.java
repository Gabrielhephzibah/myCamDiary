

package com.enyata.camdiary.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by Sanni Michael on 10/12/2019
 */

public interface SchedulerProvider {

    Scheduler computation();

    Scheduler io();

    Scheduler ui();
}
