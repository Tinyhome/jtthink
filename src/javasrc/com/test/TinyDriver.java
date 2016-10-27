package com.test;

/**
 * Created by Tinyhome on 16/7/12.
 */
public class TinyDriver {
    static {
        //当类被载入时,静态代码块,主动执行,只执行一次
        System.out.println("hello,I am Tiny's driver.");
        TinyManager._name="Tony";
    }
    /*public TinyDriver(){
        System.out.println("执行了构造函数!");
    }*/
}
