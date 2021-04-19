package util;

import file.SaveToFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.PowerPlant;
import model.adt.Graph;
import model.adt.Graph.*;

public class Drive {
	
	
	private PowerPlant Majuba, Kendal, Kusile, Medupi, Tutuka,Lethabo;
	private PowerPlant[] stations;
	private Graph<String> graph;
        private int[] v ;
	//private Graph graph;
	private List<Vertex<String>> vertices= new ArrayList<Vertex<String>>();
	public List<Vertex<String>> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertex<String>> vertices) {
		this.vertices = vertices;
	}

	public List<Edge<String>> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge<String>> edges) {
		this.edges = edges;
	}
	private List<Edge<String>> edges= new ArrayList<Edge<String>>();
	
	/**
	 *  Constructor: init for stations and graph here
	 */
	public Drive() {
	
	}
/**
 * Create a node
 * @param Name
 * @param w
 */
	public  void populateVertex(String Name, int w) {
		
		  	
		//set up Graph to default
	Vertex<String> vMajuba = new Vertex<String>(Name,w);
	
// 
   vertices.add(vMajuba);   
    System.out.println("added vertex");		
	}
public String updatedG(){
	graph = new Graph<String>(vertices, edges);
	return graph.toString();
	
}
/**
 * Used to retrieve add an edge	
 * @param weight
 * @param s
 * @param dest
 */
	public void addEdge(int weight, Vertex<String> s, Vertex<String> dest) {
		
		Edge<String> path1 = new Edge<String>(220,s,dest);
		edges.add(path1);
	System.out.println("added edge");		
	}
	
	public Graph<String> getMyGraph()
	{
		return graph;
	}

	/**
	 * Set up graph
	 */
	public void defaultGraph() {
		Vertex<String> vMajuba = new Vertex<String>("Majuba",50);
                
		Vertex<String> vKendal = new Vertex<String>("Kendal", 45);
		Vertex<String> vKusile = new Vertex<String>("Kusile", 40);
		Vertex<String> vMedupi = new Vertex<String>("Medupi", 11);
		Vertex<String> vTutuka = new Vertex<String>("Kusile", 8);
		Vertex<String> vLethabo = new Vertex<String>("Lethabo",20);
         PowerPlant p1 = new PowerPlant("Majuba",30,333,444);
          PowerPlant p2 = new PowerPlant("Majuba",30,555,788);
           PowerPlant p3 = new PowerPlant("Majuba",30,255,906);
           PowerPlant[] myP = new PowerPlant[6];
           myP[0]=p1;
          myP[1]=p2;
          myP[2]=p3;
      
            SaveToFile ny=  new  SaveToFile();
         //   ny.addPowerPlant(myP);
           //   Supplier tt = new Supplier()
              
	    Edge<String> path1 = new Edge<String>(220,vMajuba,vKendal);	
	    Edge<String> path2 = new Edge<String>(258,vMajuba,vKusile);	
	    Edge<String> path3 = new Edge<String>(100,vKendal,vMedupi);	
	    Edge<String> path4 = new Edge<String>(360,vKusile,vTutuka);	
	    Edge<String> path5 = new Edge<String>(43,vKusile,vLethabo);	
	    
	    
	    //cost
	    CostVertexPair<String> pair = new  CostVertexPair<String>(200, vMajuba);
	    CostVertexPair<String> pair2 = new  CostVertexPair<String>(100, vKendal);
	    CostVertexPair<String> pair3 = new  CostVertexPair<String>(4, vKusile);
	    CostVertexPair<String> pair4 = new  CostVertexPair<String>(10, vMedupi);
	    CostVertexPair<String> pair5 = new  CostVertexPair<String>(220, vLethabo);
	    v= new int[5];
      
	    vertices.add(vMajuba);   
	    vertices.add(vKendal);   
	    vertices.add(vKusile);   
	    vertices.add(vMedupi);   
	    vertices.add(vTutuka);
	    
	    edges.add(path1);
	    edges.add(path2);
	    edges.add(path3);
	    edges.add(path4);
	    edges.add(path5);
	    graph = new Graph<String>(vertices, edges);
          //  SaveToFile saveG = new SaveToFile(graph.toString());         
	    System.out.print(graph.toString()+"/nSucceffull");
	}
	/**
	 * Retrieve cost
	 * @return total cost
	 */
	public String Route() {
		 
	       Vertex<String> src = graph.getVertices().get(0);
	    Vertex<String> dest = graph.getVertices().get(4);
	          
                  CalcPath.genPat(graph, src);

	         CostPathPair<String> p = CalcPath.Route(graph, src, dest);  
	         System.out.print(p);

			
			return CalcPath.getShortestPathTo(src).toString();}}
