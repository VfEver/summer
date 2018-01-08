package com.summer.beans.factory;

/**
 * bean's alias name,common method
 * @author zys
 * @date 2018/01/08
 */
public interface AliasRegistry {

    /**
     * registry the alias for bean whose name is 'name'
     * @param name
     * @param alias
     */
    void registryAlias(String name, String alias);

    /**
     * remove the alias
     * @param alias
     */
    void removeAlias(String alias);

    /**
     * whether the given alias is an alias.
     * @param alias
     */
    boolean isAlias(String alias);

    /**
     * get all the alias with given name.
     * @param name
     * @return
     */
    String[] getAlias(String name);
}
