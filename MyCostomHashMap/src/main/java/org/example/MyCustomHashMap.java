package org.example;

import java.util.Map;

public class MyCustomHashMap <K, V>{
    private int capacity = 16;
    private Entry [] myHashMap;
    public MyCustomHashMap(){
        myHashMap = new Entry[capacity];
    }

    public MyCustomHashMap(int capacity){
        this.capacity = capacity;
        myHashMap = new Entry[capacity];
    }


    public void put(K key, V value){
        int index = index(key);
        Entry newEntry = new Entry(key, value, null);
        if(myHashMap[index] == null){
            myHashMap[index] = newEntry;
        }else {
            Entry<K, V> previousNode = null;
            Entry<K, V> currentNode = myHashMap[index];
            while(currentNode != null){
                if(currentNode.getKey().equals(key)){
                    currentNode.setValue(value);
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if(previousNode != null)
                previousNode.setNext(newEntry);
        }
    }

    public V get(K key){
        V value = null;
        int index = index(key);
        Entry<K, V> entry = myHashMap[index];
        while (entry != null){
            if(entry.getKey().equals(key)) {
                value = entry.getValue();
                break;
            }
            entry = entry.getNext();
        }
        return value;
    }

    public void remove(K key){
        int index = index(key);
        Entry previous = null;
        Entry entry = myHashMap[index];
        while (entry != null){
            if(entry.getKey().equals(key)){
                if(previous == null){
                    entry = entry.getNext();
                    myHashMap[index] = entry;
                    return;
                }else {
                    previous.setNext(entry.getNext());
                    return;
                }
            }
            previous = entry;
            entry = entry.getNext();
        }
    }
    public void print(){
        for(int i = 0; i < capacity; i++){
            if(myHashMap[i] != null){
                Entry<K, V> currentNode = myHashMap[i];
                while (currentNode != null){
                   
                    System.out.println(currentNode.getKey() + "-" + currentNode.getValue());
                    currentNode = currentNode.getNext();
                }
            }
        }
    }

    private int index(K key){
        if(key == null){
            return 0;
        }
        return Math.abs(key.hashCode() % capacity);
    }

}
