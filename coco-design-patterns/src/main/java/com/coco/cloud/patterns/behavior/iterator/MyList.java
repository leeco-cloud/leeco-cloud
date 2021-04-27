package com.coco.cloud.patterns.behavior.iterator;

import java.util.Iterator;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 21:00
 */
class MyList {

    private int size = 0;

    private int defaultSize = 16;

    private int writeIndex = 0;

    private Integer[] integers;

    void add(Integer integer){
        if (integers == null){
            integers = new Integer[defaultSize];
        }else{
            if (writeIndex == defaultSize){
                Integer[] newArray = new Integer[defaultSize * 2];
                int index = 0;
                for (Integer item : integers) {
                    newArray[index++] = item;
                }
                integers = newArray;
            }
        }
        integers[writeIndex++] = integer;
        size++;
    }

    MyIterator iterator(){
        return new MyIterator();
    }

    class MyIterator implements Iterator<Object>{

        private int readIndex;

        MyIterator() {
            readIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return readIndex < size;
        }

        @Override
        public Integer next() {
            return integers[readIndex++];
        }
    }

}
