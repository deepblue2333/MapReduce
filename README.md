# Flowcount MapReduce程序

## 需求一：统计流量和
统计每个号码的上行流量，下行流量和上行数据包下行数据包之和。

### 需求二：上行流量倒序排列
mapreduce在shuffle阶段对key进行排序，排序对key进行，因此需要让map阶段的输出以FlowBean
作为key，以手机号作为value。并自定义FlowBean的`compareTo`方法。
Java 的 `compareTo` 方法说明:<br>
`compareTo` 方法用于将当前对象与方法的参数进行比较。 
- 如果指定的数与参数相等返回 0。 
- 如果指定的数小于参数返回 -1。 
- 如果指定的数大于参数返回 1。

例如:o1.compareTo(o2); 返回正数的话，当前对象(调用compareTo方法的对象o1)要 排在比较对象(compareTo 传参对象 o2)后面，返回负数的话，放在前面

### 需求三：手机号码分区
```sql
select left(phonenum, 3) phoneArea, sum(uoFlow)
from data_flow
group by left(phonenum, 3)
```

### 运行方法
直接运行
