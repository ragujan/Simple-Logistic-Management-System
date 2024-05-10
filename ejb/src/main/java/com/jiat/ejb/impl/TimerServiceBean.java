package com.jiat.ejb.impl;

import com.jiat.ejb.remote.TimerService;
import jakarta.annotation.Resource;
import jakarta.ejb.*;

@Stateless
public class TimerServiceBean implements TimerService {
//    @Schedules({
//        @Schedule(persistent = true, hour = "*", minute = "*", second = "*"),
//        @Schedule(persistent = true, hour = "*", minute = "*", second = "*"),
//        @Schedule(persistent = true, hour = "*", minute = "*", second = "*"),
//    })
    public void sysSchedule(Timer timer){
        System.out.println("Hello, This is schedule task..."+Thread.currentThread());
        //timer.cancel();
    }

    @Resource
    SessionContext context;

    Timer timer;

    public void schedule(long duration) {

        ScheduleExpression se = new ScheduleExpression();
        se.hour("*");
        se.minute("*");
        se.second("*/"+duration);

        //Timer timer = context.getTimerService().createTimer(duration, "My Custom timer");
        timer = context.getTimerService().createCalendarTimer(se);
    }

    @Timeout
    public void task(){
        System.out.println("Hello, This is schedule task...");
    }

    @Override
    public void cancel() {
        if (timer != null){
            timer.cancel();
        }
    }
}
