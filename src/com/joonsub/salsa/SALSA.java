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
			
			// Put everything into a list -> contigList.
			List<String> contigList = (contigs.getKeyList());
			
			System.out.println("\n\r");
			
			// Go thru every element
			int start = 1;
			StringBuilder sb = new StringBuilder();
			StringBuilder reconstruct = new StringBuilder();
			String contig;
			for (int i = 0; i < contigList.size(); i++ ){
				start = 1;
				// Get's the substring of the contig
				sb.append(contigList.get(i).substring(start,contigList.get(1).length()));
				contig = sb.toString();
				sb.setLength(0); // clears string
				
				System.out.println("searching: " + contigList.get(i));
				System.out.println("\t:   " + contig);
				// looks for the substring
				System.out.println("\tFound: " + contigs.search(contig));
				if (contigs.search(contig).isEmpty()){
					System.out.println("END OF FILE");
				}else{
					contigList.remove(i);
					// thought: Ok, so I remove the contig from the list, now how do I keep it going and not make it go into a loop?
					// keep it comin
				}
			}
			
			// List Every contigs
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
