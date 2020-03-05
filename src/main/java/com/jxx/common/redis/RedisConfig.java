package com.jxx.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *  reids连接
 * @author strange
 * @date $
 */

@Component
public class RedisConfig {
    private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);

    private String hosts = "127.0.0.1:6379";


}
