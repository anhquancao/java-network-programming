package exceptionAndSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    public int calculateAge(String name) throws TeacherNotFoundException {
        for (Teacher teacher : teachers) {
            if (teacher.getName().equals(name)) {
                return teacher.getAge();
            }
        }
        throw new TeacherNotFoundException("Teacher not found");
    }

    public float averageAge() throws TeacherNotFoundException {
        int sum = 0;
        for (Teacher teacher : teachers) {
            sum += teacher.getAge();
        }
        if (sum == 0) {
            throw new TeacherNotFoundException("There is no teacher in the set");
        }
        return (float) sum / teachers.size();
    }

    public float averageSalary() throws TeacherNotFoundException {
        int sum = 0;
        for (Teacher teacher : teachers) {
            if (teacher.getAge() > 40 && teacher.getAge() < 60) {
                sum += teacher.getAge();
            }
        }
        if (sum == 0) {
            throw new TeacherNotFoundException("There is no teacher meet that criteria in the set");
        }
        return (float) sum / teachers.size();
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

    public boolean testNumberisFirst(int number) {
        return number == 1;
    }

    public void listen() {
        System.out.println("Server is listening at port: " + this.port);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("accept");
                ExecutorService pool = Executors.newFixedThreadPool(10);
                Runnable requestHandleTask = new RequestHandleThread(socket.getInputStream(), socket.getOutputStream(), this);
                pool.submit(requestHandleTask);

            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        int port = 33333;
        TeacherSetServer server = new TeacherSetServer(port);
        server.createTestTeacher();
        server.listen();

    }
}
