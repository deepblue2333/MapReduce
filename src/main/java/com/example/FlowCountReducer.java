package com.example;


import org.apache.hadoop.hdfs.server.namenode.sps.Context;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        Text phonePrefix = new Text(key.toString().substring(0,3));

        for (IntWritable value : values) {
            System.out.println(key);
            count += value.get();
        }

        IntWritable intWritable = new IntWritable(count);

        context.write(phonePrefix, intWritable);
    }
}
