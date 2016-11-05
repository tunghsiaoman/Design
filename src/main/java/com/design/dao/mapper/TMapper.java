package com.design.dao.mapper;

import org.apache.ibatis.annotations.Delete;

public interface TMapper {
    
    @Delete("delete from t where id = 1")
    int delete();
}
