package com.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.example.utils.DirDeleteIfExist;

public class TableJoinExample extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        Job job = Job.getInstance(getConf(), "MapReduce TableJoinExample");
        job.setJarByClass(TableJoinExample.class);
        job.setMapperClass(TableJoinMapper.class);
        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.setInputPaths(job, new Path("file:///Users/dg/Documents/tmp/input/TableJoinExampleInput"));
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setReducerClass(TableJoinReducer.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path("file:///Users/dg/Documents/tmp/output/TableJoinExampleOutput"));
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        String path = "///Users/dg/Documents/tmp/output/TableJoinExampleOutput";
        DirDeleteIfExist.deleteDir(path);

        Configuration conf = new Configuration();
        int run = ToolRunner.run(conf, new TableJoinExample(), args);
        System.exit(run);
    }
}
