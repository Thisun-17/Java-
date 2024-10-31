public class Student {
    // These are the student's details
    private String studentId;
    private String name;
    private Module[] moduleMarks;  // An array to store up to 3 modules

    public Student(String studentId) {
        this.studentId = studentId;
        this.name = "";
        this.moduleMarks = new Module[3];
    }
    // Get the student's ID
    public String getStudentId() {
        return studentId;
    }
    // Get the student's name
    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setModule(int index, Module module) {
        if (index >= 0 && index < 3) {
            moduleMarks[index] = module;
        }
    }


    // Get the student's average mark
    public String getGrade() {
        double average = calculateAverage();
        if (average >= 80) return "Distinction";
        if (average >= 70) return "Merit";
        if (average >= 40) return "Pass";
        return "Fail";
    }

    public Module[] getModules() {

        return moduleMarks;
    }

    // This method does the actual calculation of the average
    public double calculateAverage() {
        double sum = 0;
        int count = 0;
        for (Module module : this.getModules()) {
            if (module != null) {
                sum += module.getMark();
                count++;
            }
        }
        return count > 0 ? sum / count : 0;
    }


}
