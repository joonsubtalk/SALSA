package com.joonsub.splicer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Splice {
	final static Charset ENCODING = StandardCharsets.UTF_8;
	final static String OUTPUT_FILE_NAME = ".\\resources\\";
	
	private static Random rg = new Random(System.currentTimeMillis());
	static List<String> fragments = new ArrayList<String>();
	
//	public static int frag_length = 15;
//	public static int reads = 20;
//	public static int seq_length = 50;
	
	public static int frag_length = 75;
	public static int reads = 2500;
	public String sequence;
	final static String FILE_NAME = ".\\resources\\";
	
	
	public static void main(String[] args) throws IOException{
		String sequence = readLargerTextFile(FILE_NAME + "seq.txt");
		int randIndex;
		
		for (int i = 0; i < reads; i++) {
			randIndex = rg.nextInt(sequence.length() - frag_length + 1);
			fragments.add(sequence.substring(randIndex,randIndex+frag_length));
		}
		System.out.println("Sliced " + reads + " reads");
		writeLargerTextFile(OUTPUT_FILE_NAME + "output.txt", fragments);
		System.out.println("done");
	}
	
	public static void writeLargerTextFile(String aFileName, List<String> aLines) throws IOException {
		Path path = Paths.get(aFileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
			for (String line : aLines) {
				writer.write(line);
				writer.newLine();
			}
		}
	}
	
	public static String readLargerTextFile(String aFileName) throws IOException  {
		Path path = Paths.get(aFileName);
		StringBuilder sb = new StringBuilder();
		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				// process each line in some way
				sb.append(scanner.nextLine());
			}
		}
		System.out.println("Files Loaded");
		return sb.toString();
	}
	
	public static void writeSeq(String aFileName, String seq) throws IOException {
		Path path = Paths.get(aFileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
			writer.write(seq);
			writer.newLine();
		}
	}
}
