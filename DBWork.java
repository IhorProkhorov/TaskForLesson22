package twentytwo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBWork {

    public static Statement connectionMethod() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?" +
                "useSSL=false&" +
                "allowPublicKeyRetrieval=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" +
                "&user=root&password=2379822WerS");
        Statement statement = connect.createStatement();
        return statement;
    }

    public static List getListOfAllStudents() throws SQLException, ClassNotFoundException {
        Statement statement = connectionMethod();
        ResultSet resultSet = statement.executeQuery("select student_id, student_fio from students_tb");
        List<Student> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(new Student(resultSet.getInt(1), resultSet.getString(2)));
        }
        connectionMethod().close();
        return list;
    }

    public static List getListOfStudentsFromSameGroup(int group) throws SQLException, ClassNotFoundException {
        Statement statement = connectionMethod();
        ResultSet resultSet = statement.executeQuery("select student_id, student_fio from students_tb where `group` =" + group);
        List<Student> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(new Student(resultSet.getInt(1), resultSet.getString(2)));
        }
        connectionMethod().close();
        return list;
    }

    public static List getListOfStudentsFromTheSameYear(int year) throws SQLException, ClassNotFoundException {
        Statement statement = connectionMethod();
        ResultSet resultSet = statement.executeQuery("select student_id, student_fio from students_tb where year_of_start =" + year);
        List<Student> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(new Student(resultSet.getInt(1), resultSet.getString(2)));
        }
        connectionMethod().close();
        return list;
    }

    public static List getInfoSubjectMarkTeacher(int student_id) throws SQLException, ClassNotFoundException {
        Statement statement = connectionMethod();
        ResultSet resultSet = statement.executeQuery("select sub.subject_name, m.mark, t.teacher_fio from students_tb as s\n" +
                "left join marks_tb as m on s.student_id = m.student_id\n" +
                "left join subjects_tb as sub on m.subject = sub.subject_id\n" +
                "left join teachers_tb as t on sub.teacher_id = t.teacher_id\n" +
                "where s.student_id =" + student_id);
        List<Info> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(new Info(resultSet.getString(1), resultSet.getInt(2), resultSet.getString(3)));
        }
        connectionMethod().close();
        return list;
    }

    public static double getAverageMarkOfStudent(int student_id) throws SQLException, ClassNotFoundException {
        Statement statement = connectionMethod();
        double avg = 0.00d;
        ResultSet resultSet = statement.executeQuery("select avg(m.mark) from students_tb as s\n" +
                "left join marks_tb as m on s.student_id = m.student_id\n" +
                "where s.student_id =" + student_id);
        while (resultSet.next()){
            avg = resultSet.getDouble(1);
        }
        connectionMethod().close();
        return avg;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        List<Student> listOfAllStudents = getListOfAllStudents();
        for (Student st: listOfAllStudents
        ) {
            System.out.println(st);
        }

        List<Student> listSameGroup = getListOfStudentsFromSameGroup(42019);
        for (Student st: listSameGroup
        ) {
            System.out.println(st);
        }

        List<Student> listSameYear = getListOfStudentsFromTheSameYear(2013);
        for (Student st: listSameYear
             ) {
            System.out.println(st);
       }
        List<Info> list = getInfoSubjectMarkTeacher(1);
        for (Info st: list
        ) {
            System.out.println(st);
        }

        System.out.println("Средний бал студента = " + getAverageMarkOfStudent(2));
    }
}
