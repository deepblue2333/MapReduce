import random
import json

# 商品类别和商品ID
categories = ['电子产品', '服装', '书籍', '家居用品']
products = ['手机', 'T恤', '小说', '沙发']

# 用户行为类型
actions = ['浏览', '购买']

# 生成日志数据
def generate_log_data(num_records):
    log_data = []
    for _ in range(num_records):
        category = random.choice(categories)
        product = random.choice(products)
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

# 将日志数据保存到文件
with open('../resources/ecommerce_logs.json', 'w', encoding='utf-8') as f:
    json.dump(log_records, f, ensure_ascii=False, indent=4)