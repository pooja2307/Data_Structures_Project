import java.util.ArrayList;
import java.util.HashMap;

class RNode
{
	  int prev;
	  String ip_addr;
	  String binaryIp_Addr;
	  TrieStruct routing_table;
	  
    HashMap<Integer,Integer> hmap=new HashMap<Integer,Integer>();
    ArrayList<Integer> adjacency_list=new ArrayList<Integer>();
    int node_id;
    int weight;
    boolean visited;
    
    public RNode(int n)
    {
    	
    	binaryIp_Addr="";
    	routing_table=new TrieStruct();
    	node_id=n;
    	weight=34608;
    	prev=-1;
    	ip_addr="";
    	visited=false;
    	
    }
    

	public void r_map(RNode node,int weight)
    {
		if(!this.adjacency_list.contains(node.node_id))
		{
		hmap.put(node.node_id, weight);
    	this.adjacency_list.add(node.node_id);
		}
    }

    
    
}
