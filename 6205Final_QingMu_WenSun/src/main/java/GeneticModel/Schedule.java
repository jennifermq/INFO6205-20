package GeneticModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Schedule {
	public Schedule() {
		schedule = new ArrayList<Timeslot> ();
		hash = new HashMap<CourseClass,Timeslot>();
	}
	
	public Schedule(ArrayList<Timeslot> list, int crossoverPoints, int mutationProbability) {
		this.schedule = list;
		this.crossoverPoints = crossoverPoints;
		this.mutationProbability = mutationProbability;
		hash = new HashMap<CourseClass,Timeslot>();
	}

	public double fitness() {
		double score = 0;
		double highest = 0;
		for(Entry<CourseClass, Timeslot> entry : hash.entrySet()){
			highest += 2;
			CourseClass course = entry.getKey();
			Timeslot slot = entry.getValue();
			if(slot.getClassroom().getSeats() >= course.getStudentNumber()) {
				score ++;
			}
			if(slot.getCourseCount()==1) {
				score ++;
			}
		}
		return score/highest;
	}
	
	public void initHash() {
		for(Timeslot slot: schedule) {
			for(CourseClass c: slot.getCourseClassList()) {
				if(hash.get(c).equals(null)) {
					hash.put(c, slot);
				}
			}
		}
	}
	
	public void initSchedule() {
		int roomCount = School.getInstance().getRoomCount();
		
		for(int i=0; i<roomCount; i++) {
			
		}
		for(Entry<CourseClass, Timeslot> entry : hash.entrySet()){
			
		}
	}
	
	public void add(Timeslot slot) {
		schedule.add(slot);
	}
	
	private ArrayList<Timeslot> schedule;
	private int crossoverPoints;
	private int mutationProbability;
	private double fitness;
	private HashMap<CourseClass,Timeslot> hash; //key:CourseClass;value:Timeslot
}
