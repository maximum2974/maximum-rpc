package com.maximum.maximumrpc.fault.retry;

import com.github.rholder.retry.*;
import com.maximum.maximumrpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 固定时间间隔 - 重试策略
 */
@Slf4j
public class FixedIntervalRetryStrategy implements RetryStrategy{
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        Retryer<RpcResponse> retryer = RetryerBuilder.<RpcResponse>newBuilder()
                //方法指定当出现Exception异常时重试
                .retryIfExceptionOfType(Exception.class)
                //选择 fixedWait 固定时间间隔策略
                .withWaitStrategy(WaitStrategies.fixedWait(3L, TimeUnit.SECONDS))
                //选择 stopAfterAttempt 超过最大重试次数停止
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                // 监听重试
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("重试次数 {}", attempt.getAttemptNumber());
                    }
                })
                .build();
        return retryer.call(callable);
    }
}
