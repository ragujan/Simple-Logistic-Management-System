package com.jiat.ejb.remote;

import jakarta.ejb.Remote;

@Remote
public interface TimerService {
    public void schedule(long duration);
    public void cancel();
}
