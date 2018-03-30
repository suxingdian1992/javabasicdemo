package demo.collection;

import java.util.ArrayList;

import demo.hero.Hero;

public class DemoArrayList {
	public static void main(String[] args) {
		//ע�⣬���Բ�д�����ź�����Ķ���Ŷ
		ArrayList<Hero> heros = new ArrayList<Hero>();

		// ��ʼ��5������
		for (int i = 0; i < 5; i++) {
			heros.add(new Hero("hero " + i));
		}
		Hero specialHero = new Hero("special hero");
		heros.add(specialHero);

		System.out.println(heros);
		
		System.out.println("�Ƿ�������Ϊhero 1��Ӣ�ۣ�"+chapter1(heros));
		
	}
	
	/**
	 * �ж��Ƿ���������hero1��Ӣ��
	 * @param <E>
	 * @param <E>
	 */
	public static <E> boolean chapter1(ArrayList<E> heros) {
		Hero[] heros1 = (Hero[])heros.toArray(new Hero[] {});
		for (int i = 0; i < heros1.length; i++) {
			if(heros1[i].name.equals("hero 1")) {
				return true;
			}
		}
		return false;
	}
}
