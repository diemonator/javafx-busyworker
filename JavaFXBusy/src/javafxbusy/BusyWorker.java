/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxbusy;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Calendar;

public class BusyWorker implements Runnable {

    private UpdateLabel updateLabel;

    private int workToDo;

    BusyWorker(int w) { this.workToDo = w; }

    BusyWorker(int w, Label label)
    {
        this.workToDo = w;
        updateLabel = new UpdateLabel(label);
    }

    public void busyJob() {
        long endTime = System.currentTimeMillis() + workToDo;
        System.out.println("busy-worker started for " + workToDo + " ms");
        try {
            while (System.currentTimeMillis() < endTime && !Thread.currentThread().isInterrupted()) {
                if (updateLabel != null) Platform.runLater(updateLabel);
                Thread.sleep(10);
            }
        } catch (InterruptedException ignored) { }

        System.out.println("busy-worker ready");
    }

    @Override
    public void run() {
        busyJob();
    }

    class UpdateLabel implements Runnable {

        private Label label;

        private UpdateLabel(Label label) {
            this.label = label;
        }

        @Override
        public void run() {
            label.setText(Calendar.getInstance().getTime().toString() + "; milliseconds: " + Calendar.getInstance().get(Calendar.MILLISECOND));
        }
    }
}

