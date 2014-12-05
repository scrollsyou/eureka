package com.netflix.eureka2.metric;

import com.netflix.servo.monitor.Counter;
import com.netflix.servo.monitor.LongGauge;

/**
 * @author David Liu
 */
public class SerializedTaskInvokerMetrics extends EurekaMetrics {

    private final Counter inputSuccess;
    private final Counter inputFailure;
    private final Counter outputSuccess;
    private final Counter outputFailure;

    private final LongGauge queueSize;

    public SerializedTaskInvokerMetrics(String name) {
        super(name);

        inputSuccess = newCounter("inputSuccess");
        inputFailure = newCounter("inputFailure");
        outputSuccess = newCounter("outputSuccess");
        outputFailure = newCounter("outputFailure");
        queueSize = newLongGauge("queueSize");

        register(inputSuccess, inputFailure, outputSuccess, outputFailure, queueSize);
    }

    public void incrementInputSuccess() {
        inputSuccess.increment();
    }

    public void incrementInputFailure() {
        inputFailure.increment();
    }

    public void incrementOutputSuccess() {
        outputSuccess.increment();
    }

    public void incrementOutputFailure() {
        outputFailure.increment();
    }

    public void setQueueSize(long n) {
        queueSize.set(n);
    }

    public static SerializedTaskInvokerMetrics dummyMetrics() {
        return new DevNullMetrics();
    }



    static class DevNullMetrics extends SerializedTaskInvokerMetrics {
        public DevNullMetrics() {
            super("/dev/null");
        }
        public void incrementInputSuccess() {}
        public void incrementInputFailure() {}
        public void incrementOutputSuccess() {}
        public void incrementOutputFailure() {}
        public void setQueueSize(long n) {}
    }

}
