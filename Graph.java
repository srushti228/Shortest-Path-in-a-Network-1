/*
 * STUDENT ID:801027386
 * STUDENT NAME: ARUN KUNNUMPURAM THOMAS
 * FILE NAME: Graph.java
 * 
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Graph 
{

	/*
	 * it contains a vertices hashmap which holds the name of vertex and vertex object.
	 */
	private HashMap<String,Vertex> vertices= new HashMap<String,Vertex>();

	/*
	 * This method is used to add edges in both direction. If there is a duplicate edge ,then
	 * it will update the current cost
	 */

	public void addEdgeFromInputFile(String vertex1, String vertex2, double cost)
	{
		// checking vertex1 is existing in graph
		Vertex v1=getVertex(vertex1);
		// checking vertex1 is existing in graph
		Vertex v2=getVertex(vertex2);
		Edge v1ToV2= new Edge();
		v1ToV2.setSourceVertex(vertex1);
		v1ToV2.setDestVertex(vertex2);
		v1ToV2.setCost(cost);
		ArrayList<Edge> v1OutwaredEdges= v1.getOutwardEdges();
		if(v1OutwaredEdges.size() ==0)
		{
			v1.outwardEdges.add(v1ToV2);
		}
		else
		{
			if(v1.outwardEdges.contains(v1ToV2))
			{
				System.out.println("Duplicate Edge... Updating the cost !!!!");
				v1.outwardEdges.set(v1.outwardEdges.indexOf(v1ToV2), v1ToV2);
			}
			else
			{
				v1.outwardEdges.add(v1ToV2);
			}
		}

		Edge v2ToV1= new Edge();
		v2ToV1.setSourceVertex(vertex2);
		v2ToV1.setDestVertex(vertex1);
		v2ToV1.setCost(cost);
		ArrayList<Edge> v2OutwaredEdges= v2.getOutwardEdges();
		if(v2OutwaredEdges.size() ==0)
		{
			v2.outwardEdges.add(v2ToV1);
		}
		else
		{
			if(v2.outwardEdges.contains(v2ToV1))
			{
				System.out.println("Duplicate Edge... Updating the cost !!!!");
				v2.outwardEdges.set(v2.outwardEdges.indexOf(v2ToV1), v2ToV1);
			}
			else
			{
				v2.outwardEdges.add(v2ToV1);
			}
		}


	}

	/*
	 * This method is using to get the vertex from hashmap which is having the vertex details of the graph
	 * if there is no vertex present in the hashmap, it will create new one and put it in hashmap
	 * reference: quiz 8 file
	 */
	private Vertex getVertex( String vertexName )
	{
		Vertex v = vertices.get( vertexName );
		if( v == null )
		{
			v = new Vertex( vertexName );
			vertices.put( vertexName, v );
		}
		return v;
	}

	/*
	 * This method is used to build the graph from input file
	 * reference: quiz 8 file
	 */
	public void buildGraph(String network)
	{
		try
		{
			FileReader fin = new FileReader(network);
			Scanner graphFile = new Scanner( fin );

			// Read the edges and insert
			String line;
			while( graphFile.hasNextLine( ) )
			{
				line = graphFile.nextLine( );
				StringTokenizer st = new StringTokenizer( line );
				try
				{
					if( st.countTokens( ) != 3 )
					{
						System.err.println( "Skipping ill-formatted line " + line );
						continue;
					}
					String source  = st.nextToken( );
					String dest    = st.nextToken( );
					double cost    = Double.parseDouble(st.nextToken());

					//  System.out.println(source +" "+ dest+" "+cost);
					// g.addEdge( source, dest );
					addEdgeFromInputFile(source,dest,cost);
				}
				catch( NumberFormatException e )
				{ System.err.println( "Skipping ill-formatted line " + line ); }
			}
		}
		catch( IOException e )
		{ System.err.println( e ); }
	}

	/*
	 * This method is used to adding an edge from tail vertex to head vertex
	 * if edge is already present it will update the cost
	 * if the vertex is not present then it will use getVertex method to create one
	 */

	public void addedge(String dest, String source,double cost)
	{
		Vertex vertex=getVertex(source);
		Vertex destVertex=getVertex(dest);
		Edge edge= new Edge();
		edge.setSourceVertex(source);
		edge.setDestVertex(dest);
		edge.setCost(cost);
		ArrayList<Edge> outwardEdges= vertex.getOutwardEdges();
		if(outwardEdges.size() ==0)
		{
			vertex.outwardEdges.add(edge);
		}
		else
		{
			if(vertex.outwardEdges.contains(edge))
			{
				int index=vertex.outwardEdges.indexOf(edge);
				System.out.println("Duplicate Edge... Source: "+ edge.sourceVertex +" Dest: "+edge.destVertex +" Updating the cost !!!!");
				edge.status=vertex.outwardEdges.get(index).status;
				vertex.outwardEdges.set(index, edge);
			}
			else
			{
				vertex.outwardEdges.add(edge);
			}
		}

	}

	/*This method is used to delete an edge from tail vertex to head vertex
	 * 
	 */
	public void deletedge(String dest, String source)
	{

		if(vertices.containsKey(source) && vertices.containsKey(dest))

		{	
			Vertex vertex= vertices.get(source);
			Edge edge= new Edge();
			edge.setSourceVertex(source);
			edge.setDestVertex(dest);
			if(vertex.outwardEdges.contains(edge))
			{
				//  System.out.println("Deleting edge... Source: "+ edge.sourceVertex +" Dest: "+edge.destVertex);
				vertex.outwardEdges.remove(vertex.outwardEdges.indexOf(edge));
			}
		}
		else
		{
			System.out.println("Error !!! There is no source|destination vertex ");
		}
	}
	/*
	 * This method is used to mark an edge down
	 *  It will make the status of edge to false
	 */
	public void edgedown(String dest, String source)
	{
		if(vertices.containsKey(source) && vertices.containsKey(dest))

		{	
			Vertex vertex= vertices.get(source);
			Edge edge= new Edge();
			edge.setSourceVertex(source);
			edge.setDestVertex(dest);
			edge.status=false;
			if(vertex.outwardEdges.contains(edge))
			{
				int index=vertex.outwardEdges.indexOf(edge);
				edge.cost=vertex.outwardEdges.get(index).cost; 
				//  System.out.println("making Edge down... Source: "+ edge.sourceVertex +" Dest: "+edge.destVertex);
				vertex.outwardEdges.set(vertex.outwardEdges.indexOf(edge), edge);
			}
		}
		else
		{
			System.out.println("Error !!! There is no source|dest vertex with name ");
		}
	}
	/*
	 * This method is used to mark an edge up
	 *  It will make the status of edge to true
	 */
	public void edgeup(String dest, String source)
	{
		if(vertices.containsKey(source) && vertices.containsKey(dest))

		{	
			Vertex vertex= vertices.get(source);
			Edge edge= new Edge();
			edge.setSourceVertex(source);
			edge.setDestVertex(dest);
			edge.status=true;
			if(vertex.outwardEdges.contains(edge))
			{
				int index=vertex.outwardEdges.indexOf(edge);
				edge.cost=vertex.outwardEdges.get(index).cost;  
				// System.out.println("making Edge up... Source: "+ edge.sourceVertex +" Dest: "+edge.destVertex);
				vertex.outwardEdges.set(vertex.outwardEdges.indexOf(edge), edge);
			}
		}
		else
		{
			System.out.println("Error !!! There is no source|dest vertex with name ");
		}
	}
	/*
	 * This method is used to mark a vertex down
	 *  It will make the status of vertex to false
	 */
	public void vertexdown(String vertex)
	{
		if(vertices.containsKey(vertex))	 
		{
			Vertex vertexToDown= vertices.get(vertex);
			//	 System.out.println("making Vertex down... Vertex: "+ vertexToDown.vertexName);
			vertexToDown.status=false;

		}
		else
		{
			System.out.println("Error !!! There is no source vertex with name "+vertex);
		}
	}
	/*
	 * This method is used to mark a vertex up
	 *  It will make the status of vertex to true
	 */
	public void vertexup(String vertex)
	{
		if(vertices.containsKey(vertex))	 
		{
			Vertex vertexToDown= vertices.get(vertex);
			// System.out.println("making Vertex up... Vertex: "+ vertexToDown.vertexName);
			vertexToDown.status=true;

		}
		else
		{
			System.out.println("Error !!! There is no source vertex with name "+vertex);
		}
	}
	/*
	 * This method is used to print the graph- vertex in alphabetical order and its edges in
	 * alphabetical order
	 */
	public String print()
	{
		StringBuilder sb= new StringBuilder();
		HashMap<String,Vertex> hm1= vertices;
		ArrayList<Vertex> vertexList= new ArrayList<Vertex>();
		for (Map.Entry<String, Vertex> entry : hm1.entrySet()) 
			vertexList.add(entry.getValue()); 
		Collections.sort(vertexList,Vertex.vertexNameCompartor);

		for(Vertex vertex : vertexList)
		{


			// System.out.println("---------------------------------------------");
			if(!vertex.status)
			{
				//System.out.println(vertex.getVertexName()+ " DOWN");
				sb.append(vertex.getVertexName()+ " DOWN \n");

			}
			else
			{
				//System.out.println(vertex.getVertexName());
				sb.append(vertex.getVertexName()+ "\n");
			}
			//System.out.println("Edges!!!!!!!!!!!!");
			for(Edge e : vertex.getOutwardEdges())
			{
				if(!e.status)
				{
					//System.out.println("        "+e.getDestVertex()+ " "+e.getCost() + " DOWN");
					sb.append("        "+e.getDestVertex()+ " "+e.getCost() + " DOWN \n");
				}
				else
				{
					//System.out.println("        "+e.getDestVertex() +" "+e.getCost());
					sb.append("        "+e.getDestVertex()+ " "+e.getCost() + "\n");
				}
			}

			// System.out.println("---------------------------------------------");

			// ...
		}
		System.out.println(sb.toString());
		return sb.toString();

	}
	/*
	 * This method is find the shortest path from given source and destination veretx.
	 * This is implemented using Dijisktra algorithm and I am using min binary heap(Heap.java) for implementing min priority queue 
	 *
	 */
	public String shortestpath(String source,String dest)
	{
		boolean isDestFound=false;
		int count=0;
		HashMap<String,Vertex> hm1= vertices;
		//finding number of vertex
		for (Map.Entry<String, Vertex> entry : hm1.entrySet()) 
		{
			count++;
		}
		//declaring based on number of vertex
		double[] cost = new double[count];
		String[] vertex = new String[count];
		int noOfVertex=count;

		//initializing the distance to MAX
		int i=0;
		for (Map.Entry<String, Vertex> entry : hm1.entrySet()) 
		{

			vertex[i]=entry.getKey();
			entry.getValue().distance=Double.MAX_VALUE;
			cost[i]=Double.MAX_VALUE;
			i++;
		}

		int sourceIndex=Arrays.asList(vertex).indexOf(source);
		if(sourceIndex<0)
			return "Source vertex "+ source +" not found for the path \n";
		if(vertices.get(dest) ==null)
			return "Destination vertex "+ dest +" not found \n";
		vertices.get(source).distance=0.0;
		vertices.get(source).predecessor=null;
		cost[sourceIndex]=0.0;
		Heap heap= new Heap(cost, vertex, noOfVertex);
		//heap.decreseKey("Education",3.0);
		// System.out.println("current min"+heap.extractMin());
		ArrayList<String> completedVertices= new ArrayList<String>();
		completedVertices.add(source);
		while(noOfVertex>0)
		{
			String min=heap.extractMin();
			Vertex minVertex= vertices.get(min);
			completedVertices.add(min);
			if(minVertex.status)
			{
				ArrayList<Edge> edges=minVertex.getOutwardEdges();

				Edge [] edgesArray = edges.toArray(new Edge[edges.size()]);

				Iterator it= edges.iterator();
				for(int k=0;k<edgesArray.length;k++)
				{
					Edge edge=edgesArray[k];
					if(!(completedVertices.contains(edge.destVertex)) && vertices.get(edge.destVertex).distance>(minVertex.distance+edge.cost)&& edge.status)
					{
						String destVertexName=edge.destVertex;
						if(destVertexName.equals(dest))
							isDestFound=true;
						double newDist=minVertex.distance+edge.cost;;
						vertices.get(destVertexName).distance= newDist;
						vertices.get(destVertexName).predecessor=minVertex;
						heap.decreseKey(edge.destVertex, newDist);
					}

				}
			}

			noOfVertex--;
		}
		Vertex destinationVertex=vertices.get(dest);
		Vertex predecessor=destinationVertex.predecessor;
		StringBuilder path= new StringBuilder();
		BigDecimal bd = new BigDecimal(Double.toString(destinationVertex.distance));
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		path.append(bd.doubleValue() + " ");
		path.append(destinationVertex.getVertexName()+" ");
		while(predecessor !=null)
		{

			path.append(predecessor.getVertexName());
			path.append(" ");
			predecessor=predecessor.predecessor;
		}

		String[] arr = path.toString().split(" ");
		StringBuilder sb = new StringBuilder();
		for (int z = arr.length - 1; z >= 0; --z) {
			if (!arr[z].equals("")) {
				sb.append(arr[z]).append(" ");
			}
		}
		sb.append("\n");
		System.out.println(sb.toString());
        if(!isDestFound)
        	return "Path not found from "+source+ " to "+ dest+"\n";
		return sb.toString();
	}
	/*
	 * this method is used to find the reachable vertex from each vertex.
	 * This is implemented using BFS algorithm.
	 */
	public String reachable()
	{
		StringBuilder sb= new StringBuilder();
		int count=0;
		HashMap<String,Vertex> hm1= vertices;
		//finding number of vertex
		for (Map.Entry<String, Vertex> entry : hm1.entrySet()) 
		{
			count++;
		}
		String[] vertex = new String[count];
		int noOfVertex=count;
		int i=0;
		for (Map.Entry<String, Vertex> entry : hm1.entrySet()) 
		{

			vertex[i]=entry.getKey();
			i++;
		}

		Arrays.sort(vertex);
		for(int j=0;j<vertex.length;j++)
		{
			ArrayList<String> completedVertices= new ArrayList<String>();
			completedVertices.add(vertex[j]);
			if(!vertices.get(vertex[j]).status)
			{

				continue;
			}
			sb.append(vertex[j]+"\n");
			Queue<String> myQueue = new LinkedList<String>();
			ArrayList<String> reachableVertex= new ArrayList<String>();
			myQueue.add(vertex[j]);
			while(!myQueue.isEmpty())
			{
				Vertex sourceVertex= vertices.get(myQueue.poll());
				if(sourceVertex.status)
				{
					ArrayList<Edge> edges=sourceVertex.getOutwardEdges();
					Edge [] edgesArray = edges.toArray(new Edge[edges.size()]);
					Arrays.sort(edgesArray,Edge.edgeNameCompartor);
					for(int k=0;k<edges.size();k++)
					{
						Edge edge=edgesArray[k];


						if(!(completedVertices.contains(edge.destVertex)) && edge.status)
						{
							if(vertices.get(edge.destVertex).status)
							{
								reachableVertex.add("        "+edge.destVertex+"\n");
								myQueue.add(edge.destVertex);
							}
							completedVertices.add(edge.destVertex);

						}
					}

				}
			}
			Collections.sort(reachableVertex);
			for(String x : reachableVertex)
			{
				sb.append(x);
			}


		}

		System.out.println(sb.toString());
		return sb.toString();

	}
	/*
	 * this method is used to execute the queries in the given input file and writing the output to given 
	 * output file
	 */
	public void executeQuery(String queryFileName, String outputFileName)
	{
		try
		{

			FileReader fin = new FileReader(queryFileName);
			Scanner graphFile = new Scanner( fin );
			FileWriter fw= new FileWriter(outputFileName);
			BufferedWriter bw = new BufferedWriter(fw);

			// Read the edges and insert
			String line;
			while( graphFile.hasNextLine( ) )
			{
				line = graphFile.nextLine( );
				StringTokenizer st = new StringTokenizer( line );
				try
				{

					String operation  = st.nextToken( );
					String dest;
					String source;
					double cost;
					switch (operation) {
					case "print":
						String printOutPut=print();
						bw.write(printOutPut);
						bw.write("\n");
						break;
					case "path":
						source=st.nextToken( );
						dest=st.nextToken( );
						String pathOutput=shortestpath(source, dest);
						bw.write(pathOutput);
						bw.write("\n");
						break;
					case "reachable":
						String reachOutput=reachable();
						bw.write(reachOutput);
						bw.write("\n");
						break;
					case "edgeup":
						source=st.nextToken( );
						dest=st.nextToken( );
						edgeup(dest,source);
						break;
					case "edgedown":
						source=st.nextToken( );
						dest=st.nextToken( );
						edgedown(dest,source);
						break;
					case "vertexup":	
						String vertexToUp=st.nextToken( );
						vertexup(vertexToUp);
						break;
					case "vertexdown":
						String vertexToDown=st.nextToken( );
						vertexdown(vertexToDown);					
						break;
					case "deleteedge":
						source=st.nextToken( );
						dest=st.nextToken( );
						deletedge(dest, source);
						break;
					case "addedge":
						source=st.nextToken( );
						dest=st.nextToken( );
						cost=Double.parseDouble(st.nextToken( ));
						addedge(dest,source,cost);
						break;
					case "quit":    
						bw.close();
						System.exit(0);
					default:
						break;
					}
				}
				catch( NumberFormatException e )
				{ System.err.println( "Skipping ill-formatted line " + line ); }
			}

			bw.close();
		}
		catch( IOException e )
		{ System.err.println( e ); }

	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		if(args.length !=3)
		{
			System.out.println("Please pass all inputs <network,queries,outputfilename>");
			System.exit(0);
		}

		Graph g = new Graph();
		g.buildGraph(args[0]);
		g.executeQuery(args[1],args[2]);
	}

}
