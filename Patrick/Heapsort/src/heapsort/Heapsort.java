package heapsort;

import java.util.Arrays;
import java.util.Random;

public class Heapsort {
    public int[] content;
    
    private Random r;
    
    public Heapsort(int n, boolean sorted) {
        content = new int[n];
        if(sorted) {
            for(int i = 0; i < n; i++) {
                content[i] = i;
            }
        } else {
            r = new Random();
            for(int i = 0; i < n; i++) {
                content[i] = r.nextInt(3 * n) + 1;
            }
        }
    }
    
    public long sort() {
        long t = System.currentTimeMillis();
        
        int start = (content.length - 2) / 2;
        int end = content.length - 1;
        while(start >= 0) {
            seep(start, end);
            start--;
        }
        while(end > 0) {
            swap(end, 0);
            end--;
            seep(0, end);
        }
        
        return System.currentTimeMillis() - t;
    }
    
    private void swap(int a, int b) {
        int tmp = content[a];
        content[a] = content[b];
        content[b] = tmp;
    }
    
    private void seep(int start, int end) {
        int parent = start;
        int tmp;
        int child = parent * 2 + 1;
        while(child <= end) {
            if(content[parent] < content[child]) {
                tmp = child;
            } else {
                tmp = parent;
            }
            if((child + 1 <= end) && (content[tmp] < content[child + 1])) {
                tmp = child + 1;
            }
            if(tmp == parent) {
                break;
            } else {
                swap(parent, tmp);
                parent = tmp;
            }
            child = parent * 2 + 1;
        }
    }

    @Override
    public String toString() {
        return toString(content);
    }
    
    public static String toString(int[] arr) {
        String result = "";
        for(int i = 0; i < arr.length; i++) {
            result += arr[i] + "\t";
        }
        return result;
    }
   
    public static void main(String[] args) {
        Heapsort h = new Heapsort(10000000, false); //10000000
        //System.out.println(h.toString());
        int[] i = h.content.clone();
        System.out.print("Java merge sort: ");
        long t = System.currentTimeMillis();
        Arrays.sort(i); // Merge sort mit Insertion sort für card < 7
        System.out.println(System.currentTimeMillis() - t);
        System.out.print("Patrick: ");
        System.out.println(h.sort());
        //System.out.println(toString(i));
        //System.out.println(h.toString());
        if(!Arrays.equals(i, h.content)) {
            System.out.println("Die Arrays sind verschieden!");
        }
        System.out.print("Erwin: ");
        erwin e = new erwin(10000000);
        t = System.currentTimeMillis();
        e.heapify();
        e.sort();
        System.out.println(System.currentTimeMillis() - t);
    }
}
