package com.zhengguoqiang.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 无向图
 */
public class Graph {
    private int v;//顶点数
    private LinkedList<Integer> adj[];//邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0;i<v;i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s,int t){
        //无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先搜索
     * @param s 起始顶点
     * @param t 终止顶点
     */
    public void bfs(int s,int t){
       if (s == t) return;
       //标记顶点是否被访问过
       boolean[] visited = new boolean[v];
       //起始顶点已访问
       visited[s] = true;
       //用来存储每一层待访问的顶点
       Queue<Integer> queue = new LinkedList<>();
       queue.add(s);
       //记录搜索路径
       int[] prev = new int[v];
       for (int i=0;i<v;i++){
           prev[i] = -1;
       }

       while (!queue.isEmpty()){
           int w = queue.poll();
           for (int i=0;i<adj[w].size();i++){
               int q = adj[w].get(i);
               if (!visited[q]){
                   prev[q] = w;
                   if (q == t){
                       print(prev,s,t);
                       return;
                   }
                   visited[q] = true;
                   queue.add(q);
               }
           }
       }
    }

    boolean found = false;
    private void dfs(int s,int t){
        found = false;
        boolean[] visited = new boolean[v];
        //记录搜索路径
        int[] prev = new int[v];
        for (int i=0;i<v;i++){
            prev[i] = -1;
        }
        dfsByRecursive(s,t,visited,prev);
        print(prev,s,t);
    }

    private void dfsByRecursive(int w,int t,boolean[] visited,int[] prev){
        if (found) return;
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0;i<adj[w].size();i++){
            int q = adj[w].get(i);
            if (!visited[q]){
                prev[q] = w;
                dfsByRecursive(q,t,visited,prev);
            }
        }
    }

    private void print(int[] prev,int s,int t){
        if (prev[t] != -1 && t != s){
            print(prev,s,prev[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(2,5);
        graph.addEdge(3,4);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,7);
        graph.bfs(0,6);
        graph.dfs(0,6);
    }
}
