package GeneticModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Generation {
	public Generation(int number) {
		generation = new ArrayList<Schedule> ();
		this.scheduleNumber = number;
			generationID = count;
            count++;
                
	}

	public Generation crossover() {
		Generation next = new Generation(scheduleNumber);
		//TODO
            ArrayList<Schedule> parents = this.findTopTenPercent();
            //Generation newGeneration = new Generation();
            //20 kids to be born per pair
            for(int i=0;i<5;i++)
            {
                    
            }
		return next;
	}

	public ArrayList<Schedule> findTopTenPercent() {
		ArrayList<Schedule> topten  = new ArrayList<Schedule>();
                int num = scheduleNumber/10;
                //TODO
                Collections.sort(generation,new FitnessComparator());
                for(int i= 0;i<num;i++)
                    topten.add(generation.get(i));
		return topten;
	}
        
	
	private ArrayList<Schedule> generation;
	private int generationID;
    private static int count=0;
	private int scheduleNumber; // number of schedules in the generation.
}

class FitnessComparator implements Comparator<Schedule>{
            public int compare(Schedule s1, Schedule s2) {
                if(s1.fitness()>s2.fitness())
                    return 1;
                else if(s1.fitness()==s2.fitness())
                    return 0;
                else
                    return -1;
            }           
        }