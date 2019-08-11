package com.crif.contact.cloud.manger.service;

import com.crif.contact.cloud.manger.TestConfig;
import com.crif.contact.cloud.manger.config.ManagerConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ManagerConfig.class, TestConfig.class})
public class AbstractTest {

}
