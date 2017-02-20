package com.hikvision.mdp.web.commom.service;

import com.hikvision.mdp.web.common.interfaces.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class ApiRedisService {

    @Autowired(required = false)
    private ShardedJedisPool shardedJedisPool;

    private <T> T execute(Function<T, ShardedJedis> fun) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = this.shardedJedisPool.getResource();
            return fun.callback(shardedJedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                shardedJedis.close();
            }
        }
        return null;
    }

    public String get(final String key) {
        return this.execute(new Function<String, ShardedJedis>() {
            @Override
            public String callback(ShardedJedis e) {
                return e.get(key);
            }
        });
    }

    public String set(final String key, final String value) {
        return this.execute(new Function<String, ShardedJedis>() {

            @Override
            public String callback(ShardedJedis e) {

                return e.set(key, value);
            }

        });
    }

    public Long del(final String key) {
        return this.execute(new Function<Long, ShardedJedis>() {

            @Override
            public Long callback(ShardedJedis e) {
                return e.del(key);
            }
        });
    }

    public Long expire(final String key, final Integer seconds) {
        return this.execute(new Function<Long, ShardedJedis>() {

            @Override
            public Long callback(ShardedJedis e) {
                return e.expire(key, seconds);
            }
        });
    }

    public String set(final String key, final String value, final Integer seconds) {
        return this.execute(new Function<String, ShardedJedis>() {

            @Override
            public String callback(ShardedJedis e) {
                String result = e.set(key, value);
                e.expire(key, seconds);
                return result;
            }

        });
    }

}
