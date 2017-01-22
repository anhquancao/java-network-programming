package exceptionAndSocket;

import java.io.*;
import java.net.SocketException;

public class RequestHandleThread implements Runnable {

    private InputStream in;
    private OutputStream out;
    private TeacherSetServer server;

    public RequestHandleThread(InputStream in, OutputStream out, TeacherSetServer server) {
        this.in = in;
        this.out = out;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            Teacher teacher;
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            OutputStreamWriter writer = new OutputStreamWriter(this.out, "UTF8");

            while (true) {
                try {
                    String message = "";
                    Action action = (Action) objectInputStream.readObject();
                    switch (action.getType()) {
                        case TEST_NUMBER_IS_FIRST:
                            int number = (int) action.getData();
                            message = "Number is first: " + server.testNumberisFirst(number);
                            break;
                        case ADD:
                            teacher = (Teacher) action.getData();
                            server.addTeacher(teacher);
                            message = "Add: " + teacher;
                            System.out.println(message);

                            break;
                        case DELETE:
                            teacher = (Teacher) action.getData();
                            server.removeTeacher(teacher);
                            message = "Delete: " + teacher;
                            System.out.println(message);
                            break;
                        case MODIFY:
                            teacher = (Teacher) action.getData();
                            server.modifyTeacher(teacher);
                            message = "Modify: " + teacher;
                            System.out.println(message);
                            break;
                        case AVERAGE_AGE:
                            float averageAge = server.averageAge();
                            message = "Average age: " + averageAge;
                            System.out.println(message);
                            break;
                        case CALCULATE_AGE:
                            String name = (String) action.getData();
                            System.out.println(name);
                            int age = server.calculateAge(name);
                            message = "Age of teacher " + name + ": " + age;
                            System.out.println(message);
                        default:
                    }
                    writer.write(message);
                    writer.flush();
                } catch (EOFException e) {
                    break;
                }
            }

//            InputStreamReader reader = new InputStreamReader(in);
//            StringBuffer sb = new StringBuffer();
//            int c;
//            while ((c = reader.read()) != -1) {
//                sb.append((char) c);
//            }
//            System.out.println(sb);
        } catch (SocketException e) {
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                this.out.close();
                this.in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
