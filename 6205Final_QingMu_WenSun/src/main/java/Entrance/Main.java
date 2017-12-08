package Entrance;

import GeneticModel.Generation;
import Configuration.Configuration;
import GeneticModel.School;

public class Main {
	public static void main( String[] args )
    {
        Generation g0 = Configuration.configureFirstGeneration();
        //System.out.println(g0.getGeneration().get(10).getFitness());
        Generation previousGeneration = g0;
        
        while(School.findflag == 0) {
        	Generation nextGeneration = previousGeneration.crossover();
                System.out.println("Generation: " + nextGeneration.getGenerationID());
                System.out.println("    Average: " + nextGeneration.getAverage());
        	previousGeneration = nextGeneration;
        }
        
    }
	
}
