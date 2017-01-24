package exceptionAndSocket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.concurrent.*;
import java.util.function.Predicate;

public class TeacherSetServer {

    private int port;
    private ArrayList<Teacher> teachers;
    private ServerSocket serverSocket;

    public TeacherSetServer(int port) {
        this(new ArrayList<Teacher>(), port);
    }

    public TeacherSetServer(ArrayList<Teacher> teachers, int port) {
        this.teachers = teachers;
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTestTeacher() {
        Teacher t1 = new Teacher("name1", 20, 2000, Grade.BIATTS);
        Teacher t2 = new Teacher("name2", 24, 3000, Grade.PUPH);
        Teacher t3 = new Teacher("name3", 26, 4000, Grade.MCU);
        Teacher t4 = new Teacher("name4", 28, 5000, Grade.MCUPH);
        Teacher t5 = new Teacher("name5", 30, 6000, Grade.BIATTS);
        addTeacher(t1);
        addTeacher(t2);
        addTeacher(t3);
        addTeacher(t4);
        addTeacher(t5);
    }

    public Teacher findTeacherByName(String name) {
        Predicate<Teacher> isFound = t -> t.getName().equals(name);
        return this.teachers.stream().filter(isFound).findFirst().get();
    }

    public int calculateAge(String name) throws TeacherNotFoundException {
        int age = this.teachers.stream().filter(t -> t.getName().equals(name)).findFirst().get().getAge();
        return age;
    }

    public double averageAge() throws TeacherNotFoundException {
        OptionalDouble doubleOptional = this.teachers.stream().mapToDouble(Teacher::getAge).average();
        if (doubleOptional.isPresent()) {
            return doubleOptional.getAsDouble();
        } else {
            throw new TeacherNotFoundException("No teacher with that name");
        }

    }

    public double averageSalary() throws TeacherNotFoundException {
        return this.teachers.stream().mapToDouble(Teacher::getSalary).average().getAsDouble();
    }

    public double averageByAge(int min, int max) {
        Predicate<Teacher> isBetween = t -> t.getAge() > min && t.getAge() < max;
        return this.teachers.stream().filter(isBetween).mapToDouble(Teacher::getAge).average().getAsDouble();
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public void modifyTeacher(Teacher teacher) {
        for (Teacher t : teachers) {
            if (teacher.equals(t)) {
                teachers.remove(t);
                teachers.add(teacher);
            }
        }
    }

    public boolean testNumberIsPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public void listen() {
        System.out.println("Server is listening at port: " + this.port);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("accept");
                ExecutorService pool = Executors.newFixedThreadPool(10);
                Runnable requestHandleTask = new RequestHandleTask(socket.getInputStream(), socket.getOutputStream(), this);
                pool.submit(requestHandleTask);

            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void listenNumberIsFirst() {
        System.out.println("Server is listening at port: " + this.port);
        while (true) {
            try {
                int taskNumber = 10;
                System.out.println("Socket waiting for connection.");
                Socket socket = serverSocket.accept();
                System.out.println("accept");
                ExecutorService pool = Executors.newFixedThreadPool(10);
                CompletionService<Boolean> completionService =
                        new ExecutorCompletionService<Boolean>(pool);



                for (int i = 0; i < taskNumber; i++) {
//                    completionService.submit(new NumberIsPrimeTask(socket.getOutputStream(), socket.getInputStream(), this));
                    completionService.submit(() -> {
                        DataInputStream in = new DataInputStream(socket.getInputStream());
                        synchronized (socket.getInputStream()) {
                            int number = in.readInt();
                            System.out.println(number);
                            return this.testNumberIsPrime(number);
                        }
                    });
                }


                System.out.println(taskNumber);
                for (int tasksHandled = 0; tasksHandled < taskNumber; tasksHandled++) {
                    try {
                        Future<Boolean> result = completionService.take();

                        Boolean b = result.get();
                        System.out.println("Task " + String.valueOf(tasksHandled) + " Completed - results obtained : " + b);

                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                    } catch (ExecutionException e) {
//                        e.printStackTrace();
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        int port = 33333;
        TeacherSetServer server = new TeacherSetServer(port);
        server.createTestTeacher();
//        server.listen();
        server.listenNumberIsFirst();
    }
}
