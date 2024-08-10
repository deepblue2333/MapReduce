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
