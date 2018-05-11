package heap.lista_de_prioridades.operações;

import java.util.ArrayList;
import java.util.Arrays;

//Classe que define um heap de mínimo
public class HeapMin {
	private int capacity = 15; // Capacidade default do heap de mínimo
	private int size = 0;      // Tamanho do heap ##### usado para indicar a última posição livre do vetor
	
	int[] itens;  // Vetor de armazenamento dos itens do heap 
	
	//Construtor default da classe HeapMin
	public HeapMin() { 
		this.itens = new int[this.capacity];
	}
	
	/**
	 @Overload do construtor da classe HeapMin
	 */
	public HeapMin(int n) {
		this.itens = new int[n];
	}
	
	/**
	 * Métodos auxiliares da estrutura de dados
	 * 
	 * 
	 * */
	
	private int getLeftChildIndex(int index) { return 2 * (index + 1); }  //Retorna o índice do filho esquerdo
	private int getRigthChildIndex(int index) { return 2 * (index + 2);	} // Retorna o índice do filho direitp
	private int getParentIndex(int index) {	return (index - 1) / 2;	} // Retorna o indice do pai
	
	private boolean hasLeftChild(int index) { return this.getLeftChildIndex(index) < this.size; } //Verifica se existe filho esquerdo
	private boolean hasRigthChild(int index) { return this.getRigthChildIndex(index) < this.size; } //Verifica se existe filho direito
	private boolean hasParent(int index) { return this.getParentIndex(index) >= 0; } //Verifica se existe o pai
	
	/**
	 * (1) -> Retorna o elemento que é o filho esquerdo do item no indice index
	 * (2) -> Retorna o elemento que éo filho direito do item no indice index
	 * (3) -> Retorna o elemento que é pai do item na posição index
	 * */
	
	private int leftChild(int index) { return this.itens[this.getLeftChildIndex(index)]; }	/*(1)*/
	private int rigthChild(int index) { return this.itens[this.getRigthChildIndex(index)]; }	/*(2)*/
	private int parent(int index) { return this.itens[this.getParentIndex(index)]; }	/*(3)*/
	
	
	/**
	 * Método que troca dois elementos do heap
	 */
	
	private void swap(int indexOne, int indexTwo) {
		int temp = this.itens[indexOne];
		this.itens[indexOne] = this.itens[indexTwo];
		this.itens[indexTwo] = temp;
	}
	
	//Expande o tamanho do heap para o dobro do original caso ele fique cheio
	private void ensureExtraCapacity() {
		if(this.size == this.capacity) {
			this.itens = Arrays.copyOf(itens, capacity * 2);
			capacity *= 2;
		}
	}
	
	//Retorna o valor do menor elemento do heap(ídice [0]) sem removê-lo 
	public int minElement() {
		if(this.size == 0) throw new IllegalStateException();
		return this.itens[0];
	}
	
	/**
	 * Remove o menoritem do heap(índice [0]), isso pode gerar uma quebra da propiedade
	 * da estrutura heap de mínimo. Então o algoitmo conserta a propiedade, borbulha o problema para baixo(do indice [0] para o último)
	 * corrigindo-o e retorna o elemento eliminado
	 */
	public int extractMinElement() {
		int item;
		if(this.size == 0) {
			throw new IllegalStateException();
		}
		
		item =  this.itens[0];
		this.itens[0] = this.itens[size - 1];
		size--;
		heapfyDown(0);
		
		return item;
	}
	
	/**
	 * Adiciona um elemento ao heap, coserta seu tamanho se ele estiver cheio(todas as posições ocupadas)
	 * e corrige a propiedade de heap minimo, se ela for quebrada, borbulhando o problema para cima(do ultimo para o primeiro indice do vetor)
	 * e corrige-o reestabelecendo a propiedade de heap de minimo para todo o vetor 
	 */
	public void add(int item) {
		this.ensureExtraCapacity();
		this.itens[this.size] = item;
		size++;
		heapfyUp();
	}
	
	//Algorítmo de correção subindo(do último->primeiro) 
	public void heapfyUp() {
		int index = this.size - 1;
		while(this.hasParent(index) && this.parent(index) > this.itens[index]) {
			this.swap(this.getParentIndex(index), index);
			index = this.getParentIndex(index);
		}
	}
	
	//Algorítmo de correção descendo(do primeiro->último) 
	public void heapfyDown(int i) {
		int index = i;
		while(this.hasLeftChild(index)) {
			int smallerChildIndex = this.getLeftChildIndex(index);
			if(this.hasRigthChild(index) && this.rigthChild(index) < this.leftChild(index)) {
				smallerChildIndex = this.getRigthChildIndex(index);
			}
			if(this.itens[index] < this.itens[smallerChildIndex]) {
				break;
			}
			else {
				this.swap(index, smallerChildIndex);
			}
			
			index = smallerChildIndex;
		}
	}
	
	/**Obs: Embora o heapfyDown tenha sido projetado para corrigir para qualquer índice e o heapfyUp não
	 * isso se dá apenas por conveniência do autor para a estrutura criada pois ambos podem ser feitos para
	 * qualquer índice do vetor
	 */
	
	//Imprime todos os índices do vetor do menor para o maior
	public void show() {
		for(int index = 0; index < this.size; index++) {
			System.out.print(this.itens[index] + " ");
		}
	}
}







