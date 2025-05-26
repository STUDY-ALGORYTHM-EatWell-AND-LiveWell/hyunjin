package week_8;

import java.util.ArrayList;
import java.util.List;

class Node {
    int x;
    int y;
    int val;
    Node left;
    Node right;

    public Node(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

public class 프로그래머스_3_길찾기게임_이현진 {
    public static List<Node> tree;
    public static int orderIdx;

    public int[][] solution(int[][] nodeinfo) {
        int n = nodeinfo.length;
        int[][] answer = new int[2][n];
        tree = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = nodeinfo[i][0];
            int y = nodeinfo[i][1];
            tree.add(new Node(x, y, i + 1)); // 번호는 i+1
        }

        // y 기준 내림차순, y가 같으면 x 기준 오름차순 (좌우 구분 위해)
        tree.sort((a, b) -> {
            if (a.y == b.y) return a.x - b.x;
            return b.y - a.y;
        });

        Node root = tree.get(0);
        for (int i = 1; i < tree.size(); i++) {
            link(root, tree.get(i));
        }

        orderIdx = 0;
        preOrder(root, answer[0]);

        orderIdx = 0;
        postOrder(root, answer[1]);

        return answer;
    }

    static void link(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) parent.left = child;
            else link(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else link(parent.right, child);
        }
    }

    static void preOrder(Node node, int[] arr) {
        if (node != null) {
            arr[orderIdx++] = node.val;
            preOrder(node.left, arr);
            preOrder(node.right, arr);
        }
    }

    static void postOrder(Node node, int[] arr) {
        if (node != null) {
            postOrder(node.left, arr);
            postOrder(node.right, arr);
            arr[orderIdx++] = node.val;
        }
    }
}