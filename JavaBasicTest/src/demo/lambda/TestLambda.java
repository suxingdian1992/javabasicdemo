package demo.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import demo.hero.Hero;

public class TestLambda {
	public static void main(String[] args) {
		Random r = new Random();
		List<Hero> heros = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			heros.add(new Hero("hero-" + i, r.nextInt(1000 - 100 + 1) + 100, r.nextInt(100 - 10 + 1) + 10));
		}
		System.out.println("��ʼ��֮���Ӣ�ۼ��ϣ�");
		System.out.println(heros);
		System.out.println("ʹ��������ķ�ʽ��ɸѡ�� hp>100 && damange<50��Ӣ��");
		HeroChecker checker = new HeroChecker() {
			@Override
			public boolean test(Hero h) {
				return (h.hp > 100 && h.damage < 50);
			}
		};
		//����ὲ����δ��������ݱ�Ϊlambda���ʽ
		
		filter(heros, checker);
		
		// ��new HeroChcekcer������������������������Ϣȥ��
        // ֻ�������������ͷ�����
        // �����ͷ�����֮����Ϸ��� ->
        HeroChecker c2 = (Hero h) -> {
            return h.hp > 100 && h.damage < 50;
        };
 
        // ��return��{}ȥ��
        HeroChecker c3 = (Hero h) -> h.hp > 100 && h.damage < 50;
 
        // �� �������ͺ�Բ����ȥ��
        HeroChecker c4 = h -> h.hp > 100 && h.damage < 50;
 
        // ��c4��Ϊ�������ݽ�ȥ
        filter(heros, c4);
         
        // ֱ�Ӱѱ��ʽ���ݽ�ȥ ��������˵��������ʽ��ʵ�Ǹ�������������������Ϊ����ֱ�ӽ��д���
        // �����������������ֱ��ʽ֮����ô����ø����ܶ�����˵���������ൽ������
        filter(heros, h -> h.hp > 100 && h.damage < 50);

		
	}

	private static void filter(List<Hero> heros, HeroChecker checker) {
		for (Hero hero : heros) {
			if (checker.test(hero))
				System.out.print(hero);
		}
	}
}
