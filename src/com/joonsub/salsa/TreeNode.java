package com.joonsub.salsa;

import java.util.Iterator;
import java.util.Stack;

public class TreeNode{
	Node root;
	
	TreeNode(){
		root = new Node('*');
		
		// add fakes
		Node n1 = new Node('A');
		Node n2 = new Node('A');
		Node n3 = new Node('C');
		
		root.next.add(n1);
		n1.next.add(n2);
		n1.next.add(n3);
		
		//	  *
		//	  |
		//	  A  
		//	 / \
		//	A   C
	}
	public void addSingle(char c){
		Node n = new Node(c);
		root.next.add(n);
	}
	public void addContig(String s){
		
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Stack<Node> s = new Stack<Node>();
		
		s.add(root);
		sb.append('*');
		
		return "";
	}
}
