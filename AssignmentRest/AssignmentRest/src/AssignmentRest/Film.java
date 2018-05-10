package AssignmentRest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Film {
 public Film(int id, String title, int year, String director, String stars,
  String review) {
  super();
  this.id = id;
  this.title = title;
  this.year = year;
  this.director = director;
  this.stars = stars;
  this.review = review;
 }

 public Film() {

 }

 int id;
 String title;
 int year;
 String director;
 String stars;
 String review;
 
@GET
 public int getId() {
  return id;
 }
 public void setId(int id) {
  this.id = id;
 }
 @GET
 public String getTitle() {
  return title;
 }
 public void setTitle(String title) {
  this.title = title;
 }
 @GET
 public int getYear() {
  return year;
 }
 public void setYear(int year) {
  this.year = year;
 }
 @GET
 public String getDirector() {
  return director;
 }
 public void setDirector(String director) {
  this.director = director;
 }
@GET
 public String getStars() {
  return stars;
 }
 public void setStars(String stars) {
  this.stars = stars;
 }
@GET 
 public String getReview() {
  return review;
 }
 public void setReview(String review) {
  this.review = review;
 }
 @Override
 public String toString() {
  return "Film [id=" + id + ", title=" + title + ", year=" + year + ", director=" + director + ", stars=" + stars + ", review=" + review + "]";
 }
}