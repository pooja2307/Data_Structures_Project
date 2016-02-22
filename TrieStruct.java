import java.util.*;

//Creating a Trie Structure
public class TrieStruct {
	ArrayList<TrieNode> nodeList=new ArrayList<TrieNode>();

	public TrieNode start=new TrieNode();

	//Traversing the structure
	public String traverse(String str)
	{
		int i=0;
		TrieNode current=start;                             //Cuurent node is the start node of the Trie
		for(i=0;i<str.length();i++)
		{
			if(current.leaf==true)
				break;
			else
			{
				if(Integer.parseInt(str.charAt(i)+"")==0)
				{
					current=current.left;
				}
				else
					current=current.right;
			}
		}
		String s=str.substring(0, i);
		
		return s;
		
	}

	//Compress function
    public void compress_function(TrieNode root_node)
{
	if(root_node==null)
	{
		return;
	}
	if(root_node.left!=null)
	{
	
		compress_function(root_node.left);
	}
		if(root_node.right!=null)
		{
			compress_function(root_node.right);
		}
	
	if(root_node.left==null && root_node.right!=null && root_node.right.leaf==true)
	{
		if(root_node.data==0)
		{
			root_node.parent.left=root_node.right;
			root_node.right.parent=root_node.parent;
		}
		else
		{
			root_node.parent.right=root_node.right;
			root_node.right.parent=root_node.parent;
		}
	}
	else if(root_node.left!=null && root_node.right==null && root_node.left.leaf==true)
	{
		if(root_node.data==0)
		{
			root_node.parent.left=root_node.left;
			root_node.left.parent=root_node.parent;
		}
		else
		{
			root_node.parent.right=root_node.left;
			root_node.left.parent=root_node.parent;
		}
	}
	else
	if(root_node.right!=null && root_node.left!=null && root_node.left.data==root_node.right.data && root_node.left.leaf==true && root_node.right.leaf==true)
	{
		if(root_node.data==0)
		{
			root_node.parent.left=root_node.left;
			root_node.left.parent=root_node.parent;
		}
		else
		{
			root_node.parent.right=root_node.left;
			root_node.left.parent=root_node.parent;
		}
	}
	
}

    
    public void add(String ip_addr,int data)  //add function for adding nodes
    {
 	TrieNode curr=start;
 	
 	for(int i=0;i<ip_addr.length()-1;i++)
 	{
 		int ch=Integer.parseInt(ip_addr.charAt(i)+"");
 		if(ch==0)
 		{
 			if(curr.left!=null)
 			{
 				curr=curr.left;
 			}
 			else
 			{
 				TrieNode temp=new TrieNode(0,curr);
 				curr.left=temp;
 				curr=curr.left;
 			}
 		}
 		else if(ch==1)
 		{
 			if(curr.right!=null)
 			{
 				curr=curr.right;
 			}
 			else
 			{
 				TrieNode temp=new TrieNode(1,curr);
 				curr.right=temp;
 				curr=curr.right;
 			}
 		}
 		
 		else
 		{
 			try
 			{
 				throw new Exception();
 			}catch(Exception e)
 			{
 				System.out.println("Invalid input");
 			}
 		}
 	}
 	
 	int b=Integer.parseInt(ip_addr.charAt(ip_addr.length()-1)+"");  // binary integer 
 	if(b==0)
 	{
 		TrieNode tempN=new TrieNode(data,curr);
 		tempN.leaf=true;
 		curr.left=tempN;
 	}
 	else if(b==1)
 	{
 		TrieNode tempN=new TrieNode(data,curr);
 		tempN.leaf=true;
 		curr.right=tempN;
 	}
 	
 }




}

