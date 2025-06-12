package week_10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Node {
    int index; // 노드 번호
    int item;  // 양 or 늑대 정보
    List<Node> children;

    public Node(int index, int item) {
        this.index = index;
        this.item = item;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
}

class BinaryTree {
    Node root;

    public void setRoot(Node n) {
        this.root = n;
    }

    public Node getRoot() {
        return this.root;
    }
}

public class 프로그래머스_3_양과늑대_이현진 {
    int maxSheep;
    Map<Integer, Node> nodeMap;

    public int solution(int[] info, int[][] edges) {
        nodeMap = new HashMap<>();
        Node root = connectEdge(info, edges);
        maxSheep = 0;
        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);
        dfs(0, 0, 0, nextNodes, info);
        return maxSheep;
    }

    private void dfs(int currentIndex, int sheep, int wolf, List<Integer> nextNodes, int[] info) {
        int currentType = info[currentIndex];
        if (currentType == 0) sheep++;
        else wolf++;

        if (wolf >= sheep) return;
        maxSheep = Math.max(maxSheep, sheep);

        List<Integer> newNext = new ArrayList<>(nextNodes);
        newNext.remove(Integer.valueOf(currentIndex));

        for (Node child : nodeMap.get(currentIndex).children) {
            newNext.add(child.index);
        }

        for (int next : newNext) {
            dfs(next, sheep, wolf, newNext, info);
        }
    }

    private Node connectEdge(int[] info, int[][] edges) {
        for (int i = 0; i < info.length; i++) {
            nodeMap.put(i, new Node(i, info[i]));
        }

        for (int[] edge : edges) {
            Node parent = nodeMap.get(edge[0]);
            Node child = nodeMap.get(edge[1]);
            parent.addChild(child);
        }

        return nodeMap.get(0);
    }
}
