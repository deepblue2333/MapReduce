package com.example;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LogRecords implements WritableComparable<LogRecords> {
    //分别定义id、姓名、年龄、性别、班级属性
    private long user_id;
    private String product_id;
    private String category;
    private String action;

    //按照id排序
    public int compareTo(LogRecords other) {
        return this.category.compareTo(other.category);
    }

    //进行序列化操作
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(user_id);
        dataOutput.writeUTF(product_id);
        dataOutput.writeUTF(category);
        dataOutput.writeUTF(action);
    }


    //进行反序列化操作
    public void readFields(DataInput dataInput) throws IOException {
        user_id = dataInput.readLong();
        product_id = dataInput.readUTF();
        category = dataInput.readUTF();
        action = dataInput.readUTF();
    }

    //自定义set方法，为对象进行赋值
    public void set(long user_id, String product_id, String category, String action) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.category = category;
        this.action = action;
    }

    @Override
    public String toString() {
        return user_id + "," + product_id + "," + category + "," + action;
    }

    public long get_user_id() {
        return user_id;
    }

    public void set_user_id(long user_id) {
        this.user_id = user_id;
    }

    public String get_product_id() {
        return product_id;
    }

    public void set_product_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
