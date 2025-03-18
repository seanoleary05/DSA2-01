package org.example.dsa02ca1;

import javafx.scene.paint.Color;

public class PixelNode<T> {
    public T data;
    public PixelNode<?> parent = null;
    public Color color;
    public int size = 1,height = 1;



    public PixelNode(T data, Color color) {
        this.data = data;
        this.color = color;
        this.parent = this;
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
}
