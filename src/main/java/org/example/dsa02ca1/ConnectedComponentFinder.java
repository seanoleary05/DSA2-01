package org.example.dsa02ca1;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectedComponentFinder {

    private int[] parent;
    private int[] rank;
    private int width;
    private int height;
    private Color[] pixels;

    public ConnectedComponentFinder(Color[] pixels) {
        this.pixels = pixels;
        height = 300; // set wImage to 300 X 300 pixels
        width = 300;
        int size = width * height;
        parent = new int[size];
        rank = new int[size];

        // Initialize each pixel as its own parent
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }


    }


    private int find(int i) { //finds the root or representative  element of the set which i belongs to
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent[i]); // Path compression
    }

    private void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        if (rootI != rootJ) {
            if (rank[rootI] < rank[rootJ]) {
                parent[rootI] = rootJ;
            } else if (rank[rootI] > rank[rootJ]) {
                parent[rootJ] = rootI;
            } else {
                parent[rootJ] = rootI;
                rank[rootI]++;
            }
        }
    }



}