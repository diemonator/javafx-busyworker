/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxbusy;

import java.util.Calendar;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BusyApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Label label1;
    private Label label2;

    @Override
    public void start(Stage primaryStage) {
        // Initiation
        VBox vBox = new VBox();
        label1 = new Label();
        label2 = new Label("Thread Experiment");
        btn1 = new Button("show current time");
        btn2 = new Button("Work on same Tread");
        btn3 = new Button("Work on other Thread");
        btn4 = new Button("Stop tread");
        ButtonHandler handler = new ButtonHandler();
        Scene scene = new Scene(vBox, 500, 500);
        // Set Visual Stuff
        vBox.setSpacing(8);
        vBox.setPadding(new Insets(30));
        vBox.getChildren().addAll(label1, label2, btn1, btn2, btn3, btn4);
        // Setting stage
        primaryStage.setTitle("BusyWorker");
        primaryStage.setScene(scene);
        primaryStage.show();
        // Onclick Actions
        btn1.setOnAction(handler);
        btn2.setOnAction(handler);
        btn3.setOnAction(handler);
        btn4.setOnAction(handler);
        btn1.fire();
        new ThreadOverview();
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        Thread thread;
        @Override
        public void handle(ActionEvent event) {
            BusyWorker busyWorker;
            if (event.getSource() == btn1) {
                label1.setText(Calendar.getInstance().getTime().toString() + "; milliseconds: " + Calendar.getInstance().get(Calendar.MILLISECOND));
            } else if (event.getSource() == btn2) {
                busyWorker = new BusyWorker(5000);
                busyWorker.busyJob();
            } else if (event.getSource() == btn3) {
                busyWorker = new BusyWorker(50000, label2);
                thread = new Thread(busyWorker);
                thread.start();
            } else if (event.getSource() == btn4 && thread != null) {
                thread.interrupt();
            }
        }
    }
}
