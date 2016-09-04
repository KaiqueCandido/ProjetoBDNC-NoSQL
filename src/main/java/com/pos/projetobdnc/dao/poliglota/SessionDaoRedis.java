/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pos.projetobdnc.dao.poliglota;

import redis.clients.jedis.Jedis;

/**
 *
 * @author kaiqu
 */
public class SessionDaoRedis {

    private final String host;
    private final int port;
    private final Jedis jedis;

    public SessionDaoRedis() {
        this.host = "localhost";
        this.port = 6379;
        this.jedis = new Jedis(host, port);
    }

    public boolean createKey(String key, String value) {
        String set = jedis.set(key, value);
        return set.equalsIgnoreCase("OK");
    }

    public String getKey(String key) {
        String get = jedis.get(key);
        if (get != null) {
            return get;
        }
        return "";
    }

    public boolean deletKey(String key) {
        jedis.del(key);
        return true;
    }

}
