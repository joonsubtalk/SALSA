package com.joonsub.generator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Fragger {

	private static Random rg = new Random(System.currentTimeMillis());
	static Set<String> fragments = new HashSet<String>();
	public static Generator gen;
	public static int frag_length = 30;
	public static int reads = 15000;
	public static int seq_length = 500;
	
	public static void main(String[] args) throws IOException{
		gen = new Generator(seq_length);
		gen.getSequence();
		int randIndex;
		
		for (int i = 0; i < reads; i++) {
			randIndex = rg.nextInt(seq_length - frag_length);
			fragments.add(gen.getSequence().substring(randIndex,randIndex+frag_length));
		}
		
		writeLargerTextFile(OUTPUT_FILE_NAME + "output.txt", fragments);
		writeSeq(OUTPUT_FILE_NAME  + "seq.txt", gen.getSequence());
		System.out.println("done");
	}
	
	final static Charset ENCODING = StandardCharsets.UTF_8;
	final static String OUTPUT_FILE_NAME = ".\\resources\\";
	
	public static void writeLargerTextFile(String aFileName, Set<String> aLines) throws IOException {
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
