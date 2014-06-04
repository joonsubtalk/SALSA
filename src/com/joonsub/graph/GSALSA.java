package com.joonsub.graph;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.joonsub.salsa.Trie;
import com.joonsub.salsa.TrieImpl;

public class GSALSA {
	//final static String FILE_SEQ = "output.txt";
	final static String FILE_NAME = ".\\resources\\";
	final static Charset ENCODING = StandardCharsets.UTF_8;
    static Trie<String> contigs = new TrieImpl<String>();
    static StringBuilder strands = new StringBuilder();
	static List<String> usedStrands = new ArrayList<String>();
    static char neuclotide;
    
    static Boolean test = false;
    
	public static void main(String[] args) throws IOException{
		//root = new Node();
		//			root = new TreeNode<Character>('r');
		readLargerTextFile(FILE_NAME + "output.txt");
		
		//contigs.getAllKeys();
		
		// Put everything into a list -> contigList.
		List<String> contigList = (contigs.getKeyList());
		
		for (int i = 0; i < contigList.size(); i++){
			System.out.println(contigList.get(i).toString());
		}
		GEdge ge = new GEdge("AAAAATAA","TAAAATAA");
		System.out.println(ge.getWeight());
	}
	
	public static void readLargerTextFile(String aFileName) throws IOException {
		Path path = Paths.get(aFileName);
		StringBuilder sb = new StringBuilder();
		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				// process each line in some way
				sb.append(scanner.nextLine());
				contigs.add(sb.toString(),sb.toString());
				sb.setLength(0);
			}
		}
		System.out.println("Files Loaded");
	}
}