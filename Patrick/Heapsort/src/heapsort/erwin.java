package heapsort;

import java.util.Random;

public class erwin {
	Random r;
	int[] iArray ;

	public erwin(int n){
		iArray = new int[n];
		r= new Random();
		
		for(int i = 0; i < n ; i++)
		{
			iArray[i]= Math.abs(r.nextInt())%(3*n)+1;
		}
	}
	
	public void sort(){
		for(int i = iArray.length-1;i>0;i--)
		{
			swap(0,i);
			siftDown(0,i-1);
		}
	}
	
	public void heapify(){
		for(int i = iArray.length-1; i >= 0; i--) {
			siftDown(i,iArray.length-1);
		}
	}
	
	private void siftDown(int start,int end){
		int i =start;
		while(end>i*2+1)
		{
			if(iArray[i*2+1]>iArray[i*2+2] && iArray[i]<iArray[i*2+1])
			{
				swap(i,i*2+1);
				i=i*2+1;
			}
			else if(iArray[i]<iArray[i*2+2])
			{
				swap(i,i*2+2);
				i=i*2+2;
			}
			else
			{
				break;
			}
		}
		if(end == i*2+1)
		{
			if(iArray[i]<iArray[i*2+1])
			{
				swap(i,i*2+1);
				i=i*2+1;
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
