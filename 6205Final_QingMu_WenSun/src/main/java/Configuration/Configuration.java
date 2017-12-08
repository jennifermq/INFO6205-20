package Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import GeneticModel.Chromosome;
import GeneticModel.Classroom;
import GeneticModel.CourseClass;
import GeneticModel.Generation;
import GeneticModel.Schedule;
import GeneticModel.School;
import GeneticModel.Timeslot;

public class Configuration {
	//在第一代中随机产生一个schedule的时候，相当于是遍历每一门课然后给这门课分配duration个连续的slot。
	public Configuration() {
		
	}
	
	public static Generation configureFirstGeneration() {
		School school = School.getInstance();
		
		CourseClass c1 = new CourseClass(1,"Algorithm",50,2);
		CourseClass c2 = new CourseClass(2,"Database",80,3);
		CourseClass c3 = new CourseClass(3,"Object-Oriented Design",100,3);
		CourseClass c4 = new CourseClass(4,"Big Data",60,2);
                CourseClass c5 = new CourseClass(5,"Web Design",80,3);
                CourseClass c6 = new CourseClass(6,"Web Tools",60,3);
                CourseClass c7 = new CourseClass(7,"User Experience Design",90,2);
                CourseClass c8 = new CourseClass(8,"Application Engineering Development",200,3);
                CourseClass c9 = new CourseClass(9,"Cloud Computing",90,3);
                CourseClass c10 = new CourseClass(10,"SmartPhone Application Development",50,2);
                CourseClass c11 = new CourseClass(11,"Lineal Algebra",150,3);
                CourseClass c12 = new CourseClass(12,"Advanced Mathematics",150,3);
		school.addCourseClass(c1);
		school.addCourseClass(c2);
		school.addCourseClass(c3);
		school.addCourseClass(c4);
                school.addCourseClass(c5);
                school.addCourseClass(c6);
                school.addCourseClass(c7);
                school.addCourseClass(c8);
                school.addCourseClass(c9);
                school.addCourseClass(c10);
                school.addCourseClass(c11);
                school.addCourseClass(c12);
		
		Classroom r1 = new Classroom(1,60);
		Classroom r2 = new Classroom(2,80);
		Classroom r3 = new Classroom(3,120);
                Classroom r4 = new Classroom(3,200);
		school.addClassroom(r1);
		school.addClassroom(r2);
		school.addClassroom(r3);
                school.addClassroom(r4);
		
		Chromosome chromosome = Chromosome.getInstance();
		chromosome.setList();
           
		
		Generation g0 = new Generation(100);
		System.out.println("Generation: " + g0.getGenerationID());
		for(int i=0; i<g0.getScheduleNumber(); i++) {
			HashMap<CourseClass,Timeslot> hash = new HashMap<CourseClass,Timeslot>();
			ArrayList<CourseClass> classList = school.getClassList();
			for(CourseClass course: classList) {
				int randSlot = (int) (Math.random()*chromosome.getList().size());
				hash.put(course, chromosome.getList().get(randSlot));
			}
			double mutationProbability = 0.3;
			Schedule schedule = new Schedule(hash,2,mutationProbability);
			//System.out.println(i + ": " + schedule.getFitness());
			
                        
			for(Entry<CourseClass, Timeslot> e : hash.entrySet()) {
				String day = "";
				switch(e.getValue().getDay()) {
					case 1: day="Monday"; break;
					case 2: day="Tuesday";break;
					case 3: day="Wednesday";break;
					case 4: day="Thursday";break;
					case 5: day="Friday";break;
				}
				int time1 = e.getValue().getStart();
				int time2 = e.getValue().getStart() + e.getKey().getDuration();
                                /*
				System.out.println("Course Class: " + e.getKey().toString());
				System.out.println("    Time: " + day + "  " + time1 + ":00 - " + time2 + ":00");
				System.out.println("    Location: " + "Room " + e.getValue().getClassroom().getRoomID());
                                System.out.println("Student Count: " + e.getKey().getStudentNumber());
                                System.out.println("Room Capacity: " + e.getValue().getClassroom().getSeats());
*/
			}
			//System.out.println();
			
			g0.getGeneration().add(schedule);
		}
		return g0;
	}
}
