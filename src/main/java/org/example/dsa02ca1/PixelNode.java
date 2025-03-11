package org.example.dsa02ca1;

import javafx.scene.paint.Color;

public class PixelNode<T> {
    public T data;
    public Color color;


    public PixelNode(T data, Color color) {
        this.data = data;
        this.color = color;
    }
}
