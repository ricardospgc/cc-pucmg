public class Principal {
    public static void main(String[] args){
        try{
            ListaSimples list = new ListaSimples();

            System.out.println("II: ");
            list.inserirInicio(35);
            list.inserirInicio(47);
            list.inserirInicio(23);

            list.mostrar();

            System.out.println("IF: ");
            list.inserirFim(7);
            list.inserirFim(89);
            list.inserirFim(16);

            list.mostrar();

            System.out.println("IP: ");
            list.inserirPos(46, 3);
            list.inserirPos(50, 2);
            list.inserirPos(67, 5);

            list.mostrar();

            System.out.println("RI: " + list.removerInicio());
            System.out.println("RF: " + list.removerFim());
            System.out.println("RP: " + list.removerPos(4));
            
            list.mostrar();
    
            System.out.println(list.pesquisa(89));
            System.out.println(list.pesquisa(50));
            System.out.println(list.pesquisa(47));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        
    }// main()
}// class Principal
