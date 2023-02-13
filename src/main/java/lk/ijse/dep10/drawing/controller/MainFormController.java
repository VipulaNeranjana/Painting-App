package lk.ijse.dep10.drawing.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;

public class MainFormController {

    public VBox vBox;
    public Canvas cnvMain;
    public AnchorPane root;
    public Label lblText;
    public Rectangle rectBlink;
    @FXML
    private Button btnEreser;

    @FXML
    private Button btnOvel;

    @FXML
    private Button btnPencil;

    @FXML
    private Button btnRect;

    @FXML
    private Button btnRound;

    @FXML
    private Button btnText;

    @FXML
    private ColorPicker clrFill;

    @FXML
    private ColorPicker clrStroke;

    @FXML
    private Label lblDrag;

    private GraphicsContext gp;
    double xOfLbl;
    double yOfLbl;

    boolean rect = false;
    boolean round = false;
    boolean oval = false;
    boolean pencil = false;
    boolean eraser = false;
    boolean text = false;

    double startPointX;
    double startPointY;
    double stopPointX;
    double stopPointY;

    boolean strokeColor;
    boolean fillColor;

    String textFromTyping = "";

    Image image;

    Timeline timeline;


    public void initialize(){
        gp = cnvMain.getGraphicsContext2D();
        lblText.setVisible(false);
        rectBlink.setVisible(false);

        KeyFrame key1 = new KeyFrame(Duration.seconds(0), event -> {
            rectBlink.setVisible(true);
        });
        KeyFrame key2 = new KeyFrame(Duration.seconds(0.75), event -> {
            rectBlink.setVisible(false);
        });
        KeyFrame key3 = new KeyFrame(Duration.seconds(1.5), event -> {
            rectBlink.setVisible(true);
        });
        timeline = new Timeline(key1, key2, key3);
        timeline.setCycleCount(Animation.INDEFINITE);

    }

    @FXML
    void btnEreserOnAction(ActionEvent event) {
        System.out.println(eraser);
        if(eraser) {
            cnvMain.setCursor(Cursor.DEFAULT);
            System.out.println(eraser);
            eraser = false;
            return;
        }
        round = false;
        rect = false;
        oval = false;
        pencil = false;
        eraser = true;
        text = false;
        System.out.println("eraser");

        image = new Image(Objects.requireNonNull(this.getClass().getResource("/MainIcons/eraser (1).png")).toString(),20,20,true,true);
        cnvMain.setCursor(new ImageCursor(image));


    }

    @FXML
    void btnOvelOnAction(ActionEvent event) {
        round = false;
        rect = false;
        oval = true;
        pencil = false;
        eraser = false;
        text = false;

        timeline.stop();
        rectBlink.setVisible(false);

    }

    @FXML
    void btnPencilOnAction(ActionEvent event) {
        round = false;
        rect = false;
        oval = false;
        pencil = true;
        eraser = false;
        text = false;

        timeline.stop();
        rectBlink.setVisible(false);

    }

    @FXML
    void btnRectOnAction(ActionEvent event) {
        rect = true;
        round = false;
        oval = false;
        pencil = false;
        eraser = false;
        text = false;

        timeline.stop();
        rectBlink.setVisible(false);

    }

    @FXML
    void btnRoundOnAction(ActionEvent event) {
        round = true;
        rect = false;
        oval = false;
        pencil = false;
        eraser = false;
        text = false;

        timeline.stop();
        rectBlink.setVisible(false);

    }

    @FXML
    void btnTextOnAction(ActionEvent event) {
        if(text){
            text = false;
            timeline.stop();
            lblText.setText("");
            textFromTyping = "";
            rectBlink.setVisible(false);
            return;
        }
        round = false;
        rect = false;
        oval = false;
        pencil = false;
        eraser = false;
        text = true;

    }

    @FXML
    void clrFillOnAction(ActionEvent event) {
        fillColor = true;
        GraphicsContext gp = cnvMain.getGraphicsContext2D();
        gp.setFill(clrFill.getValue());

    }

    @FXML
    void clrStrokeOnAction(ActionEvent event) {
        strokeColor = true;
        gp.setStroke(clrStroke.getValue());
    }

    @FXML
    void lblOnDrag(MouseEvent event) {
        double xOfMouse = event.getSceneX();
        double yOfMouse = event.getSceneY();



        System.out.println(xOfMouse + "  "+ xOfLbl);

        vBox.setLayoutX(xOfMouse - xOfLbl);
        vBox.setLayoutY(yOfMouse - yOfLbl);
    }

    public void lblDragOnClicked(MouseEvent mouseEvent) {
        lblDrag.setEffect(new InnerShadow(10, Color.BLACK));
        xOfLbl = mouseEvent.getX();
        yOfLbl = mouseEvent.getY();
        System.out.println(xOfLbl);
    }

    public void lblDragOnMouseEnterd(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),lblDrag);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
        lblDrag.setEffect(new InnerShadow(6,Color.BLACK));
    }

    public void lblDragOnMouseExited(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),lblDrag);
        scaleTransition.setFromX(1.05);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
        lblDrag.setEffect(null);
    }

    public void lblDragOnReleased(MouseEvent mouseEvent) {
        lblDrag.setEffect(null);
    }

    public void cnvOnMouseClicked(MouseEvent mouseEvent) {




    }

    public void cnvOnMouseReleased(MouseEvent mouseEvent) throws IOException {
        stopPointX = mouseEvent.getX();
        stopPointY = mouseEvent.getY();


        if (rect && fillColor){
            if(stopPointX < startPointX && stopPointY < startPointY) gp.fillRect(stopPointX,stopPointY,startPointX-stopPointX,startPointY-stopPointY);
            else if(stopPointX > startPointX && stopPointY < startPointY) gp.fillRect(startPointX,stopPointY,stopPointX-startPointX,startPointY-stopPointY);
            else if(stopPointX < startPointX && stopPointY > startPointY) gp.fillRect(stopPointX,startPointY,startPointX-stopPointX,stopPointY -startPointY);
            else gp.fillRect(startPointX,startPointY,stopPointX-startPointX,stopPointY-startPointY);
        }

        if(round && fillColor){
            double radius = Math.min(Math.abs(stopPointX - startPointX), Math.abs(stopPointY - startPointY));

            if(stopPointX < startPointX && stopPointY < startPointY) gp.fillOval(startPointX-radius,startPointY-radius,radius,radius);
            else if(stopPointX > startPointX && stopPointY < startPointY) gp.fillOval(startPointX,startPointY-radius,radius,radius);
            else if(stopPointX < startPointX && stopPointY > startPointY) gp.fillOval(startPointX-radius,startPointY,radius,radius);
            else gp.fillOval(startPointX,startPointY,radius,radius);
        }
        if(oval && fillColor){
            if(stopPointX < startPointX && stopPointY < startPointY) gp.fillOval(stopPointX,stopPointY,startPointX-stopPointX,startPointY-stopPointY);
            else if(stopPointX > startPointX && stopPointY < startPointY) gp.fillOval(startPointX,stopPointY,stopPointX-startPointX,startPointY-stopPointY);
            else if(stopPointX < startPointX && stopPointY > startPointY) gp.fillOval(stopPointX,startPointY,startPointX-stopPointX,stopPointY -startPointY);
            else gp.fillOval(startPointX,startPointY,stopPointX-startPointX,stopPointY-startPointY);
        }
    }

    public void cnvOnMouseDragged(MouseEvent mouseEvent) {
        stopPointX = mouseEvent.getX();
        stopPointY = mouseEvent.getY();
        if(rect){

            if(stopPointX < startPointX && stopPointY < startPointY) {
                gp.clearRect(0,0,cnvMain.getWidth(), cnvMain.getHeight());
                gp.strokeRect(stopPointX, stopPointY, startPointX - stopPointX, startPointY - stopPointY);
            }
            else if(stopPointX > startPointX && stopPointY < startPointY) {
                gp.clearRect(0,0,cnvMain.getWidth(), cnvMain.getHeight());
                gp.strokeRect(startPointX, stopPointY, stopPointX - startPointX, startPointY - stopPointY);
            }
            else if(stopPointX < startPointX && stopPointY > startPointY) {
                gp.clearRect(0,0,cnvMain.getWidth(), cnvMain.getHeight());
                gp.strokeRect(stopPointX, startPointY, startPointX - stopPointX, stopPointY - startPointY);
            }
            else {
                gp.clearRect(0,0,cnvMain.getWidth(), cnvMain.getHeight());
                gp.strokeRect(startPointX, startPointY, stopPointX - startPointX, stopPointY - startPointY);
            }

        }
        if(round ){
            double radius = Math.min(Math.abs(stopPointX - startPointX), Math.abs(stopPointY - startPointY));
            gp.clearRect(0,0,cnvMain.getWidth(), cnvMain.getHeight());
            if(stopPointX < startPointX && stopPointY < startPointY) gp.strokeOval(startPointX-radius,startPointY-radius,radius,radius);
            else if(stopPointX > startPointX && stopPointY < startPointY) gp.strokeOval(startPointX,startPointY-radius,radius,radius);
            else if(stopPointX < startPointX && stopPointY > startPointY) gp.strokeOval(startPointX-radius,startPointY,radius,radius);
            else gp.strokeOval(startPointX,startPointY,radius,radius);
        }
        if(oval){
            gp.clearRect(0,0,cnvMain.getWidth(), cnvMain.getHeight());
            if(stopPointX < startPointX && stopPointY < startPointY) gp.strokeOval(stopPointX,stopPointY,startPointX-stopPointX,startPointY-stopPointY);
            else if(stopPointX > startPointX && stopPointY < startPointY) gp.strokeOval(startPointX,stopPointY,stopPointX-startPointX,startPointY-stopPointY);
            else if(stopPointX < startPointX && stopPointY > startPointY) gp.strokeOval(stopPointX,startPointY,startPointX-stopPointX,stopPointY -startPointY);
            else gp.strokeOval(startPointX,startPointY,stopPointX-startPointX,stopPointY-startPointY);
        }
        if(pencil){
            gp.strokeLine(startPointX,startPointY,stopPointX,stopPointY);
            startPointX = stopPointX;
            startPointY = stopPointY;

        }
        if(eraser){
            gp.clearRect(mouseEvent.getX(),mouseEvent.getY(),image.getWidth(), image.getHeight());

            System.out.println(cnvMain.getLayoutX()+" "+cnvMain.getLayoutY()+ " "+image.getWidth() +" " +  image.getHeight());
        }


    }

    public void cnvOnMousePressed(MouseEvent mouseEvent) {
        startPointX = mouseEvent.getX();
        startPointY = mouseEvent.getY();

//        cnv = new Canvas(root.getWidth(), root.getHeight());
//        gp = cnv.getGraphicsContext2D();

        if (text){
            gp.clearRect(0,0,root.getWidth(),root.getHeight());
            textFromTyping = "";
            lblText.setText("");

            cnvMain.requestFocus();
            lblText.setVisible(false);
            rectBlink.setVisible(true);

            rectBlink.setX(startPointX + 3);
            rectBlink.setY(startPointY -10);

            timeline.playFromStart();
        }

    }

    public void rootOnMouseDragged(MouseEvent mouseEvent) {
        cnvMain.setWidth(root.getWidth());
        cnvMain.setHeight(root.getHeight());
    }

    public void rootOnMousePressed(MouseEvent mouseEvent) {
    }

    public void rootOnMouseRelesed(MouseEvent mouseEvent) {

    }

    public void btnRectOnMouseEnterd(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnRect);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.1);
        scaleTransition.play();

    }

    public void btnRectOnMouseExited(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnRect);
        scaleTransition.setFromX(1.05);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    public void btnRoundOnMouseEnterd(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnRound);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    public void btnRoundOnMouseExited(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnRound);
        scaleTransition.setFromX(1.05);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    public void btnOvelOnMouseEnterd(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnOvel);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    public void btnOvelOnMouseExited(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnOvel);
        scaleTransition.setFromX(1.05);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    public void btnPencilOnMouseEnterd(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnPencil);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    public void btnPencilOnMouseExited(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnPencil);
        scaleTransition.setFromX(1.05);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    public void btnEresorOnMouseEnterd(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnEreser);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    public void btnEresorOnMouseExited(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnEreser);
        scaleTransition.setFromX(1.05);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    public void btnTextOnMouseEnterd(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnText);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    public void btnTextOnMouseExited(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), btnText);
        scaleTransition.setFromX(1.05);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    public void btnStrockOnMouseEnterd(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), clrStroke);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    public void btnStrockOnMouseExited(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), clrStroke);
        scaleTransition.setFromX(1.05);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    public void btnFillOnMouseEnterd(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), clrFill);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    public void btnFillOnMouseExited(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), clrFill);
        scaleTransition.setFromX(1.05);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    public void btnTextOnMousePressed(MouseEvent mouseEvent) {

    }

    public void lblTextOnKeyPressed(KeyEvent keyEvent) {
    }

    int i = 1;
    public void cnvMainOnKeyPressed(KeyEvent keyEvent) {
        if(text){
            if (keyEvent.getCode() == KeyCode.ENTER) {
                btnText.fire();
            }
            if (keyEvent.getCode().getCode() == 8) {
                textFromTyping = textFromTyping.substring(0, textFromTyping.length() - 1);

                gp.clearRect(0, 0, root.getWidth(), root.getHeight());

                lblText.setText(textFromTyping);
                gp.fillText(textFromTyping, startPointX, startPointY);

                rectBlink.setX(startPointX + lblText.getWidth() - 4);
                return;
            }
            textFromTyping = textFromTyping + keyEvent.getText();
            System.out.println(keyEvent.getCode());
            System.out.println(textFromTyping);

            lblText.setText(textFromTyping);
            gp.fillText(textFromTyping, startPointX, startPointY);

            rectBlink.setX(startPointX + lblText.getWidth() + 4);
        }


//        KeyFrame key1 = new KeyFrame(Duration.seconds(0), event -> {
//            rectBlink.setVisible(true);
//        });
//        KeyFrame key2 = new KeyFrame(Duration.seconds(0.75), event -> {
//            rectBlink.setVisible(false);
//        });
//        KeyFrame key3 = new KeyFrame(Duration.seconds(1.5), event -> {
//            rectBlink.setVisible(true);
//        });
//        Timeline timeline = new Timeline(key1,key2,key3);
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.playFromStart();
    }
}
