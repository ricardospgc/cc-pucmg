public class Principal {
    public static void main(String[] args){
        try{
            ListaSimples list = new ListaSimples();

            MyIO.println("II: ");
            list.inserirInicio(35);
            list.inserirInicio(47);
            list.inserirInicio(23);

            list.mostrar();

            MyIO.println("IF: ");
            list.inserirFim(7);
            list.inserirFim(89);
            list.inserirFim(16);

            list.mostrar();

            MyIO.println("IP: ");
            list.inserirPos(46, 3);
            list.inserirPos(50, 2);
            list.inserirPos(67, 5);

            list.mostrar();

            MyIO.println("RI: " + list.removerInicio());
            MyIO.println("RF: " + list.removerFim());
            MyIO.println("RP: " + list.removerPos(4));
            
            list.mostrar();
    
            MyIO.println(list.pesquisa(89));
            MyIO.println(list.pesquisa(50));
            MyIO.println(list.pesquisa(47));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }// main()
}// class Principal
