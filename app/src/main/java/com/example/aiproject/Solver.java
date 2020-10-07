package com.example.aiproject;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Solver {
    public static ArrayList<Integer[][]> visitedNode = new ArrayList<>();

    public static int BatasIterasi = 100000;

    public static String BFS(Integer[][] node)
    {
        visitedNode.clear(); // ClOSE

        Queue<Node> processedNode = new LinkedList<>(); // OPEN
        Node solution = null;
        Node n = new Node(null,node,0,0,"");
        processedNode.add(n);
        visitedNode.add(n.state);

        int batas = 0;
        while(!processedNode.isEmpty())
        {
            n = processedNode.peek();
            processedNode.remove();
            if (n.isGoal())
            {
                solution = n;
                break;
            }
            Node.GeneratePossibleChildren(n,"bfs");
            for (Node child: n.children) {
                if (!visitedNode.contains(child.state))
                {
                    visitedNode.add(child.state);
                    processedNode.add(child);
                }
                else
                {
                    System.out.println("Found recurring BFS");
                }
            }
            //System.out.println( String.format("Node size : %s", processedNode.size()) );
            batas++;
            if (batas >= Solver.BatasIterasi)
            {
                //System.out.println(" Sampai Batas ");
                return "solve tidak ditemukan";
            }
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
            //System.out.println( String.format("Move %s, Total Cost", jwban, totalCost) );
            return jwban;
        }
        return "solve tidak ditemukan";
    }
    public static String Astar(Integer[][] node)
    {
        visitedNode.clear();
        PriorityQueue<Node> NodeProcessed = new PriorityQueue<>(1,new ArrayComparator());
        Node solution = null;
        Node n = new Node(null,node,0,0,"");
        NodeProcessed.add(n);
        visitedNode.add(n.state);

        int batas = 0;
        while(!NodeProcessed.isEmpty())
        {
            int priority;
            n = NodeProcessed.peek();
            NodeProcessed.poll();
            priority = n.cost;
            if (n.isGoal())
            {
                solution = n;
                break;
            }
            Node.GeneratePossibleChildren(n,"a*");
            for (Node v : n.children ) {
                if (!visitedNode.contains(v.state))
                {
                    visitedNode.add(v.state);
                    NodeProcessed.add(v);
                }
                else
                {
                    System.out.println("Found recurring A*");
                }
            }
            //System.out.println( String.format("Depth: %s priority : %s", n.depth, priority) );
            batas++;
            if (batas > Solver.BatasIterasi)
            {
                //System.out.println(" Sampai Batas ");
                return "Tidak Bisa Solving";
            }
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
            //System.out.println( String.format("Move %s Total Cost %s", jwban,totalCost) );
            return jwban;
        }
        return "solve tidak ditemukan";
    }
    public static String IterativeDeepening(Integer[][] node)
    {
        Stack<Node> checkedNode = new Stack<>();
        Stack<Node> processedNode = new Stack<>();
        Node solution = null;
        Node n = new Node(null,node,0,0,"");
        processedNode.add(n);
        int depthlimit = 0;
        int batas = 0;
        while(!processedNode.isEmpty())
        {
            n = processedNode.pop();
            checkedNode.add(n);
            if (n.isGoal())
            {
                solution = n;
                break;
            }
            if (checkedNode.isEmpty())
            {
                depthlimit += 1;
            }
            checkedNode.clear();
            Node.GeneratePossibleChildren(n,"iterativeDeepening");
            for (Node v : n.children ) {
                if (!checkedNode.contains(v))
                {
                    processedNode.add(v);
                    checkedNode.add(v);
                }
            }
            batas++;
            if (batas > Solver.BatasIterasi)
            {
                return "Tidak Bisa Solving";
            }
        }
        if (solution != null)
        {
            Stack<Node> step = new Stack<>();
            while(solution.parent != null)
            {
                step.push(solution);
                solution = solution.parent;
            }
            String jwban = "";
            while(!step.isEmpty())
            {
                Node s = step.pop();
                jwban += s.move + " ";
            }
            //System.out.println( String.format("Move %s Total Cost %s", jwban,totalCost) );
            return jwban;
        }
        return "Tidak Ditemukan";
    }
}
