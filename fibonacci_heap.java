import java.util.*;


// Creating a structure for Fibonacci heap

public class fibonacci_heap {
	
	public HashMap<Integer,Fibo_Node> fnode_map=new HashMap<Integer,Fibo_Node>();
	Fibo_Node start;
	int numOfNodes;
	
	public fibonacci_heap()
	{
		numOfNodes=0;
	}
	
	//decreaseKey() function for a fibonacci heap
		public void decrease_key(Fibo_Node f_node, int val)
	{
		if(f_node.fibo_node_parent_number==-1 || val > f_node.getParent(this).value)
		{
			f_node.value=val;
		}
		else
		{			
			f_node.value=val;
			Fibo_Node curr=new Fibo_Node();
			curr=f_node.getParent(this);
			add_toTopLevel(f_node);
			
			//Now, till  the condition is met, move up in the heap
			
			while(curr.child_cut==true && curr.fibo_node_parent_number!=-1)
			{						
				Fibo_Node temp=new Fibo_Node();
				temp=curr;
				curr=curr.getParent(this);
				add_toTopLevel(temp);					
			}
			if(curr.fibo_node_parent_number!=-1)//non root element
				curr.child_cut=true;
		}
	}

		//Insert new nodes
		public void addNode(Fibo_Node f_Node)                              
		{
		  fnode_map.put(f_Node.fibo_node_id, f_Node);	
		  if(numOfNodes==0)
		  {		
			  numOfNodes=1;
			  start=f_Node;
			  start.l_neighbor= start.r_neighbor= f_Node;
			  
		  }
		  else
		  {
			  start.r_neighbor.l_neighbor=f_Node;
			  f_Node.r_neighbor=start.r_neighbor;
			  f_Node.l_neighbor=start;
			  start.r_neighbor=f_Node;
			
			  numOfNodes++;  
		  }
		  findNewMinimum();
		}
		
		
	public void add_toTopLevel(Fibo_Node node)   //add node to top level linked list
	{
		if(node.l_neighbor!=node)
		{
			node.l_neighbor.r_neighbor=node.r_neighbor;
			node.r_neighbor.l_neighbor=node.l_neighbor;
		}

		Fibo_Node parent=node.getParent(this);
		int par=parent.fibo_node_child_list.indexOf(node);
		if(par!=-1)
		{
		parent.fibo_node_child_list.remove(par);
		parent.fibo_node_Degree--;
		}
		
		start.l_neighbor.r_neighbor=node;
		node.l_neighbor=start.l_neighbor;

		start.l_neighbor=node;
		node.r_neighbor=start;	
		node.fibo_node_parent_number=-1;
		
					
	}



	// The function of : Delete minumum of the fibonacci heap
	public void deleteMinimumFunction()
	{
		
		numOfNodes--;
		//Only one node in the top level linked list
		if(start.l_neighbor==start && start.r_neighbor==start)
		{
			if(start.fibo_node_child_list.size()!=0)
			{
			start=start.fibo_node_child_list.get(0);
			Fibo_Node node1=start.r_neighbor;
			Fibo_Node node2=start;
			node2.fibo_node_parent_number=-1;
		
			while(node1!=node2)
			{
				node1.fibo_node_parent_number=-1;
				if(node1.value<start.value)
				{
					start=node1;
				}
				node1=node1.r_neighbor;
			}
			pairWiseCombine();
			}
			else
			{
				start=null;
			}
		}
		
		
		
		else
		{
			//multiple nodes in the top level linked list
			if(start.fibo_node_Degree>0)
			{
			start.l_neighbor.r_neighbor=start.fibo_node_child_list.get(0);
			start.fibo_node_child_list.get(0).l_neighbor=start.l_neighbor;
			start.r_neighbor.l_neighbor=start.fibo_node_child_list.get(start.fibo_node_Degree-1);
			start.fibo_node_child_list.get(start.fibo_node_Degree-1).r_neighbor=start.r_neighbor;
			}
			else
			{
				//remove the node if no children
				start.l_neighbor.r_neighbor=start.r_neighbor;
				start.r_neighbor.l_neighbor=start.l_neighbor;
			}
			
			start=start.r_neighbor;
			findNewMinimum();
			pairWiseCombine();
		}
		
	}
	

	public void pairWiseCombine()        //PairWiseCombine Operation Implementation
	{
		Fibo_Node node=start;
		Fibo_Node pointer =start.r_neighbor;
		int count=1;
		
		while(pointer!=node)
		{
			count++;
			pointer=pointer.r_neighbor;
		}
		pointer=start.r_neighbor;
	    
        HashMap<Integer,Fibo_Node> tracker=new HashMap<Integer,Fibo_Node>();
		tracker.put(start.fibo_node_Degree, start);
        for(int i=0;i<count-1;i++)
		{
			while(tracker.containsKey(pointer.fibo_node_Degree))
			{
				
				Fibo_Node node_1=tracker.get(pointer.fibo_node_Degree);
				Fibo_Node node_2=pointer;
				tracker.remove(pointer.fibo_node_Degree);
				
				if(node_1.value<=node_2.value)
				{					
					
					node_1.r_neighbor.l_neighbor=node_1.l_neighbor;
					node_1.l_neighbor.r_neighbor=node_1.r_neighbor;
					
					node_2.l_neighbor.r_neighbor=node_1;
					
					node_1.l_neighbor=node_2.l_neighbor;
					
					node_2.r_neighbor.l_neighbor=node_1;
					
					node_1.r_neighbor=node_2.r_neighbor;
					node_1.addNewChildToHeap(node_2);
					pointer=node_1;
					if(node_2==start)
					{
						start=node_1;
					}
					
				}
				else
				{
					node_1.l_neighbor.r_neighbor=node_1.r_neighbor;
					node_1.r_neighbor.l_neighbor=node_1.l_neighbor;
					node_2.addNewChildToHeap(node_1);
				}
			}
			tracker.put(pointer.fibo_node_Degree, pointer);
			pointer=pointer.r_neighbor;
		}		
		
	}
	
		public void findNewMinimum()
	{
	
		Fibo_Node node1=start;
		Fibo_Node node2=start.r_neighbor;

		node1.fibo_node_parent_number=-1;
		
		//Now, find sibling with the least value of data
		while(node2!=node1)               

		{	 
			node2.fibo_node_parent_number=-1;
			if(node2.value<start.value)
			{
				start=node2;
			}
			node2=node2.r_neighbor;
		}		
	}

	
}

