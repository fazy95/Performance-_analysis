import java.io.*;
import java.util.*;
import java.util.Map.Entry;


/**
 * Incidence matrix implementation for the FriendshipGraph interface.
 *
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class IndMatrix <T extends Object> implements FriendshipGraph<T>
{
	//private int SIZE= 4000;
	//private int infinity= 1000000000; 
	private Integer ind= 0;
	public int [][] indmat;
	private HashMap<T,Integer> indexVert;
	private HashMap<IndEdgeList<T>,Integer> indexEdge;
    /**
     * Contructs empty graph.
     */
    public IndMatrix() {
        // Implement me!
    	indexEdge = new HashMap<>();
    	indexVert = new HashMap<>();
    	indmat = new int [0][0];
    /*	for(int i=0; i< ; i++){
    		for(int j =0; i< SIZE; i++) {
    			indmat[i][j]= 0;
    		}
    	}*/
    } // end of IndMatrix()


    public void addVertex(T vertLabel) {
        // Implement me!
    	if(!indexVert.containsKey(vertLabel)) {
	    	this.indexVert.put(vertLabel,(indmat.length));
	    	Sizeincre();
    	}
    } // end of addVertex()


    public void addEdge(T srcLabel, T tarLabel) {
    	if(!existEdge(srcLabel, tarLabel) && !srcLabel.equals(tarLabel)) {
    		IndEdgeList<T> edge = new IndEdgeList<>(srcLabel, tarLabel);
        	int Height= indmat[0].length;
    		indexEdge.put(edge, Height);
    		Sizeincre();
    		indmat[indexVert.get(srcLabel)][Height]= 1;
    		indmat[indexVert.get(tarLabel)][Height]= 1;
    	}
    	
    } // end of addEdge()


    public ArrayList<T> neighbours(T vertLabel) {
        ArrayList<T> neighbours = new ArrayList<T>();
        // Implement me!
        ind= indexVert.get(vertLabel);
        if(ind== null) {
        	return neighbours;
        }
        
        for(int m= 0; m< indmat[0].length; m++) {
        	if(indmat[ind][m]== 1) {
        		for(Entry<IndEdgeList<T>, Integer> i: indexEdge.entrySet()) {
        			if(i.getValue()== m) {
        				if(i.getKey().getsrcvertLabel().equals(vertLabel)) {
        					neighbours.add(i.getKey().gettarvertLabel());
        				}
        				else {
        					neighbours.add(i.getKey().getsrcvertLabel());
        				}
        			}
        		}
        	}
        }
        return neighbours;
    } // end of neighbours()


    public void removeVertex(T vertLabel) {
        // Implement me!
    if(indexVert.containsKey(vertLabel)) {
    	for(T i: indexVert.keySet()) {
    		if(existEdge(vertLabel, i))
    		{
    			removeEdge(vertLabel, i);
    			}
    		}
    	indexVert.remove(vertLabel);
    	newVert();
    	SizeDecre();
    	}
    } // end of removeVertex()


    public void removeEdge(T srcLabel, T tarLabel) {
        // Implement me!
    	if(indexVert.containsKey(srcLabel) && indexVert.containsKey(tarLabel) && existEdge(srcLabel, tarLabel)) {
    		Iterator<IndEdgeList<T>> i= indexEdge.keySet().iterator();
    		
    		while(i.hasNext()) {
    			IndEdgeList<T> k= i.next();
    			if(k.getsrcvertLabel()== (srcLabel) && k.gettarvertLabel()==(tarLabel) || k.getsrcvertLabel()==(tarLabel) && k.gettarvertLabel()==(srcLabel))
    			{
    				i.remove();
    				newEdg();
    			}
    		}
    	}
 
    } // end of removeEdges()
    
	public boolean existEdge(T srcLabel, T tarLabel) {
		IndEdgeList<T> check =new IndEdgeList<T>(srcLabel, tarLabel);
		for(IndEdgeList j: indexEdge.keySet()) {
			if (j.getsrcvertLabel().equals(check.getsrcvertLabel()) && j.gettarvertLabel().equals(check.gettarvertLabel()) || j.gettarvertLabel().equals(check.getsrcvertLabel()) && j.getsrcvertLabel().equals(check.gettarvertLabel())) {
				
				return true;
			}
		}
		return false;
		
	}


    public void printVertices(PrintWriter os) {
        // Implement me!
    	for(T i: indexVert.keySet()) {
    		String key= i.toString();
    		os.println(key);
    	}
    } // end of printVertices()


    public void printEdges(PrintWriter os) {
        // Implement me!
    	for(IndEdgeList<T> i: indexEdge.keySet()) {
    		os.println(i.OutputEdges());
    	}
    } // end of printEdges()

    	private void Sizeincre() {
    		int AryIncrement[][]= new int [indexVert.size()] [indexEdge.size()];
    		
    		for(int m= 0; m<indmat.length; m++) {
    			for(int n= 0; n< indmat[0].length; n++) {
    				AryIncrement [m][n]= indmat[m][n];
    			}
    		}
    		indmat= AryIncrement;
    		AryIncrement= null;
    	}

    public int shortestPathDistance(T vertLabel1, T vertLabel2) {
        // Implement me!
    	long startTime= System.nanoTime();
    	if(indexVert.containsKey(vertLabel1) && indexVert.containsKey(vertLabel2)) {
	    	T NowV;
	    	Queue<T> que= new LinkedList<T>();
	    	T Vert;
	    	Map<T,T> traversal= new HashMap<T,T>();
	    	ArrayList<T> Distance;
	    	que.add(vertLabel1);
	    	
	    	while(!que.isEmpty()) {
	    		NowV= que.poll();
	    		if(NowV.equals(vertLabel2))
	    		{
	    			Distance= new ArrayList<>();
	    			Vert= vertLabel2;
	    			while(Vert !=null) {
	    				Distance.add(Vert);
	    				Vert= traversal.get(Vert);
	    				//Distance.add(Vert);
	    			}
	    		Distance.remove(vertLabel1);

		    	long endTime= System.nanoTime();
		    	long Dur= endTime- startTime;
		    	System.out.println(Dur);
	    		return Distance.size();
	    		}
	    		for(T close: neighbours(NowV)) {
	    			if(traversal.containsKey(close) || traversal.containsValue(close)) {
	    				continue;
	    			}
	    			traversal.put(close, NowV);
	    			que.add(close);
	    		}
	    	}
	    	long endTime= System.nanoTime();
	    	long Dur= endTime- startTime;
	    	System.out.println(Dur);
	    }
    	

        // if we reach this point, source and target are disconnected
        return disconnectedDist;
    } // end of shortestPathDistance()
    
	private void SizeDecre() {
		int AryDecrement[][]= new int [indexVert.size()] [indexEdge.size()];
		
		for(int m= 0; m<AryDecrement.length; m++) {
			for(int n= 0; n< AryDecrement[0].length; n++) {
				AryDecrement [m][n]= indmat[m][n];
			}
		}
		indmat= AryDecrement;
		AryDecrement= null;
	}

    
    public void newEdg()
    {
    	int i= 0;
    		for(IndEdgeList<T> a: indexEdge.keySet()) {
    			
    			indexEdge.put(a, i);
    			i++;
    		}
    }
    
    public void newVert()
    {
    	int i= 0;
    		for(T a: indexVert.keySet()) {
    			
    			indexVert.put(a, i);
    			i++;
    		}
    }

} // end of class IndMatrix
