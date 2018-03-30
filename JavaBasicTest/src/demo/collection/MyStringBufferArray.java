package demo.collection;

import java.util.Random;

/**
 * 使用字符数组实现的StringBuffer，连接很迅速
 * @author suxin
 *
 */
public class MyStringBufferArray implements IsStringBuffer {

	public int capacity = 16; //当前的字符串对象的容量
	public int length = 0;//当前字符串的长度
	public char[] value;
	
	
	public MyStringBufferArray() {
		value = new char[capacity];//重新生成容量为capacity的字符数组
	}

	/**
	 * 含有参数的构造函数
	 * @param str
	 */
	public MyStringBufferArray(String str) {
		if(str!=null) {
			char[]temp = str.toCharArray();//将传入的字符串转为字符数组
			length = temp.length;
			if(capacity<length) {
				capacity = value.length*2;//容量扩充为两倍
			}
			value = new char[capacity];
			System.arraycopy(temp, 0, value, 0, temp.length);
		}
		
	}
	@Override
	public void append(String str) {
		// TODO Auto-generated method stub

	}

	@Override
	public void append(char c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(int pos, char b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insert(int pos, String b) {
		//边界条件判断
        if(pos<0)
            return;
          
        if(pos>length)
            return;
          
        if(null==b)
            return;
          
        //扩容
        while(length+b.length()>capacity){
            capacity = (int) ((length+b.length())*1.5f);
            char[] newValue = new char[capacity];
            System.arraycopy(value, 0, newValue, 0, length);//容量变为新长度的2.5倍然后新构造字符数组
            value = newValue;
        }
          
        char[] cs = b.toCharArray();
          
        //先把已经存在的数据往后移，将原始字符串的pos开始之后的部分向后移动cs.length哥单位
        System.arraycopy(value, pos, value,pos+ cs.length, length-pos);
        //把要插入的数据插入到指定位置
        System.arraycopy(cs, 0, value, pos, cs.length);
          
        length = length+cs.length;
	}

	@Override
	public void delete(int start) {
		// TODO Auto-generated method stub
		delete(start,length);
	}

	@Override
	public void delete(int start, int end) {
		//边界条件判断
        if(start<0)
            return;
         
        if(start>length)
            return;
         
        if(end<0)
            return;
         
        if(end>length)
            return;
         
        if(start>=end)
            return;
         
        System.arraycopy(value, end, value, start, length- end);
        length-=end-start;
	}

	@Override
	public void reverse() {
		for (int i = 0; i < length / 2; i++) {
            char temp = value[i];
            value[i] = value[length - i - 1];
            value[length - i - 1] = temp;
        }
	}

	@Override
	public int length() {
		return length;
	}
	
	public String toString() {
        char[] realValue = new char[length];
 
        System.arraycopy(value, 0, realValue, 0, length);
 
        return new String(realValue);
    }
	
	public static void main(String[] args) {
		MyStringBufferArray sb = new MyStringBufferArray("there light");
        System.out.println(sb);
        sb.insert(0, "let ");
        System.out.println(sb);
 
        sb.insert(10, "be ");
        System.out.println(sb);
        sb.insert(0, "God Say:");
        System.out.println(sb);
        sb.append("!");
        System.out.println(sb);
        sb.append('?');
        System.out.println(sb);
        sb.reverse();
        System.out.println(sb);
         
        sb.reverse();
        System.out.println(sb);
         
        sb.delete(0,4);
        System.out.println(sb);
        sb.delete(4);
        System.out.println(sb);
        
        
        //性能比较，比较用+和用Stringbuffer连接字符串的性能
        
        Random rd = new Random();
        char[] cArray = new char[10];
        System.out.println((int)'z');
        String strResult="";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
        	for (int j = 0; j < 10; j++) {
            	int x = (int)'a' +rd.nextInt(((int)'z')-((int)'a')+1);
            	cArray[j]=(char)x;
    		}
            String str = new String(cArray);
            strResult+=str;
		}
        long end = System.currentTimeMillis();
        System.out.println("使用+连接耗时（毫秒）："+(end-start));
        start = System.currentTimeMillis();
        MyStringBufferArray strResultFasy = new MyStringBufferArray();
        for (int i = 0; i < 10000; i++) {
        	for (int j = 0; j < 10; j++) {
            	int x = (int)'a' +rd.nextInt(((int)'z')-((int)'a')+1);
            	cArray[j]=(char)x;
    		}
        	String temp = new String(cArray);
            strResultFasy.append(temp);
		}
        end = System.currentTimeMillis();
        System.out.println("使用StringBuffer连接耗时（毫秒）："+(end-start));
    }

}
