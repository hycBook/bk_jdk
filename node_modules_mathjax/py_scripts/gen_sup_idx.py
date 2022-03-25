#!/usr/bin/env Python
# -- coding: utf-8 --

"""
@version: v1.0
@author: huangyc
@file: parse_html.py
@Description: 自动化生成标题的索引
@time: 2022/2/15 19:40
"""

import os
from collections import defaultdict
from itertools import groupby
from typing import List

from bs4 import BeautifulSoup


def parse_idx(num_list: List[int]):
    """
    解析列表序号
    :param num_list: 编号层级列表
    :return: 编号列表 1 1.1 1.2 1.2.1 1.2.2 2 2.1 ...
    """
    grouped_num_list: List[List[int]] = []
    tmp_order_list: List[int] = []
    for num in num_list:
        if num == 1:
            grouped_num_list.append(tmp_order_list)
            tmp_order_list: List[int] = []
            tmp_order_list.append(num)
        else:
            tmp_order_list.append(num)
    if tmp_order_list:
        # 最后一组也不要忘记
        grouped_num_list.append(tmp_order_list)

    # 最终的编号列表 结果
    final_res = []
    # 按一级标题分组, 构建每组里面的小编号
    for start_idx, num_l in enumerate(grouped_num_list):
        def_order_dict = defaultdict(int)
        # 每组标题下的小标题编号结果列表
        inner_res = []
        for key, values in groupby(num_l):
            values = list(values)
            if key == 1:
                inner_res.append(f"{start_idx}")
            if key == 2:
                for str_idx, value in enumerate(values, start=def_order_dict[key] + 1):
                    inner_res.append(f"{start_idx}.{str_idx}")
                def_order_dict[key] = def_order_dict[key] + len(values)

            if key == 3:
                for str_idx, value in enumerate(values, start=def_order_dict[key] + 1):
                    inner_res.append(f"{'.'.join(inner_res[-1].split('.')[:2])}.{str_idx}")
        final_res.extend(inner_res)

    return final_res


def check_title_func(titles: List[str], check_title: str):
    """
    检查标题的对应关系
    @param titles: 右侧悬浮标题列表
    @param check_title: 需要检查的正文标题
    @return: 是否是右侧悬浮标题列表的子集
    """
    return any([check_title.startswith(title) for title in titles])


def parse_ht(html_path: str):
    # 构建html文件的soup对象
    soup = BeautifulSoup(open(html_path, 'r', encoding='utf-8)'), 'lxml')  # html.parser是解析器，也可是lxml
    # 添加2D模型
    script_tag, div_tag = gen_model_tag(soup=soup)
    book_body_tag = soup.find('div', attrs={"class": "book-body"})
    book_body_tag.append(script_tag)
    book_body_tag.append(div_tag)

    # 添加鼠标点击特效
    click_canvas_tag, click_script_tag = gen_click_tag(soup=soup)
    book_tag = soup.find('div', attrs={"class": "book"})
    book_tag.append(click_canvas_tag)
    book_tag.append(click_script_tag)

    # 对于无标题的文件, 直接返回不做处理
    if not soup.find('div', id="anchor-navigation-ex-navbar"):
        # 更新原始的html文件
        with open(html_path, "w", encoding='utf-8') as file:
            html_str = str(soup)
            find_idx = html_str.find('<body>') + len('<body>')
            html_str = f"{html_str[:find_idx]}{gen_snow_div()}{html_str[find_idx:]}"
            file.write(html_str)
        return

    # 查找所有的右边悬浮导航栏列表对象
    suspension_titles = soup.find('div', id="anchor-navigation-ex-navbar").find_all("li")
    titles, num_list = [], []
    for idx, node in enumerate(suspension_titles):
        lt = len([i.name for i in node.parents if i.name == 'ul'])
        num_list.append(lt)
    order_num_lst = parse_idx(num_list)

    # 更新右边悬浮导航栏列表 更新为带编号的样式
    for idx, node in enumerate(suspension_titles):
        title = node.find('a').text
        titles.append(title)
        node.find('a').string = f'{order_num_lst[idx]} {node.text}'

    # 查找所有的正文标题
    body_titles = soup.findAll(["h1", "h2", "h3"])
    body_titles = [h for h in body_titles if 'id' in h.attrs and check_title_func(titles, h.text)]

    assert len(body_titles) == len(titles), "长度不匹配"
    # 更新正文标题列表 更新为带编号的样式
    for order_num, bt in zip(order_num_lst, body_titles):
        bt.string = f"{order_num} {bt.text}"

    # 更新[TOC]为真正的导航
    try:
        target_html = soup.find('div', id="anchor-navigation-ex-navbar").next.next
        toc = soup.find('p', text='[toc]') if soup.find('p', text='[toc]') else soup.find('p', text='[TOC]')
        toc.contents = target_html
    except:
        pass

    # 更新原始的html文件
    with open(html_path, "w", encoding='utf-8') as file:
        html_str = str(soup)
        find_idx = html_str.find('<body>') + len('<body>')
        html_str = f"{html_str[:find_idx]}{gen_snow_div()}{html_str[find_idx:]}"
        file.write(html_str)


def gen_model_tag(soup):
    """ 生成 2d模型代码模块 """
    script_tag = soup.new_tag('script', src='https://cdn.jsdelivr.net/gh/zztongtong/CDN/js/live2d.min.js')
    div_tag = soup.new_tag('div', style="position:absolute; bottom:0; left:0; width:200;")
    canvas_tag = soup.new_tag('canvas', style="position:absolute; bottom:0; left:0; width:200;")
    canvas_tag.attrs = {"id": "model_1", "width": "200", "height": "350"}
    div_tag.append(canvas_tag)
    return script_tag, div_tag


def gen_click_tag(soup):
    """ 生成 鼠标点击特效 """
    click_canvas_tag = soup.new_tag('canvas')
    click_canvas_tag.attrs = {"class": "fireworks"}
    click_script_tag = soup.new_tag('script', src="https://cdn.jsdelivr.net/npm/animejs@3.2.1/lib/anime.min.js")
    return click_canvas_tag, click_script_tag


def gen_snow_div() -> str:
    """
          <div class="c1">
            <div class="cloud"></div>
          </div>
          <div class="c2">
            <div class="cloud"></div>
          </div>
          <div class="c3">
            <div class="cloud"></div>
          </div>
    """
    snow = '''
          <div class="mountain_a"></div>
          <div class="mountain_b"></div>
          <div class="house right">
            <div class="fence"></div>
            <div class="wall"></div>
            <div class="roof left"></div>
            <div class="roof right"></div>
            <div class="door"></div>
          </div>
          <div class="house left">
            <div class="fence"></div>
            <div class="wall"></div>
            <div class="roof left"></div>
            <div class="roof right"></div>
            <div class="door"></div>
          </div>
          <div class="tree_back"></div>
          <div class="tree"></div>
          <div class="postbox_a">
            <div class="hole"></div>
          </div>
          <div class="postbox_b">
            <div class="hole"></div>
          </div>
          <div class="windmill">
            <div class="tower"></div>
            <div class="t1"></div>
            <div class="t2"></div>
            <div class="blade">
              <div class="windblade"></div>
              <div class="windblade windblade2"></div>
              <div class="windblade windblade3"></div>
              <div class="windblade windblade4"></div>
            </div>
          </div>
          <div class="allsnows">
            <div class="snow1"></div>
            <div class="snow2"></div>
            <div class="snow3"></div>
            <div class="snow4"></div>
            <div class="snow5"></div>
            <div class="snow6"></div>
            <div class="snow7"></div>
            <div class="snow8"></div>
            <div class="snow9"></div>
            <div class="snow10"></div>
            <div class="snow11"></div>
            <div class="snow12"></div>
            <div class="snow13"></div>
            <div class="snow14"></div>
            <div class="snow15"></div>
            <div class="snow16"></div>
            <div class="snow17"></div>
            <div class="snow18"></div>
            <div class="snow19"></div>
            <div class="snow20"></div>
          </div>
          <div class="ground">
            <div class="g1"></div>
            <div class="g2"></div>
            <div class="g3"></div>
            <div class="ice">
              <div class="glare"></div>
              <div class="ice_shadow"></div>
            </div>

          </div>
    '''
    return snow


def start(path: str):
    for file_name in os.listdir(path):
        if file_name.endswith('.html'):
            f_path = os.path.join(path, file_name)
            parse_ht(f_path)
            print(f"{f_path} 处理完成")


if __name__ == '__main__':
    base_path = r'_book'
    start(base_path)
    start(os.path.join(base_path, 'chapters'))
