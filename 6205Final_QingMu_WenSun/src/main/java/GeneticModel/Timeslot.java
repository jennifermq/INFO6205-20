package GeneticModel;

import java.util.ArrayList;

public class Timeslot {
	public Timeslot(int id, int day, int start, Classroom room) {
		this.id = id;
		this.start = start;
		this.day = day;
		//this.end = end;
		this.classroom = room;
		//this.classList = new ArrayList<CourseClass>();
	}
	
	/*
	public ArrayList<CourseClass> getCourseClassList() {
		return this.classList;
	}
	*/
	public Classroom getClassroom() {
		return this.classroom;
	}
	
	public int getDay() {
		return this.day;
	}
	
	public int getStart() {
		return this.start;
	}
	public int getID() {
		return this.id;
	}
	/*
	public void addCourseClass(CourseClass c) {
		classList.add(c);
	}
	
	public int getCourseCount() {
		return classList.size();
	}
	*/
	
	private int start; //start time of the time slot
	//private int end; //end time of the time slot
	//private ArrayList<CourseClass> classList;
	private Classroom classroom;
	private int day; //day of the week of the timeslot(1-5)
	private int id;
	
	
}
