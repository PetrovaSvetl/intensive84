package org.example;

public class Main {
    public static void main(String[] args) {
        MyCustomHashMap<String, Integer> map = new MyCustomHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("ten", 10);
        map.put("seven", 7);
        map.print();
        map.remove("one");
        System.out.println("Remove element with key 'one' ");
        map.print();
        map.put("two", 22);
        System.out.println("Change element with key 'two'");
        map.print();
    }
}
