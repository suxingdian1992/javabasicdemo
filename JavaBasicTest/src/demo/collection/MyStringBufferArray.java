package demo.collection;

import java.util.Random;

/**
 * ʹ���ַ�����ʵ�ֵ�StringBuffer�����Ӻ�Ѹ��
 * @author suxin
 *
 */
public class MyStringBufferArray implements IsStringBuffer {

	public int capacity = 16; //��ǰ���ַ������������
	public int length = 0;//��ǰ�ַ����ĳ���
	public char[] value;
	
	
	public MyStringBufferArray() {
		value = new char[capacity];//������������Ϊcapacity���ַ�����
	}

	/**
	 * ���в����Ĺ��캯��
	 * @param str
	 */
	public MyStringBufferArray(String str) {
		if(str!=null) {
			char[]temp = str.toCharArray();//��������ַ���תΪ�ַ�����
			length = temp.length;
			if(capacity<length) {
				capacity = value.length*2;//��������Ϊ����
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
		//�߽������ж�
        if(pos<0)
            return;
          
        if(pos>length)
            return;
          
        if(null==b)
            return;
          
        //����
        while(length+b.length()>capacity){
            capacity = (int) ((length+b.length())*1.5f);
            char[] newValue = new char[capacity];
            System.arraycopy(value, 0, newValue, 0, length);//������Ϊ�³��ȵ�2.5��Ȼ���¹����ַ�����
            value = newValue;
        }
          
        char[] cs = b.toCharArray();
          
        //�Ȱ��Ѿ����ڵ����������ƣ���ԭʼ�ַ�����pos��ʼ֮��Ĳ�������ƶ�cs.length�絥λ
        System.arraycopy(value, pos, value,pos+ cs.length, length-pos);
        //��Ҫ��������ݲ��뵽ָ��λ��
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
		//�߽������ж�
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
        
        
        //���ܱȽϣ��Ƚ���+����Stringbuffer�����ַ���������
        
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
        System.out.println("ʹ��+���Ӻ�ʱ�����룩��"+(end-start));
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
        System.out.println("ʹ��StringBuffer���Ӻ�ʱ�����룩��"+(end-start));
    }

}
