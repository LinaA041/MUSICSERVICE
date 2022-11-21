package model;


public class ProducerUser extends User{

private String name;

private double timeListened;

private String representativeUrl;

private int numberOfReprodution;


public ProducerUser(String nickname, String id, String name,String representativeUrl) {
    super(nickname, id);
    this.name = name;
    this.timeListened = 0;
    this.representativeUrl = representativeUrl;
    this.numberOfReprodution = 0;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public double getTimeListened() {
    return timeListened;
}

public void setTimeListened(double timeListened) {
    this.timeListened = timeListened;
}

public String getRepresentativeUrl() {
    return representativeUrl;
}

public void setRepresentativeUrl(String representativeUrl) {
    this.representativeUrl = representativeUrl;
}

public int getNumberOfReprodution() {
    return numberOfReprodution;
}

public void setNumberOfReprodution(int numberOfReprodution) {
    this.numberOfReprodution = numberOfReprodution;
}

public void updateSoldInfo(int duration){
    
    numberOfReprodution++;
    
    timeListened += duration;
}


}