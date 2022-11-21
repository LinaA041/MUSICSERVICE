package model;
import java.util.ArrayList;
import java.util.Random;

public class Playlist{

    public static Random random = new Random();

    private String playlistName;

    private int[][] matriz;

    private ArrayList<Audio> audios;

    private int tracksNumber;

    private int podcastsNumber;

    private String code;

    public Playlist(String playlistName) {

        generateMatrizCode();
        audios = new ArrayList<Audio>();
        matriz = new int[6][6];
        this.playlistName = playlistName;
        this.tracksNumber = 0;
        this.podcastsNumber = 0;

    }

    public int getTracksNumber() {
        return tracksNumber;
    }

    public void setTracksNumber(int tracksNumber) {
        this.tracksNumber = tracksNumber;
    }

    public int getPodcastsNumber() {
        return podcastsNumber;
    }

    public void setPodcastsNumber(int podcastsNumber) {
        this.podcastsNumber = podcastsNumber;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }


    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public void addAudio(Audio audio) {
        audios.add(audio);
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    /**
     * Description: allows to display the audios saved in the list 
     * pre:that there are audios on the list
     * pos: none
     * @return msg String will display the list of audios 
     */
    public String showAudios() {

        String msg = "";

        for (int i = 0; i < audios.size(); i++) {

            msg += audios.get(i).getName() + "\n";

        }
        return msg;
    }
    

    /**
     * Description:this method provides a code for the playlist to be shared 
     * pre: that the playlist exists 
     * pos: none
     * @return msg String string with the code 
     */
    public String sharePlaylist() {

        String msg = "";

        if (audios.size() > 0) {

            generateCode();

            for (int i = 0; i < 6; i++) {
                
                for (int j = 0; j < 6; j++) {
                    
                    msg += " " + matriz[i][j] + " ";
                
                }
                
                msg += "\n";

            }

           msg += "\nCode"+code;
            
        } else {
           
            msg = "The playlist is empty";

        }

        return msg;
    }

	/**
     * Description:this method allows us to add an audio as appropriate 
     * pre: none
     * pos: none
	 * @param audio Audio object to add 
	 * @return msg String will indicate if the audio could be added to its corresponding list
	 */
	public String createAudio(Audio audio) {
	
        String msg = "The audio was added successfully";
	        
           Audio sentinel = searchAudio(audio.getName());
	       
            if (sentinel == null) {
        
                audios.add(audio);
                
                if (audio instanceof Song) {
                    
                    tracksNumber++;

                } else {
                    
                    podcastsNumber++;

                }
        
            } else {

                msg = "The audio already exists";

            }
        
        return msg;

    }

    /**
     * Description:this method allows us to delete an audio 
     * pre: that the audio exists 
     * pos: none
     * @param audio Audio object to be deleted 
     * @return msg String will indicate whether or not the audio could be deleted. 
     */
    public String deleteAudio(Audio audio) {
	

        String msg = "";

        Audio sentinel = searchAudio(audio.getName());

        if (sentinel != null) {

            audios.remove(audio);

        } else {

            msg = "The audio doesn't exist";
        }
        msg += "the audio was successfully deleted";

        return msg;
    }

    /**
     * Description:allows us to search for an audio in the playlist 
     * pre:none
     * pos:none
     * @param name String name of the audio to be searched 
     * @return sentinel Audio
     */
    public Audio searchAudio(String name) {

        Audio sentinel = null;

        boolean flag = false;

        if (audios != null) {

            for (int i = 0; i < audios.size() && !flag; i++) {

                if (audios.get(i).getName().equalsIgnoreCase(name)) {

                    sentinel= audios.get(i);
                    flag = true;

                }
            }
        }
        return sentinel;
    }
    
    /**
     * Description:is in charge of generating the matrix with the code 
     * pre: none
     * pos: none 
     *@return no return
     */
    public void generateMatrizCode() {

	    for (int i = 0; i < 6; i++) {
                    
            for (int j = 0; j < 6; j++) {
                        
                matriz[i][j] = random.nextInt(10);
                    
            }
        }
	
	}

    /**
     * Description: handles the paths to auto-generate the code depending on the content of the playlist 
     * pre: none
     * pos: none
     * @return not return 
     */
    public void generateCode() {
        
        String msg1 = "";
        String msg2 = "";
        String msg3 = "";

        if (tracksNumber > 0 && podcastsNumber > 0) {

            for (int i = 5; i >= 0; i--) {

                for (int j = 5; j >= 0; j--) {

                    int sum = i + j;

                    if (sum % 2 != 0) {
                       
                        if (sum != 1) {

                            code += matriz[i][j] + " ";

                        }
                    }


                }
            }


        } else if (tracksNumber> 0) {

            for (int j = 0; j < matriz.length; j++) {
               
                if (j > 0 && j < matriz.length - 1) {
                    
                    msg2 += matriz[j][j] + " ";

                }

                msg1 += matriz[matriz.length - (j + 1)][0] + " ";
                msg3 += matriz[matriz.length - (j + 1)][matriz.length - 1] + " ";
            }
            code = msg1 + msg2 + msg3;

        } else if (podcastsNumber>0) {

            for (int i = 0; i < matriz.length; i++) {

                msg1 += matriz[0][i] + " ";

                if (i != 0) {

                    msg2 += matriz[i][2] + " ";

                }
                if (i != matriz.length - 1) {

                    msg3 += matriz[matriz.length - (i + 1)][3] + " ";

                }
            }

            code = msg1.substring(0, 5) + " " + msg2 + msg3 + msg1.substring(6, 11);

        }


    }


}