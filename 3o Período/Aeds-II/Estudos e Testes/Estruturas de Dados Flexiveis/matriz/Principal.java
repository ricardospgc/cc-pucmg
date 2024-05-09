public class Principal {
    public static void main(String[] args){
        Matriz mat = new Matriz(5,5);
        mat.fill();
        mat.print();

        Matriz mat2 = new Matriz(5,5);
        mat2.fill();

        Matriz mat3 = mat.soma(mat2);
        mat3.print();
    }// main()
}
