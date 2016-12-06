package game;

import java.util.Random;

public class CPUaction extends PokemonSuper {
	
	protected boolean cpuMove() {
		
		Random myRand = new Random();
		int choice = myRand.nextInt(2); 
//		System.out.println(choice); 
		boolean cpuMoveChoice[] = new boolean[2];
		cpuMoveChoice[0] = true; 
		cpuMoveChoice[1] = false; 
		
		return cpuMoveChoice[choice];
		
	}
	
	protected boolean cpuMoveHard(double[][] array, int[] playerr, int player, int[] cpuu, int cpu) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
		System.out.println(array[i][j]);
			}
		}
		if (array[cpuu[cpu]][playerr[player]] >= array[cpuu[cpu]][NORMAL]) {
			return false; 
		} else {
			return true; 
		}
	}
}
