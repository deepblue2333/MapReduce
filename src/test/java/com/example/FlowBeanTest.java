package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.*;

public class FlowBeanTest {
    private FlowBean flowBean;

    @BeforeEach
    public void setUp() {
        // 在每个测试方法运行之前执行
        flowBean = new FlowBean();
    }

    @Test
    public void testFlowBean() {
        flowBean.setUpFlow(10);
        flowBean.setDownFlow(10);
        flowBean.setUpCountFlow(20);
        flowBean.setDownCountFlow(20);

        // 测试写数据
        Assertions.assertEquals(20, flowBean.getUpCountFlow());
    }
}
