package demo.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ��������ɱ궨��ɫ
 * @author suxin
 *
 */
public class RedAndBlackTreeDemo {
	public static void main(String[] args) {
		//��ʼ�������
		int[] is = {13,8,1,11,17,15,25,22,27};//����Ϊ10�����飬ʵ���ϻ�����21���ڵ�
		
		RedOrBlackTree rbTree = new RedOrBlackTree();
		for (int i : is) {
			rbTree.add(i);
		}
		
		List<Object> rbTreeList = rbTree.values();
		System.out.println(rbTreeList);
		
		//�г���ǰ�����е���ɫ�Ŀ���
		
		
		
	}
}

//�ж��Ƿ��Ǻ�����Ĺ���
/*1.�ڵ��Ǻ�ɫ���ɫ��

2.���ڵ��Ǻ�ɫ��

3.ÿ��Ҷ�ӽڵ㶼�Ǻ�ɫ�Ŀսڵ㣨NIL�ڵ㣩��

4 ÿ����ɫ�ڵ�������ӽڵ㶼�Ǻ�ɫ��(��ÿ��Ҷ�ӵ���������·���ϲ��������������ĺ�ɫ�ڵ�)

5.����һ�ڵ㵽��ÿ��Ҷ�ӵ�����·����������ͬ��Ŀ�ĺ�ɫ�ڵ㡣*/

//ö�����п�����ɫ��������ڱ�����ʱ��Ե�ǰ�ڵ������ɫ

/**
 * �ڵ�
 * @author suxin
 *
 */
class RedOrBlackTree{
	//��ǰ�ڵ��ֵ
	Integer value;
	//��ǰ�ڵ����ɫ
	String color;
	
	//�����ڵ�,ע�⣬��ײ�Ҷ�ڵ�ֵ����Ϊnull
	RedOrBlackTree leftNode;
	//�Ҳ���ڵ�
	RedOrBlackTree rightNode;
	//���ӽڵ�
	RedOrBlackTree parentNode;
	
	public void add(int i) {
		if(value == null) {//�����ǰ�ڵ��ֵΪ����ֵ�����ڵ�ǰ�ڵ�
			this.value = i;
			parentNode=this;//���ڵ�Ϊ�Լ�
		}else {
			if(value<i) {//����������ڵ�ǰ�ڵ��ֵ������Ҳ�ڵ�
				if(rightNode==null) {
					rightNode = new RedOrBlackTree();
				}
				this.rightNode.add(i);
				this.rightNode.parentNode=this;
			}else {//����������ڵ�
				if(leftNode==null) {
					leftNode = new RedOrBlackTree();
				}
				this.leftNode.add(i);
				this.leftNode.parentNode=this;
			}
		}
	}
	//�����������
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
	
	public RedOrBlackTree getLeftNode() {
		return leftNode;
	}
	public void setLeftNode(RedOrBlackTree leftNode) {
		this.leftNode = leftNode;
	}
	public RedOrBlackTree getRightNode() {
		return rightNode;
	}
	public void setRightNode(RedOrBlackTree rightNode) {
		this.rightNode = rightNode;
	}
	public RedOrBlackTree getParentNode() {
		return parentNode;
	}
	public void setParentNode(RedOrBlackTree parentNode) {
		this.parentNode = parentNode;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
}
