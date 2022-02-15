#!/usr/bin/env Python
# -- coding: utf-8 --

"""
@version: v1.0
@author: huangyc
@file: parse_html.py
@Description: 
@time: 2022/2/15 19:40
"""

from bs4 import BeautifulSoup
from collections import defaultdict
from itertools import groupby


def platform():
    """ 获取当前操作系统类型 """
    import platform
    return platform.system().lower()


def parse_idx(num_lst):
    """
    解析列表序号
    :param num_lst:
    :return:
    """
    new_num_lst = []
    tmp_lst = []
    for num in num_lst:
        if num == 1:
            new_num_lst.append(tmp_lst)
            tmp_lst = []
            tmp_lst.append(num)
        else:
            tmp_lst.append(num)

    if tmp_lst:
        new_num_lst.append(tmp_lst)
    new_num_lst = [t for t in new_num_lst]

    new_res = []
    for start_idx, num_l in enumerate(new_num_lst):
        dic_def = defaultdict(int)
        res = []
        for key, values in groupby(num_l):
            values = list(values)

            if key == 1:
                res.append(f"{start_idx}")
            if key == 2:
                for str_idx, value in enumerate(values, start=dic_def[key] + 1):
                    res.append(f"{start_idx}.{str_idx}")
                dic_def[key] = dic_def[key] + len(values)

            if key == 3:
                for str_idx, value in enumerate(values, start=dic_def[key] + 1):
                    res.append(f"{'.'.join(res[-1].split('.')[:2])}.{str_idx}")
        new_res.extend(res)

    return new_res


def check(titles, check_title: str):
    return any([check_title.startswith(title) for title in titles])


def parse_ht(path):
    soup = BeautifulSoup(open(path, 'r', encoding='utf-8)'), 'lxml')  # html.parser是解析器，也可是lxml
    res = soup.find('div', id="anchor-navigation-ex-navbar").find_all("li")
    titles = []

    num_lst = []
    for idx, node in enumerate(res):
        lt = len([i.name for i in node.parents if i.name == 'ul'])
        num_lst.append(lt)
    new_res = parse_idx(num_lst)

    for idx, node in enumerate(res):
        lt = len([i.name for i in node.parents if i.name == 'ul'])
        title = node.find('a').text
        titles.append(title)
        node.find('a').string = f'{new_res[idx]} {node.text}'

    all_h = soup.findAll(["h1", "h2", "h3"])
    all_h = [h for h in all_h if 'id' in h.attrs and check(titles, h.text)]

    assert len(all_h) == len(titles), "长度不匹配"
    for idx, h in zip(new_res, all_h):
        h.string = f"{idx} {h.text}"

    with open(path, "w", encoding='utf-8') as file:
        file.write(str(soup))


if __name__ == '__main__':
    base_path = r'U:\迅雷下载\bk_jdk-gh-pages'
    base_path = r'_book'
    import os

    base_path = os.path.join(base_path, 'chapters')

    for file_name in os.listdir(base_path):
        if file_name.endswith('.html'):
            path = os.path.join(base_path, file_name)
            print(path)
            parse_ht(path)
