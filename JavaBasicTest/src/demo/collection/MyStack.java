package demo.collection;

import demo.hero.Hero;

public interface MyStack {
	//��Ӣ�����뵽���λ��
    public void push(Hero h);
    //�����һ��Ӣ��ȡ����
    public Hero pull();
    //�鿴���һ��Ӣ��
    public Hero peek();
}
