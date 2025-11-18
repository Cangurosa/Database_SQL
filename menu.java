import java.util.Scanner;

public class menu {
    private static final Scanner input = new Scanner(System.in);

    public static void start()
    {
        boolean running = true;

        while(running)
        {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Aggiungi voce");
            System.out.println("2. Mostra voci");
            System.out.println("3. Elimina voce");
            System.out.println("4. Elimina tutto");
            System.out.println("5. Esci");
            System.out.print("Scelta: ");
            int scelta = input.nextInt();
            input.nextLine();

            switch(scelta){
                case 1 -> addValue();
                case 2 -> DataBase.ShowValues();
                case 3 -> deleteValue();
                case 4 -> DataBase.deleteAllAndReset();
                case 5 -> running = false;
            }
        }
    }

            public static void addValue()
        {
            System.out.print("Categoria: ");
            String cat = input.nextLine();

            System.out.print("Importo: ");
            double imp = input.nextDouble();
            input.nextLine();

            System.out.print("Data (YYYY-MM-DD): ");
            String data = input.nextLine();

            DataBase.insertValues(cat, imp, data);
        }

        public static void deleteValue()
        {
            DataBase.ShowValues();
            System.out.println("Quale riga vuoi eliminare? ");
            int id = input.nextInt();
            DataBase.DeleteValues(id);
        }
}
