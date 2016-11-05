package com.design.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.design.dao.mapper.TMapper;
import com.design.service.ITService;

@Service
public class TService implements ITService {
    
    @Autowired
    TMapper tMapper;

    @Override
    public int delete() {
        return tMapper.delete();
    }

}
