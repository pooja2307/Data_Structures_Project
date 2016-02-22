public class TrieNode {
 boolean leaf;
 public TrieNode left,right,parent;
 public int data;
 
 public TrieNode()    // default constructor
 {
	 left=null;
	 right=null;
	 parent=null;
	 data=-1;
	 leaf=false;
 }
 
 //constructor with parameters of an integer value and an instance of trienode
 public TrieNode(int d, TrieNode p)        
 {
	 left=null;
	 right=null;
	 this.parent=p;
	 data=d;
	 leaf=false;
 }
}
