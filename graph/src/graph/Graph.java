/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
import java.util.LinkedList;
/**
 *
 * @author JHNG
 */
public class Graph {
	private int numberOfVertices;
	private int numberOfEdges;
	
	private LinkedList<Integer>[] adjacencyList;
	private int[][] distanceMatrix;
    private int[][] predecessorMatrix;
	public Graph(int vertices, int edges){
		this.numberOfVertices = vertices;
		this.numberOfEdges = edges;
	}
	
	// Vertices & Edges
	public int getVertices(){return numberOfVertices;}
	public int getEdges(){return numberOfEdges;}
	
	// AdjacencyList
	public LinkedList<Integer>[] getAdjacencyList(){return adjacencyList;}
	public void setAdjacencyList(LinkedList<Integer>[] adjacencyList){ this.adjacencyList = adjacencyList;}
        
	// Distance Matrix
    public int[][] getDistanceMatrix(){return distanceMatrix;}
    public void setDistanceMatrix(int[][] distanceMatrix){this.distanceMatrix = distanceMatrix;}
     
	// Predecessor Matrix
	public int[][] getPredecessorMatrix(){return predecessorMatrix;}
	public void setPredecessorMatrix(int[][] predecessorMatrix){this.predecessorMatrix = predecessorMatrix;}
}
