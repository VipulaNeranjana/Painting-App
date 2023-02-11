package lk.ijse.dep10.drawing.controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class MainFormController {

    public VBox vBox;
    public Canvas cnvMain;
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

    double startPointX;
    double startPointY;
    double stopPointX;
    double stopPointY;

    boolean strokeColor;
    boolean fillColor;

    public void initialize(){
        gp = cnvMain.getGraphicsContext2D();
    }

    @FXML
    void btnEreserOnAction(ActionEvent event) {

    }

    @FXML
    void btnOvelOnAction(ActionEvent event) {

    }

    @FXML
    void btnPencilOnAction(ActionEvent event) {

    }

    @FXML
    void btnRectOnAction(ActionEvent event) {
        rect = true;
    }

    @FXML
    void btnRoundOnAction(ActionEvent event) {

    }

    @FXML
    void btnTextOnAction(ActionEvent event) {

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
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500),lblDrag);
        scaleTransition.setFromX(1);
        scaleTransition.setFromY(1);
        scaleTransition.setToX(1.05);
        scaleTransition.setToY(1.1);
        scaleTransition.play();
    }

    public void lblDragOnMouseExited(MouseEvent mouseEvent) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500),lblDrag);
        scaleTransition.setFromX(1.05);
        scaleTransition.setFromY(1.1);
        scaleTransition.setToX(1);
        scaleTransition.setToY(1);
        scaleTransition.play();
    }

    public void lblDragOnReleased(MouseEvent mouseEvent) {
        lblDrag.setEffect(null);
    }

    public void cnvOnMouseClicked(MouseEvent mouseEvent) {




    }

    public void cnvOnMouseReleased(MouseEvent mouseEvent) {
        stopPointX = mouseEvent.getX();
        stopPointY = mouseEvent.getY();


        if (rect && fillColor){
            if(stopPointX < startPointX && stopPointY < startPointY) gp.fillRect(stopPointX,stopPointY,startPointX-stopPointX,startPointY-stopPointY);
            else if(stopPointX > startPointX && stopPointY < startPointY) gp.fillRect(startPointX,stopPointY,stopPointX-startPointX,startPointY-stopPointY);
            else if(stopPointX < startPointX && stopPointY > startPointY) gp.fillRect(stopPointX,startPointY,startPointX-stopPointX,stopPointY -startPointY);
            else gp.fillRect(startPointX,startPointY,stopPointX-startPointX,stopPointY-startPointY);
        }
    }

    public void cnvOnMouseDragged(MouseEvent mouseEvent) {
        if(rect){
            stopPointX = mouseEvent.getX();
            stopPointY = mouseEvent.getY();

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
    }

    public void cnvOnMousePressed(MouseEvent mouseEvent) {
        startPointX = mouseEvent.getX();
        startPointY = mouseEvent.getY();

    }
}
