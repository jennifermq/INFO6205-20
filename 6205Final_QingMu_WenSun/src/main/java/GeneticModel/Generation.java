package GeneticModel;

import java.util.ArrayList;

public class Generation {
	public Generation() {
		generation = new ArrayList<Schedule> ();
		//this.generationID = id;
		//this.scheduleNumber = count;
	}
	
	public void initGeneration() {
		//
		//TODO
	}
	
	public Generation crossover() {
		Generation next = new Generation();
		//TODO
		return next;
	}
	
	public int getScheduleNumber() {
		return this.scheduleNumber;
	}
	
	public void setScheduleNumber(int n) {
		this.scheduleNumber = n;
	}
	
	public void setGenerationID(int n) {
		this.generationID = n;
	}

	public ArrayList<Schedule> findTopTenPercent() {
		ArrayList<Schedule> topten  = new ArrayList<Schedule>();
		//TODO
		return topten;
	}
	
	private ArrayList<Schedule> generation;
	private int generationID;
	private int scheduleNumber; // number of schedules in the generation.
}
