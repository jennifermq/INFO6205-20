package GeneticModel;

import java.util.ArrayList;

public class Chromosome {
	public Chromosome() {
		list = new ArrayList<Timeslot>();
	}
	
	public static Chromosome getInstance(){
        if(chromosome==null){
        	chromosome=new Chromosome();           
        }
        return chromosome;
    }
	
	public void setList() {
		for(Classroom room: School.getInstance().getRoomList()) {
			for(int day=1; day<=5; day++) {
				for(int hour=8; hour<21; hour++) {
					Timeslot slot = new Timeslot(day,hour,room);
					list.add(slot);
				}
			}
		}
	}
	
	
	private static Chromosome chromosome;
	private ArrayList<Timeslot> list;
	
	
}
