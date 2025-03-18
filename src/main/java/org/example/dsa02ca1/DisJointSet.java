package org.example.dsa02ca1;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DisJointSet<T> {

    private int width;
    private int height;// set wImage to 300 X 300 pixels
    public PixelNode[] pixels;


    public DisJointSet() {
        width = getWidth();
        height = getHeight();
        int size = width * height;
        pixels  = new PixelNode[size];

    }

    public int getHeight() {
        return 300;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return 300;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public PixelNode<T>[] getPixels() {
        return pixels;
    }

    public void setPixels(PixelNode<T>[] pixels) {
        this.pixels = pixels;
    }

    public static PixelNode<?> find(PixelNode<?> node) { //finds the root or representative  element of the set which i belongs to
       if(node.parent==null) return node;
       else return find(node.parent);
    }

    public static void unionByHeight(PixelNode<?> p, PixelNode<?> q) {
        PixelNode<?> rootp=find(p);
        PixelNode<?> rootq=find(q);
        PixelNode<?> deeperRoot=rootp.height>=rootq.height ? rootp : rootq;
        PixelNode<?> shallowerRoot=deeperRoot==rootp ? rootq : rootp;
        shallowerRoot.parent=deeperRoot;
        if(deeperRoot.height==shallowerRoot.height) deeperRoot.height++;
    }
    public static void unionBySize(PixelNode<?> p, PixelNode<?> q) {
        PixelNode<?> rootp=find(p);
        PixelNode<?> rootq=find(q);
        PixelNode<?> biggerRoot=rootp.size>=rootq.size ? rootp : rootq;
        PixelNode<?> smallerRoot=biggerRoot==rootp ? rootq : rootp;
        smallerRoot.parent=biggerRoot;
        biggerRoot.size+=smallerRoot.size;
    }



}