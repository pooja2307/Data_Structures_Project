import java.io.*;
import java.util.ArrayList;

public class ssp {
	
	public static fibonacci_heap fibonacci_heap;
	public static int start_node,end_node;
	
	public static RNode[] arr;
	public static String file_name;
	
	public static void main(String arguments[])throws Exception
	{	
		
		start_node=Integer.parseInt(arguments[1]);
		end_node=Integer.parseInt(arguments[2]);
		String str;
		
		
		file_name=arguments[0];
		BufferedReader bufferedReader=new BufferedReader(new FileReader(file_name));
				
		boolean flag=true; // flag checks if it is the first line
		int numberOfVertices,number_edges;
		
		arr=new RNode[1];
		
		 while ((str = bufferedReader.readLine()) != null)
		    {

			 if(!str.equals(""))
	    {
		String input_Arr[];
		input_Arr=str.split(" ");
		        
		    if(flag)
		    {
		    numberOfVertices=Integer.parseInt(input_Arr[0]);	
		    arr=new RNode[numberOfVertices];
		    for(int i=0;i<numberOfVertices;i++)
		    {
		  	arr[i]=new RNode(i);
		    }
		         number_edges=Integer.parseInt(input_Arr[1]);
		         flag=false;
		        }
		        
		        else
		        {
		        	int x=Integer.parseInt(input_Arr[0]);
		        	int y=Integer.parseInt(input_Arr[1]);
		        	int weight=Integer.parseInt(input_Arr[2]);
		        	arr[x].r_map(arr[y], weight);
		        	arr[y].r_map(arr[x], weight);
		
		        }
		        
			 } 
		    }
		 bufferedReader.close();
		 dijkstra_function(arr[start_node]);

		}
	
		// Implementing The Dijkstra's Algorithm
	public static void dijkstra_function(RNode rNode)throws Exception
	{

		createFiboHeap(rNode);		
		fibonacci_heap.deleteMinimumFunction();
		rNode.visited=true;


		while(fibonacci_heap.numOfNodes>0)
		{

		 Fibo_Node start=fibonacci_heap.start;		
		 RNode r_Node=arr[start.fibo_node_id];		 
		 r_Node.visited=true;

		 
		 if(r_Node.node_id==end_node)
		 {
			// Print the path if reached the end node 
			 System.out.println(r_Node.weight);
			 ArrayList<String> path=new ArrayList<>();
			 path.add(end_node+ " " );
			 RNode backtracker= r_Node;
			
			 while(backtracker.prev!=-1){
				 path.add(backtracker.prev+ " " );
				 backtracker=arr[backtracker.prev];
			 }
			 
			 System.out.print(start_node+" ");
			 //to reverse the LIFO nature of the path
			 for(int i =path.size()-1;i>=0;i--){
				 System.out.print(path.get(i) + " ");
			 }
			 break;
		 }

	   
		 for(int counter=0;counter<r_Node.adjacency_list.size();counter++)
		 {
			 
			
			RNode node=arr[r_Node.adjacency_list.get(counter)];
			 if(node.visited==false)
			 {
				 
			 if(fibonacci_heap.fnode_map.containsKey(arr[r_Node.adjacency_list.get(counter)].node_id))
			 {

				Fibo_Node f_Node=new Fibo_Node();
				f_Node=fibonacci_heap.fnode_map.get(arr[r_Node.adjacency_list.get(counter)].node_id);
				int newValue=start.value+r_Node.hmap.get(r_Node.adjacency_list.get(counter));
				
				if(f_Node.value > newValue)
				{
					node.prev=r_Node.node_id;
					fibonacci_heap.decrease_key(f_Node,newValue);

					node.weight=start.value+r_Node.hmap.get(arr[r_Node.node_id].adjacency_list.get(counter));
					
				}
			 }
			 else
			 {
				node.prev=r_Node.node_id;
				Fibo_Node f_Node=new Fibo_Node();
				f_Node.value=start.value+r_Node.hmap.get(arr[r_Node.node_id].adjacency_list.get(counter));
				arr[r_Node.adjacency_list.get(counter)].weight=start.value+r_Node.hmap.get(arr[r_Node.node_id].adjacency_list.get(counter));
				f_Node.fibo_node_id=arr[arr[r_Node.node_id].adjacency_list.get(counter)].node_id;
				fibonacci_heap.addNode(f_Node);
				
			 }
			
			 }
			
			
		 }

		 fibonacci_heap.deleteMinimumFunction();

		}	

		
	}
	
	//Creating Fibonacci Heap
	
	private static void  createFiboHeap(RNode rNode){
		Fibo_Node fheapNode=new Fibo_Node();
		fibonacci_heap=new fibonacci_heap();
		
		fheapNode.value=0;
		fheapNode.fibo_node_id=rNode.node_id;
		rNode.weight=0;
		fibonacci_heap.addNode(fheapNode);
		

		for(int i=0;i<rNode.adjacency_list.size();i++)
		{
			fheapNode=new Fibo_Node();
			fheapNode.value=rNode.hmap.get(rNode.adjacency_list.get(i));
			arr[rNode.adjacency_list.get(i)].weight=fheapNode.value;
			fheapNode.fibo_node_id=arr[rNode.adjacency_list.get(i)].node_id;
			fibonacci_heap.addNode(fheapNode);
		
		}
		
	}

}
