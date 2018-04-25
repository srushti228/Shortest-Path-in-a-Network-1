/*
 * STUDENT ID:801027386
 * STUDENT NAME: ARUN KUNNUMPURAM THOMAS
 * FILE NAME: Vertex.java
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Vertex {
	/*
	 * contains the details of vertex name, vertex status, list of outgoing edges, distance and predecessor
	 * comparator for sorting based on vertex name
	 */
	String vertexName;
	boolean status=true;
	ArrayList<Edge> outwardEdges= new ArrayList<Edge>();
	double distance;
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public Vertex getPredecessor() {
		return predecessor;
	}
	public void setPredecessor(Vertex predecessor) {
		this.predecessor = predecessor;
	}
	Vertex predecessor;
	Vertex(String name)
	{
		vertexName=name;
	}
	public String getVertexName() {
		return vertexName;
	}
	public void setVertexName(String vertexName) {
		this.vertexName = vertexName;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public ArrayList<Edge> getOutwardEdges() {
		Collections.sort(outwardEdges,Edge.edgeNameCompartor);
		return outwardEdges;
	}
	public void setOutwardEdges(ArrayList<Edge> outwardEdges) {
		this.outwardEdges = outwardEdges;
	}
	public static Comparator<Vertex> vertexNameCompartor = new Comparator<Vertex>() {

		public int compare(Vertex vertex1, Vertex vertex2) {
		
		String vertexName1 = vertex1.getVertexName();
		String vertexName2 = vertex2.getVertexName();
		return vertexName1.compareTo(vertexName2);
			
		}
	};
}