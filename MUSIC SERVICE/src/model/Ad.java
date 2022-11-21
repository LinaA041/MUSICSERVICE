package model;

public enum Ad implements Playable{
    
    NIKE("Nike, Just do it"),COCACOLA("Coca-cola, open happiness"),MyMs("M&M, Melts in your mouth, not in your hands");
    
    private String msg;

    Ad(String msg) {
        this.msg = msg;
    }
    
    public String play() {

        String message;

        message = "promoted by : "+msg;

        return message;
    }





}
