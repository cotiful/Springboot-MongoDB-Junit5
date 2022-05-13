import requests
from datetime import datetime
from bs4 import BeautifulSoup


def num_to_aid(num):
    num_str = str(num)
    return num_str.zfill(10)


# dependency ->num_to_aid(int:num)
def naver_craw(num):
    result = {"title": "", "comapny": "국민일보", "createdAt": datetime.now()}
    # company = "국민일보"
    # title = ""
    # createdAt = datetime.now()

    response = requests.get(
        f"https://entertain.naver.com/read?oid=005&aid={num_to_aid(num)}")

    html = response.text
    html_bs = BeautifulSoup(html, "html.parser")

    title = html_bs.select(".end_tit")[0] .get_text().strip()
    print(title)

    result["title"] = title

    return result
# print(f"{company}{title}{createdAt}")
