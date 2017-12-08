package Entrance;

import GeneticModel.Generation;
import Configuration.Configuration;

public class Main {
	public static void main( String[] args )
    {
        Generation g0 = Configuration.configureFirstGeneration();
        //System.out.println(g0.getGeneration().get(10).getFitness());
        Generation previousGeneration = g0;
        
        while(true) {
        	System.out.println("Generation: " + previousGeneration.getGenerationID());
        	Generation nextGeneration = previousGeneration.crossover();
        	previousGeneration = nextGeneration;
        }
        
    }
	
}
