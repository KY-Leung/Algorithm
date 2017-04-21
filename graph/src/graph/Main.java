/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author JHNG
 */
public class Main {
    public static void main(String[] args) {
        int numOfVertices1, numOfVertices2, numOfVertices3, numOfEdges;
        
        numOfVertices1 = 5000;
        numOfVertices2 = 7500;
        numOfVertices3 = 10000;
        long startTime,timeTaken;  
        
        LinkedList<Integer> q = new LinkedList<Integer>();
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Edges");
        numOfEdges = sc.nextInt();
        
        Graph g1 = new Graph(numOfVertices1, numOfEdges);                            // Create 3 Graph classes
        Graph g2 = new Graph(numOfVertices2, numOfEdges);
        Graph g3 = new Graph(numOfVertices3, numOfEdges);
        
        int[][] distanceMatrix1 = new int[numOfVertices1][numOfVertices1];           // Create 5000 x 5000 matrixes; branch for each vertix
        int[][] predecessorMatrix1 = new int[numOfVertices1][numOfVertices1];
        
        int[][] distanceMatrix2 = new int[numOfVertices2][numOfVertices2];           // Create 10000 x 10000 matrixes; branch for each vertix
        int[][] predecessorMatrix2 = new int[numOfVertices2][numOfVertices2];
        
        int[][] distanceMatrix3 = new int[numOfVertices3][numOfVertices3];           // Create 20000 x 20000 matrixes; branch for each vertix
        int[][] predecessorMatrix3 = new int[numOfVertices3][numOfVertices3];
        
        for(int i=0; i < numOfVertices1; i ++){                                      // Fill up all matrixes with -1
            for(int j = 0; j < numOfVertices1; j++){
                distanceMatrix1[i][j] = -1;
                predecessorMatrix1[i][j] = -1;
            }   
        }
        
        for(int i=0; i < numOfVertices2; i ++){
            for(int j = 0; j < numOfVertices2; j++){
                distanceMatrix2[i][j] = -1;
                predecessorMatrix2[i][j] = -1;
            }      
        }
       
        for(int i=0; i < numOfVertices3; i ++){
            for(int j = 0; j < numOfVertices3; j++){
                distanceMatrix3[i][j] = -1;
                predecessorMatrix3[i][j] = -1;
            }      
        }
        
        generateGraph(g1);                                                          // Generate Adjacency List
        generateGraph(g2);
        generateGraph(g3);
        
        g1.setDistanceMatrix(distanceMatrix1);                                      // Store matrixes into Graph classes
        g1.setPredecessorMatrix(predecessorMatrix1);
        
        g2.setDistanceMatrix(distanceMatrix2);
        g2.setPredecessorMatrix(predecessorMatrix2);
        
        g3.setDistanceMatrix(distanceMatrix3);
        g3.setPredecessorMatrix(predecessorMatrix3);
        
        startTime = System.nanoTime();                                              // Time BFS
        for(int i = 0; i < numOfVertices1; i++){
            BFS(g1, i, q);
        }
        timeTaken = System.nanoTime() - startTime;
        System.out.println("BFS for Graph 1 (5,000 vertices): " + timeTaken + "ns");
        
        startTime = System.nanoTime();
        for(int i = 0; i < numOfVertices2; i++){
            BFS(g2, i, q);
        }
        timeTaken = System.nanoTime() - startTime;
        System.out.println("BFS for Graph 2 (7,500 vertices): " + timeTaken + "ns");
        
        startTime = System.nanoTime();
        for(int i = 0; i < numOfVertices3; i++){
            BFS(g3, i, q);
        }
        timeTaken = System.nanoTime() - startTime;
        System.out.println("BFS for Graph 3 (10,000 vertices): " + timeTaken + "ns");
        
        while(true){
            System.out.println("Enter source vertex");
            int source = sc.nextInt();

            System.out.println("Enter dest vertex");
            int dest = sc.nextInt();
            distanceMatrix1 = g1.getDistanceMatrix();
            distanceMatrix2 = g2.getDistanceMatrix();
            distanceMatrix3 = g3.getDistanceMatrix();
            predecessorMatrix1 = g1.getPredecessorMatrix();
            predecessorMatrix2 = g2.getPredecessorMatrix();
            predecessorMatrix3 = g3.getPredecessorMatrix();
            System.out.println("Shortest dist for Graph 1: "  + distanceMatrix1[source][dest]);
            int next = dest;
            while(next != source && predecessorMatrix1[source][next]!= -1){
                System.out.println("At vertex:" +  next);
                next = predecessorMatrix1[source][next];
            }
            System.out.println("Source:"+ source + " REACHED! \n");
            
            System.out.println("Shortest dist for Graph 2: " + distanceMatrix2[source][dest]);
            next = dest;
            while(next != source && predecessorMatrix2[source][next]!= -1){
                System.out.println("At vertex:" +  next);
                next = predecessorMatrix2[source][next];
            }
            System.out.println("Source:"+ source + " REACHED! \n");
            
            System.out.println("Shortest dist for Graph 3: " + distanceMatrix3[source][dest]);
            next = dest;
            while(next != source && predecessorMatrix3[source][next]!= -1){
                System.out.println("At vertex:" +  next);
                next = predecessorMatrix3[source][next];
            }
            System.out.println("Source:"+ source + " REACHED! \n");
        }
    }
    
    public static void generateGraph(Graph graph){
		int numOfVertices = graph.getVertices();
		int numOfEdges = graph.getEdges();
		LinkedList<Integer>[] al = new LinkedList[numOfVertices];
		Random rand = new Random();
		for(int i = 0;i<numOfVertices;i++){
			al[i] = new LinkedList<Integer>();
		}
		for(int i = 0;i<numOfEdges;i++){
			int num1 = rand.nextInt(numOfVertices);                        // Generate 2 random num from 0 to numOfVertices
			int num2 = rand.nextInt(numOfVertices);
			if(al[num1].contains(num2)){                                   // Exclude self-loops and parallel edges
				i--;
				continue;                       
			}else if(al[num2].contains(num1)){
				i--;                 
				continue;
			}
            if(num1 == num2){
            	i--;
            }else{
            	al[num1].add(num2);                                        // Connect both vertices with edge
                al[num2].add(num1);
            }
		}
		graph.setAdjacencyList(al);      
	}
    
    // Run BFS on every vertex as the starting vertex
    public static void BFS(Graph g, int s, LinkedList<Integer> q){
		int vertices = g.getAdjacencyList().length;                        // Get # of vertices
		LinkedList<Integer>[] al = g.getAdjacencyList();
		int [] distance = new int[vertices];                               // Int array to store the shortest path from source to each vertex.
        int [] previous = new int[vertices];
        for(int i = 0;i<vertices;i++){
			distance[i] = -1;                                              // To prevent unconnected vertex.
        }
        for(int i = 0;i<vertices;i++){
			previous[i] = -1;                                              // To prevent unconnected vertex.
		}
        int[][] distanceMatrix  = g.getDistanceMatrix();
        int[][] predecessorMatrix = g.getPredecessorMatrix();
                
		boolean [] mark = new boolean[vertices];                           // Store the marked vertices, reset in every iteration.
		
		q.add(s);			                                               // Enqueue current vertex
		mark[s] = true;                                                    // Mark as visited
                
		distance[s] = 0;                                                   // Mark as Step 0
                
		while(!q.isEmpty()){
			int front = (int) q.get(0);                                    // Get 1st vertex in q
			q.remove(0);                                                   // Dequeue 1st vertex in q
             
			for(int i = 0;i<al[front].size();i++){	                       // If node has neighbour
				int element = (int) al[front].get(i);   
				if(!mark[element]){                                        // If neighbour is not visited
                	distance[element] = distance[front] + 1;               // Mark Step
                    previous[element] = front;                             // Store previous step
                    predecessorMatrix[s][element] = previous[element];     // Store predecessor to predecessorMatrix
                    distanceMatrix[s][element] =  distance[element];       // Store Step to distanceMartix
                                        
					q.add(element);                                        // Enqueue connected vertix                  
					mark[element] = true;
                    // Continue iteration to find all neighbours
				}
			}               
		}                                   
	}
    /*
    public static int randomNum(){
    	Random rand = new Random();
    	return rand.nextInt(5000);
    }
    */
}
