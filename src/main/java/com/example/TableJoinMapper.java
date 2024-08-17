package com.example;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import org.apache.log4j.Logger;

public class TableJoinMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Logger logger = Logger.getLogger(TableJoinMapper.class);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text pid = new Text("");

        if (value.toString().startsWith("P")){
            pid.set(value.toString().split(",")[0]);
        } else {
            pid.set(value.toString().split(",")[2]);
        }
        logger.info("pid: " + pid + "\tvalue: " + value);
        context.write(pid, value);
    }
}
