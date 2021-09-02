package com.example.myseckill.redis;

public interface KeyPrefix {
    public int expireSeconds();

    public String getPrefix();
}
