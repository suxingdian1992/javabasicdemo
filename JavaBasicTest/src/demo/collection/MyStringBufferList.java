package demo.collection;

import java.util.ArrayList;
import java.util.Random;

/**
 * 使用arraylist实现的Stringbuffer
 * @author suxin
 *
 */
public class MyStringBufferList implements IsStringBuffer {
	
	ArrayList<Character> value = new ArrayList<Character>();
	int length = 0;//记录字符串的长度
	public MyStringBufferList() {
		
	}
	public MyStringBufferList(String str) {
		if(null == str)
            return;
         
        char[] ca = str.toCharArray();
        for(char c : ca){
            value.add(c);
        }
        length = ca.length;
	}
	@Override
	public void append(String str) {
		insert(value.size(), str);
	}

	@Override
	public void append(char c) {
		insert(value.size(), Character.toString(c));
	}

	@Override
	public void insert(int pos, char b) {
		insert(pos, Character.toString(b));
	}

	@Override
	public void insert(int pos, String b) {
		if(pos < 0 || pos > length){
            System.out.println("插入位置出错");
            return;
        }
        if(null == b){
            System.out.println("插入为null");
            return;
        }
         
        char[] ca = b.toCharArray();
        for(int i = 0; i < ca.length; i++){
            value.add(pos, ca[i]);//从指定位置插入字符
            pos++;
        }
        length = value.size();
	}

	@Override
	public void delete(int start) {
		delete(start, length);
	}

	@Override
	public void delete(int start, int end) {
		if(start < 0 || start > length || end < 0 || end > length || start >= end){
            System.out.println("输入超出边界了");
            return;
        }
        int j=start;
        for(int i = start; i < end ; i++){
            value.remove(j);//每删除一次则字符串缩短一次
        }
        length = value.size();
	}

	@Override
	public void reverse() {
		for(int i = 0; i < length / 2; i++){
            char temp = value.get(i);
			value.set(i, value.get(length - i - 1));
			value.set(length - i - 1, temp);
        }
		length = value.size();
	}

	@Override
	public int length() {
		return length;
	}
	
	@Override
    public String toString(){
        String str = "";
        for(Character s : value){
            str += s;
        }
        return str;
    }
	
	public static void main(String[] args) {
		MyStringBufferList sb = new MyStringBufferList("there light");
        System.out.println(sb);
        sb.insert(0, "let ");
        System.out.println(sb);
 
        sb.insert(10, "be ");
        System.out.println(sb + " " + sb.length);
        sb.insert(0, "God Say:");
        System.out.println(sb + " " + sb.length);
        sb.append("!");
        System.out.println(sb + " " + sb.length);
        sb.append('?');
        System.out.println(sb + " " + sb.length);
        sb.reverse();
        System.out.println(sb + " " + sb.length);
         
        sb.reverse();
        System.out.println(sb + " " + sb.length);
         
        sb.delete(0,4);
        System.out.println(sb + " " + sb.length);
        sb.delete(4);
        System.out.println(sb + " " + sb.length);
        
        
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
