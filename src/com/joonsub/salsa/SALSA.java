package com.joonsub.salsa;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class SALSA {
	
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
			
			System.out.println("\n\r");
			
			// Go thru every element
			StringBuilder reconstruct = new StringBuilder();	//actual string we want built
			
			// max strand
			int maxChar = 0;
			int maxID = -1;
			List<String> strandList = new ArrayList<String>();
			for (int i = 0; i < contigList.size(); i++){
				//add first
				reconstruct.append(contigList.get(i));
				//Reach till end
				getNextContig(contigList.get(i));

				System.out.println("fin " + i + "/"+contigList.size());
				//add it to the db
				strandList.add(reconstruct.toString() + strands.toString());
				
				if (maxChar < reconstruct.length() + strands.length()){
					maxChar = reconstruct.length() + strands.length();
					maxID = i;
				}
				
//				System.out.println(reconstruct.toString() + strands.toString());
				//reset
				reconstruct.setLength(0);
				strands.setLength(0);
			}
			
			
			
			//add first
//			reconstruct.append(contigList.get(0));
//			
//			//Reach till end
//			getNextContig(contigList.get(0));
//			
			
			// List Every contigs
			if (test){
				Iterator itr = strandList.iterator();
				while(itr.hasNext()) {
					Object element = itr.next();
					System.out.print(element + " ");
				}
			}
//			System.out.println("this: " + usedStrands.toString());
			System.out.println(contigs.size() + " The total size");
			System.out.println(maxID);
			System.out.println(strandList.get(maxID));
			System.out.println(maxChar);
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
		
		public static String getNextContig(String query){
			if (test)
				System.out.println("0: " + query);
			// substring one less :: QUERY
			String contigToBeSearched = query.substring(1, query.length());
			if (test)
				System.out.println("1: " + contigToBeSearched);
			// look for the contig :: search(-UERY)
			String foundContig = contigs.search(contigToBeSearched).toString();
			if (test)
				System.out.println("2: " + foundContig);
			// get last alphabet
			
			if (!foundContig.equals("[]")){
				neuclotide = foundContig.substring(foundContig.length()-2,foundContig.length()-1).charAt(0);
//				System.out.println("3: " + neuclotide);
				strands.append(neuclotide);
				
				usedStrands.add(foundContig); // add it to the list of goodies
				
				// format found :: [QUERY]
				foundContig = foundContig.substring(1,foundContig.length()-1);
//				System.out.println("4: " + foundContig);
				
				return getNextContig(foundContig);
			}
			//else if (query.length() > )
			else
				return null;
		}
		
}
