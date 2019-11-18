package com.graph;

import java.util.*;


public class BreadthFirstSearch {
    private Set<String> visited = new HashSet<String>();
    private Queue<String> queue = new LinkedList<String>();
    private Graph graph;

	public void findConnection(Graph g, String startingVertex, String targetVertex) {
		this.graph = g;
		this.queue.add(startingVertex);
		this.visited.add(startingVertex);
		Map<String, String> parent = new HashMap<String, String>();
		boolean found = false;
		while (!queue.isEmpty()) {
			String next = queue.remove();
			if (next.equalsIgnoreCase(targetVertex)) {
				found = true;
				break;
			}
			for (String neighbor : this.graph.getNeighbors(next)) {
				if (!this.visited.contains(neighbor)) {
					this.queue.add(neighbor);
					this.visited.add(neighbor);
					parent.put(neighbor, next);
				}
			}
		}
		if (found) {
			ArrayList<String> pathList = new ArrayList<String>();
			String key = targetVertex;
			pathList.add(key);
			do {
				pathList.add(parent.get(key));
				key = parent.get(key);
			} while (null!=parent.get(key));
			for(int i=pathList.size()-1;i>=0;i--) {
				System.out.println(pathList.get(i));
			}
		} else {
			System.out.println("Two Nodes Are Not Connected");
		}

	}

}
