package org.example.dsa02ca1;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DisjointSet<T> {
    private int[] parent;

    public DisjointSet(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i; // Each pixel is its own parent initially
        }
    }



    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX; // Merge the two sets
        }
    }
    public void printParents() {
        System.out.println("Index -> Parent Mapping:");
        for (int i = 0; i < parent.length; i++) {
            System.out.println(i + " -> " + parent[i]);
        }
    }

    public int[] getParent(){
        return parent;
    }
}





