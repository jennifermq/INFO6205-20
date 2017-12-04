package GeneticModel;

public class Timeslot {
	public Timeslot(int start, int end, CourseClass courseClass, Classroom room) {
		this.start = start;
		this.end = end;
		this.courseClass = courseClass;
		this.classroom = room;
		
	}
	
	private int start; //start time of the time slot
	private int end; //end time of the time slot
	private CourseClass courseClass;
	private Classroom classroom;
	//private int day; //day of the week of the timeslot(1-5)
	
	
}
