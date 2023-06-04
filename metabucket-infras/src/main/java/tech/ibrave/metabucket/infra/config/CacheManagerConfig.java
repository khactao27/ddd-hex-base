package tech.ibrave.metabucket.infra.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.ResourceUtils;

import java.io.IOException;

/**
 * author: anct
 * date: 5/25/2023
 * YNWA
 */
@Configuration
@EnableCaching
public class CacheManagerConfig {

    @Profile("redis")
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() throws IOException {
        var config = Config.fromYAML(ResourceUtils.getFile("classpath:redisson-cache-config.yml"));
        return Redisson.create(config);
    }
}