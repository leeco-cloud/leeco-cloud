package com.coco.cloud.patterns.behavior.iterator;

/**
 * @author liuqiang@ourdocker.cn
 * @version 0.0.1
 * @date 2021/4/27 21:11
 */
public class IteratorTest {

    public static void main(String[] args) {
        MyList myList = new MyList();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        MyList.MyIterator iterator = myList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("=====================");
        myList.add(7);
        iterator = myList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

}
