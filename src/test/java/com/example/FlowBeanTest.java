package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.apache.hadoop.io.DataOutputBuffer;
import java.io.*;


public class FlowBeanTest {
    private FlowBean flowBean;

    @BeforeEach
    public void setUp() {
        // 在每个测试方法运行之前执行
        flowBean = new FlowBean();
    }

    @Test
    public void testFlowBean() throws IOException {
        // 设置数据
        flowBean.setUpFlow(10);
        flowBean.setDownFlow(10);
        flowBean.setUpCountFlow(20);
        flowBean.setDownCountFlow(20);

        // 测试写数据
        Assertions.assertEquals(20, flowBean.getUpCountFlow());

        // 创建 DataOutputBuffer 对象用于存储序列化数据
        DataOutputBuffer dob = new DataOutputBuffer();

        // 将 FlowBean 的数据写入 dob
        flowBean.write(dob);

        // 验证写入数据是否正确（例如，你可以检验写入的字节数或进一步解析）
        byte[] writtenData = dob.getData(); // 获取写入的字节数据
        Assertions.assertTrue(writtenData.length > 0, "数据应该成功写入 DataOutputBuffer");

        // 创建一个新的 FlowBean 并从 dob 读取数据
        FlowBean deserializedBean = new FlowBean();
        // 读区字节数组为字节输入流对象
        ByteArrayInputStream bis = new ByteArrayInputStream(dob.getData());

        // 获取字节输入流变为datainputstream对象
        DataInputStream dis = new DataInputStream(bis);

        // 从输入流中读取数据并填充到新的 FlowBean 中
        deserializedBean.readFields(dis);

        // 验证反序列化后的数据是否与原始数据一致
        Assertions.assertEquals(flowBean.getUpFlow(), deserializedBean.getUpFlow());
        Assertions.assertEquals(flowBean.getDownFlow(), deserializedBean.getDownFlow());
        Assertions.assertEquals(flowBean.getUpCountFlow(), deserializedBean.getUpCountFlow());
        Assertions.assertEquals(flowBean.getDownCountFlow(), deserializedBean.getDownCountFlow());
    }
}
