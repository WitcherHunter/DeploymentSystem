package com.yeejoin.deloymentsystem.data.model;

/**
 * Created by maodou on 2017/12/5.
 * 列表格式返回数据顶层结构
 */

public class DataListResult<T> {
    public String result;
    public String message;
    public T dataList;
}
