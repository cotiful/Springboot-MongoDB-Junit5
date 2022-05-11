import requests
import datetime
from bs4 import BeautifulSoup
from pymongo import MongoClient
from pymongo.cursor import CursorType


def mongo_save(mongo, datas, db_name=None, collection_name=None):
    result = mongo[db_name][collection_name].insert_many(datas).inserted_ids
    return result


# Mongo 연결
mongo = MongoClient("localhost", 20000)

list = []


aid = 1
date = datetime.datetime.now()
while True:
    print(aid)
    i = ('{:0>10}'.format(aid))
    try:
        html = requests.get(
            f"https://entertain.naver.com/read?oid=005&aid={i}")

        if(html.status_code == 200):
            aid = aid+1
            soup = BeautifulSoup(html.text, 'html.parser')

            company = soup.select(".press_logo > img")[0]["alt"]
            # print(company)
            title = soup.select_one(".end_tit").text
            print(title)

            createdAt = date.strftime('%Y-%m-%d %H:%M:%S')  # 현재시간으로 바꿔줌
            # print(date)

            dict = {"company": company, "title": title, "createdAt": createdAt}
            list.append(dict)

        if aid == 21:
            break

    except Exception as e:
        print("잘못된것")
        pass
# print(len(list))


print("="*50)
result = mongo_save(mongo, list, "greendb", "navers")  # List안에 dict을 넣어야 함
print(result)
