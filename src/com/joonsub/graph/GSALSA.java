package com.joonsub.graph;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
		// Read in the Files that have been output.
		readLargerTextFile(FILE_NAME + "output.txt");
		
		//contigs.getAllKeys();
		
		// Create a List of Edges
		List<GEdge> edgeList = new ArrayList<GEdge>();
		
		// Put everything into a list -> contigList.
		List<String> contigList = (contigs.getKeyList());
		GEdge edges;
		
		// Greedy List
		List<GEdge> maxEdgeList = new ArrayList<GEdge>();
		
		// This is a temp solution
		int maxEdge = 0;	// We want to find all the max edges
		int location = 0; 	// Location of id;
		//int counter = 0; 	// we bump this up to know what part of contigList is max
		
		for (int i = 0; i < contigList.size(); i++){
			//System.out.println(contigList.get(i).toString());
			for (int j = 0; j < contigList.size(); j++){
				//if (i != j){	// We don't want to find weights of same node...
					edges = new GEdge(contigList.get(i),contigList.get(j));
					
					// found a new max, but not itself...
					if (edges.getWeight() != contigList.get(0).length() && edges.getWeight() > maxEdge ){
						maxEdge = edges.getWeight();
						location = j;
					}
					edgeList.add(edges);
				//}
			}
			// Put all the max edges into a separate List:greedyList --> choose max edges.
			maxEdgeList.add(edgeList.get(i*contigList.size()+location));
			
			// Reset maxEdge back to 0
			maxEdge = 0;
			// Reset location
			location = 1;
			
		}
		System.out.println(" ss  ");
		System.out.println(" ss  ");
		for (int i = 0; i < maxEdgeList.size(); i++){
			System.out.println(maxEdgeList.get(i).toString());
		}
		
		// Print out all the Edges
		for (int i = 0; i < edgeList.size(); i++){
			System.out.println(edgeList.get(i).toString());
		}
		
		StringBuilder sb = new StringBuilder();

		System.out.println("Max Edges");
		// Let's reconstruct based on the edges
		
		//Get lowest Edge Value
		int lowEdge = maxEdgeList.get(0).getWeight();
		int locEdge = 0;;
		for (int i = 0; i < maxEdgeList.size(); i++){
			if (lowEdge > maxEdgeList.get(i).getWeight()){
				lowEdge =  maxEdgeList.get(i).getWeight();
				locEdge = i;
			}
		}
		
		System.out.println(maxEdgeList.get(locEdge));
		
		//last node
		sb.append(reverseString(maxEdgeList.get(locEdge).getNode1()));
		
		StringBuilder sequence = new StringBuilder(maxEdgeList.get(locEdge).getNode1());
		
		//remove it from list...
		maxEdgeList.remove(locEdge);
		
		// build the string up
		while(maxEdgeList.size() > 0){
			//System.out.println("maxEdgeList:" + maxEdgeList.size() + " for seq:" + sequence);
			for(int i = 0; i<maxEdgeList.size(); i++){
				System.out.println(">> maxEdgeList:" + maxEdgeList.size() + " for seq:" + sequence);
				// find sequence matching the second
				if (sequence.toString().equals(maxEdgeList.get(i).getNode2())){
					// remember the location we matched
					locEdge = i;
					lowEdge = maxEdgeList.get(i).getWeight();
					
					System.out.println(maxEdgeList.get(i).getNode1().toString().substring(0,
							maxEdgeList.get(i).getNode1().toString().length() - lowEdge));
					
					// add the next sequences
					sb.append(reverseString(maxEdgeList.get(i).getNode1().toString().substring(0,
							maxEdgeList.get(i).getNode1().toString().length() - lowEdge)));
					
					break;
				}
			}
			System.out.println(maxEdgeList.get(locEdge).getNode1());
			//clear sequence
			sequence.setLength(0);
			sequence.append(maxEdgeList.get(locEdge).getNode1());
//			System.out.println("\t "+sequence);
			
			// fix this. We want to reduce the maxEdgeList...
			maxEdgeList.remove(locEdge);
		}

		System.out.println("writing...");
		//System.out.println(reverseString(sb.toString()));

		writeLargerTextFile(FILE_NAME + "re.txt", reverseString(sb.toString()));
		System.out.println("done");
	}
	
	public static void writeLargerTextFile(String aFileName, String aLines) throws IOException {
		Path path = Paths.get(aFileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
				writer.write(aLines);
				writer.newLine();
		}
	}
	
	public static String reverseString(String s){
		StringBuilder sb = new StringBuilder();
		int i = s.length()-1;
		while (i >= 0){
			sb.append(s.charAt(i));
			i--;
		}
		return sb.toString();
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
