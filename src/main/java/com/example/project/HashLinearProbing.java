package com.example.project;

import java.util.Random;

public class HashLinearProbing {
    private int hsize; // tamano de la tabla hash
    private Persona[] buckets; // array que  representa la tabla hash
    private String AVAILABLE;
    private int size; // cantidad de elementos en la tabla hash

    public HashLinearProbing(int hsize) {
        this.buckets = new Persona[hsize];
        this.hsize = hsize;
        this.AVAILABLE = String.valueOf(Integer.MIN_VALUE);
        this.size = 0;
    }

    public int hashing(String key) {
    	int num = 0;
    	for(int i=0;i< key.length();i++){
    	      num += key.charAt(i);
    	     
    	    }
        int hash = num % hsize;
        if (hash < 0) {
            hash += hsize;
        }
        return hash;
    }

    public void insertHash(Persona key) {
        
        int hash = hashing(key.getDNI());

        if (isFull()) {
            System.out.println("Tabla hash esta llena!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] == null || buckets[hash].getDNI().equals(AVAILABLE)) {
                buckets[hash] = key;
                size++;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
    }

    public void deleteHash(String key) {
       
        int hash = hashing(key);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] != null && buckets[hash].getDNI().equals(key)) {
            	buckets[hash].setDNI(AVAILABLE);
                size--;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + key + " no encontrada");
    }

    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] == null || buckets[i].getDNI().equals(AVAILABLE)) {
                System.out.println("Celda " + i + ": Vacia");
            } else {
                System.out.println("Celda " + i + ": " + buckets[i].toString());
            }
        }
    }

    public String findHash(String key) {
        
        int hash = hashing(key);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return null;
        }

        for (int i = 0; i < hsize; i++) {
            try {
                if (buckets[hash].getDNI().equals(key)) {
                    
                    return buckets[hash].getNombre();
                }
            } catch (Exception E) {
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + key + " no encontrada!");
        return null;
    }    
   
    public boolean isFull() {        
        return size == hsize;
    }

    public boolean isEmpty() {
        boolean response = true;
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] != null) {
                response = false;
                break;
            }
        }
        return response;
    }

    public static void main (String[] args){
        //HashLinearProbing tb = new HashLinearProbing(10);

       // Random rd = new Random();

       // for(int i = 0; i < 5; i++){
            //tb.insertHash(rd.nextInt(100));
       // }

        //tb.displayHashtable();        
    }
}
