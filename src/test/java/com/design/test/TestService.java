package com.design.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.design.service.ITService;

@RunWith(SpringJUnit4ClassRunner.class) // 整合 
@ContextConfiguration(locations="classpath:config/applicationContext.xml") // 加载配置
public class TestService {
    
    @Autowired
    ITService tService;
    
    @Test
    public void t(){
        System.out.println(tService.delete());
    }
}
