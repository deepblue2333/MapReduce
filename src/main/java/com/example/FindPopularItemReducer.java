package com.example;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FindPopularItemReducer extends Reducer<LogRecords, NullWritable, Text, IntWritable> {
    public Map<String, Integer> productCounts = new HashMap<>();

    @Override
    protected void reduce(LogRecords key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
//        System.out.println(key.toString());

        for (NullWritable value:values){
            String product = key.get_product_id();

            if (productCounts.containsKey(product)) {
                // 如果产品已存在，增加其计数
                productCounts.put(product, productCounts.get(product) + 1);
            } else {
                // 如果产品不存在，初始化计数为 1
                productCounts.put(product, 1);
            }
        }

        // 初始化变量
        Text maxKey = new Text();
        IntWritable maxCount = new IntWritable();

        // 遍历 HashMap 并找出计数最多的键
        for (Map.Entry<String, Integer> entry : productCounts.entrySet()) {
            if (entry.getValue() > maxCount.get()) {
                maxCount.set(entry.getValue());
                maxKey.set(entry.getKey());
            }
        }

        context.write(maxKey, maxCount);

    }
}
