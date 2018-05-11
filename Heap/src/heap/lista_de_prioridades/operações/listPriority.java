/**
 * @author Ancelmo de Souza Lopes
 * */

package heap.lista_de_prioridades.operações;

import java.util.ArrayList;
import java.util.Arrays;

public class listPriority {
	
	public static void main(String[] args) {
		HeapMin heap = new HeapMin();
		int[] vectorTeste = new int[15];
		
		for( int index = 0; index < 15; index++ ) { 
			if(index % 2 == 0) {
				vectorTeste[index] = (2 * index) + 1;
			}
			else {
				vectorTeste[index] = (2 * index) - 1;
			}
		}
		
		heap.show();
		
		System.exit(0);
	}
}
