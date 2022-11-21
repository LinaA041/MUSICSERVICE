package model;
import java.util.ArrayList;

public class Artist extends ProducerUser{

    private ArrayList<Song> songs;

    public Artist(String nickname, String id, String name,String representativeUrl) {
        super(nickname, id, name, representativeUrl);

        this.songs = new ArrayList<Song>();
        
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Artist [songs=" + songs + "]";
    }

    /**
     * Description:to register a song you need to assign it to its respective creator
     * pre:artist users can only have songs added to them 
     * pos:none
     * @param audio Audio is the object of type audio to be added 
     * @return msg String will indicate whether the audio could be added or not 
     */
    public String addAudioToArtist(Audio audio) {
	
        String msg = "The audio was successfully added ";

        Audio obj = verifyAudio(audio.getName());

        if (obj == null) {

            songs.add((Song)audio);
            return "Audio added";
        } else {
            msg  = "Audio could not be added ";
        }

        return msg;
    }

    /**
     * Description:this method searches for the user name that you received to see
     *  if it can be added or not 
     * pre:none
     * pos:none
     * @param name String parameter to be searched for or verified
     * @return audio Audio
     */
    public Audio verifyAudio(String name) {

        Audio audio = null;
        boolean flag = false;

        if (songs != null) {
            for (int i = 0; i < songs.size() && !flag; i++) {

                if (songs.get(i).getName().equalsIgnoreCase(name)) {
                    
                    audio = songs.get(i);
                    
                    flag = true;

                }
            }
        }

        return audio;

    }
    
}

