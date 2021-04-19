package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import model.adt.Graph;
import model.adt.Graph.CostPathPair;
import model.adt.Graph.CostVertexPair;
import model.adt.Graph.Edge;
import model.adt.Graph.Vertex;
/**
 * 
 * @author Kubeka BS 217010763
 *
 */
public class CalcPath {
	
	private static Queue<CostVertexPair<String>> Visit ;
	static CostVertexPair<String> dblP;
	static CostVertexPair<String> dblT;
	private static CostVertexPair<String> dblF;
    private static ArrayList<Edge<String>> listT;
	private static int weight;
/**
 * 
 * @param myG graph used
 * @param src  defines start vertex
 * @param dest defines end vertex
 * @param routes defines a list of routes
 * @param weights defines the weight  
 * @return value representing route
 */
	protected static   CostPathPair<String> Route(Graph<String> myG, Vertex<String> src, Vertex<String> dest, HashMap<Vertex<String>, ArrayList<Edge<String>>> routes, HashMap<Vertex<String>, CostVertexPair<String>> weights){
				
			 //check each node in the graph
			 for (Vertex<String> n : myG.getVertices())
			 {
		            if (!n.equals(src)) {
		            	weights.put(n, new CostVertexPair<String>(Double.MAX_EXPONENT, n));
		     		   
		                                }
		            else {
		                weights.put(n, new CostVertexPair<String>(0, n));
		 		       
		            }
		       }
			 
			 //check each node in the graph
			 for (Vertex<String> n : myG.getVertices())
			 {
		            routes.put(n, new ArrayList<Edge<String>>());
			 }
			 
		Visit = new PriorityQueue<CostVertexPair<String>>();
	    Visit.add(weights.get(src));

	do {
        dblP = Visit.remove();
      Vertex<String> v = dblP.getVertex();

        // defines weights from this node to  other nodes which haven't been checked
        for (Edge<String> myE : v.getEdges())
        {
           dblT = weights.get(myE.getToVertex()); 
          dblF = weights.get(v); 
           weight = dblF.getCost() + myE.getCost();
           // required to remove the pair then input again
            if (weight < dblT.getCost())
            {
            	 Visit.remove(dblT);
                 dblT.setCost(weight);
                 Visit.add(dblT); 

               //keep track  of routes
                 listT = routes.get(myE.getToVertex()); 
                 listT.clear();
                 listT.addAll(routes.get(myE.getFromVertex()));
                 listT.add(myE);
            }
            else if (dblT.getCost() == Double.MAX_VALUE)			 
            {       
            	 Visit.remove(dblT); 
                 dblT.setCost(weight);
                 Visit.add(dblT);
                //keep track  of routes
                listT = routes.get(myE.getToVertex()); 
                listT.addAll(routes.get(myE.getFromVertex()));
                listT.add(myE);
				
            }
        }

        //close if route found
        if (dest!= null && v.equals(src)) {
             break;
        }
    } while (!Visit.isEmpty()); 

    if (dest== null) {
        return null;
    }else {
    	dblP = weights.get(dest);
        listT = routes.get(dest);
           return (new CostPathPair<String>(dblP.getCost(), listT));
    }
	
	}
	
	public static CostPathPair<String> Route(Graph<String> myG, Vertex<String> src, Vertex<String> dest) {
       
         HashMap<Vertex<String>, ArrayList<Edge<String>>> routes = new HashMap<Vertex<String>, ArrayList<Edge<String>>>();
        HashMap<Vertex<String>,CostVertexPair<String>> weights = new HashMap<Vertex<String>, CostVertexPair<String>>();
        return Route(myG, src, dest, routes, weights);
    }
	
	
      public  static void genPat(Graph<String> g,Vertex<String> myVertex){
 
	        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
	        vertexQueue.add(myVertex);
	        while (!vertexQueue.isEmpty()) {
	        	Vertex<String> v = vertexQueue.poll();

	            	// Visit each edge 
	            	for (Edge e : g.getEdges())
	            	{
	            		Vertex myV = e.getFromVertex();
                                Vertex pV = e.getToVertex();
	            		int weight = e.getCost();
	            		int distanceThroughS = v.getWeight() + weight;
	            		if (distanceThroughS < pV.getWeight()) {
	            				vertexQueue.remove(myV);
	            				pV.setWeight(distanceThroughS) ;
	            				myV = v;
	            				vertexQueue.add(myV);
	            		}
	            }
	        }
          
      }  
      
      public static List<Vertex> getShortestPathTo(Vertex target)
	{
		List<Vertex> path = new ArrayList<Vertex>();
		
	   
			path.add(target);
		Collections.reverse(path);
         
		return path;
		
	}
        
}
