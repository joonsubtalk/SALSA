package com.joonsub.salsa;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SALSA {
	
	//final static String FILE_SEQ = "output.txt";
		final static String FILE_NAME = ".\\resources\\";
		final static Charset ENCODING = StandardCharsets.UTF_8;
		public static Node root;
		
		public static void main(String[] args) throws IOException{
			root = new Node();
			readLargerTextFile(FILE_NAME + "output.txt");
		}
		
		public static void readLargerTextFile(String aFileName) throws IOException {
			Path path = Paths.get(aFileName);
			try (Scanner scanner = new Scanner(path, ENCODING.name())) {
				while (scanner.hasNextLine()) {
					// process each line in some way
					insertSequence(scanner.nextLine());
				}
			}
			System.out.println("Files Loaded");
		}
		
		public static void insertSequence(String s){
			char c;
			int cnt;
			for (int i = 0; i < s.length(); i++){
				c = s.charAt(i); 		// get's neuclotide
				Node n = new Node(c); 	// create a new Node
				n = root;
				cnt = 0;
				
				
				
				
			}
		}
}
