package com.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;

import static com.example.FileDirectoryUtils.deleteDirectory;

public class FindPopularItem {
    public static void main(String[] args) throws Exception {
        // 你要检查和删除的目录路径
        String directoryPath = "/Users/dg/Documents/tmp/output";

        // 创建 File 对象
        File directory = new File(directoryPath);

        // 判断目录是否存在
        if (directory.exists()) {
            // 判断是否为目录
            if (directory.isDirectory()) {
                // 删除目录及其内容
                deleteDirectory(directory);
                System.out.println("目录已删除");
            } else {
                System.out.println("指定路径不是一个目录");
            }
        } else {
            System.out.println("目录不存在");
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "FindPopularItem");
        job.setJarByClass(FindPopularItem.class);
        job.setMapperClass(FindPopularItemMapper.class);
        job.setReducerClass(FindPopularItemReducer.class);

        job.setMapOutputKeyClass(LogRecords.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setPartitionerClass(CategoryPartition.class);
        job.setNumReduceTasks(4);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}