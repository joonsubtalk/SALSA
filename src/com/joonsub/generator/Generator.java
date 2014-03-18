package com.joonsub.generator;

import java.util.Random;

public class Generator {
	private Random gen = new Random(System.currentTimeMillis());
	private String sequence;
	//private final int LENGTH = 200;
	
	public Generator(int len){
		sequence = generateSequence(len);
		System.out.println("Added " + len + " genome");
	}
	
	public String getSequence(){
		return sequence;
	}

	private String generateSequence(int length){
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < length; i++){
			sb.append(getNeuclotide(gen.nextInt( 4 )));
		}
		
		return sb.toString();
	}
	
	private char getNeuclotide(int id){
		switch(id){
		case 0:
			return 'A';
		case 1:
			return 'C';
		case 2:
			return 'T';
		case 3:
			return 'G';
		default:
			return ' ';
		}
	}
	
}
