package org.example.dsa02ca1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.image.Image;
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
    Scene scene;
    Button button;
    Label label = new Label();
    ImageView imageView = new ImageView();
    ImageView imageView2 = new ImageView();
    ImageView imageView3 = new ImageView();


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        window = primaryStage;
        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        button = new Button("Run Function");
        choiceBox.getItems().add("Open An Image");
        choiceBox.getItems().add("Open Image As Greyscale");
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
        HBox multiRoot = new HBox();
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
                    pictureRoot.getChildren().addAll(imageView,triC, t);
                    pictureStage.setScene(new Scene(pictureRoot, 1000, 500));
                    pictureStage.show();
                    break;


                }

            case "Open Image As Greyscale":
                Label label = new Label();
                FileChooser fileChooser2 = new FileChooser();
                fileChooser2.setTitle("Open File");
                fileChooser2.setInitialDirectory(new File("C:/"));
                File file2 = fileChooser2.showOpenDialog(new Stage());

                if (file2 != null) {
                    label.setText(file2.getName());
                    t.setText("Image Info: " + file2.getPath());
                    try {
                        Image image2 = new Image(file2.toURI().toString()); // Use toURI().toString() for better compatibility
                        int width = (int) image2.getWidth();
                        int height = (int) image2.getHeight();

                        WritableImage wImage1 = new WritableImage(width, height);
                        PixelReader pr = image2.getPixelReader();
                        PixelWriter pw = wImage1.getPixelWriter();

                        for (int y = 0; y < height; y++) {
                            for (int x = 0; x < width; x++) {
                                Color c = pr.getColor(x, y);
                                double r = c.getRed();
                                //System.out.println("red value = " + r);
                                double g = c.getGreen();
                                //System.out.println("green value = " + g);
                                double b = c.getBlue();
                                //System.out.println("blue value = " + b);
                                double grey = (r + g + b) / 3;
                                Color nc = new Color(grey, grey, grey, 1.0);
                                pw.setColor(x, y, nc); // Set the color for each pixel
                            }
                        }

                        imageView2.setImage(wImage1);
                        imageView2.setFitHeight(300);
                        imageView2.setFitWidth(300);
                        pictureRoot.getChildren().addAll(imageView2, t);
                        pictureStage.setScene(new Scene(pictureRoot, 1000, 500));
                        pictureStage.show();


                    } catch (Exception e) {
                        // Handle potential image loading errors (e.g., file not found, invalid format)
                        System.err.println("Error loading image: " + e.getMessage());
                        // Optionally, display an error message to the user.
                        label.setText("Error loading image"); // Or a more informative message
                    }


                }

        }

    }

    public Image convertToTriColor(Image inputImage) {
        HBox triRoot = new HBox();
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();
        WritableImage wImage = new WritableImage(width, height);
        PixelReader pr = inputImage.getPixelReader();
        PixelWriter pw = wImage.getPixelWriter();
        ColorAdjust ca = new ColorAdjust();
        ca.setContrast(0.1);
        ca.setHue(-0.05);
        ca.setBrightness(0.1);
        ca.setSaturation(0.2);


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color c = pr.getColor(x, y);
                double r = c.getRed();
                //System.out.println("red value = " + r);
                double g = c.getGreen();
                //System.out.println("green value = " + g);
                double b = c.getBlue();
                //System.out.println("blue value = " + b);
                double grey = (r + g + b) / 3;
                Color white = new Color(0, 0, 0, 1);
                Color nc = new Color(grey, grey, grey, 1.0);
                pw.setColor(x, y, nc); // Set the color for each pixel
            }
        }
        ImageView triView = new ImageView();
        triView.setImage(wImage);
        Stage triStage = new Stage();
        triRoot.getChildren().addAll(triView);
        triStage.setScene(new Scene(triRoot,1000, 500));
        triStage.show();
        return wImage;

    }

}












