import java.sql.*;

public class DataBase {
  private static final String URL = "jdbc:sqlite:budget.db";

  public static Connection connect()
  {
    try {
        System.out.println("DB usato: " + new java.io.File("budget.db").getAbsolutePath());

        return DriverManager.getConnection(URL);
    } catch (SQLException e) {
        System.out.println("Errore di connessione: "+ e.getMessage());
        return null;
    }
  }

  public static void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS budget (
                numero_spesa INTEGER PRIMARY KEY AUTOINCREMENT,
                categoria TEXT NOT NULL,
                importo REAL NOT NULL,
                data TEXT NOT NULL
            );
        """;

    try (Connection conn = connect();
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
}

  public static void insertValues(String categoria, double importo, String data)
  {
    String sql = "INSERT INTO budget(categoria, importo, data) VALUES(?,?,?)";

    try (Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
        pstmt.setString(1, categoria);
        pstmt.setDouble(2, importo);
        pstmt.setString(3, data);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
  }

  public static void ShowValues()
  {
    int i = 1;
    String sql = "SELECT * FROM budget";

    try(Connection conn = connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){
                String categoria = rs.getString("categoria");
                double importo = rs.getDouble("importo");
                String data = rs.getString("data");

                System.out.println( i + ") " + categoria + " - " + importo + "$ - " + data);
                i++;
            }
        

        } catch(SQLException e){
           e.printStackTrace();
        }
  }

 public static void DeleteValues(int val)
{
    String sql = "DELETE FROM budget WHERE numero_spesa = ?";

    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)){
        pstmt.setInt(1, val);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public static void deleteAllAndReset() 
{
    String deleteAll = "DELETE FROM budget";
    String resetSeq = "DELETE FROM sqlite_sequence WHERE name='budget'";

    try (Connection conn = connect();
         Statement stmt = conn.createStatement()) {

        stmt.execute(deleteAll);
        stmt.execute(resetSeq);

        System.out.println("Tutte le voci eliminate e ID resettati.");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}




}
