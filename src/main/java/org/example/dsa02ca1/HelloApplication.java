package org.example.dsa02ca1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    private Stage window;
    //Scene scene;

    Button button;
    Label label = new Label();
    ImageView imageView = new ImageView();
    ImageView imageView2 = new ImageView();
    ImageView imageView3 = new ImageView();
    private PixelNode[] Dset;


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

    public void convertToTriColor(Image inputImage) {
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
        });
        Stage triStage = new Stage();
        triRoot.getChildren().addAll(triView, countButton,testArrayButton);
        //countButton.setOnAction(e -> countCells(wImage));
        triStage.setScene(new Scene(triRoot, 1000, 500));
        triStage.show();


    }

    public void populateImageArray(Image writableImage) {
        int width = (int) writableImage.getWidth();
        int height = (int) writableImage.getHeight();
        Dset = new PixelNode[width * height];
        PixelReader pr = writableImage.getPixelReader();

        System.out.println(width + ", " + height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int i = (x + y * width);

                Color color = pr.getColor(x, y);
                Dset[i] = new PixelNode("" + i, color, Dset[i]);
            }
        }
                    /*if (Dset[i].parent == null) { // to be the root of a disjoint set
                        Dset[i] = Dset[i].parent;
                    }
                    if (x <= width - 1) {
                        Color xColor = pr.getColor(x + 1, y);
                        if (xColor == Color.RED) Dset[i] = Dset[i + 1].parent;
                    }

                    if (y <= height - 1) {
                        Color yColor = pr.getColor(x, y + width);
                        if (yColor == Color.RED) Dset[i] = Dset[i + width].parent;
                    }


                }
                else if (color.toString() == "0xffffffff") {
                    Dset[i] = new PixelNode(i,Color.WHITE, Dset[i]);

                  /*  if (Dset[i].parent == null) { // to be the root of a disjoint set
                        Dset[i] = Dset[i].parent;
                    }
                    if (x <= width - 1) {
                        Color xColor = pr.getColor(x + 1, y);
                        if (xColor == Color.PURPLE) Dset[i] = Dset[i + 1].parent;
                    }

                    if (y <= height - 1) {
                        Color yColor = pr.getColor(x, y + width);
                        if (yColor == Color.PURPLE) Dset[i] = Dset[i + width].parent;
                    }



                } else {// pixel is purple
                    Dset[i] = new PixelNode(i,Color.PURPLE, Dset[i]);
                //    Dset[i] = Dset[i].parent;


                }

                     */




    }








    private boolean isWhite(Color color) {
        double green = color.getGreen();
        double blue = color.getBlue();
        double red = color.getRed();



        return  red > 0.8 && blue > 0.8 && green > 0.8;
    }

    private boolean isPurple(Color color){

        double b = color.getBlue();
        double r = color.getRed();
        double g = color.getGreen();

        return (r > 0.5) && (b > 0.5) && (g <0.9);

    }

    private boolean isRed(Color color){
        double r = color.getRed();
        double b = color.getBlue();
        double g = color.getGreen();


        return r > 0.5 && g < 0.8 && b < 0.9;
    }



}












