package com.company;

import java.time.DayOfWeek;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller();
        view.setController(controller);
        controller.setView(view);
//        controller.start();
                System.out.println(Direction.RIGHT);
    }
}
