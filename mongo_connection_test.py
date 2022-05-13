from pymongo import MongoClient
from datetime import datetime


def naver_craw(num):
    return {"title": "", "comapny": "국민일보", "createdAt": datetime.now()}


def mongo_save(mongo, datas, db_name=None, collection_name=None):
    result = mongo[db_name][collection_name].insert_many(datas).inserted_ids
    return result


mongo = MongoClient("localhost", 27017)

datas = []
for i in range(1, 21):
    naver_data = naver_craw(i)
    datas.append(naver_data)

mongo_save(mongo, datas, "greendb", "navers")  # List안에 dict을 넣어야 함
