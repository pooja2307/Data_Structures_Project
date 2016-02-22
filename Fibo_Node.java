import java.util.ArrayList;

// Creating the structure of a Fibonacci node
class Fibo_Node
{

	int fibo_node_parent_number;
	Fibo_Node l_neighbor, r_neighbor;
	int fibo_node_Degree;
	ArrayList<Fibo_Node> fibo_node_child_list=new ArrayList<Fibo_Node>();
	int value;  //data of the node
	int fibo_node_id;
	
	boolean child_cut;
    int pre_node=-1;
	
	
	public Fibo_Node()
	{
		
		value=Integer.MAX_VALUE;
		fibo_node_Degree=0;
		pre_node =-1;
		fibo_node_parent_number=-1;
		child_cut=Boolean.FALSE;
		l_neighbor=null;
		r_neighbor=null;
	}
	
	
	
	public void addNewChildToHeap(Fibo_Node fibo_node)
	{

		fibo_node.child_cut=false;
		fibo_node.fibo_node_parent_number=fibo_node_id;
		fibo_node_child_list.add(fibo_node);
		fibo_node_Degree=fibo_node_child_list.size();
		
		//Maintain the circular list after checking degree
		
		if(this.fibo_node_Degree==1)   
		{
		fibo_node.l_neighbor=fibo_node;
     	fibo_node.r_neighbor=fibo_node;
		}
		
		else if(this.fibo_node_Degree==2)
		{
		fibo_node.l_neighbor=fibo_node_child_list.get(0);
		fibo_node.r_neighbor=fibo_node_child_list.get(0);
	
		fibo_node_child_list.get(0).l_neighbor=fibo_node;
		fibo_node_child_list.get(0).r_neighbor=fibo_node;
		}
		else
		{
		
		int new_degree=fibo_node_Degree-2; 
		
		fibo_node.l_neighbor=fibo_node_child_list.get(new_degree);
		fibo_node_child_list.get(new_degree).r_neighbor=fibo_node;
		fibo_node.r_neighbor=fibo_node_child_list.get(0);
		fibo_node_child_list.get(0).l_neighbor=fibo_node;
		}
		
	}

	public Fibo_Node getParent(fibonacci_heap f)
	{
		return f.fnode_map.get(fibo_node_parent_number);
	}
	
}
