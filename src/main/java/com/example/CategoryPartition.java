package com.example;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;
import java.util.HashMap;
import java.util.Map;

public class CategoryPartition extends Partitioner<LogRecords, NullWritable> {

    public Map<String, Integer> categoryMap = new HashMap<>();

    public int getPartition(LogRecords logRecord, NullWritable nullWritable, int numPartitions) {

        // 添加类别和对应的值
        categoryMap.put("电子产品", 0);
        categoryMap.put("书籍", 1);
        categoryMap.put("家居用品", 2);
        categoryMap.put("服装", 3);

        return categoryMap.get(logRecord.getCategory());

    }
}

