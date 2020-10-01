package com.example.aiproject;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Solver {
    public static ArrayList<Integer[][]> visitedNode = new ArrayList<>();
    public static String BFS(Integer[][] node)
    {
        visitedNode.clear(); // ClOSE

        Queue<Node> processedNode = new LinkedList<>(); // OPEN

        Node solution = null;

        Node n = new Node(null,node,0,0,"");
        processedNode.add(n);
        visitedNode.add(n.state);

        int maxNodes = 200000;
        while(!processedNode.isEmpty())
        {
            n = processedNode.peek();
            processedNode.remove();
            if (n.isGoal())
            {
                solution = n;
                break;
            }
            Node.GeneratePossibleChildren(n);
            for (Node child: n.children) {
                if (!visitedNode.contains(child.state))
                {
                    visitedNode.add(child.state);
                    processedNode.add(child);
                }
                if (processedNode.size() >= maxNodes)
                {
                    break;
                }
            }
            System.out.println( String.format("Node size : %s", processedNode.size()) );
        }
        if (solution != null)
        {
            Stack<Node> step = new Stack<>();
            while(solution.parent != null)
            {
                step.push(solution);
                solution = solution.parent;
            }
            int totalCost = 0;
            String jwban = "";
            while(!step.isEmpty())
            {
                Node s = step.pop();
                totalCost += s.cost;
                jwban += s.move + " ";
            }
            System.out.println( String.format("Move %s", jwban) );
            return jwban;
        }
        else
        {
            System.out.println(" Tidak bisa solving ");
            return "solve tidak ditemukan";
        }
    }
    public static String Astar(Integer[][] node)
    {
        visitedNode.clear();
        PriorityQueue<Node> NodeProcessed = new PriorityQueue<>();
        Node solution = null;
        Node n = new Node(null,node,0,0,"");
        NodeProcessed.add(n);
        visitedNode.add(n.state);
        int maxNodes = 1;
        while(!NodeProcessed.isEmpty())
        {
            int priority;
            n = NodeProcessed.poll();
            priority = n.cost;
            if (n.isGoal())
            {
                System.out.println( String.format("Depth: %s, MaxNode : %s", n.depth,maxNodes) );
                solution = n;
                break;
            }
            Node.GeneratePossibleChildren(n);
            for (Node v : n.children ) {
                if (!visitedNode.contains(v.state))
                {
                    visitedNode.add(v.state);
                    NodeProcessed.add(v);
                }
                if (NodeProcessed.size() > maxNodes)
                {
                    maxNodes = NodeProcessed.size();
                }
            }
            System.out.println( String.format("Depth: %s, MaxNode : %s", n.depth,maxNodes) );
        }
        if (solution != null)
        {
            Stack<Node> step = new Stack<>();
            while(solution.parent != null)
            {
                step.push(solution);
                solution = solution.parent;
            }
            int totalCost = 0;
            String jwban = "";
            while(!step.isEmpty())
            {
                Node s = step.pop();
                totalCost += s.cost;
                jwban += s.move + " ";
            }
            System.out.println( String.format("Move %s", jwban) );
            return jwban;
        }
        else
        {
            System.out.println(" Tidak bisa solving ");
            return "Tidak Bisa Solving";
        }
    }
}
