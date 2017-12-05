package GeneticModel;

public class CourseClass {
	public CourseClass(int classID, int studentNumber, int duration) {
		this.classID = classID;
		this.studentNumber = studentNumber;
		this.duration = duration;
	}
	
	public int getStudentNumber() {
		return this.studentNumber;
	}
	
	private int classID;
	private int studentNumber;
	private int duration;
}
