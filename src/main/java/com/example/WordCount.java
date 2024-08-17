package com.example;

import com.example.utils.DirDeleteIfExist;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import static com.example.utils.DirDeleteIfExist.deleteDir;


public class WordCount extends Configured implements Tool {

    public int run(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(WordCountMapper.class);

        job.setCombinerClass(WordCountCombiner.class);

        job.setReducerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path("file:///Users/dg/Documents/tmp/input/WordCountInput"));
        TextOutputFormat.setOutputPath(job, new Path("file:///Users/dg/Documents/tmp/output/WordCountOutput"));

        boolean b = job.waitForCompletion(true);
        return b ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {

        String dir = "///Users/dg/Documents/tmp/output/WordCountOutput";
        deleteDir(dir);

        Configuration conf = new Configuration();

        int run = ToolRunner.run(conf, new WordCount(), args);
        System.exit(run);
    }
}

