package com.joonsub.salsa;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SALSA {
	
	//final static String FILE_SEQ = "output.txt";
		final static String FILE_NAME = ".\\resources\\";
		final static Charset ENCODING = StandardCharsets.UTF_8;
	    static Trie<String> contigs = new TrieImpl<String>();
		
		public static void main(String[] args) throws IOException{
			//root = new Node();
			//			root = new TreeNode<Character>('r');
			readLargerTextFile(FILE_NAME + "output.txt");
			
			//contigs.getAllKeys();
			//System.out.println(contigs.search("A"));
			
			// Put everything into a list.
			List<String> contigList = (contigs.getKeyList());
			
			// Go thru every element
//			for (int i = 0; i < contigList.size(); i++ ){
//				System.out.println("searching: " + contigList.get(i).substring(1,contigList.size()-1));
//				System.out.println("\t: " + contigList.get(i));
//				contigs.search(contigList.get(i).substring(1,contigList.size()-1));
//			}
			
			Iterator itr = contigList.iterator();
			while(itr.hasNext()) {
				Object element = itr.next();
				System.out.print(element + " ");
			}
			System.out.println(contigs.size());
		}
		
		public static void readLargerTextFile(String aFileName) throws IOException {
			Path path = Paths.get(aFileName);
			int cnt = 0;
			StringBuilder sb = new StringBuilder();
			try (Scanner scanner = new Scanner(path, ENCODING.name())) {
				while (scanner.hasNextLine()) {
					// process each line in some way
					sb.append(scanner.nextLine());
					contigs.add(sb.toString(),sb.toString());
					sb.setLength(0);
					cnt++;
				}
			}
			System.out.println("Files Loaded");
		}
		
}
