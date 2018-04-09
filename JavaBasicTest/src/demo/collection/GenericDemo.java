package demo.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import demo.hero.APHero;
import demo.hero.Hero;
import demo.hero.Item;
import demo.hero.LOL;

/**
 * ���Ͳ��ԣ�����list����ָ�����͵Ļ��Ϳ���װ��������͵Ķ���
 * ָ���˷�����ֻ�ܷ�ָ�����͵Ķ���
 * ���ͻ�������Ҫָ���ģ���ֹת����ʱ���������
 * @author suxin
 */
public class GenericDemo {
	public static void main(String[] args) {

		// ���ڲ�ʹ�÷��͵������������������Ӣ�ۣ�Ҳ�������������Ʒ
		List heros = new ArrayList();

		heros.add(new Hero("����"));

		// �������ڴ��Ӣ�۵�����������Ҳ���Դ����Ʒ��
		heros.add(new Item("����",123));

		// ����ת�ͻ��������
		Hero h1 = (Hero) heros.get(0);
		System.out.println(h1.name);
		// ��������������ŵĶ���̫���ʱ�򣬾ͼǲ�����ĸ�λ�÷ŵ����������͵Ķ�����
		// Hero h2= (Hero) heros.get(1);

		// ���뷺��Generic
		// ����������ʱ�򣬾�ָ��������������ֻ�ܷ�Hero���������ľͻ����
		List<Hero> genericheros = new ArrayList<Hero>();
		genericheros.add(new Hero("����"));
		// �������Hero���ͣ������ͷŲ���ȥ
		// genericheros.add(new Item("����"));

		// ����֮�⣬���ܴ��Hero������
		genericheros.add(new APHero());

		// ������ȡ�����ݵ�ʱ�򣬲���Ҫ�ٽ���ת���ˣ���Ϊ����϶��Ƿŵ�Hero����������
		Hero h = genericheros.get(0);
		
		GenericDemo test = new GenericDemo();
		test.chapter3();

	}
	
	/**
	 * ��ϰ����취��arraylistֻ�ܷ���hero��item����
	 */
	public void chapter1() {
		List<LOL> list = new ArrayList<LOL>();
		
		//���һ�����ߵĹ��и��༴��
		list.add(new Hero("����"));
		list.add(new Item("����",123));
	}
	
	/**
	 * ��������
	 */
	public void chapter2() {
		List<LOL> heros = new ArrayList<LOL>();

		// ��5��Hero��������
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero name " + i));
        }
 
        // ��һ�ֱ��� forѭ��
        System.out.println("--------���� for ѭ��-------");
        for (int i = 0; i < heros.size(); i++) {
            Hero h = (Hero) heros.get(i);
            System.out.println(h);
        }
        
        //�ڶ�����ǿfor
        System.out.println("--------��ǿ��ʽ��forѭ��");
        for (LOL lol : heros) {
        	Hero h = (Hero)lol;
			System.out.println(h.name);
		}
        
        //�����ֵ���������
        System.out.println("--------ʹ��while��iterator-------");
        Iterator<LOL> it= heros.iterator();
        //���ʼ��λ���ж�"��һ��"λ���Ƿ�������
        //����о�ͨ��nextȡ���������Ұ�ָ�������ƶ�
        //ֱ��"��һ��"λ��û������
        while(it.hasNext()){
            Hero h = (Hero) it.next();//ע�⣬hasnext�����ƶ�ָ�룬ֻ�ǲ鿴���滹��û��Ԫ�أ�����next������ƶ�һ��ָ��
            System.out.println(h);
        }
        //��������forд��
        System.out.println("--------ʹ��for��iterator-------");
        for (Iterator<LOL> iterator = heros.iterator(); iterator.hasNext();) {
            Hero hero = (Hero) iterator.next();
            System.out.println(hero);
        }
 
	}
	
	//��ҵ��
	/**
	 * ���ȳ�ʼ��һ��Hero���ϣ������100��Hero�������Ʒֱ��Ǵ� hero 0 hero 1 hero 2 ... hero 99.
	 * ͨ���������ֶΣ�ɾ�������ֱ����8�ı����Ķ���
	 */
	public void chapter3() {
		List<LOL> heros = new ArrayList<LOL>();

		// ��5��Hero��������
        for (int i = 0; i < 100; i++) {
            heros.add(new Hero("hero name " + i));
        }
        
        List<Integer> iList = new ArrayList<Integer>();
        //forд��ȥ��Ԫ��
        for (int i = 0; i < heros.size(); i++) {
        	Hero h = (Hero) heros.get(i);//ע�⣬hasnext�����ƶ�ָ�룬ֻ�ǲ鿴���滹��û��Ԫ�أ�����next������ƶ�һ��ָ��
            String[] strs = h.name.split(" ");
            if(Integer.parseInt(strs[2])%8==0) {
            	heros.remove(i);
            	i--;
            }
		}
        
        
        Iterator<LOL> it = heros.iterator();
        while(it.hasNext()){
            Hero h = (Hero) it.next();//ע�⣬hasnext�����ƶ�ָ�룬ֻ�ǲ鿴���滹��û��Ԫ�أ�����next������ƶ�һ��ָ��
            System.out.println(h);
        }
	}
}

/**
 * ֧�ַ��͵���
 * @author suxin
 * @param <T>
 */
class MyStackGeneric<T> {
	   
    LinkedList<T> values = new LinkedList<T>();
       
    public void push(T t) {
        values.addLast(t);
    }
   
    public T pull() {
        return values.removeLast();
    }
   
    public T peek() {
        return values.getLast();
    }
       
    public static void main(String[] args) {
        //���������Stack��ʱ��ʹ�÷���<Hero>�ͱ�ʾ��Stackֻ�ܷ�Hero
    	MyStackGeneric<Hero> heroStack = new MyStackGeneric<>();
        heroStack.push(new Hero());
        //���ܷ�Item
        //heroStack.push(new Item());
         
        //���������Stack��ʱ��ʹ�÷���<Item>�ͱ�ʾ��Stackֻ�ܷ�Item
        MyStackGeneric<Item> itemStack = new MyStackGeneric<>();
        itemStack.push(new Item());
        //���ܷ�Hero
        //itemStack.push(new Hero());
    }
   
}
