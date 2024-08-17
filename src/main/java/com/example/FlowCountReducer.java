package com.example;


import org.apache.hadoop.hdfs.server.namenode.sps.Context;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<FlowBean, Text, Text, FlowBean> {

    @Override
    public void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        for (Text phonenum : values) {
            System.out.println(key);
            context.write(phonenum, key);
        }
    }
}
