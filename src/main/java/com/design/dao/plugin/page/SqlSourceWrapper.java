package com.design.dao.plugin.page;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;

public class SqlSourceWrapper implements SqlSource {
    
    private BoundSql boundSql;

    public SqlSourceWrapper(BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    public BoundSql getBoundSql(Object parameterObject) {
        return boundSql;
    }
}
