package com.maximum.maximumrpc.fault.tolerant;

import com.maximum.maximumrpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 静默处理异常 - 容错策略(遇到异常后，记录一条日志，然后正常返回一个响应对象，就好像没有出现过报错)
 */
@Slf4j
public class FailSafeTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        log.info("静默处理异常", e);
        return new RpcResponse();
    }
}
