package exceptionAndSocket;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket connection = null;
        try {
            connection = new Socket("localhost", 33333);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(connection.getOutputStream());

            InputThread inputThread = new InputThread(connection.getInputStream());
            inputThread.start();

            // add teacher
            Teacher newTeacher = new Teacher("name6", 27, 3600, Grade.BIATTS);
            Action action = new Action(ActionType.ADD, newTeacher);
            objectOutputStream.writeObject(action);
            objectOutputStream.flush();


            // calculate age
            Action actionCalculateAge = new Action(ActionType.CALCULATE_AGE, "name1");
            objectOutputStream.writeObject(actionCalculateAge);
            objectOutputStream.flush();

            // calculate average age
            Action actionCalculateAverage = new Action(ActionType.AVERAGE_AGE);
            objectOutputStream.writeObject(actionCalculateAverage);
            objectOutputStream.flush();


            connection.shutdownOutput();

//			Writer output = new OutputStreamWriter(connection.getOutputStream());
//			output.write("Hello");

//            output.flush();

        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
