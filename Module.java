public class Module {
    private String name;
    private double mark;

    public Module(String name, double mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {

        return name;
    }

    public double getMark() {

        return mark;
    }

    public void setMark(double mark) {

        this.mark = mark;
    }
}
