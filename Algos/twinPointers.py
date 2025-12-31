"""
If the graph has n vertices and m edges, and memory size is not a constraint, what is the time complexity of the most efficient algorithm to set the twin pointer for every adjacency list entry?
"""

class Graph:
    def __init__(self, vertices):
        self.vertices = vertices
        self.adj_list = {}

    def __str__(self):
        return str(self.adj_list)

    def add_edge(self, edge):
        u, v = edge
        if u in self.adj_list:
            self.adj_list[u].append(v)
        else:
            self.adj_list[u] = [v]

        if v in self.adj_list:
            self.adj_list[v].append(u)
        else:
            self.adj_list[v] = [u]

    @classmethod
    def makeGraph(cls, vertices, edges):
        graph = cls(vertices)
        for edge in edges:
            graph.add_edge(edge)
        return graph

    def makeHashTable(self):
        hashTable = {}
        for vertex, neighbors in self.adj_list.items():
            for i, neighbor in enumerate(neighbors):
                hashTable[(vertex, neighbor)] = i
        return hashTable
            
    def twinPointers(self):
        # First make a hashTable (vertex(u, v): adj[v].u, vertex(v, u): adj[u].v)
        hashTable = self.makeHashTable()
        for u in self.adj_list:
            for i in range(len(self.adj_list[u])):
                v = self.adj_list[u][i]
                twin_index = hashTable[(v, u)]
                self.adj_list[u][i] = [v, twin_index]

if __name__ == "__main__":
    graph = Graph.makeGraph(5, [(0, 1), (0, 2), (1, 2), (2, 3), (2, 4)])
    graph.twinPointers()
    print(graph)
