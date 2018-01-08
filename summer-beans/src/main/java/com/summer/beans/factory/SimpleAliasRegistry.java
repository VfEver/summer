package com.summer.beans.factory;

import com.summer.common.support.Assert;
import com.summer.common.utils.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * simple implementation of interface AliasRegistry
 * @author zys
 * @date 2018/01/08
 */
public class SimpleAliasRegistry implements AliasRegistry {

    /**
     * the bean's alias name to canonical name map.
     */
    private Map<String, String> aliasMap = new ConcurrentHashMap<>(16);

    @Override
    public void registryAlias(String name, String alias) {

        Assert.hasText(name, "the bean's specific name can't be empty");
        Assert.hasText(alias, "the alias registed can't be empty");

        //TODO shold be carried to check circling.
        this.aliasMap.put(alias, name);
    }

    @Override
    public void removeAlias(String alias) {

        this.aliasMap.remove(alias);
    }

    @Override
    public boolean isAlias(String alias) {

        Assert.hasText(alias, "the alias can't ben empty");
        return StringUtils.isNotEmpty(this.aliasMap.get(alias));
    }

    @Override
    public String[] getAlias(String name) {


        return new String[0];
    }
}
