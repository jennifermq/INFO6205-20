package GeneticModel;

import java.util.ArrayList;

public class Generation {
	public Generation() {
		generation = new ArrayList<Schedule> ();
	}
	
	public Generation crossover() {
		Generation next = new Generation();
		//TODO
		return next;
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
