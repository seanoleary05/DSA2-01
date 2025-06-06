package org.example.dsa02ca1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.canvas.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class HelloApplication extends Application {

    private Stage window;
    //Scene scene;

    Button button;
    Label label = new Label();
    ImageView imageView = new ImageView();
    ImageView imageView2 = new ImageView();
    ImageView imageView3 = new ImageView();
    private PixelNode[] Dset = new PixelNode[1000000];
    DisJointSet ds = new DisJointSet(1000000);  // Example size



    public static void main(String[] args) {


        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        ChoiceBox<String> choiceBox = new ChoiceBox<>();


        button = new Button("Run Function");
        choiceBox.getItems().add("Open An Image");
        choiceBox.getItems().add("Test");
        choiceBox.getItems().add("Open Image Details");
        choiceBox.setValue("Dropdown: Choose a function");

        button.setOnAction(e -> {
            String option = choiceBox.getValue();
            if (option != null) {
                executeOption(option);
            }
        });
        VBox root = new VBox(10);
        root.getChildren().addAll(choiceBox, button, imageView, imageView2);
        Scene scene = new Scene(root, 700, 700);
        primaryStage.setTitle("Image Application");
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    private void executeOption(String option) {
        HBox pictureRoot = new HBox();
        Text t = new Text("");
        Button triC = new Button("Convert to Tricolor");

        Stage pictureStage = new Stage();
        switch (option) {
            case "Open An Image":
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open File");
                fileChooser.setInitialDirectory(new File("C:/"));
                File file = fileChooser.showOpenDialog(new Stage());
                if (file != null) {
                    label.setText(file.getName());
                    Image image1 = new Image(file.getPath());
                    triC.setOnAction(e -> {
                        convertToTriColor(image1);

                    });
                    imageView.setImage(image1);
                    imageView.setFitHeight(300);
                    imageView.setFitWidth(300);
                    t.setText("Image Info: " + file.getPath() + "\n");
                    pictureRoot.getChildren().addAll(imageView, triC, t);
                    pictureStage.setScene(new Scene(pictureRoot, 1000, 500));
                    pictureStage.show();
                    break;


                }


        }

    }

    public void convertToTriColor(Image inputImage ) {
        HBox triRoot = new HBox();
        Button countButton = new Button("Count All Red & White Cells");
        Button testArrayButton = new Button("Button for debugging");
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();
        WritableImage wImage = new WritableImage(width, height);
        PixelReader pr = inputImage.getPixelReader();
        PixelWriter pw = wImage.getPixelWriter();


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = pr.getColor(x, y);


                if (isRed(color)) {
                    pw.setColor(x, y, Color.RED);

                } else if (isWhite(color)) {
                    pw.setColor(x, y, Color.WHITE);

                } else {
                    pw.setColor(x, y, Color.PURPLE);
                }

            }

        }
        ImageView triView = new ImageView();
        triView.setImage(wImage);
        triView.setFitHeight(300);
        triView.setFitWidth(300);
        countButton.setOnAction(e -> {
            populateImageArray(wImage);
            processImage(wImage);
            //drawBoundingBoxes(wImage,1000,1000);

        });
        Stage triStage = new Stage();
        triRoot.getChildren().addAll(triView, countButton,testArrayButton);
        triStage.setScene(new Scene(triRoot, 1000, 500));
        triStage.show();


    }

    public void populateImageArray(Image writableImage) {
        int width = (int) writableImage.getWidth();
        int height = (int) writableImage.getHeight();
        PixelReader pr = writableImage.getPixelReader();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int i = (x + y * width);

                Color color = pr.getColor(x, y);
                Dset[i] = new PixelNode("" + i, color, i);


            }
        }

    }


   public void processImage(Image wImage) {
       int width = (int) wImage.getWidth();
       int height = (int) wImage.getHeight();
       int size = width * height;


       for (int y = 0; y < height; y++) {
           for (int x = 0; x < width; x++) {
               int index = y * width + x;

               if (isWhite(Dset[index].color)) continue;

               // Compare with right neighbor
               if (x + 1 < width && isSameCategory(Dset[index].color, Dset[index + 1].color)) {
                   ds.union(index, index + 1);
               }

               // Compare with bottom neighbor
               if (y + 1 < height && isSameCategory(Dset[index].color, Dset[index + width].color)) {
                   ds.union(index, index + width);
               }

           }

       }
   }

 /*   public void processImage(Image wImage) {
        int width = (int) wImage.getWidth();
        int height = (int) wImage.getHeight();
        int size = width * height;
        DisjointSet disjointSet = new DisjointSet(size);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = y * width + x;


                if (isWhite(Dset[index].color)) continue; // Skip white pixels

                // Compare with the right neighbor
                if (x + 1 < width && isSameCategory(Dset[index].color, Dset[index + 1].color)) {
                    disjointSet.union(index, index+1);
                }

                // Compare with the bottom neighbor
                if (y + 1 < height && isSameCategory(Dset[index].color, Dset[index + width].color)) {
                    disjointSet.union(index, index + width);
                }
            }
        }
        for(int i = 0; i<Dset.length;i++){
            System.out.println(Dset[i].parent);
        }
    }

  */
    private boolean isSameCategory(Color color1, Color color2) {
        return (isRed(color1) && isRed(color2)) || (isPurple(color1) && isPurple(color2));
    }


    private boolean isWhite(Color color) {
        double green = color.getGreen();
        double blue = color.getBlue();
        double red = color.getRed();
        double o = color.getOpacity();



        return  red > 0.7 && blue > 0.7 && green > 0.7;
    }

    private boolean isPurple(Color color){

        double b = color.getBlue();
        double r = color.getRed();
        double g = color.getGreen();
        double o = color.getOpacity();

        return (r > 0.4) && (b > 0.4) && (g <0.9);

    }

    private boolean isRed(Color color){
        double r = color.getRed();
        double b = color.getBlue();
        double g = color.getGreen();
        double o = color.getOpacity();


        return r > 0.5 && g < 0.6 && b < 0.9 && o > 0.95;
    }



}












