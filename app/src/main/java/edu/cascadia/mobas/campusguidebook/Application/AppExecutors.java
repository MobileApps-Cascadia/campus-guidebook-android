package edu.cascadia.mobas.campusguidebook.Application;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

// Global executor pools for sequential (disk), concurrent (web), or scheduled task execution

public class AppExecutors {

    private final Executor mDiskIO;
    private final Executor mNetworkIO;
    private final ScheduledExecutorService mScheduled;

    private AppExecutors(Executor diskIO, Executor networkIO, ScheduledExecutorService scheduled) {
        this.mDiskIO = diskIO;
        this.mNetworkIO = networkIO;
        this.mScheduled = scheduled;
    }

    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
                Executors.newScheduledThreadPool(2));
    }

    public Executor diskIO() {
        return mDiskIO;
    }

    public Executor networkIO() {
        return mNetworkIO;
    }

    public ScheduledExecutorService scheduled() {
        return mScheduled;
    }
}