package demo.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 节点 二叉树排序测试
 * @author suxin
 *
 */
public class Node {

	public Node leftNode;//当前节点的左节点
	
	public Node rightNode;//当前节点的右节点
	
	public Object value;//当前节点内部存放的对象
	
	/**
	 * 插入排序，如果插入的值比当前值小则放在左边，大的话则放在右边
	 */
	public void add(Object v) {
		// 如果当前节点没有值，就把数据放在当前节点上
		if (null == value) {
			value = v;
		} else {// 如果当前节点有值，就进行判断，新增的值与当前值的大小关系
			// 新增的值，比当前值小或者相同

			if ((Integer) v - ((Integer) value) <= 0) {
				if (null == leftNode)
					leftNode = new Node();
				leftNode.add(v);
			}
			// 新增的值，比当前值大
			else {
				if (null == rightNode)
					rightNode = new Node();
				rightNode.add(v);
			}

		}
	}
	
	/**
	 * 遍历二叉树
	 * 中序遍历：左中右
	 * 左序遍历：中左右
	 * 右序遍历：左右中
	 * @return
	 */
	public List<Object> values() {
		List<Object> result = new ArrayList<Object>();
		if(this.leftNode!=null) {
			result.addAll(leftNode.values());
		}
		
		result.add(value);//中间节点的值留在中间
		
		if(this.rightNode!=null) {
			result.addAll(rightNode.values());
		}
		return result;
	}
	
	public static void main(String[] args) {
		Random rd = new Random();//产生四万个随机数
		int[] x = new int[40000];
		for (int i = 0; i < 40000; i++) {
			x[i]=10000+rd.nextInt((40000-10000+1));
		}
		
		//二叉树排序
		long start = System.currentTimeMillis();
		Node binaryTree = new Node();
		for (int i = 0; i < x.length; i++) {
			binaryTree.add(x[i]);
		}
		long end = System.currentTimeMillis();
		System.out.println(binaryTree.values());
		System.out.println("二叉树排序耗时时间："+(end-start)+"ms");
		
		//冒泡排序
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
		System.out.println("冒泡排序耗时："+(end-start)+"ms");
		
	}
}
