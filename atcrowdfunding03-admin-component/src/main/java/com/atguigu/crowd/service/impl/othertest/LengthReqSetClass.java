package com.atguigu.crowd.service.impl.othertest;


import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class LengthReqSetClass {
    public static void main(String[] args) throws Exception {

        setLengthReq();
        String resString = "June      一二三四  987654321 ";
        getLengthRes(resString);
    }
    //设置定长报文请求
    private static void setLengthReq() throws Exception {
        HashMap<String, HashMap<Integer,String>> reqMap = new LinkedHashMap<>();
        reqMap.put("一",new HashMap(){{ put(10,"June"); }});
        reqMap.put("二",new HashMap(){{ put(10,"一二三四"); }});
        reqMap.put("三",new HashMap(){{ put(10,"987654321"); }});
        String reqString = "";

        for (String key :reqMap.keySet()){
//            System.out.println("key的值 ： " + key);
            HashMap tempMap = reqMap.get(key);
            for (Object tempKey : tempMap.keySet()){
                String tempString =  "";
//
                int lenth = Integer.parseInt(String.valueOf(tempKey));//定义长度
                String tempKeyString = String.valueOf(tempMap.get(tempKey));
                int tempLenth = getRealLength(tempKeyString);//字符串真实长度
                if(tempLenth > lenth){
                    System.out.println("这个key值超长了 " + key);
                    throw new Exception();
                }else {
                    String padString = StringUtils.rightPad("",lenth-tempLenth);//这个左右没关系
                    tempString = tempKeyString +padString;//这里左右决定顺序。如果右补充，就把padString写在后面
                }

//                System.out.println(tempString);
                reqString += tempString;
            }

//            reqString +=
        }
        System.out.println(reqString.length() + reqString);
    }

    //获取定长报文响应
    private static void getLengthRes(String resString) throws Exception {
        HashMap<String, Integer> resMap = new LinkedHashMap<>();
        resMap.put("一",10);
        resMap.put("二",15);
        resMap.put("三",5);
        int beginIndex =0 ;
        int endIndex = 0;
        for(String key : resMap.keySet()){
            int length = resMap.get(key);
            System.out.println("beginIndex"  + beginIndex  );
            byte[] bytes = resString.getBytes("GBK");
//            System.out.println("resString的长度" + resString.length());
//            System.out.println("bytes的长度" + bytes.length);
            String value = new String(bytes,beginIndex,length,"GBK");
            beginIndex += length;
            System.out.println(key + "的值:"+value);
        }

    }

    //计算字符长度（可能含中文）
    public static int getRealLength(String str) {
        int m = 0;
        char arr[] = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            // 中文字符(根据Unicode范围判断),中文字符长度为2
            if ((c >= 0x0391 && c <= 0xFFE5)) {
                m = m + 2;
            } else if ((c >= 0x0000 && c <= 0x00FF)) // 英文字符
            {
                m = m + 1;
            }
        }
        return m;
    }


}
