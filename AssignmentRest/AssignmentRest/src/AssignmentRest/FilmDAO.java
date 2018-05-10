package AssignmentRest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.*;

@Path("/films")
public class FilmDAO {

 Film oneFilm = null;
 Connection conn = null;
 Statement stmt = null;

 public FilmDAO() {}

 //open connection
 public void openConnection() {

   try {
    Class.forName("com.mysql.jdbc.Driver").newInstance();
   } catch (Exception e) {
    System.out.println(e);
   }

   // connecting to database
   try {
    // connection string including user and pass
    conn = DriverManager.getConnection("jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:3306/mcnamard?user=mcnamard&password=Rimsdarl3");
    stmt = conn.createStatement();
   } catch (SQLException se) {
    System.out.println(se);
   }
  }
  //close connection
 public void closeConnection() {
  try {
   conn.close();
  } catch (SQLException e) {

   e.printStackTrace();
  }
 }
//get next film (used for getAll)
 private Film getNextFilm(ResultSet rs) {
  Film thisFilm = null;
  try {
   thisFilm = new Film(
    rs.getInt("id"),
    rs.getString("title"),
    rs.getInt("year"),
    rs.getString("director"),
    rs.getString("stars"),
    rs.getString("review"));
  } catch (SQLException e) {

   e.printStackTrace();
  }
  return thisFilm;
 }

 //get all films in xml
@Path("/getAllFilmsXML")
@GET
@Produces( { MediaType.APPLICATION_XML })
 public ArrayList < Film > getAllFilmsXML() {

  ArrayList < Film > allFilms = new ArrayList < Film > ();
  openConnection();

  // Create select statement and execute it
  try {
   String selectSQL = "select * from films";
   ResultSet rs1 = stmt.executeQuery(selectSQL);
   // Retrieve the results
   while (rs1.next()) {
    oneFilm = getNextFilm(rs1);
    allFilms.add(oneFilm);
   }

   stmt.close();
   closeConnection();

   //System.out.println("Select was successful -");
  } catch (SQLException se) {
   System.out.println(se);
  }
  System.out.println(allFilms);
  return allFilms;
 }

//get all films in JSON
@Path("/getAllFilmsJSON")
@GET
@Produces( { MediaType.APPLICATION_JSON })
 public ArrayList < Film > getAllFilmsJSON() {

  ArrayList < Film > allFilms = new ArrayList < Film > ();
  openConnection();

  // Create select statement and execute it
  try {
   String selectSQL = "select * from films";
   ResultSet rs1 = stmt.executeQuery(selectSQL);
   // Retrieve the results
   while (rs1.next()) {
    oneFilm = getNextFilm(rs1);
    allFilms.add(oneFilm);
   }

   stmt.close();
   closeConnection();

   //System.out.println("Select was successful -");
  } catch (SQLException se) {
   System.out.println(se);
  }
  System.out.println(allFilms);
  return allFilms;
 }

//get all films in TEXT
@Path("/getAllFilmsTEXT")
@GET
@Produces( { MediaType.TEXT_PLAIN })
public ArrayList < Film > getAllFilmsTEXT() {

ArrayList < Film > allFilms = new ArrayList < Film > ();
openConnection();

// Create select statement and execute it
try {
 String selectSQL = "select * from films";
 ResultSet rs1 = stmt.executeQuery(selectSQL);
 // Retrieve the results
 while (rs1.next()) {
  oneFilm = getNextFilm(rs1);
  allFilms.add(oneFilm);
 }

 stmt.close();
 closeConnection();

 //System.out.println("Select was successful -");
} catch (SQLException se) {
 System.out.println(se);
}
System.out.println(allFilms);
return allFilms;
}
//get a film in JSON
@Path("/getFilmJSON")
@POST
@Produces({MediaType.APPLICATION_JSON} )
 public ArrayList < Film > getFilmJSON(@FormParam("title")String title) {

  openConnection();
  ArrayList < Film > aFilm = new ArrayList < Film > ();

  // Create select statement and execute it
  try {
   String selectSQL = "select * from films where title LIKE '%" + title + "%'";
   ResultSet rs1 = stmt.executeQuery(selectSQL);
   // Retrieve the results
   while (rs1.next()) {
    oneFilm = getNextFilm(rs1);
    aFilm.add(oneFilm);
   }

   stmt.close();
   closeConnection();
   System.out.println("Select was successful - " + aFilm);
  } catch (SQLException se) {
   System.out.println(se);
  }

  //return response;
  return aFilm;
 }

//get a film in XML
@Path("/getFilmXML")
@POST
@Produces({MediaType.APPLICATION_XML} )
 public ArrayList < Film > getFilmXML(@FormParam("title")String title) {
	System.out.println("String title - " + title);
  openConnection();
  ArrayList < Film > aFilm = new ArrayList < Film > ();

  // Create select statement and execute it
  try {
   String selectSQL = "select * from films where title LIKE '%" + title + "%'";
   ResultSet rs1 = stmt.executeQuery(selectSQL);
   // Retrieve the results
   while (rs1.next()) {
    oneFilm = getNextFilm(rs1);
    aFilm.add(oneFilm);
   }

   stmt.close();
   closeConnection();
   System.out.println("Select was successful - " + aFilm);
  } catch (SQLException se) {
   System.out.println(se);
  }

  //return response;
  return aFilm;
 }

//insert a film
@Path("/insertFilm")
@POST
 public String insertFilm(@FormParam("id") int id, @FormParam("title") String title, @FormParam("year") int year, @FormParam("director") String director, @FormParam("stars") String stars, @FormParam("review") String review) throws SQLException {

  try {
   openConnection(); // 
   System.out.println("Create operation -database successfully opened");

   String sql = "INSERT INTO films(id, title, year, director, stars, review)  " + "VALUES ('" + id + "','" + title 
   + "','" + year + "', '" + director + "', '" + stars + "', '" + review + "')";
   System.out.println(sql);
   stmt.executeUpdate(sql);
   stmt.close();
   closeConnection();
   System.out.println("Insert was successful");
   return "Insert was successful!";
   
  } catch (Exception ex) {
   System.out.println(ex);
   System.exit(0);
  }
return "Insert was not successful!";

 

 }}

