package org.example.dsa02ca1;

import javafx.scene.paint.Color;

public class PixelNode<T> {
    public T data;
    public PixelNode<?> parent = null;
    public Color color;




    public PixelNode(T data, Color color, PixelNode<T> parent) {
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

    public PixelNode<?> getParent() {
        return parent;
    }

    public void setParent(PixelNode<T> parent) {
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
