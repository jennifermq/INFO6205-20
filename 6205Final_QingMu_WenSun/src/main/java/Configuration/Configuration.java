package Configuration;

import GeneticModel.Classroom;
import GeneticModel.CourseClass;
import GeneticModel.Generation;
import GeneticModel.School;

public class Configuration {
//在第一代中随机产生一个schedule的时候，相当于是遍历每一门课然后给这门课分配duration个连续的slot。
	public Configuration() {
		
	}
	
	
	
	
	public static Generation configureFirstGeneration() {
		School school = School.getInstance();
		
		CourseClass c1 = new CourseClass(1,50,2);
		CourseClass c2 = new CourseClass(2,80,3);
		CourseClass c3 = new CourseClass(3,100,3);
		CourseClass c4 = new CourseClass(4,60,2);
		school.addCourseClass(c1);
		school.addCourseClass(c2);
		school.addCourseClass(c3);
		school.addCourseClass(c4);
		
		Classroom r1 = new Classroom(1,60);
		Classroom r2 = new Classroom(1,80);
		Classroom r3 = new Classroom(1,120);
		school.addClassroom(r1);
		school.addClassroom(r2);
		school.addClassroom(r3);
		
		Generation g0 = new Generation();
		g0.setGenerationID(0);
		g0.setScheduleNumber(100);
		for(int i=0; i<g0.getScheduleNumber(); i++) {
			
		}
		return g0;
	}
}
