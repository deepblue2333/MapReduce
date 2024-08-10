MapReduce 是一种分布式计算模型，用于处理和生成大数据集。在 MapReduce 中，"分区"（Partitioning）是一种将数据分割成不同部分的方法，以便于并行处理。分区通常在 Map 阶段结束后，Reduce 阶段开始之前进行。

### 测试情景：用户行为日志分析

**场景描述**：
假设我们有一个大型的电子商务网站，该网站记录了所有用户的浏览和购买行为。我们需要分析这些日志数据，
找出每个类别的商品中最受欢迎的商品 （购买和浏览数总和最多的）。

**数据集**：
- 用户ID
- 商品ID
- 商品类别
- 行为类型（浏览、购买）

**MapReduce 任务**：

1. **Map 阶段**：
    - 输入：用户行为日志记录
    - 输出：键值对（商品类别，商品ID）

   Map 函数会读取每条日志记录，输出商品类别和商品ID作为键值对。

2. **分区阶段**：
    - 功能：根据商品类别将数据分配到不同的分区。

   分区函数会根据商品类别将输出的键值对分配到不同的分区，确保同一类别的商品数据被发送到同一个 Reduce 任务。

3. **Reduce 阶段**：
    - 输入：经过分区后的数据
    - 输出：每个类别中最受欢迎的商品

   Reduce 函数会统计每个商品的浏览和购买次数，找出每个类别中最受欢迎的商品。
### 目录结构
```shell
.
├── README.md
├── application.log                                    # hadoop本地测试日志
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           ├── CategoryPartition.java
│   │   │           ├── FindPopularItem.java           # 运行主类
│   │   │           ├── FindPopularItemMapper.java     # Mapper
│   │   │           ├── FindPopularItemReducer.java    # Reducer
│   │   │           └── LogRecords.java
│   │   ├── python
│   │   │   ├── generate_log_data.py                   # 数据生成脚本
│   │   │   └── python.iml
│   │   └── resources
│   │       ├── ecommerce_logs.json                    # json数据文件
│   │       └── log4j.properties
│   └── test
│       └── java

```

### 生成测试数据

以下是生成测试数据的 Python 代码示例：

```python
import random
import json

# 商品类别和商品ID
categories = ['电子产品', '服装', '书籍', '家居用品']
products = {'电子产品': ['手机A', '手机B', '手机C', '手机D'],
            '服装': ['T恤A', 'T恤B', 'T恤C', 'T恤D'],
            '书籍': ['小说A', '小说B', '小说C', '小说D'],
            '家居用品': ['沙发A', '沙发B', '沙发C', '沙发D']}

# 用户行为类型
actions = ['浏览', '购买']

# 生成日志数据
def generate_log_data(num_records):
   log_data = []
   for _ in range(num_records):
      category = random.choice(categories)
      product = random.choice(products[category])
      user_id = random.randint(1, 1000)
      action = random.choice(actions)
      log_data.append({
         'user_id': user_id,
         'product_id': product,
         'category': category,
         'action': action
      })
   return log_data

# 生成1000条日志记录
log_records = generate_log_data(1000)

# 打印部分日志数据
for record in log_records[:10]:  # 打印前10条记录
   print(json.dumps(record, ensure_ascii=False))

# 将日志数据保存到按行读取的 JSON 文件
with open('../resources/ecommerce_logs.json', 'w', encoding='utf-8') as f:
   for record in log_records:
      f.write(json.dumps(record, ensure_ascii=False) + '\n')

```
数据示例：

```json
{"user_id": 569, "product_id": "小说D", "category": "书籍", "action": "购买"}
{"user_id": 530, "product_id": "手机A", "category": "电子产品", "action": "购买"}
{"user_id": 630, "product_id": "小说B", "category": "书籍", "action": "购买"}
{"user_id": 843, "product_id": "T恤C", "category": "服装", "action": "购买"}
{"user_id": 129, "product_id": "沙发C", "category": "家居用品", "action": "购买"}
{"user_id": 417, "product_id": "手机D", "category": "电子产品", "action": "购买"}
{"user_id": 806, "product_id": "手机C", "category": "电子产品", "action": "购买"}
```
这段代码会生成1000条随机的用户行为日志记录，并保存到一个名为 `ecommerce_logs.json` 的文件中。这些数据可以作为 MapReduce 任务的输入数据。

### 程序运行
运行主类：`FindPopularItem`
运行参数：`file:///Users/dg/IdeaProjects/MapReduce/src/main/resources/ecommerce_logs.json file:///Users/dg/Documents/tmp/output`
