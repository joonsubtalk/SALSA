package com.joonsub.graph;

public class GEdge {
	private int weight;
	private String node1;
	private String node2;
	// test purpose
	private boolean test = false;
	
	public GEdge(String n1, String n2){
		this.weight = getWeight(n1,n2);
		this.node1 = n1;
		this.node2 = n2;
	}
	
	public void setNode1(String s){
		this.node1 = s;
	}
	public void setNode2(String s){
		this.node2 = s;
	}
	
	public String getNode1(){
		return this.node1;
	}
	public String getNode2(){
		return this.node2;
	}
	
	public int getWeight(){
		return this.weight;
	}

	//n1: ACTGA
	//n2: TGAGG
	private int getWeight(String n1, String n2) {
		// Strings are immutable
		StringBuilder sb1 = new StringBuilder(n1);
		StringBuilder sb2 = new StringBuilder(n2);
		
		// We find the max matching distance
		for (int i = n1.length(); i > 0; i--){
			if (test){
				System.out.println("cnt: " + i);
				System.out.println(sb1.substring(n1.length()-i,n1.length()));
				System.out.println(sb2.substring(0,i));
			}
			if (sb1.substring(n1.length()-i,n1.length()).equals(sb2.substring(0,i)))
				return i;
		}
		// return 0 if no match
		return 0;
	}
}
