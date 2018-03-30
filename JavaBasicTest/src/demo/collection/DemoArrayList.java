package demo.collection;

import java.util.ArrayList;

import demo.hero.Hero;

public class DemoArrayList {
	public static void main(String[] args) {
		//注意，可以不写尖括号和里面的东西哦
		ArrayList<Hero> heros = new ArrayList<Hero>();

		// 初始化5个对象
		for (int i = 0; i < 5; i++) {
			heros.add(new Hero("hero " + i));
		}
		Hero specialHero = new Hero("special hero");
		heros.add(specialHero);

		System.out.println(heros);
		
		System.out.println("是否含有名字为hero 1的英雄："+chapter1(heros));
		
	}
	
	/**
	 * 判断是否含有名字是hero1的英雄
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
