package com.maximum.maximumrpc.fault.tolerant;

import com.maximum.maximumrpc.model.RpcResponse;

import java.util.Map;

/**
 * 快速失败 - 容错策略(遇到异常后，将异常再次抛出，交给外层处理)
 */
public class FailFastTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务报错", e);
    }
}
