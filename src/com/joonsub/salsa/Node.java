package com.joonsub.salsa;

import java.util.List;

public class Node {
	
	// make child node
	private Node[] c;
	public Node(){
		c = new Node[4];
	}
	
	public void insert(String s){
		
	}
	
	private char value;
	private List<Node> nextNode;
	/*
	public Node(){
		this.value = ' ';
	}
	*/
	public Node(char value){
		this.value = value;
	}
	
		
}
