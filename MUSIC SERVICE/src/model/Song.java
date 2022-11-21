package model;

public class Song extends Audio implements Purchasable {

private String album;
private TypeOfGenre genre;
private double salesValue;
private int timesSold;

public Song(String name, String distinctiveUrl, int duration, User creator,String album, int genre, double salesValue) {
   
    super(name, distinctiveUrl, duration,creator);
    this.album = album;
    this.genre = TypeOfGenre.values()[genre];
    this.salesValue = salesValue;
    this.timesSold = 0;
}

public String getAlbum() {
    return album;
}

public void setAlbum(String album) {
    this.album = album;
}

public TypeOfGenre getGenre() {
    return genre;
}

public void setGenre(TypeOfGenre genre) {
    this.genre = genre;
}

public double getSalesValue() {
    return salesValue;
}

public void setSalesValue(double salesValue) {
    this.salesValue = salesValue;
}

public int getTimesSold() {
    return timesSold;
}

public void setTimesSold(int timesSold) {
    this.timesSold = timesSold;
}

@Override
public String play() {
    
    String msg = "";

    msg += "The song is playing";

    return msg;
}


/* 
*Description: gives us the information about the sale of the song 
*pre: none
*pos:none
* @return String the sold song 
*/
public String purchase(){

    timesSold++;
    super.getCreator().updateSoldInfo(super.getDuration());
    return "The song " + getName() + " has been sold";

}

/**
 * Description:calculates the total price of the sale 
 * pre: none
 * pos: none
 * @return totalSells double 
 */
public double totalPrice(){
    
    double totalSells= timesSold*salesValue;
    
    return totalSells;

}

@Override
public String toString() {
    return "Song [album=" + album + ", genre=" + genre + ", salesValue=" + salesValue + ", timesSold=" + timesSold +"]";
}


}
