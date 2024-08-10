package com.example;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import com.alibaba.fastjson.JSON;
import org.jline.utils.Log;

import java.io.IOException;

public class FindPopularItemMapper extends Mapper<LongWritable, Text, LogRecords, NullWritable> {
    LogRecords l = new LogRecords();
    LogRecords logRecord = new LogRecords();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.printf("key: %s, value: %s\n", key, JSON.parseObject(value.toString()));

        //将json字符串转换成students对象
        logRecord = JSON.parseObject(value.toString(), LogRecords.class);

        //为k赋值
        l.set(logRecord.get_user_id(), logRecord.get_product_id(), logRecord.getCategory(), logRecord.getAction());
        //写出
        context.write(l, NullWritable.get());
    }
}