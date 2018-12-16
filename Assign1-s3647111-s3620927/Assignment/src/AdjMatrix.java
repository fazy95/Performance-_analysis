import java.io.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{
	private int SIZE= 4000;
	private int infinity= 1000000000; 
	private int ind= 0;
	public int [][] adjmat;
	private HashMap<T,Integer> indexTo;
	private HashMap<Integer,T> indexAt;
	
    /**
     * Contructs empty graph.
     */
    public AdjMatrix() {
    	//Intializing empty graph
    	
    	indexTo = new HashMap<>();
    	indexAt = new HashMap<>();
    	adjmat = new int [SIZE][SIZE];
    	for(int i=0; i< SIZE; i++){
    		for(int j =0; i< SIZE; i++) {
    			adjmat[i][j]= 0;
    		}
    	 }	
    } // end of AdjMatrix()


    public void addVertex(T vertLable) {
    	if(this.indexTo.containsKey(vertLable))
    	{
    		System.out.println("Vertex already exists!");
    	}
    	else {
	    	this.indexTo.put(vertLable,ind);
	    	this.indexAt.put(ind,vertLable);
	    	ind++;
    	}
    } // end of addVertex()


    public void addEdge(T srcLabel, T tarLabel) {
    	ArrayList<T> Edge= new ArrayList<T>();
    	if(!this.indexTo.containsKey(srcLabel) || !this.indexTo.containsKey(tarLabel))
    	{
    		System.out.println("One of the vertex doesnot exist");
    	}
    	else {
	    	int indtosrc = indexTo.get(srcLabel);
	    	int indtotar= indexTo.get(tarLabel);
	    	adjmat[indtosrc][indtotar]= 1;	    	
	    	adjmat[indtotar][indtosrc]= 1;
    	}
    } // end of addEdge()


    public ArrayList<T> neighbours(T vertLabel) {
    	int indVert;
        ArrayList<T> neighbours = new ArrayList<T>();
        indVert= indexTo.get(vertLabel);
        for(int i=0; i<ind; i++) {
        	if(adjmat[indVert][i]== 1 ){
        		neighbours.add(indexAt.get(i));
        	}
        }
        return neighbours;
    } // end of neighbors()


    public void removeVertex(T vertLabel) {
    	int indVert;
        if (!this.indexTo.containsKey(vertLabel))
        {
        	System.out.println("Vertex doesnot exist");
        }else {
        	indVert= indexTo.get(vertLabel);
        	for(int i =0; i<ind; i++) {
        		adjmat[indVert][i]= 0;
        		adjmat[i][indVert]= 0;
        	}
        	this.indexTo.remove(vertLabel);
        	this.indexAt.remove(vertLabel);
        }
    }
    	 // end of removeVertex()


    public void removeEdge(T srcLabel, T tarLabel) {
       	if(!this.indexTo.containsKey(srcLabel)||!this.indexTo.containsKey(tarLabel))
    	{
    		System.out.println("One of the vertex doesnot exist");
    	}
       	else {
       		int indtosrc = indexTo.get(srcLabel);
	    	int indtotar= indexTo.get(tarLabel);
	    	adjmat[indtosrc][indtotar]= 0;	    	
	    	adjmat[indtotar][indtosrc]= 0;
       	}
    } // end of removeEdges()


    public void printVertices(PrintWriter os) {
    	for(T i: this.indexTo.keySet()) {
    		String key= i.toString();
    		os.println(key);
    	}
    } // end of printVertices()


    public void printEdges(PrintWriter os) {
        for (Entry<T,Integer> i: this.indexTo.entrySet()) {
        	 int indtosrc= i.getValue();
        	 for(Entry<T,Integer> j: this.indexTo.entrySet()) {
        		 int indtotar= j.getValue();
        		 if(adjmat[indtosrc][indtotar] ==1) {
        			 os.println(i.getKey() +" "+ j.getKey());        			 
        		 }
        	 }
        }
    } // end of printEdges()


    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
    	int intialDis[]= new int [ind];
    	int indtosrc= this.indexTo.get(vertLabel1);
    	Boolean visted[]= new Boolean[ind];
    	int indtotar= this.indexTo.get(vertLabel2);
    	long startTime= System.nanoTime();
    	
    	for(int i=0; i<ind; i++) {
    		intialDis[i]= infinity;
    		visted[i]= false;   		
    	}
    	
    	intialDis[indtosrc]= 0;
    	
    	for(int incre= 0; incre< ind-1; incre++) {
        	int indmin= -1;
        	int min= infinity;
        	
        	for(int i= 0; i< ind; i++ ) 
        		if(visted[i]== false && intialDis[i] <= min) {
        			indmin= i;
        			min= intialDis[i];
        		}
        	int j= indmin;
        	visted[j]= true;
        	
        	for(int i= 0; i< ind; i++)
        		if(adjmat[j][i]!= 0 && intialDis[j]+ adjmat[j][i] < intialDis[i] && intialDis[j] != infinity && !visted[i])
        			intialDis[i]= intialDis[j]+ adjmat[j][i];
    	}
    	if(intialDis[indtotar]> 0 && intialDis[indtotar]!= infinity) {
	    	long endTime= System.nanoTime();
	    	long Dur= endTime- startTime;
	    	System.out.println(Dur);
    		return intialDis[indtotar];
    	}
    	else {
    		
            return disconnectedDist;
    	}
    	

        // if we reach this point, source and target are disconnected

    } // end of shortestPathDistance()

} // end of class AdjMatrix