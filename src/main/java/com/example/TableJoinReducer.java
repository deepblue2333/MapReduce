package com.example;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.ArrayList;

public class TableJoinReducer extends Reducer<Text, Text, Text, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 存储所有商品信息
        ArrayList<String> products = new ArrayList<>();

        // 存储所有订单信息
        ArrayList<String> orders = new ArrayList<>();

        for (Text value : values) {
            if (value.toString().startsWith("P")) {
                products.add(value.toString());
            } else {
                orders.add(value.toString());
            }

        }

        for (String product : products) {
            for (String order : orders) {
                String result = product + "," + order;
                context.write(new Text(result), NullWritable.get());
            }
        }
    }
}
