package heapsort;

import java.util.ArrayList;
import java.util.Random;

public class Heapsort {
    public int[] content;
    public ArrayList<Command> orders;
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
        orders = new ArrayList<Command>(n);
        orders.add(new Command(content, 0, n - 1, Instruction.Init));
    }
    
    public ArrayList<Command> sort() {        
        int start = (content.length - 2) / 2;
        int end = content.length - 1;
        while(start >= 0) {
            seep(start, end);
            start--;
        }
        while(end > 0) {
            swap(end, 0);
            orders.add(new Command(content, end, 0, Instruction.Finish));
            end--;
            seep(0, end);
        }
        orders.add(new Command(content, 0, content.length - 1, Instruction.End));
        return orders;
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
            orders.add(new Command(content, parent, child, Instruction.ComparisonParent));
            if(content[parent] < content[child]) {
                tmp = child;
                orders.add(new Command(content, tmp, child + 1, Instruction.ComparisonFlat));
            } else {
                tmp = parent;
                orders.add(new Command(content, tmp, child + 1, Instruction.ComparisonParent));
            }
            if((child + 1 <= end) && (content[tmp] < content[child + 1])) {
                tmp = child + 1;
            }
            if(tmp == parent) {
                break;
            } else {
                swap(parent, tmp);
                orders.add(new Command(content, tmp, parent, Instruction.Swap));
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
   
   /* public static void main(String[] args) {
        Heapsort h = new Heapsort(200, false); //10000000
        System.out.print("Patrick: ");
        System.out.println(h.sort());
        System.out.println(h.orders);
    }*/
}
