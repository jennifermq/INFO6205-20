package GeneticModel;

import java.util.ArrayList;
import java.util.HashMap;

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
		double fitness = 0;
		//TODO
		return fitness;
	}
	
	public void initHash() {
		//TODO
	}
	
	public void add(Timeslot slot) {
		schedule.add(slot);
	}
	
	private ArrayList<Timeslot> schedule;
	private int crossoverPoints;
	private int mutationProbability;
	private HashMap<CourseClass,Timeslot> hash; //key:CourseClass;value:Timeslot
}
