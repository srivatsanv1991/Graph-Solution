package com.graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {

	
	private Map<String, List<String>> edges = new HashMap<String, List<String>>();
	
	public void addEdge(String src, String dest) {
        List<String> srcNeighbors = this.edges.get(src);
        if (srcNeighbors == null) {
            this.edges.put(src,
                srcNeighbors = new ArrayList<String>()
            );
        }
        srcNeighbors.add(dest);
    }

    public Iterable<String> getNeighbors(String vertex) {
        List<String> neighbors = this.edges.get(vertex);
        if (neighbors == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableList(neighbors);
        }
    }
    
    public static void main(String ap[]) throws IOException {

		Graph graph = new Graph();
		Scanner sc = new Scanner(System.in);
		BufferedReader br = null;
		System.out.println("Please create a file with edges");
		System.out.println("For eg. filename.txt contains");
		System.out.println("AA dd");
		System.out.println("A1 B2");
		System.out.println("Enter absolute path for file eg. (/Users/srivatsan.v/Downloads/filename.txt)");
		System.out.println("Please Enter the file name");
		String node1, node2;
		String fileName = sc.next();
		System.out.println("Enter node1");
		node1 = sc.next();
		System.out.println("Enter node2");
		node2 = sc.next();
		if (!"".equalsIgnoreCase(fileName) && null != fileName && !"".equalsIgnoreCase(node1) && null != node1
				&& !"".equalsIgnoreCase(node2) && null != node2) {
			File file = new File(fileName);
			BreadthFirstSearch breadth = new BreadthFirstSearch();
			try {
				br = new BufferedReader(new FileReader(file));
				String fileContent;
				while ((fileContent = br.readLine()) != null) {
					fileContent = fileContent.trim().replaceAll("\\s+", " ");
					String fileContents[] = fileContent.split(" ");
					if (fileContents.length > 1) {
						graph.addEdge(fileContents[0].toLowerCase(), fileContents[1].toLowerCase());
					} else {
						graph.addEdge(fileContents[0].toLowerCase(), fileContents[0].toLowerCase());
					}
				}
				breadth.findConnection(graph, node1.toLowerCase(), node2.toLowerCase());
				br.close();
				sc.close();
			} catch (FileNotFoundException e) {
				System.out.println("The Entered file name " + fileName + " is not found");
				System.out.println("Please check whether file name is entered with absolute path");
			}
		} else {
			System.out.println("Either the filename entered or node1, node2 entered is not valid");
		}

	}
}
