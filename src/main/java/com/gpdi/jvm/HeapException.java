package com.gpdi.jvm;

import com.gpdi.jvm.entity.Dept;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、堆异常描述：堆里面存储对象，也是GC的主要战场
 *             当堆里存储了超过jvm内存大小的对象，就会堆异常（HeapException）
 *
 * 2、jvm设置堆大小：-Xms1m -Xmx1m
 *
 */

public class HeapException {

    public static void main(String[] args) {
        heapException();
    }

    //堆异常(对象存储在堆里面)
    public static void heapException() {
        List<Dept> list = new ArrayList();
        while (true) {
            list.add(new Dept(1, "张三"));
        }
    }


}
