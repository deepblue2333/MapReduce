package com.example;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
//        System.out.printf("K1: %s V1: %s\n", key, value);
        String line = value.toString();
        String[] words = line.split("\\s+");
        for (String str : words) {
            word.set(str);
            context.write(word, one);
        }
    }
}
