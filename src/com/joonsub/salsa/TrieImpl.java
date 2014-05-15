package com.joonsub.salsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrieImpl<T> implements Trie<T>{
    private TrieNode<T> rootNode = new TrieNode<T>();

    private void addNode(TrieNode<T> currNode, String key, int pos, T value) {
        Character c = key.charAt(pos);
        TrieNode<T> nextNode = currNode.getChildren().get(c);
        currNode.hit++;
         
        if (nextNode == null) {
            nextNode = new TrieNode<T>();
            nextNode.setNodeKey(c);
            if (pos < key.length() - 1) {
                addNode(nextNode, key, pos + 1, value);
            } else {
                nextNode.setNodeValue(value);
                nextNode.setTerminal(true);
                
            }
            currNode.getChildren().put(c, nextNode);
        } else {
            if (pos < key.length() - 1) {
                addNode(nextNode, key, pos + 1, value);
            } else {
                nextNode.setNodeValue(value);
                nextNode.setTerminal(true);
            }
        }
    }
    
    private void getValuesFromNode(TrieNode<T> currNode, List<T> valueList) {
        if (currNode.isTerminal()) {
            valueList.add(currNode.getNodeValue());
        }
         
        Map<Character, TrieNode<T>> children = currNode.getChildren();
        Iterator childIter = children.keySet().iterator();
        while (childIter.hasNext()) {
            Character ch = (Character)childIter.next();
            TrieNode<T> nextNode = children.get(ch);
            getValuesFromNode(nextNode, valueList);
        }
    }
     
    private void getKeysFromNode(TrieNode<T> currNode, String key, Set keySet) {
        if (currNode.isTerminal()) {
            keySet.add(key);
        }
         
        Map<Character, TrieNode<T>> children = currNode.getChildren();
        Iterator childIter = children.keySet().iterator();
        while (childIter.hasNext()) {
            Character ch = (Character)childIter.next();
            TrieNode<T> nextNode = children.get(ch);
            String s = key + nextNode.getNodeKey();
            getKeysFromNode(nextNode, s, keySet);
        }
    }
    
    private T findKey(TrieNode<T> currNode, String key) {
        Character c = key.charAt(0);
        if (currNode.getChildren().containsKey(c)) {
            TrieNode<T> nextNode = currNode.getChildren().get(c);
            if (key.length() == 1) {
                if (nextNode.isTerminal()) {
                    return nextNode.getNodeValue();
                }
            } else {
                return findKey(nextNode, key.substring(1));
            }
        }         
        return null;
    }
    
    private boolean hasKey(TrieNode<T> currNode, String key) {
        Character c = key.charAt(0);
        if (currNode.getChildren().containsKey(c)) {
            TrieNode<T> nextNode = currNode.getChildren().get(c);
            if (key.length() == 1) {
                if (nextNode.isTerminal()) {
                    return true;
                }
            } else {
                return hasKey(nextNode, key.substring(1));
            }
        }         
        return false;
    }
    
	@Override
	public void add(String key, T value) {
		// TODO Auto-generated method stub
        addNode(rootNode, key, 0, value);
	}

	@Override
	public T find(String key) {
		// TODO Auto-generated method stub
        return findKey(rootNode, key);
	}

	@Override
	public List<T> search(String prefix) {
        List<T> list = new ArrayList<T>();
         
        char[] ch = prefix.toCharArray();
        TrieNode<T> node = rootNode;
        for (int i = 0; i < ch.length; i++) {
            node = node.getChildren().get(ch[i]);
            if (node == null) {
                break;
            }
        }
         
        if (node != null) {
            getValuesFromNode(node, list);
        }
         
        return list;
    }

	@Override
	public boolean contains(String key) {
		// TODO Auto-generated method stub
        return hasKey(rootNode, key);
	}
	
	@Override
	public Set<String> getAllKeys() {
		// TODO Auto-generated method stub
        Set<String> keySet = new HashSet<String>();
        getKeysFromNode(rootNode, "", keySet);
         
        return keySet;
	}

	/**
	 * Returns the contigs as a list
	 * @param nothing
	 * 
	 */
	public List<String> getKeyList() {
		StringBuilder sb = new StringBuilder();
		sb.append(getAllKeys());
		if (sb.length() > 2){
			sb.deleteCharAt(0);
			sb.deleteCharAt(sb.length()-1);
		}
		String keys = sb.toString();
		List<String> items = new ArrayList<String>(Arrays.asList(keys.split("[\\s,]+")));
		
		/*
		 Iterator itr = items.iterator();
	      while(itr.hasNext()) {
	         Object element = itr.next();
	         System.out.print(element + " ");
	      }
	      System.out.println();
	      */
		
		return items;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
        return getAllKeys().size();
	}

}
