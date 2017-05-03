package com.netflix.evcache.connection;

import java.util.List;

import com.netflix.config.DynamicIntProperty;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.evcache.pool.DIEVCacheKetamaNodeLocatorConfiguration;
import com.netflix.evcache.pool.EVCacheClient;
import com.netflix.evcache.pool.EVCacheNodeLocator;

import net.spy.memcached.DefaultHashAlgorithm;
import net.spy.memcached.MemcachedNode;
import net.spy.memcached.NodeLocator;

public class DIConnectionFactory extends BaseConnectionFactory {

    private final DiscoveryClient discoveryClient;
    
    DIConnectionFactory(EVCacheClient client, DiscoveryClient discoveryClient, int len, DynamicIntProperty operationTimeout, long opMaxBlockTime) {
        super(client, len, operationTimeout, opMaxBlockTime);
        this.discoveryClient = discoveryClient;
    }

    @Override
    public NodeLocator createLocator(List<MemcachedNode> list) {
        this.locator = new EVCacheNodeLocator(client, list,  DefaultHashAlgorithm.KETAMA_HASH, new DIEVCacheKetamaNodeLocatorConfiguration(client, discoveryClient));
        return locator;
    }

}
