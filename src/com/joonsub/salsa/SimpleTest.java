package com.joonsub.salsa;

public class SimpleTest {
    static Trie<String> contigs = new TrieImpl<String>();
	
	public static void main(){
        contigs.add("1", "ACTGAG");
        contigs.add("2", "ATCAGA");
        contigs.getAllKeys();
	}
}

//public class TrieTest extends TestCase {
//     
//    public void setUp() throws Exception {
//    }
//     
//    public void testAdd() {
//        assertEquals(5, productTrie.size());
//    }
//     
//    public void testFind() {
//        assertNotNull(productTrie.find("ham"));
//    }
//     
//    public void testSearch() {
//        assertEquals(3, productTrie.search("ha").size());
//    }
//     
//    public void testContains() {
//        assertEquals(true, productTrie.contains("ipod"));
//    }
//     
//    public void testGetAllKeys() {
//        assertEquals(5, productTrie.getAllKeys().size());
//    }
//     
//    
//}
