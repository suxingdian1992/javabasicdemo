package demo.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import demo.hero.Hero;

public class HelloReflection {
	public static void main(String[] args) {
        String className = "demo.hero.Hero";//使用反射获取类对象
        try {
            Class pClass1=Class.forName(className);
            Class pClass2=Hero.class;
            Class pClass3=new Hero().getClass();
            //System.out.println(pClass1==pClass2);
            //System.out.println(pClass1==pClass3);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            //使用反射的方式创建对象
        	className = "demo.hero.Hero";
            //类对象
            Class pClass=Class.forName(className);
            //构造器
            Constructor c= pClass.getConstructor();
            //通过构造器实例化
            Hero h2= (Hero) c.newInstance();//通过反射取得的构造器构造对象
            h2.name="gareen";
            System.out.println(h2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        //03、调用hero类中的setname方法，set那么有一个参数，是String类型
        Hero h = new Hero();
        
        try {
            // 获取这个名字叫做setName，参数类型是String的方法
            Method m = h.getClass().getMethod("setName", String.class);
            // 对h对象，调用这个方法
            m.invoke(h, "盖伦");
            // 使用传统的方式，调用getName方法
            /*h.getName();*/
            Method m1 = h.getClass().getMethod("getName");
            System.out.println(m1.invoke(h));//通过反射来调用getname方法
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}
}
