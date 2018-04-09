package demo.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import demo.hero.Item;

/**
 * �ڵ� �������������
 * @author suxin
 *
 */
public class Node<T extends Comparable> {

	public Node<T> leftNode;//��ǰ�ڵ����ڵ�
	
	public Node<T> rightNode;//��ǰ�ڵ���ҽڵ�
	
	public T value;//��ǰ�ڵ��ڲ���ŵĶ���
	
	/**
	 * ����������������ֵ�ȵ�ǰֵС�������ߣ���Ļ�������ұ�
	 */
	public void add(T v) {
		// �����ǰ�ڵ�û��ֵ���Ͱ����ݷ��ڵ�ǰ�ڵ���
		if (null == value) {
			value = v;
		} else {// �����ǰ�ڵ���ֵ���ͽ����жϣ�������ֵ�뵱ǰֵ�Ĵ�С��ϵ
			// ������ֵ���ȵ�ǰֵС������ͬ

			if (v.compareTo(value) <= 0) {
				if (null == leftNode)
					leftNode = new Node<T>();
				leftNode.add(v);
			}
			// ������ֵ���ȵ�ǰֵ��
			else {
				if (null == rightNode)
					rightNode = new Node<T>();
				rightNode.add(v);
			}

		}
	}
	
	/**
	 * �������������
	 * ���������������
	 * ���������������
	 * ���������������
	 * @return
	 */
	public List<Object> values() {
		List<Object> result = new ArrayList<Object>();
		if(this.leftNode!=null) {
			result.addAll(leftNode.values());
		}
		
		result.add(value);//�м�ڵ��ֵ�����м�
		
		if(this.rightNode!=null) {
			result.addAll(rightNode.values());
		}
		return result;
	}
	
	public static void main(String[] args) {
		Random rd = new Random();//��������������
		int[] x = new int[40000];
		for (int i = 0; i < 40000; i++) {
			x[i]=10000+rd.nextInt((40000-10000+1));
		}
		
		//����������
		long start = System.currentTimeMillis();
		Node binaryTree = new Node();
		for (int i = 0; i < x.length; i++) {
			binaryTree.add(x[i]);
		}
		long end = System.currentTimeMillis();
		System.out.println(binaryTree.values());
		System.out.println("�����������ʱʱ�䣺"+(end-start)+"ms");
		
		//ð������
		start = System.currentTimeMillis();
		for (int i = 0; i < x.length; i++) {
			for (int j = i; j < x.length; j++) {
				if (x[i] > x[j]) {
					int temp = x[j];
					x[i]=x[j];
					x[j]=temp;
				}
			}
		}
		end = System.currentTimeMillis();
		System.out.println("ð�������ʱ��"+(end-start)+"ms");
		
		Node<Comparable> name = new Node<Comparable>();
		name.chapter1();
	}
	
	/**
	 * ʹ�÷������޸�֮��Ķ������������ʵ������
	 */
	public void chapter1() {
		Node<Item> binaryTree = new Node<Item>();
		for (int i = 0; i < 10; i++) {
			int x = (int)((Math.random()*(100-10+1))+10);
			Item v = new Item("item"+x,x);
			binaryTree.add(v);
		}
		System.out.println(binaryTree.values());
	}
}
