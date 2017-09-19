package com.jy.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by yutao on 2017/4/14.
 */

@RunWith(BaseJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration
        ({"classpath*:/spring/spring-*.xml"/*,"/spring/service/app*.xml"*/}) //加载配置文件
//@Transactional
//这里的事务关联到配置文件中的事务控制器（transactionManager = "transactionManager"），同时//指定自动回滚（defaultRollback = true）。这样做操作的数据才不会污染数据库！
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BaseJunit4Test {
    @Autowired
    protected ApplicationContext applicationContext;
}
