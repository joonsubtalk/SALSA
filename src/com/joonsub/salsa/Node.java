package com.joonsub.salsa;

import java.util.ArrayList;
import java.util.List;

public class Node {

	char value;
	List next = new ArrayList<Node>();
	
	Node(char c){
		this.value = c;
	}
}
