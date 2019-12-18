package twentytwo;

public class Student {
    private int student_id;
    private String student_fio;

    public Student(int student_id, String student_fio) {
        this.student_id = student_id;
        this.student_fio = student_fio;
    }

    @Override
    public String toString() {
        return  "student_id=" + student_id +
                ", student_fio='" + student_fio + '\'' ;
    }
}
