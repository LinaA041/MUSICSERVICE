package model;

import java.time.LocalDate;

public class Purchase {

private LocalDate purchaseDate;
private Song  selectedSong;

public Purchase (Song audioType){
    this.purchaseDate = LocalDate.now();
    this.selectedSong = audioType;
}
public LocalDate getPurchaseDate() {
    return purchaseDate;
}

public void setPurchaseDate(LocalDate purchaseDate) {
    this.purchaseDate = purchaseDate;
}

public Song getSelectedSong() {
    return selectedSong;
}
public void setSelectedSong(Song audioType) {
    this.selectedSong = audioType;
}


    
}