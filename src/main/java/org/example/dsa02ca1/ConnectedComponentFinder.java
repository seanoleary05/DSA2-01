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
    private Color[][] pixels;

    public ConnectedComponentFinder(Color[][] pixels) {
        this.pixels = pixels;
        height = pixels.length;
        width = pixels[0].length;
        int size = width * height;
        parent = new int[size];
        rank = new int[size];

        // Initialize each pixel as its own parent
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        // Find connected components
        findConnectedComponents();
    }

    private int getIndex(int x, int y) {
        return y * width + x;
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

    private void findConnectedComponents() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int currentIndex = getIndex(x, y);
                Color currentColor = pixels[y][x];

                // Check neighbors (up, down, left, right) for red pixels
                if (currentColor.equals(Color.RED)) {
                    if (x > 0 && pixels[y][x - 1].equals(Color.RED)) {
                        union(currentIndex, getIndex(x - 1, y));
                    }
                    if (x < width - 1 && pixels[y][x + 1].equals(Color.RED)) {
                        union(currentIndex, getIndex(x + 1, y));
                    }
                    if (y > 0 && pixels[y - 1][x].equals(Color.RED)) {
                        union(currentIndex, getIndex(x, y - 1));
                    }
                    if (y < height - 1 && pixels[y + 1][x].equals(Color.RED)) {
                        union(currentIndex, getIndex(x, y + 1));
                    }
                }
                //check neighbors for purple pixels.
                else if (currentColor.equals(Color.PURPLE)){
                    if (x > 0 && pixels[y][x - 1].equals(Color.PURPLE)) {
                        union(currentIndex, getIndex(x - 1, y));
                    }
                    if (x < width - 1 && pixels[y][x + 1].equals(Color.PURPLE)) {
                        union(currentIndex, getIndex(x + 1, y));
                    }
                    if (y > 0 && pixels[y - 1][x].equals(Color.PURPLE)) {
                        union(currentIndex, getIndex(x, y - 1));
                    }
                    if (y < height - 1 && pixels[y + 1][x].equals(Color.PURPLE)) {
                        union(currentIndex, getIndex(x, y + 1));
                    }
                }
                //white pixels are ignored.
            }
        }
    }

    public Map<Integer, Rectangle2D> getComponentBounds() {
        Map<Integer, Rectangle2D> bounds = new HashMap<>();
        Map<Integer, List<int[]>> components = new HashMap<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int root = find(getIndex(x, y));
                components.computeIfAbsent(root, k -> new ArrayList<>()).add(new int[]{x, y});
            }
        }

        for (List<int[]> pixelsInComponent : components.values()) {
            int minX = Integer.MAX_VALUE;
            int minY = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxY = Integer.MIN_VALUE;

            for (int[] pixel : pixelsInComponent) {
                minX = Math.min(minX, pixel[0]);
                minY = Math.min(minY, pixel[1]);
                maxX = Math.max(maxX, pixel[0]);
                maxY = Math.max(maxY, pixel[1]);
            }
            bounds.put(find(getIndex(pixelsInComponent.get(0)[0], pixelsInComponent.get(0)[1])),
                    new Rectangle2D(minX, minY, maxX - minX + 1, maxY - minY + 1));
        }

        return bounds;
    }
}