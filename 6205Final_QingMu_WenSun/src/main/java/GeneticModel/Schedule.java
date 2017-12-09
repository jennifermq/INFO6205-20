package GeneticModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
		else {
			//System.out.println("Course " + course.getCourseID() + ": Room Capacity violation");
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
                        //System.out.println("Course " + course.getCourseID() + ": Right time violation");
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
                    if(!(slot.getClassroom().equals(t2.getClassroom()))) {
                        continue;
                    }
                    else {
                    	/*
                        if(slot.getDay() != t2.getDay()) {
                            continue;
                        }
                        
                        else {
                            int startTime2 = t2.getStart();
                            if(startTime == startTime2) {
                                overlapflag = 0;
                                break;
                            }
                            else if(startTime < startTime2) {
                                if(endSlot >= t2.getID()) {
                                    overlapflag = 0;
                                    break;
                                }
                            }
                            else {
                                if(t2.getID() + course.getDuration()-1 >= startSlot) {
                                    overlapflag = 0;
                                    break;
                                }
                            }
                        }
                        */
                    	if(Math.abs(slot.getDay() - t2.getDay())>=2) {
                            continue;
                        }
                        else {
                            int id1 = slot.getID();
                            int id2 = t2.getID();
                            int d1 = course.getDuration();
                            int d2 = c2.getDuration();

                            ArrayList<Timeslot> list1 = new ArrayList<Timeslot>();
                            ArrayList<Timeslot> list2 = new ArrayList<Timeslot>();

                            for(int i=id1; i<id1+d1; i++) {
                                if(id1+d1 < Chromosome.getInstance().getList().size()) {
                                    Timeslot temp = Chromosome.getInstance().getList().get(i);
                                    list1.add(temp);
                                }else {
                                    break;
                                }
                            }
						
                            for(int i=id2; i<id2+d2; i++) {
                                if(id2+d2 < Chromosome.getInstance().getList().size()) {
                                    Timeslot temp = Chromosome.getInstance().getList().get(i);
                                    list2.add(temp);
                                }
                                else {
                                    break;
                                }
                            }
						
                            for(int i=0; i<list1.size() && overlapflag==1; i++) {
                                for(int j=0; j<list2.size() && overlapflag==1; j++) {
                                    if(list1.get(i).equals(list2.get(j))) {
                                        overlapflag = 0;
                                        //System.out.println("Course " + course.getCourseID() + ": Overlap violation");
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
            //System.out.println("---------------");
            fitness =  score/highest;
            //System.out.println("    fitness: " + fitness);
            if(score == highest) {
                System.out.println("Score: " + score + ", Full: " + highest);
                System.out.println("Fitness:" + fitness);
                School.findflag = 0;
                
                ArrayList<String> Monday = new ArrayList<String>();
                ArrayList<String> Tuesday = new ArrayList<String>();
                ArrayList<String> Wednesday = new ArrayList<String>();
                ArrayList<String> Thursday = new ArrayList<String>();
                ArrayList<String> Friday = new ArrayList<String>();

                for(Entry<CourseClass, Timeslot> e : hash.entrySet()) {
                    switch(e.getValue().getDay()) {
                        case 1: Monday.add(printClass(e));break;
                        case 2: Tuesday.add(printClass(e));break;
                        case 3: Wednesday.add(printClass(e));break;
                        case 4: Thursday.add(printClass(e));break;
                        case 5: Friday.add(printClass(e));break;
                    }
                }
                System.out.println("-------------------------\n"+"Monday:"); 
                logger.info("-------------------------\n"+"Monday:");    //info级别的信息
                for(String s : Monday)
                {
                    System.out.println(s+"\n");
                    logger.info(s+"\n");
                }
                System.out.println("-------------------------\n"+"Tuseday:");
                logger.info("-------------------------\n"+"Tuseday:");
                for(String s : Tuesday)
                {
                    System.out.println(s+"\n");
                    logger.info(s+"\n");
                }
                System.out.println("-------------------------\n"+"Wednesday:");
                logger.info("-------------------------\n"+"Wednesday:");
                for(String s : Wednesday)
                {
                    System.out.println(s+"\n");
                    logger.info(s+"\n");
                }
                System.out.println("-------------------------\n"+"Thursday:");
                logger.info("-------------------------\n"+"Thursday:");
                for(String s : Thursday)
                {
                    System.out.println(s+"\n");
                    logger.info(s+"\n");
                }
                System.out.println("-------------------------\n"+"Friday:");
                logger.info("-------------------------\n"+"Friday:");
                for(String s : Friday)
                {
                    System.out.println(s);
                    logger.info(s+"\n");
                }
            /*
            System.out.println();
            System.out.println("-------------------------");
            for(Entry<CourseClass, Timeslot> e : hash.entrySet()) {
                int endTime = e.getValue().getStart()+e.getKey().getDuration();
                    System.out.println("Course Class: " + e.getKey().toString());
                    System.out.println("    Time: " + e.getValue().getDay() + "  " + e.getValue().getStart() + ":00 - " +  endTime + ":00");
                    System.out.println("    Location: " + "Room " + e.getValue().getClassroom().getRoomID());
                    System.out.println("    Student Count: " + e.getKey().getStudentNumber());
                    System.out.println("    Room Capacity: " + e.getValue().getClassroom().getSeats());
            }
*/ 
            System.exit(0);
        }
    }
	
        private String printClass(Entry<CourseClass, Timeslot> e)
        {
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
            String s ="Course Class: " + e.getKey().toString()+"\n"+
                    "    Time: " + time1 + ":00 - " + time2 + ":00"+"\n"+
                    "    Location: " + "Room " + e.getValue().getClassroom().getRoomID()+"\n"+
                    "    Student Count: " + e.getKey().getStudentNumber()+"\n"+
                    "    Room Capacity: " + e.getValue().getClassroom().getSeats();
            return s;
        }
        
	private int crossoverPoints;
	private double mutationProbability;
	private double fitness;
	private HashMap<CourseClass,Timeslot> hash; //key:CourseClass;value:Timeslot
	private static Logger logger = LogManager.getLogger(Schedule.class.getName());
	public double getFitness() {
            return this.fitness;
	}
	
	
}
