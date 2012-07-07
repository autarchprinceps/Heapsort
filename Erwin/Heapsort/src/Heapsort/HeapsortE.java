package heapsort;

import java.util.Date;
import java.util.Random;

public class HeapsortE {
	Random r;
	int[] iArray ;

	public HeapsortE(int n){
		iArray = new int[n];
		r= new Random();
		
		for(int i = 0; i < n ; i++)
		{
			iArray[i]= Math.abs(r.nextInt())%(3*n)+1;
		}
	}
	
	public static void main(String[]args){
		Date beginning = new Date();
		HeapsortE a = new HeapsortE(10000000);
		int count =0;
		a.heapify();
		a.sort();
		System.out.println(new Date().getTime()-beginning.getTime());
		/*
		for(int i=0;i<a.iArray.length;i++)
		{
			System.out.print(a.iArray[i]+" ");
			if(i==count)
			{
				System.out.println();
				count=(count+1)*2;
			}
		}
		*/
	}
	
	public void sort(){
		for(int i = iArray.length-1;i>0;)
		{
			swap(0,i);
			i--;
			siftDown(0,i);
		}
	}
	
	private void heapify(){
		for(int i = (iArray.length-2)/2; i >= 0; i--) {
			siftDown(i,iArray.length-1); 
		}
	}
	
	private void siftDown(int start,int end){
		int i =start;
		int child = i*2+1;
		
		abc : for(int y =0;true;)
		{
			for(int a =0;true;)
			{
				break abc;
			}
		}
		
		while(end>child)
		{
			if( iArray[child]>=iArray[child+1] && iArray[i]<iArray[child] )
			{
				swap(i,child);
				i=child;
			}
			else if(iArray[i]<iArray[child+1])
			{
				swap(i,child+1);
				i=child+1;
			}
			else
			{
				break;
			}
			child=i*2+1;
		}
		if(end == child)
		{
			if(iArray[i]<iArray[child])
			{
				swap(i,child);
				i=child;
			}
		}
	}
	
	private void swap(int a , int b){
		int temp;
		temp=iArray[a];
		iArray[a]=iArray[b];
		iArray[b]=temp;
	}
}
