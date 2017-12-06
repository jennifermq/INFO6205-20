package Entrance;

import GeneticModel.Generation;
import Configuration.Configuration;

public class Main {
	public static void main( String[] args )
    {
        Generation g0 = Configuration.configureFirstGeneration();
        Generation previousGeneration = g0;
        while(true) {
        	Generation nextGeneration = previousGeneration.crossover();
        	previousGeneration = nextGeneration;
        }
    }
	
}
