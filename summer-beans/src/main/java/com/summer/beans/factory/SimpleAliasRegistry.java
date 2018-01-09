package com.summer.beans.factory;

import com.summer.common.support.Assert;
import com.summer.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
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

        checkForAliasCircle(alias, name);
        this.aliasMap.put(alias, name);
    }

    @Override
    public void removeAlias(String alias) {

        Assert.hasText(alias, "the alias registed can't be empty");
        this.aliasMap.remove(alias);
    }

    @Override
    public boolean isAlias(String alias) {

        Assert.hasText(alias, "the alias can't ben empty");
        return StringUtils.isNotEmpty(this.aliasMap.get(alias));
    }

    @Override
    public String[] getAlias(String name) {

        Assert.hasText(name, "the alias registed can't be empty");
        List<String> aliasList = new ArrayList<>(8);

        for (Map.Entry<String, String> entry : aliasMap.entrySet()) {

            if (name.equals(entry.getValue())) {

                aliasList.add(entry.getKey());
            }
        }
        return StringUtils.toStringArray(aliasList);
    }

    @Override
    public boolean hasAlias(String alias, String name) {

        for (Map.Entry<String, String> entry : aliasMap.entrySet()) {

            String registeredName = entry.getValue();
            if (registeredName.equals(name)) {

                String registeredAlias = entry.getKey();
                return alias.equals(registeredAlias) || hasAlias(registeredAlias, alias);
            }
        }
        return false;
    }

    /**
     * Check whether the given name points back to the given alias as an alias
     * in the other direction already, catching a circular reference upfront
     * and throwing a corresponding IllegalStateException.
     * @param alias
     * @param name
     */
    public void checkForAliasCircle(String alias, String name) {

        if (hasAlias(alias, name)) {

            throw new IllegalStateException("the alias " + alias +
                    " has already been register for bean name " + name);
        }
    }
}
