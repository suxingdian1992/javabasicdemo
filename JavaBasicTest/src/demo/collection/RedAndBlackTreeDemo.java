package demo.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 红黑树生成标定颜色
 * @author suxin
 *
 */
public class RedAndBlackTreeDemo {
	public static void main(String[] args) {
		//初始化红黑树
		int[] is = {13,8,1,11,17,15,25,22,27};//长度为10的数组，实际上会生成21个节点
		
		RedOrBlackTree rbTree = new RedOrBlackTree();
		for (int i : is) {
			rbTree.add(i);
		}
		
		List<Object> rbTreeList = rbTree.values();
		System.out.println(rbTreeList);
		
		//列出当前树所有的着色的可能
		
		
		
	}
}

//判断是否是红黑树的规则
/*1.节点是红色或黑色。

2.根节点是黑色。

3.每个叶子节点都是黑色的空节点（NIL节点）。

4 每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)

5.从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。*/

//枚举所有可能着色的情况，在遍历的时候对当前节点进行着色

/**
 * 节点
 * @author suxin
 *
 */
class RedOrBlackTree{
	//当前节点的值
	Integer value;
	//当前节点的颜色
	String color;
	
	//左侧根节点,注意，最底层叶节点值必须为null
	RedOrBlackTree leftNode;
	//右侧根节点
	RedOrBlackTree rightNode;
	//父子节点
	RedOrBlackTree parentNode;
	
	public void add(int i) {
		if(value == null) {//如果当前节点的值为空则将值保存在当前节点
			this.value = i;
			parentNode=this;//父节点为自己
		}else {
			if(value<i) {//当这个数大于当前节点的值则放入右侧节点
				if(rightNode==null) {
					rightNode = new RedOrBlackTree();
				}
				this.rightNode.add(i);
				this.rightNode.parentNode=this;
			}else {//否则放入左侧节点
				if(leftNode==null) {
					leftNode = new RedOrBlackTree();
				}
				this.leftNode.add(i);
				this.leftNode.parentNode=this;
			}
		}
	}
	//进行中序遍历
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
