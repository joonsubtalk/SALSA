package com.joonsub.generator;

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

public class Fragger {
	final static Charset ENCODING = StandardCharsets.UTF_8;
	final static String OUTPUT_FILE_NAME = ".\\resources\\";
	
	private static Random rg = new Random(System.currentTimeMillis());
	static List<String> fragments = new ArrayList<String>();
	
	public static Generator gen;
	public static int frag_length = 15;
	public static int reads = 20000;
	public static int seq_length = 100;
	
	public static void main(String[] args) throws IOException{
		gen = new Generator(seq_length);
		gen.getSequence();
		int randIndex;
		
		for (int i = 0; i < reads; i++) {
			randIndex = rg.nextInt(seq_length - frag_length + 1);
			fragments.add(gen.getSequence().substring(randIndex,randIndex+frag_length));
		}
		System.out.println("Sliced " + reads + " reads");
		writeLargerTextFile(OUTPUT_FILE_NAME + "output.txt", fragments);
		writeSeq(OUTPUT_FILE_NAME  + "seq.txt", gen.getSequence());
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
	
	public static void writeSeq(String aFileName, String seq) throws IOException {
		Path path = Paths.get(aFileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)) {
			writer.write(seq);
			writer.newLine();
		}
	}
}
