 /*Siralak    Teekha                 6213133
  Weerawich  Wongchatchalikun       6213166
  Korawit    Wisetsuwan 	    6213192*/
import java.util.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
public class Mygraph {
    
    
    private Graph<Integer, DefaultWeightedEdge> G;
    
    public Mygraph( Graph<Integer, DefaultWeightedEdge> temp) {
        G = temp;

    }
   

  
    
    public List<Integer> FindShortestPath(int key1 ,int key2)
    {
	

	// source and target must exist, otherwise error
	if (G.containsVertex(key1) && G.containsVertex(key2))
	{
            ShortestPathAlgorithm<Integer, DefaultWeightedEdge> shpath = null;
            
            try // Error if negative weight exists
            {
                shpath = new DijkstraShortestPath<>(G);
                shpath.getPath(key1, key2);                   // dummy instruction to check exception
            }
            catch(IllegalArgumentException e) { System.out.println(e); }
            
            
            if (shpath.getPath(key1, key2) != null)
            {
                //System.out.printf("\nTotal edges = %d\n", shpath.getPath(key1, key2).getLength());
                List<Integer> allNodes = shpath.getPath(key1, key2).getVertexList();
              
                return allNodes;
            }
            else
                System.out.printf("\nPath from %s to %s doesn't exist\n", key1, key2);            
	}
        return null;
    }
        public Graph<Integer, DefaultWeightedEdge> getG(){
        return G;
     }
    
    
}
