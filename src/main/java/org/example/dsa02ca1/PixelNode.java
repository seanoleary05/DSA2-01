package org.example.dsa02ca1;

import javafx.scene.paint.Color;

public class PixelNode<T> {
    public T data;
    public int parent;
    public Color color;




    public PixelNode(T data, Color color, int parent) {
        this.data = data;
        this.color = color;
        setParent(parent);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "PixelNode{" +
                "parent=" + parent +
                ", color=" + color +
                ", data=" + data +
                '}';
    }
}
