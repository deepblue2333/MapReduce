package com.example;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import org.apache.log4j.Logger;

public class WordCountCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {
    private final Logger logger = Logger.getLogger(WordCountCombiner.class);

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }

        logger.debug(key.toString() + " : " + sum);

        context.write(key, new IntWritable(sum));
    }

}
