package twentytwo;

public class Info {
    private String subject_name;
    private int mark;
    private String teacher_name;

    public Info(String subject_name, int mark, String teacher_name) {
        this.subject_name = subject_name;
        this.mark = mark;
        this.teacher_name = teacher_name;
    }

    @Override
    public String toString() {
        return  "subject_name='" + subject_name + '\'' +
                ", mark=" + mark +
                ", teacher_name='" + teacher_name + '\'';
    }
}
