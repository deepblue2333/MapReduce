package com.example;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable result = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
//        System.out.println("key: " + key);
//        System.out.println("values: " + values);
        for (IntWritable val : values) {
//            System.out.println("val: " + val);
            sum += val.get();
        }
        result.set(sum);
        context.write(key, result);
    }
}
