package com.example;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

public class FlowPartition extends Partitioner<Text, IntWritable> {

    @Override
    public int getPartition(Text key, IntWritable value, int numPartitions) {
        String phonenum = key.toString();

        System.out.println(phonenum);

        if(phonenum.startsWith("135")){
            return 0;
        } else if (phonenum.startsWith("136")){
            return 1;
        } else if (phonenum.startsWith("137")){
            return 2;
        } else {
            return 3;
        }
    }
}
