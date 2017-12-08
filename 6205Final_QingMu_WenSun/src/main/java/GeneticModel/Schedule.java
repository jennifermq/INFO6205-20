package GeneticModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Schedule {
	public Schedule() {
		//schedule = new ArrayList<Timeslot> ();
		hash = new HashMap<CourseClass,Timeslot>();
	}
	
	public Schedule(HashMap <CourseClass,Timeslot> hash, int crossoverPoints, double mutationProbability) {
		//this.schedule = list;
		this.crossoverPoints = crossoverPoints;
		this.mutationProbability = mutationProbability;
		this.hash = hash;
		fitness();
                
	}

	/*
	public double getFitness() {
		fitness();
		return this.fitness;
	}
	*/
	
	public int getCrossoverPoints() {
		return this.crossoverPoints;
	}
	
	public double getMutationProbability() {
		// TODO Auto-generated method stub
		return this.mutationProbability;
	}
	
	public HashMap<CourseClass,Timeslot> getHash() {
		return this.hash;
	}
	
	public void fitness() {
            double score = 0;
            double highest = School.getInstance().getClassList().size()*3;
            Chromosome chromosome = Chromosome.getInstance();
            for(Entry<CourseClass, Timeslot> entry : hash.entrySet()){
		CourseClass course = entry.getKey();
		Timeslot slot = entry.getValue();
		
		if(slot.getClassroom().getSeats() >= course.getStudentNumber()) {
                    score ++;
		}
		//Seats capability satisfies the demand of the class
		
                int samedayflag = 1;
		int startTime = slot.getStart();
                int startSlot = slot.getID();
                int endSlot = startSlot + course.getDuration()-1;
		if(endSlot>=chromosome.getList().size()) {
                    samedayflag = 0;
                }
                else {
                    if(slot.getDay() != chromosome.getList().get(endSlot).getDay()) {
                        samedayflag = 0;
                    }
                }
                if(samedayflag == 1) {
                    score ++;
                }
                //class duration is not splitted into two days
			
                int overlapflag = 1;
                for(Entry<CourseClass, Timeslot> entry2 : hash.entrySet()){
                    if(entry.equals(entry2)) {
                        continue;
                    }
                    CourseClass c2 = entry2.getKey();
                    Timeslot t2 = entry2.getValue();
                    if(!slot.getClassroom().equals(t2.getClassroom())) {
                        continue;
                    }
                    else {
                        if(Math.abs(slot.getDay() - t2.getDay()) >= 2) {
                            continue;
                        }
                        else {
                            int id1 = slot.getID();
                            int id2 = t2.getID();
                            int d1 = course.getDuration();
                            int d2 = c2.getDuration();

                            ArrayList<Timeslot> list1 = new ArrayList<Timeslot>();
                            ArrayList<Timeslot> list2 = new ArrayList<Timeslot>();

                            for(int i=id1; i<=id1+d1; i++) {
                                if(id1+d1 <= Chromosome.getInstance().getList().size()-1) {
                                    Timeslot temp = Chromosome.getInstance().getList().get(i);
                                    list1.add(temp);
                                }else {
                                    break;
                                }
                            }
						
                            for(int i=id2; i<=id2+d2; i++) {
                                if(id2+d2 <= Chromosome.getInstance().getList().size()-1) {
                                    Timeslot temp = Chromosome.getInstance().getList().get(i);
                                    list2.add(temp);
                                }
                                else {
                                    break;
                                }
                            }
						
                            for(int i=0; i<list1.size()&&overlapflag==1; i++) {
                                for(int j=0; j<list2.size()&&overlapflag==1; j++) {
                                    if(chromosome.getList().get(i).equals(chromosome.getList().get(j))) {
                                        overlapflag = 0;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if(overlapflag == 1) {
                    score ++;
                }
			//classes do not overlap each other in the same classroom
            }
            fitness =  score/highest;
            //System.out.println("    fitness: " + fitness);
            if(score == highest) {
                System.out.println("Score: " + score + ", Full: " + highest);
                System.out.println("Fitness:" + fitness);
                School.findflag = 0;
                    //print the result
                    //System.out.println(fitness);
                for(Entry<CourseClass, Timeslot> e : hash.entrySet()) {
                    String day = "";
                    switch(e.getValue().getDay()) {
                        case 1: day="Monday";break;
                        case 2: day="Tuesday";break;
                        case 3: day="Wednesday";break;
                        case 4: day="Thursday";break;
                        case 5: day="Friday";break;
                    }
                    int time1 = e.getValue().getStart();
                    int time2 = e.getValue().getStart() + e.getKey().getDuration();

                    System.out.println("Course Class: " + e.getKey().toString());
                    System.out.println("    Time: " + day + "  " + time1 + ":00 - " + time2 + ":00");
                    System.out.println("    Location: " + "Room " + e.getValue().getClassroom().getRoomID());
                    System.out.println("    Student Count: " + e.getKey().getStudentNumber());
                    System.out.println("    Room Capacity: " + e.getValue().getClassroom().getSeats());
                }
                System.exit(0);
            }
        }
	
	private int crossoverPoints;
	private double mutationProbability;
	private double fitness;
	private HashMap<CourseClass,Timeslot> hash; //key:CourseClass;value:Timeslot
	public double getFitness() {
            return this.fitness;
	}
	
	
}
