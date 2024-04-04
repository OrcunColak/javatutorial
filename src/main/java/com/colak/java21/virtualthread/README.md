# Configuring Virtual Thread Scheduler

**jdk.virtualThreadScheduler.parallelism **  
This property sets the number of platform threads available for scheduling virtual threads.  
By default, it equals the number of available processors.

**jdk.virtualThreadScheduler.maxPoolSize**  
This property defines the maximum number of platform threads available to the scheduler.   
Its default value is typically 256, but you can adjust it for your application's needs: