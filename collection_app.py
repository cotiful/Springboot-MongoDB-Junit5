# 해당 파일의 메서드, 변수 다 사용하겠다
from collection import *

datas = []
for i in range(1, 21):
    naver_data = naver_craw(i)
    datas.append(naver_data)

mongo_save(mongo, datas, "greendb", "navers")  # List안에 dict을 넣어야 함
