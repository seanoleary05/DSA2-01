package org.example.dsa02ca1;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;

public class HelloController {
    @FXML
    private Stage window;

    //Scene scene;

    Button button;
    javafx.scene.control.Label label = new Label();
    ImageView imageView = new ImageView();
    ImageView imageView2 = new ImageView();
    ImageView imageView3 = new ImageView();
    private PixelNode[] Dset = new PixelNode[1000000];
    DisJointSet ds = new DisJointSet(1000000);  // Example size



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
                    javafx.scene.image.Image image1 = new javafx.scene.image.Image(file.getPath());
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

    public void convertToTriColor(javafx.scene.image.Image inputImage) {
        HBox triRoot = new HBox();
        Button countButton = new Button("Count All Red & White Cells");
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();
        WritableImage wImage = new WritableImage(width, height);
        PixelReader pr = inputImage.getPixelReader();
        PixelWriter pw = wImage.getPixelWriter();


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                javafx.scene.paint.Color color = pr.getColor(x, y);


                if (isRed(color)) {
                    pw.setColor(x, y, javafx.scene.paint.Color.RED);

                } else if (isWhite(color)) {
                    pw.setColor(x, y, javafx.scene.paint.Color.WHITE);

                } else {
                    pw.setColor(x, y, javafx.scene.paint.Color.PURPLE);
                }

            }

        }
        ImageView triView = new ImageView();
        triView.setImage(wImage);
        triView.setFitHeight(300);
        triView.setFitWidth(300);
        Stage triStage = new Stage();
        triRoot.getChildren().addAll(triView, countButton);
        //countButton.setOnAction(e -> countCells(wImage));
        triStage.setScene(new Scene(triRoot, 1000, 500));
        triStage.show();


    }

    public void populateImageArray(Image writableImage) {
        PixelReader pr = writableImage.getPixelReader();
        int width = (int) writableImage.getWidth();
        int height = (int) writableImage.getHeight();
        int i = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                javafx.scene.paint.Color color = pr.getColor(x, y);
                if (color == javafx.scene.paint.Color.WHITE) {


                }


            }
        }
    }


    private boolean isWhite(javafx.scene.paint.Color color) {
        double green = color.getGreen();
        double blue = color.getBlue();
        double red = color.getRed();


        return red > 0.8 && blue > 0.8 && green > 0.8;
    }

    private boolean isPurple(javafx.scene.paint.Color color) {

        double b = color.getBlue();
        double r = color.getRed();
        double g = color.getGreen();

        return (r > 0.6 && r < 0.99) && (g < 0.91) && (b > 0.7);

    }

    private boolean isRed(Color color) {
        double r = color.getRed();
        double b = color.getBlue();
        double g = color.getGreen();


        return r > 0.5 && g < 0.8 && b < 0.9;
    }
}




