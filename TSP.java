package tsp2;

import java.util.Scanner;

public class TSP {
	public static int[][]graf;
	public static int[]vertices;
	public static int []min_arr;
	static int min=999;
	static int count=1;
	static int j=0;
	static void permutasyon(int i, int n) {
		int j;
	    if (i == n) {
	        for (int a = 0; a < n + 1; a++) {
	            int b = (a + 1) % (n + 1);
	            if (graf[vertices[a]][vertices[b]] == 0 || (a == n && graf[vertices[a]][b] == 0)) {
	                return;
	            }
	        }
	        yazdir(n + 1);
	    }
	    else {
	        for (j = i; j <= n; j++) {
	            degistir(vertices,i,j);
	            permutasyon(i + 1, n);
	            degistir(vertices,i,j);
	        }
	    }
	}
	
	static void yazdir(int n) 
		{
	    int i, k,cost=0;
	    System.out.printf("\n0");
	    for (i = 1; i < n; i++) {
	        cost = cost + graf[vertices[i - 1]][vertices[i]];
	        System.out.printf(" -> %d", vertices[i]);
	    }
	    System.out.printf(" -> 0");
	    cost = cost + graf[vertices[i - 1]][vertices[0]];
	    System.out.printf("\t Cost: %d", cost);

	    if (min >= cost) {
	        if (min == cost) 
	        {
	            j = (count * n) + count;
	            for (k = 0; k < n; j++, k++) {
	                min_arr[j] = vertices[k];
	            }
	            count++;
	        }
	        else 
	        {
	            min = cost;
	            for (i = 1; i < n; i++) 
	            {
	                min_arr[i] = vertices[i];
	            }
	        }
	    }
	}

	static void degistir(int []arr, int num1,int num2) {
	    int temp;
	    temp = arr[num1];
        arr[num1] = arr[num2];
        arr[num2] = temp;
	}
	
	public static void main(String[] args) 
	{
		int n;
		do
		{
			Scanner sc= new Scanner(System.in);
			System.out.print("3 ile 100 arasinda bir n degeri giriniz:");
			n=sc.nextInt();
		}while(n<3||n>100);
		vertices = new int[n];
		min_arr=new int[100];
		for (int i = 0; i < n; i++) {
            min_arr[i] = 0;
            vertices[i]=i;
        }
    	RandomGraph r=new RandomGraph(n);
    	System.out.printf("\nNode 0'dan olasi tum yollar\n");
    	do {
    		do
        	{
        		r.GraphYap();
        	}while(r.isCyclic(r)!=true);
        	graf=r.convert(r.adjacencyList, n);
            permutasyon(1, n - 1);
    	}while(min==999);
        System.out.println("\n\nEn az maliyet:"+min+" olarak hesaplandi ve "+ count +" adet optimal yol bulundu:\n");
        if (count >= 1) 
        {
            int x = 1;
            for (int i = 0; i < count * n + count; i++)
            {
            	System.out.print(min_arr[i]);
                if (i == x * n + x - 1) 
                {
                	System.out.println();
                    x++;
                }
                else {
                	System.out.printf(" -> ");
                }
            }
        }
        System.out.println();
        System.out.println("Elde edilen graf bilgileri:");
        System.out.println("Kose sayisi:"+n+"\t Kenar sayisi:"+r.edges+ "\nGrafin komsuluk matrisi:");
        r.printMatrix(graf,n);
	}
}
