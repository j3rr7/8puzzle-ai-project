package com.example.aiproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Solver {
    public static ArrayList<Node> visitedNode = new ArrayList<>();
    public static void BFS(Integer[][] node)
    {
        visitedNode.clear(); // ClOSE

        Queue<Node> processedNode = new LinkedList<>(); // OPEN

        Node solution = null;

        Node n = new Node(null,node,0,0,"");
        processedNode.add(n);
        visitedNode.add(n);

        int maxNodes = 1;
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
                if (!visitedNode.contains(child))
                {
                    visitedNode.add(child);
                    processedNode.add(child);
                }
                if (processedNode.size() > maxNodes)
                {
                    maxNodes = processedNode.size();
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
            for (Node s:step) {
                totalCost += s.cost;
                System.out.println( String.format("Move %s", s.move.toString()) );
            }
        }
        else
        {
            System.out.println(" Tidak bisa solving ");
        }
    }
    public static void Astar(Integer[][] node)
    {
        visitedNode.clear();

        PriorityQueue<Node> NodeProcessed = new PriorityQueue<>();

        Node solution = null;

        Node n = new Node(null,node,0,0,"");
        NodeProcessed.add(n);
        visitedNode.add(n);

        int maxNodes = 1;
        while(!NodeProcessed.isEmpty())
        {
            int priority;
            n = NodeProcessed.poll();
            priority = n.cost;
            if (n.isGoal())
            {
                //System.out.println( String.format("Depth: %s, MaxNode : %s", n.depth,maxNodes) );
                solution = n;
                break;
            }
            Node.GeneratePossibleChildren(n);
            for (Node v : n.children ) {
                if (!visitedNode.contains(v))
                {
                    visitedNode.add(v);
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
            for (Node s:step) {
                totalCost += s.cost;
                System.out.println( String.format("Move %s", s.move.toString()) );
            }
        }
        else
        {
            System.out.println(" Tidak bisa solving ");
        }
    }
}
