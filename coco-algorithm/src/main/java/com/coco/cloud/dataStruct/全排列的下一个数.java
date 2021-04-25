package com.coco.cloud.dataStruct;

/**
 * eg : 13254  =>  13425
 * 54321 => null
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/25 16:18
 */
public class 全排列的下一个数 {

    private static Integer nextNumber(Integer number){
        byte[] bytes = String.valueOf(number).getBytes();
        int index = bytes.length - 1;
        for (int i = index; i > 0; i--) {
            byte temp = bytes[i - 1];
            bytes[i - 1] = bytes[i];
            bytes[i] = temp;
            if (Integer.parseInt(new String(bytes)) > number){
                return Integer.parseInt(new String(bytes));
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Integer number = 13254;
        System.out.println(nextNumber(number));
    }

}
