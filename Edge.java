/*
 * STUDENT ID:801027386
 * STUDENT NAME: ARUN KUNNUMPURAM THOMAS
 * FILE NAME: Edge.java
 * 
 */
import java.util.Comparator;

public class Edge {
   
	/*
	 * it contains the details of source vertex, destination vertex, cost of the edge, status of the edge
	 * it also contains the getter and setter for above variables and a comparator for sorting based on destination vertex name
	 * override the hashcode and equals, and two objects will match based on the  sourceVertex and destVertex
	 */
	String sourceVertex;
	String destVertex;
	double cost;
	boolean status=true;
	public String getSourceVertex() {
		return sourceVertex;
	}
	public void setSourceVertex(String sourceVertex) {
		this.sourceVertex = sourceVertex;
	}
	public String getDestVertex() {
		return destVertex;
	}
	public void setDestVertex(String destVertex) {
		this.destVertex = destVertex;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destVertex == null) ? 0 : destVertex.hashCode());
		result = prime * result + ((sourceVertex == null) ? 0 : sourceVertex.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (destVertex == null) {
			if (other.destVertex != null)
				return false;
		} else if (!destVertex.equals(other.destVertex))
			return false;
		if (sourceVertex == null) {
			if (other.sourceVertex != null)
				return false;
		} else if (!sourceVertex.equals(other.sourceVertex))
			return false;
		return true;
	}
    
	public static Comparator<Edge> edgeNameCompartor = new Comparator<Edge>() {

		public int compare(Edge Edge1, Edge Edge2) {
		
		String edgeName1 = Edge1.getDestVertex();
		String edgeName2 = Edge2.getDestVertex();
		return edgeName1.compareTo(edgeName2);	
		}
	};
	
	
}
