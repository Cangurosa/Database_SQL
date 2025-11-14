public class BudgetEntry {
    private String categoria;
    private int importo;
    private String data;

    public BudgetEntry(String categoria, int importo, String data)
    {
        this.categoria = categoria;
        this.importo = importo;
        this.data = data;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public int getImporto()
    {
        return importo;
    }

    public String getData()
    {
        return data;
    }
}
