package com.test;

/**
 * Created by Tinyhome on 16/7/6.
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TinyServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        String getURI = req.getRequestURI();
        getURI = getURI.replace("/test/","");

        /*
        req.setAttribute("name","Tiny");
        req.setAttribute("age","18");
        */


        try {
            //设置response的header头
            resp.setHeader("Content-type","text/html;charset=utf-8");

            //使用反射动态引入类
            Class c = Class.forName("com.test.TinyClass");
            //实例化类
            Object obj = c.newInstance();

            //获取所有声明的字段,不包括父类
            Field[] fields = c.getDeclaredFields();
            for(Field f : fields){
                //获取字段上面的注解
                Annotation[] f_ans = f.getAnnotations();
                for(Annotation a : f_ans){
                    //a是否为TinyLoad类的一个实例
                    if(TinyLoad.class.isInstance(a)){
                        //获取声明类的名字
                        String getClassName = f.getDeclaringClass().getName();
                        resp.getWriter().write(getClassName);
                        //到配置文件tiny.xml中,匹配相关节点
                        //取到到之后,通过反射生成class,然后对属性进行赋值
                        //Class createClass = Class.forName(getClassName);
                    }
                }
            }
            resp.getWriter().close();

            //获取类的所有方法
            Method[] mlist = c.getMethods();

            //遍历所有方法列表
            for(Method m : mlist){
                //获取方法上的注解列表
                Annotation[] anlist = m.getAnnotations();
                //遍历注解列表
                for(Annotation a: anlist){
                    //强制类型转换
                    TinyAnnotation geta = (TinyAnnotation) a;
                    //匹配注解
                    if(geta.name().equals(getURI)){
                        //pw.write(m.invoke(obj,req,resp).toString());
                        //pw.write(m.invoke(obj,new Object[]{req,resp}).toString());
                        //动态执行方法
                        m.invoke(obj,new Object[]{req,resp});
                        //pw.write("<hr/>");
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
