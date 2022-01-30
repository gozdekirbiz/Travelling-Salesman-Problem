package tsp2;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
public class RandomGraph 
{
		public int vertices;
	    public int edges;	  
	    Random random = new Random();
	    public List<List<Integer>> adjacencyList;
	  
	    public RandomGraph(int n)
	    {
	        this.vertices = n;
	        this.edges= random.nextInt(computeMaxEdges(vertices))+1;
	        adjacencyList = new ArrayList<>(vertices);
	    }
	    public List<List<Integer>> GraphYap()
	    {
	    	for (int i = 0; i < vertices; i++)
	            adjacencyList.add(new ArrayList<>());
	        for (int i = 0; i < edges; i++) 
	        {
	            int v = random.nextInt(vertices);
	            int w = random.nextInt(vertices);
	            if((v == w ) || adjacencyList.get(v).contains(w)) {
	                    i = i - 1;
	                    continue;
	            }
	            addEdge(v, w);
	        }
	        return adjacencyList;
	    }
	    int computeMaxEdges(int numOfVertices)
	    {
	        return numOfVertices * ((numOfVertices - 1) / 2);
	    }
	    void addEdge(int v, int w)
	    {

	        adjacencyList.get(v).add(w);
	        adjacencyList.get(w).add(v);
	    }
	    int[][] convert(List<List<Integer>> adj,int V)
	    {
	    	int [][]matrix = new int[V][V];

	    	for(int i = 0; i < V; i++)
	    	{
	    		
	    		for(int j : adj.get(i))
	    		{
	    			int weight=random.nextInt(25)+1;
	    			matrix[i][j] = weight;
	    			matrix[j][i]=weight;
	    		}
	                
	    	}
	    	return matrix;
	    }
	    Boolean isCyclicUtil(int v, Boolean visited[], int parent,RandomGraph g)
	    {
      
	    	visited[v] = true;
	    	Integer i;
	    	Iterator<Integer> it = g.adjacencyList.get(v).iterator();
	    	while (it.hasNext())
	    	{
	    		i = it.next();
           if (!visited[i])
           {
               if (isCyclicUtil(i, visited, v,g))
                   return true;
           }
           else if (i != parent)
               return true;
	       }
	       return false;
	    }

	   Boolean isCyclic(RandomGraph g)
	   {
	       
	       Boolean visited[] = new Boolean[g.vertices];
	       for (int i = 0; i < g.vertices; i++)
	           visited[i] = false;
	       for (int u = 0; u < g.vertices; u++)
	       { 
	           if (!visited[u])
	               if (isCyclicUtil(u, visited, -1,g))
	                   return true;
	       }
	
	       return false;
	   }
	    static void printMatrix(int[][] adj, int V)
	    {
	        for(int i = 0; i < V; i++)
	        {
	            for(int j = 0; j < V; j++)
	            {
	                System.out.print(adj[i][j] + "\t");
	            }
	            System.out.println();
	        }
	        System.out.println();
	    }
	    public void printAllPaths(int s, int d)
	    {
	        boolean[] isVisited = new boolean[vertices];
	        ArrayList<Integer> pathList = new ArrayList<>();
	        pathList.add(s);
	  	    printAllPathsUtil(s, d, isVisited, pathList);
	    }
	  
	    private void printAllPathsUtil(Integer u, Integer d,boolean[] isVisited,List<Integer> localPathList)
	    {
	  
	        if (u.equals(d)) {
	            System.out.println(localPathList);
	            return;
	        }
	  
	        isVisited[u] = true;
	        for (Integer i : adjacencyList.get(u)) {
	            if (!isVisited[i]) {
	                localPathList.add(i);
	                printAllPathsUtil(i, d, isVisited, localPathList);
	                localPathList.remove(i);
	            }
	        }
	        isVisited[u] = false;
	    }
}
