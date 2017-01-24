package exceptionAndSocket;

import java.io.*;
import java.net.Socket;

public class Client {
    private OutputStream out;

    public Client(OutputStream out) {
        this.out = out;
    }

    public void addTeacher() throws IOException {
        // add teacher
        Teacher newTeacher = new Teacher("name6", 27, 3600, Grade.BIATTS);
        Action action = new Action(ActionType.ADD, newTeacher);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.out);
        objectOutputStream.writeObject(action);
        objectOutputStream.flush();
    }

    public void calculateAverageAge() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.out);
        Action actionCalculateAverage = new Action(ActionType.AVERAGE_AGE);
        objectOutputStream.writeObject(actionCalculateAverage);
        objectOutputStream.flush();
    }


    public void calculateAge(String name) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.out);
        // calculate age
        Action actionCalculateAge = new Action(ActionType.CALCULATE_AGE, name);
        objectOutputStream.writeObject(actionCalculateAge);
        objectOutputStream.flush();
    }

    public void testNumberIsFirst() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(this.out);
        // calculate age
        Action actionCalculateAge = new Action(ActionType.TEST_NUMBER_IS_FIRST, 1);
        objectOutputStream.writeObject(actionCalculateAge);
        objectOutputStream.flush();
    }

    public void sendNumberToServer(int number) throws IOException {
        DataOutputStream out = new DataOutputStream(this.out);
        out.writeInt(number);
        out.flush();
    }

    public static void main(String[] args) {
        Socket connection = null;
        try {
            connection = new Socket("localhost", 33333);

//            InputThread inputThread = new InputThread(connection.getInputStream());
//            inputThread.start();

            Client client = new Client(connection.getOutputStream());
//            client.addTeacher();
//            client.calculateAge("name1");
//            client.calculateAverageAge();
//            client.testNumberIsFirst();
            for (int i = 0; i < 10; i++) {
                client.sendNumberToServer(i);
            }
//            connection.shutdownOutput();

//			Writer output = new OutputStreamWriter(connection.getOutputStream());
//			output.write("Hello");

//            output.flush();

        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
