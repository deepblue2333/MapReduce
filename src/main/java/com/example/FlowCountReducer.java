package com.example;


import org.apache.hadoop.hdfs.server.namenode.sps.Context;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

    @Override
    public void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        Integer upFlowSum = 0;
        Integer downFlowSum = 0;
        Integer upFlowCountSum = 0;
        Integer downFlowCountSum = 0;

        for (FlowBean flowBean : values) {
            upFlowSum += flowBean.getUpFlow();
            downFlowSum += flowBean.getDownFlow();
            upFlowCountSum += flowBean.getUpCountFlow();
            downFlowCountSum += flowBean.getDownCountFlow();
        }

        FlowBean flowBean = new FlowBean();
        flowBean.setUpFlow(upFlowSum);
        flowBean.setDownFlow(downFlowSum);
        flowBean.setUpCountFlow(upFlowCountSum);
        flowBean.setDownCountFlow(downFlowCountSum);

        context.write(key, flowBean);
    }
}
