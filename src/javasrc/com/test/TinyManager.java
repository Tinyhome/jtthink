package com.test;

/**
 * Created by Tinyhome on 16/7/12.
 */
public class TinyManager {
    //静态变量是属于整个类的变量,而不是某个对象的,不能把任何方法体内的变量申明为静态
    public static String _name="";
    public static void showName(){
        System.out.println("我的名字是:"+_name);
    }
}
