package tp3.ex2.up13.monftp;

/**
 * Created by caoquan on 1/31/17.
 */
public class FTPContent {
    private String login;
    private String password;
    private String machine;
    private String fileName;
    private String file;

    public FTPContent(String login, String password, String machine, String fileName, String file) {
        this.login = login;
        this.password = password;
        this.machine = machine;
        this.fileName = fileName;
        this.file = file;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getMachine() {
        return machine;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFile() {
        return file;
    }

    @Override
    public String toString() {
        return "FTPContent{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", machine='" + machine + '\'' +
                ", fileName='" + fileName + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}
