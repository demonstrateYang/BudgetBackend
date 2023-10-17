package com.person.budget.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtils {
    private Jedis pool = null;

    public JedisUtils(){
        try {
            pool = new JedisPool("172.27.219.204",6379).getResource();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized void insert2Redis(String category,String listDecoder){
        try {
            pool.set(category, listDecoder);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized String get2Redis(String category){
        String result = null;
        try {
            result =  pool.get(category);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
