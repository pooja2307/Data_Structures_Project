import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class routing {
	public static RNode[] arr;
	public static fibonacci_heap f_heap;
	public static String file_name,file2_name;
	public static int start_node,end_node;
	
	public static void main(String args[])throws Exception
	{	
		file_name=args[0];
		file2_name=args[1];
		
		BufferedReader buffer=new BufferedReader(new FileReader(file_name));
		BufferedReader buffer_2=new BufferedReader(new FileReader(file2_name));
		
		start_node=Integer.parseInt(args[2]);
		end_node=Integer.parseInt(args[3]);
		
		int num_vertices,num_edges;
		
		int flag=0;
		String str=null;
		arr=new RNode[1];
		
		while ((str = buffer.readLine()) != null)
		    {
			 if(!str.equals(""))
			 {
			 	
		      String new_Arr[];
		        new_Arr=str.split(" ");
		        if(flag==0)
		        {
		         num_vertices=Integer.parseInt(new_Arr[0]);	
		         arr=new RNode[num_vertices];
		         for(int i=0;i<num_vertices;i++)
		         {
		           	arr[i]=new RNode(i);
		           			        		        	
		         }
		         num_edges=Integer.parseInt(new_Arr[1]);
		         flag=1;
		        }
		        else
		        {
		        	int x=Integer.parseInt(new_Arr[0]);
		        	int y=Integer.parseInt(new_Arr[1]);
		        	int weight=Integer.parseInt(new_Arr[2]);
		        	
		        	arr[x].r_map(arr[y], weight);
		        	arr[y].r_map(arr[x], weight);
		        }
		        
			 } 
		    }
		 buffer.close();
		 int curr_counter=0;
		 while ((str = buffer_2.readLine()) != null)
		 {
		 if(!str.equals(""))
		{
				
		 arr[curr_counter].ip_addr=str;
		 String make_bin[];
		 make_bin=str.split("\\.");
		 for(int j=0;j<make_bin.length;j++)
				 {
					 
					 make_bin[j]=Integer.toBinaryString(Integer.parseInt(make_bin[j]));
					 
					 if(make_bin[j].length()<8)
					 {
						 int n=8-make_bin[j].length();
						 for(int m=0;m<n;m++)
						 {
							 make_bin[j]="0"+make_bin[j];
						 }
					 }
				 }
				 for(int y=0;y<make_bin.length;y++)
				 {
					 arr[curr_counter].binaryIp_Addr+=make_bin[y];
				 }
				 curr_counter++;
			 }
		 }
		 buffer_2.close();		
		 RNode currentNode=arr[start_node];
		 dijkstra_r(arr[start_node]);
		 System.out.println(arr[end_node].weight);
		 
		 ArrayList<Integer> al=new ArrayList<Integer>();
		 
		 al.add(end_node);
		 RNode cTnode=arr[end_node];
		 
		 while(cTnode.prev!=-1)
		 {
			 al.add(cTnode.prev);
			 cTnode=arr[cTnode.prev];
		 }
		for(int q=al.size()-1;q>0;q--)
		{
			reset();
			RNode currNode=arr[al.get(q)];
			dijkstra_r(currNode);
			for(int j=0;j<arr.length;j++)
			{
				if(arr[j]!=currNode)
				{
					
					String ipStr=arr[j].binaryIp_Addr;
					RNode r=arr[j];
					while(arr[r.prev].prev!=-1)
					{
						r=arr[r.prev];
					}
					currNode.routing_table.add(ipStr, r.node_id);
				}
			}
			currNode.routing_table.compress_function(currNode.routing_table.start);
			System.out.print(currNode.routing_table.traverse(arr[end_node].binaryIp_Addr)+" ");
		}
		 //long endTime=System.currentTimeMillis();
		//Take note of time here.
	}
	
	//Dijkstra Implementation for RNodes
		public static void dijkstra_r(RNode node)throws Exception
		{
			
			f_heap=new fibonacci_heap();
			Fibo_Node fn=new Fibo_Node();
			fn.value=0;
			fn.fibo_node_id=node.node_id;
			node.weight=0;
			f_heap.addNode(fn);

			for(int a=0;a<node.adjacency_list.size();a++)
			{
			   
				fn=new Fibo_Node();
				fn.value=node.hmap.get(node.adjacency_list.get(a));
				
				arr[node.adjacency_list.get(a)].weight=fn.value;
				arr[node.adjacency_list.get(a)].prev=node.node_id;
				
				fn.fibo_node_id=arr[node.adjacency_list.get(a)].node_id;
				f_heap.addNode(fn);
			}
					
			 f_heap.deleteMinimumFunction();
			node.visited=true;
			
			int count1=0;
			while(f_heap.numOfNodes>0)
			{
				count1++;
			 Fibo_Node tStart=f_heap.start;
			 if(tStart==null)
			 {
				 System.out.println("Null Error:"+count1+" Number of nodes:"+f_heap.numOfNodes);
			 }	
			 RNode tNode=arr[tStart.fibo_node_id];		 
			 tNode.visited=true;

			 
		   int count=0;
			
			 for(int b=0;b<tNode.adjacency_list.size();b++)
			 {		
				RNode currentINode=arr[tNode.adjacency_list.get(b)];
				 if(currentINode.visited==false)
				 {
				 if(f_heap.fnode_map.containsKey(arr[tNode.adjacency_list.get(b)].node_id))
				 {
					Fibo_Node temp=new Fibo_Node();
					temp=f_heap.fnode_map.get(arr[tNode.adjacency_list.get(b)].node_id);
					int nVal=tStart.value+tNode.hmap.get(tNode.adjacency_list.get(b));
					if(temp.value > nVal)
					{
						currentINode.prev=tNode.node_id;				
					f_heap.decrease_key(temp,nVal);
					currentINode.weight=tStart.value+tNode.hmap.get(arr[tNode.node_id].adjacency_list.get(b));
						
					}
				 }
				 else
				 {
				Fibo_Node temp=new Fibo_Node();
				temp.value=tStart.value+tNode.hmap.get(arr[tNode.node_id].adjacency_list.get(b));
				arr[tNode.adjacency_list.get(b)].weight=tStart.value+tNode.hmap.get(arr[tNode.node_id].adjacency_list.get(b));
				temp.fibo_node_id=arr[arr[tNode.node_id].adjacency_list.get(b)].node_id;
				currentINode.prev=tNode.node_id;
			    f_heap.addNode(temp);
					}			
				 }			
			 }
		 f_heap.deleteMinimumFunction();
		}	
	}
	
		public static void reset()  //	Resets the values of the RNodes
	{
		for(int k=0;k<arr.length;k++)
		{
			arr[k].weight=32608;
			arr[k].visited=false;
			arr[k].prev=-1;
		}
	}
	
	
}
