class FilaRecreio{
	public static void sort(int[] array, int n){
		for (int i = 1; i < n; i++) {
			int tmp = array[i];
         		int j = i - 1;

         		while ((j >= 0) && (array[j] < tmp)) {
            			array[j + 1] = array[j];
            			j--;
        		}	
         		array[j + 1] = tmp;
	      }
	}

	public static int elemsMesmoIndex(int[] a, int[] b){
		if(a.length != b.length) return -1;
		
		int iguais = 0;
		for(int i = 0; i < a.length; i++){
			if(a[i] == b[i]) iguais++;
			
		}
		
		return iguais;
	}

	public static void testeFila(){
		int qtdAlunos = MyIO.readInt();
		int[] notas = new int[qtdAlunos];

		for(int i = 0; i < qtdAlunos; i++){
			notas[i] = MyIO.readInt();
		}

		int[] copia = notas.clone();
		
		sort(notas, notas.length);

		int qtdMesmoLugar = elemsMesmoIndex(notas, copia);
		MyIO.println(qtdMesmoLugar);
	}


	public static void main(String[] args) {
        	int n; // quantidade de testes
		n = MyIO.readInt();

		for(int i = 0; i < n; i++){
			testeFila();
		}
    }
}
