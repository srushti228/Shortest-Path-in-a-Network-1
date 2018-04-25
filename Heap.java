/*
 * STUDENT ID:801027386
 * STUDENT NAME: ARUN KUNNUMPURAM THOMAS
 * FILE NAME: Heap.java
 * 
 */
import java.util.Arrays;

public class Heap {

	/*
	 * storing the distance to each vertex in a double array and vertexHandler is a handler which holds the
	 * vertex name of each distance element in distance array. 
	 * ex: distance[0] will be the distance from source vertex to vertexHandler[0]
	 * 
	 */
	double[] distance;
	String[] vertexHandler;
	int currentSize;
	
	Heap(double distance[],String[] vertexHandler,int currentSize)
	{
		this.distance=distance;
		this.vertexHandler=vertexHandler;
		this.currentSize=currentSize;
		minBuildHeap();
	}
	/*
	 * this method is used to build the min heap from the given inputs.
	 */
	void minBuildHeap()
	{
		
		
		int i=(int)(currentSize)/2;
		while(i>=0)
		{
			
			minHeapify(i);
			i--;
		}
		//System.out.println("min is done"+distance+vertexHandler);
	}
	/*
	 * give the parent of an element
	 */
	int parent(int i)
	{
		return i/2;
	}
	/*
	 * give the left child of an element
	 */
	int left(int i)
	{
		return 2*i;
	}
	/*
	 * give the right child of an element
	 */
	int right(int i)
	{
		return (2*i)+1;
	}
	/*
	 * using to do the min heapification at index i
	 * handler also needs to update based on the swapping operations.
	 */
	void minHeapify(int i)
	{
		
		int left=left(i);
		int right=right(i);
		int min;
		if(left<currentSize && distance[left]<distance[i])
		{
			min=left;
		}
		else
		{
			min=i;
		}
		if(right<currentSize && distance[right]<distance[min])
		{
			min=right;
		}
		if(min !=i)
		{
			double temp=distance[min];
			distance[min]=distance[i];
			distance[i]=temp;
			
			//swapping the handler also
			
			String tempStr=vertexHandler[min];
			vertexHandler[min]=vertexHandler[i];
			vertexHandler[i]=tempStr;
			
			minHeapify(min);
			
		}
	}
	/*
	 * give the min in the heap array and do the minHeapify after exchanging the last element with distance[0]
	 * size will also reduce by 1.
	 * handler also needs to update based on the swapping operations.
	 */
	
	String extractMin() 
	{
		if(currentSize<0)
		{
			return "ERROR- heap underflow";
		}
		double min=distance[0];
		distance[0]=distance[currentSize-1];
		distance[currentSize-1]=distance[0];
		
		
		String minStr=vertexHandler[0];
		vertexHandler[0]=vertexHandler[currentSize-1];
		vertexHandler[currentSize-1]=vertexHandler[0];
		currentSize=currentSize-1;
		minHeapify(0);
		
		return minStr;
	}
	
	/*
	 * used when there is an updation the distance of a vertex from source vertex(min distance)
	 * 
	 */
	void decreseKey(String vertexName,double dist)
	{
		int index=Arrays.asList(vertexHandler).indexOf(vertexName);
		
		if(distance[index]<dist)
		{
			System.out.println("Error, New distance is higher than current distance");
		}
		else
		{
			int i=index;
			distance[index]=dist;
			while( i>=0 && distance[parent(i)]>distance[i])
			{
				double temp=distance[i];
				distance[i]=distance[parent(i)];
				distance[parent(i)]=temp;
				
				String tempStr=vertexHandler[i];
				vertexHandler[i]=vertexHandler[parent(i)];
				vertexHandler[parent(i)]=tempStr;
				i=parent(i);
			}
		}
		
	}
	
}
