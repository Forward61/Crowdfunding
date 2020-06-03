package com.atguigu.crowd.service.impl.othertest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SameItemTest {
    /**
     * 把数组A的数据作为map的key和value， 然后用B的数据取值，不为NULL，说明相同
     *
     * Map接口 Map提供了一种映射关系，其中的元素是以键值对（key-value）的形式存储的，能够实现根据key快速查找value；
     * Map中的键值对以Entry类型的对象实例形式存在；
     * 建（key值）不可重复，value值可以重复，一个value值可以和很多key值形成对应关系，每个建最多只能映射到一个值。
     * Map支持泛型，形式如：Map<K,V> Map中使用put(K key,V value)方法添加
     *
     * HashMap类 HashMap是Map的一个重要实现类，也是最常用的，基于哈希表实现 HashMap中的Entry对象是无序排列的
     * Key值和value值都可以为null，但是一个HashMap只能有一个key值为null的映射（key值不可重复）
     *
     */
    public static List<String> getSameElementByMap(String[] strArr1, String[] strArr2) {

        // HashMap key值 不可重复 Key值和value值都可以为null
        HashMap<String, Object> map = new HashMap<String, Object>();

        // 数组A中的元素放入Map中
        for (String string1 : strArr1) {
            map.put(string1, string1);
        }

        List<String> list = new ArrayList<String>();
        // 用数组B元素做为Key来取值，如为NULL则说明相同
        for (String string2 : strArr2) {
            Object j = map.get(string2);
            if (j != null) {
                list.add(string2);
                 System.out.println("数组AB中相同的元素： "+j.toString());
            }
        }
        return list;

    }

    public static void main(String[] args) {
        String [] list1=new String[]{"1","2","3","4","b"};
        String [] list2= new String[]{"2","4","b"};
        getSameElementByMap(list1,list2);

    }
}
