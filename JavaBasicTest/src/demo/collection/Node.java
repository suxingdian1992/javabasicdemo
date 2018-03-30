package demo.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * �ڵ� �������������
 * @author suxin
 *
 */
public class Node {

	public Node leftNode;//��ǰ�ڵ����ڵ�
	
	public Node rightNode;//��ǰ�ڵ���ҽڵ�
	
	public Object value;//��ǰ�ڵ��ڲ���ŵĶ���
	
	/**
	 * ����������������ֵ�ȵ�ǰֵС�������ߣ���Ļ�������ұ�
	 */
	public void add(Object v) {
		// �����ǰ�ڵ�û��ֵ���Ͱ����ݷ��ڵ�ǰ�ڵ���
		if (null == value) {
			value = v;
		} else {// �����ǰ�ڵ���ֵ���ͽ����жϣ�������ֵ�뵱ǰֵ�Ĵ�С��ϵ
			// ������ֵ���ȵ�ǰֵС������ͬ

			if ((Integer) v - ((Integer) value) <= 0) {
				if (null == leftNode)
					leftNode = new Node();
				leftNode.add(v);
			}
			// ������ֵ���ȵ�ǰֵ��
			else {
				if (null == rightNode)
					rightNode = new Node();
				rightNode.add(v);
			}

		}
	}
	
	/**
	 * ����������
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
		
	}
}
