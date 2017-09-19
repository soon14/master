package com.jy.test;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

/**
 * Created by yutao on 2017/4/20.
 * 项目中使用的是slf4j，这个类基本上没有用，暂时保留
 */
public class BaseJUnit4ClassRunner extends SpringJUnit4ClassRunner {
    static {
        try {
            Log4jConfigurer.initLogging("classpath:log4j.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public BaseJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }
}
