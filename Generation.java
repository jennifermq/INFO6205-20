package GeneticModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

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
            //20 kids to be born per pair
            while(next.getGeneration().size()<next.getScheduleNumber())
            {
                int f = (int)(Math.random()*scheduleNumber);
                int m = (int)(Math.random()*scheduleNumber);
                while(f==m)
                {
                    m=(int)(Math.random()*scheduleNumber);
                }
                next.getGeneration().add(crossover(f,m));
                next.getGeneration().add(crossover(m,f));
            }
            return next;
	}
        
        private Schedule crossover(int f,int m)
        {
            School school = School.getInstance();
            Schedule father = generation.get(f);
            Schedule mother = generation.get(m);
            
            int breakpointNum = father.getCrossoverPoints();
            
            int b1 = (int)(Math.random()*(father.getHash().size()-1));
            int b2 = (int)(Math.random()*(father.getHash().size()-1));
            while(b2<=b1)
                b2 = (int)(Math.random()*(father.getHash().size()-1));
            
            HashMap<CourseClass,Timeslot> kidHash = new HashMap<CourseClass,Timeslot>();
            int i = 0;
            for(;i<b1;i++)
                kidHash.put(school.getClassList().get(i), father.getHash().get(school.getClassList().get(i)));
            for(;i<b2;i++)
                kidHash.put(school.getClassList().get(i), mother.getHash().get(school.getClassList().get(i)));
            for(;i<father.getHash().size();i++)
                kidHash.put(school.getClassList().get(i), father.getHash().get(school.getClassList().get(i)));

            mutate(kidHash,(father.getMutationProbability()+mother.getMutationProbability())/2);
            Schedule kid = new Schedule(kidHash,father.getCrossoverPoints(),(father.getMutationProbability()+mother.getMutationProbability())/2);
            
            return kid;
        }
        
        private void mutate(HashMap<CourseClass,Timeslot> kid,double prob)
        {
            Chromosome chro = Chromosome.getInstance();
            if(Math.random()<prob)
            {
                int mut = (int)Math.random()*(kid.size());
                int val = (int)Math.random()*(chro.getList().size());
                kid.put(mut,chro.getList().get(val));
            }
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

    public ArrayList<Schedule> getGeneration() {
        return generation;
    }

    public int getScheduleNumber() {
        return scheduleNumber;
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