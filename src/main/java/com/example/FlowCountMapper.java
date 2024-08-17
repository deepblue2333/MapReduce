package com.example;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text phonenum = new Text(value.toString().split("\t")[0].substring(0,3));

//        FlowBean flowBean = new FlowBean();
//        flowBean.setUpFlow(Integer.parseInt(value.toString().split("\t")[6]));
//        flowBean.setDownFlow(Integer.parseInt(value.toString().split("\t")[7]));
//        flowBean.setUpCountFlow(Integer.parseInt(value.toString().split("\t")[8]));
//        flowBean.setDownCountFlow(Integer.parseInt(value.toString().split("\t")[9]));

//        System.out.println(flowBean.toString());

        context.write(phonenum, new IntWritable(1));
    }

}
