package model;
import java.util.ArrayList;

public class  ContentCreator extends ProducerUser {


    private  ArrayList<Podcast>podcasts;

    public ContentCreator(String nickname, String id, String name,String representativeUrl) {
        super(nickname, id, name,representativeUrl);

        this.podcasts = new ArrayList<Podcast>();
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
    
    /**
     * Description: in this method, a podcast is assigned to a content creator. 
     * pre:the type of audio to be added must be of podcast type 
     * pos: none
     * @param audio Audio the object to be added 
     * @return msg String will indicate whether or not the audio could have been added.
     */
    public String addAudioToContentCreator(Audio audio) {

        String msg = "podcast was successfully added";

            Audio obj = verifyAudio(audio.getName());

            if (obj == null) {

                podcasts.add((Podcast)audio);

            } else {

                msg = "is already registered ";

            }
                
        return msg;
    }


    /**
     * Description:allows to search an audio in the user's list of audios
     * pre: none
     * pos: none
     * @param name is the name of the audio to be searched.
     * @return Audio the audio found.
     */
    public Audio verifyAudio(String name) {

        Audio audio = null;
        boolean flag = false;

        if (podcasts!=null) {
            
            for (int i = 0; i < podcasts.size() && !flag; i++) {
                
                if (podcasts.get(i).getName().equalsIgnoreCase(name)) {
                    
                    audio = podcasts.get(i);
                    flag = true;

                }
            }
        }

        return audio;

    }

    
}