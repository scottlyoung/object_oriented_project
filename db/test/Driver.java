import java.util.*;
import java.sql.*;

void submitUpdate(String query)
{
  //
}

public class Driver
{
  public static void main(String[] args)
  {
    // C:\Program Files\MySQL\mysql-connector-java-8.0.11.jar
    // /mnt/c/Program\ Files/MySQL/mysql-connector-java-8.0.11.jar
    // jdbc:mysql://localhost:3306/laikadb
    System.out.println("Hello world");

    String url = "jdbc:mysql://localhost:3306/laikadb?autoReconnect=true&useSSL=false";
    String username = "java";
    String password = "pass123";

    System.out.println("Connecting to database...");

    try(Connection conn = DriverManager.getConnection(url, username, password))
    {
      System.out.println("Database connected!");

      Statement stmt = conn.createStatement();

      ResultSet rs = stmt.executeQuery("SELECT * FROM dbaccounts");

      while(rs.next())
      {
        System.out.println(rs.getString(2));
        System.out.println(rs.getString("password"));
        System.out.println(rs.getInt("isAdmin"));
      }

      rs.close();

      ResultSet rs2 = stmt.executeQuery("SELECT * FROM dbsongs");

      while(rs2.next())
      {
        System.out.println(rs2.getString("title"));
      }

      rs2.close();

      String tmp1 = "dumbUserName";
      String tmp2 = "dumbPassword";
      String query = "INSERT INTO dbaccounts VALUES (null, '" + tmp1 + "', '" + tmp2 + "', 0)";

      stmt.executeUpdate(query);

      stmt.close();

      conn.close();
    }
    catch(SQLException e)
    {
      throw new IllegalStateException("Cannot connect the database!", e);
    }
  }
}
