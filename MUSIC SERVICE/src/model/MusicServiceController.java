package model;
import java.util.ArrayList;
import java.util.Random;


public class MusicServiceController {

    public static Random advertisement = new Random();

    private ArrayList<User> allUsers;

    private ArrayList<Audio> allAudios;
    
    
    public MusicServiceController(){

        allUsers = new ArrayList<User>();
        allAudios = new ArrayList<Audio>();
    }

    /**
     * Description: search for a specific user's nickname 
     * @param nickname String nickname of the searched user 
     * @return User
     */
    public User searchUser(String nickname) {
        
        User verifyUser  = null;
        boolean flag = false;

        for (int i = 0; i < allUsers.size() && !flag; i++) {
            
            if (allUsers.get(i).getNickname().equals(nickname)) {

                verifyUser = allUsers.get(i);
                flag = true;
            }
        }
        return verifyUser;
    }


    /**
     * Description: register a Artist 
     * @param nickname String user nickname
     * @param id String user id 
     * @param name String user name
     * @param representativeUrl String url of representative image for the artist
     * @return boolean will indicate whether or not the user was registered 
     */
    public boolean registerArtist(String nickname, String id,String name, String representativeUrl){
        

        for (int j=0;j<allUsers.size();j++){

            if(allUsers.get(j)!= null){

                if(allUsers.get(j).getId().equals(id)){
                    
                    return false;

                }

            }

        }

        Artist newArtist = new Artist(nickname, id, name, representativeUrl);

            return allUsers.add(newArtist);
            
    }

    /**
     * Description: register a content creator 
     * @param nickname String user nickname
     * @param id String user id 
     * @param name String user name 
     * @param representativeUrl String representative image url for the content creator 
     * @return boolean will indicate whether or not the user was registered 
     */
    public boolean registerContentCreator(String nickname, String id,String name, String representativeUrl){
        
        User newUser = searchUser(nickname);

        if (newUser== null) {

        newUser = new ContentCreator(nickname, id, name,representativeUrl);
        return allUsers.add(newUser);
        
        } else {

            return false;

        }

    }
    
    /**
     * Description: register a preimun user 
     * @param nickname String user nickname
     * @param id String user id 
     * @return boolean will indicate whether or not the user was registered 
     */
    public boolean registerPremiumUser(String nickname, String id){
     
     User newUser = searchUser(nickname);

        if(newUser==null){

            newUser = new PremiumUser(nickname, id);
            return allUsers.add(newUser);
        } else {

            return false;
        }
    
    }
	
	/**
     * Description: register a standar user
	 * @param nickname String user nickname
	 * @param id String user id 
	 * @return boolean will indicate whether or not the user was registered 
	 */
	public boolean registerStandardUser(String nickname, String id){
        
        User newUser = searchUser(nickname);

        if(newUser==null){

         newUser = new StandardUser(nickname, id);
         return allUsers.add(newUser);

        }else{
            return false;
        }
		
    }

    /**
     * Description: shows the audios 
     * pre: none
     * pos: none 
     * @return msg String existing audios 
     */
    public String showAudios(){
    
        String msg = "";

		for (int i = 0; i < allAudios.size(); i++) {

			if (allAudios.get(i) != null) {

				msg += allAudios.get(i).getName();
			}

		}
		return msg;

    }

    /**
     * Description: request the parameters to register a podcast
     * @param name String name of the podcast 
     * @param distinctiveUrl String url of the representative image 
     * @param duration int duration of the podcast 
     * @param nickname String user nickname
     * @param description String a short description of the podcast 
     * @param category int  podcast category 
     * @return msg String will indicate whether or not the podcast was created. 
     */
    public String registerPodcast(String name, String distinctiveUrl, int duration, String nickname,String description, int category){
     
        Audio pod = searchAudio(name);
        User creator = searchUser(nickname);
        String msg = "The podcast was successfully registered";
        
        if (creator != null) {
           
            if (pod == null) {

                ContentCreator newContentCreator = (ContentCreator)creator;

                pod = new Podcast(name, distinctiveUrl, duration, creator, description, category);

                allAudios.add(pod);

                newContentCreator.addAudioToContentCreator((Song)pod);

            }else {
                msg = "the operation could not be performed ";
            }
        } else {

            msg = "Error, first register a user ";
        }

        return msg;

        
    }
    
    /**
     * Description:request data to register a song and add it to its respective artist 
     * @param name String name of the song 
     * @param distinctiveUrl String 
     * @param duration int 
     * @param nickname String user nickname
     * @param album String name of the album 
     * @param genre int genre of the song 
     * @param salesValue double price of the song 
     * @return msg String will indicate whether or not the song could have been created.
     */
    public String registerSong(String name, String distinctiveUrl, int duration,String nickname, String album, int genre, double salesValue){
       
        Audio song = searchAudio(name);
        User creator = searchUser(nickname);
        String msg = "The song was successfully registered";

        if (creator != null) {
           
            if (song == null) {

                Artist newArtist = (Artist)creator;

                song = new Song(name, distinctiveUrl, duration, creator, album, genre,salesValue);

                allAudios.add(song);

                newArtist.addAudioToArtist((Song)song);

            }
        } else {

            msg = "Error, first register a user ";
        }

        return msg;
    }


    /**
     * Description: an empty playlist will be created and the requested user will be added to it. 
     * @param playlistName String name of the playlist to create
     * @param nickname String user nickname
     * @return msg String will indicate whether or not the playlist could be created. 
     */
    public String createPlaylist(String playlistName, String nickname){

        String msg = "";

        User verifyUser = searchUser(nickname);

        if (verifyUser != null) {

            if (verifyUser instanceof PremiumUser) {

                msg = ((PremiumUser)verifyUser).createPlaylist(playlistName);

            } else if (verifyUser instanceof StandardUser) {

                msg = ((StandardUser)verifyUser).createPlaylist(playlistName);
            }
        } else {
            
            return msg += "nickname could not be found, try registering first ";
        }
        
        msg += "playlist successfully added to the user ";

        return msg;

    }

    /**
     * Description: search for the requested audio 
     * @param audioName String name of the audio to be searched 
     * @return object Audio 
     */
    public Audio searchAudio(String audioName) {
        
        Audio object = null;
        boolean found = false;

        for (int i = 0; i < allAudios.size() && !found; i++) {

            if (allAudios.get(i).getName().equals(audioName)) {

                object = allAudios.get(i);
                found = true;

            }
        }

        return object;
    }

    /**
     * @param audioName String 
     * @param playlistName String name of the selected playlist 
     * @param nickname String user nickname
     * @return msg String will indicate whether or not the audio could have been added to the playlist.
     */
    public String addAudioToPlaylist(String audioName, String playlistName, String nickname) {
        
        String msg = "";

        User user = searchUser(nickname);

        Audio audio = searchAudio(audioName);

            if (user!= null) {

                if (audio!= null) {

                    if (user instanceof ConsumerUser) {

                        msg = ((ConsumerUser)user).addAudioToPlaylist(playlistName, audio);

                    }

                } else {
                   
                    msg = "Error, could not find that audio name ";

                }

            } else {

                msg = "Error, user's nickname could not be found";

            }

        return msg;
    }

    /**
     * Description : allows us to remove audio from a playlist 
     * pre: that there is a playlist 
     * @param playlistName String name of the required playlist 
     * @param nickname String user nickname 
     * @param audioName String name of the audio to be deleted 
     * @return msg String will indicate whether or not the audio could be removed from the playlist. 
     */
    public String deleteAudioToPlaylist(String playlistName, String nickname, String audioName) {

        String msg = "";

        User obj = searchUser(nickname);

        Audio audio = searchAudio(audioName);

        if (obj != null && audio!= null) {

            if (obj instanceof ConsumerUser) {

                msg = ((ConsumerUser) obj).deleteAudioToPlaylist(playlistName, audio);

            } else {

                msg = "Error, The user is not a consumer ";
            }
        } else {
            msg = "Error, The user or the audio does not exist";
        }

        return msg;

    }

    /**
     * Description: allows you to rename an existing playlist 
     * pre: that there is a playlist 
     * @param nickname String user nickname
     * @param playlistName String playlist name to change 
     * @param newPlaylistName String new playlist name 
     * @return msg String will indicate whether or not the name could be changed. 
     */
    public String editPlaylistName(String nickname, String playlistName, String newPlaylistName) {
        
        String msg = "";

        User user = searchUser(nickname);

        if (user!= null) {

            if (user instanceof ConsumerUser) {

                msg = ((ConsumerUser)user).editPlaylistName(playlistName, newPlaylistName);

            } 

        } else {

            msg = "Error, user's nickname could not be found ";
        }
        
        msg += "user's nickname could not be found";
        return msg;
    }

    /**
     * Description:allows you to share the playlist using the generated code 
     * pre: that there is a playlist 
     * pos: none 
     * @param nickname String user nickname
     * @param playlistName String name of the playlist to share 
     * @return msg String indicates whether the playlist could be shared 
     */
    public String sharePlaylist(String nickname, String playlistName) {

        String msg = "";

        User verifyUser = searchUser(nickname);

        if (verifyUser!= null) {

            if (verifyUser instanceof ConsumerUser) {

                msg = ((ConsumerUser)verifyUser).sharePlaylist(playlistName);

            } else {
                msg = "This user cannot share playlists";
            }
        } 

        msg += "playlist shared with success";

        return msg;
    }

    /**
     * Description: shows the respective advertisements 
     * pre: none
     * pos: none
     * @return msg String advertisement
     */
    public String showAd() {

        String msg = "";
        int advert = advertisement.nextInt(3);

        switch (advert) {
            case 0:
                msg = Ad.COCACOLA.play();
                break;
            case 1:
                msg = Ad.NIKE.play();
                break;
            case 2:
                msg = Ad.MyMs.play();
                break;
        }
        return msg;
    }

    /**
     * Description: allows you to play an audio 
     * @param nickname String user nickname
     * @param nameAudio String name of the audio to be played 
     * @return msg  String will indicate if the audio is playing 
     */
    public String playAudio(String nickname, String nameAudio) {

        String msg = "";
        User user = searchUser(nickname);

        Audio audio = searchAudio(nameAudio);

        if (user != null) {

            if (user instanceof ConsumerUser) {

                if (audio!= null) {

                    if (user instanceof PremiumUser) {

                        msg = ((PremiumUser)user).playAudio(audio);

                    } else if (user instanceof StandardUser) {

                        StandardUser user2 = (StandardUser)user;

                        if (audio instanceof Song) {

                            if (user2.showAds()) {

                                msg += showAd();
                                msg += "\n" + ((StandardUser)user).playAudio(audio);

                            } else {
                                msg = ((StandardUser)user).playAudio(audio);
                            }
                        } else {
                            msg += showAd();
                            msg += "\n" + ((StandardUser)user).playAudio(audio);
                        }

                    }
                } else {
                    msg = "The audio does not exist";
                }
            }
        } else {
            msg = "The user does not exist";
        }
        return msg;
    }

    /**
     * Description: allows the user to purchase a song 
     * @param nickname String user nickname
     * @param audioName String name of the audio you wish to purchase 
     * @return msg String indicate whether or not it was possible to purchase the song 
     */
    public String buySong(String nickname, String audioName) {

        String msg = "";

        User obj = searchUser(nickname);

        Audio sentinel = searchAudio(audioName);

        if (obj != null) {

            if (sentinel!= null && sentinel instanceof Song) {

                if (obj instanceof PremiumUser) {

                    msg = ((PremiumUser) obj).buyAudio(sentinel);

                } else if (obj instanceof StandardUser) {

                    msg = ((StandardUser)obj).buyAudio(sentinel);
                }
            } else {
                msg = "This audio could not be purchased ";
            }
        } else {
            msg = "The user does not exist";
        }

        return msg;
    }
    
    /**
     * Description: calculates the number of plays of the podcasts on the platform 
     * pre: that there are registered podcasts and they have been reproduced 
     * pos:none
     * @return msg String the accumulated number of plays of the podcasts
     */
    public String reportOnAccumulatedPodcastPlays(){

        String msg = "";

        int totalReproductions = 0;

            for (int i = 0; i < allAudios.size(); i++) {

                if (allAudios.get(i) instanceof Podcast) {

                    totalReproductions += allAudios.get(i).getNumberOfPlays();
                }
            }
            if (totalReproductions != 0) {
                
                msg = "The total of plays of the podcasts is " + totalReproductions;

            } else {
                msg = "There are no played podcasts";
            }

        return msg;
    }

    /**
     * Description: calculates the number of plays of the songs on the platform 
     * pre: that there are registered songs and they have been reproduced 
     * pos:none
     * @return msg String the accumulated number of plays of the songs
     */
    public String reportOnAccumulatedSongsPlays() {

        String msg = "";
        int totalReproductions = 0;

            for (int i = 0; i < allAudios.size(); i++) {

                if (allAudios.get(i) instanceof Song) {
                    
                    totalReproductions += (allAudios.get(i)).getNumberOfPlays();
                }
            }

            if (totalReproductions > 0) {
                msg = "the accumulated number of plays of the songs on the platform is:" + totalReproductions;

            } else {

                msg = "There are no played songs";

            }

        return msg;
    }
    
     /**
     * Description: search for the song genre most listened to by a specific user 
     * @param nickname nickname of the user you wish to consult
     * @return msg String genre most listened to by the user 
     */
    public String mostListenedGenreToByUser(String nickname) {
        
        String msg = "";

        User sentinel= searchUser(nickname);

            if (sentinel != null) {

                if (sentinel instanceof ConsumerUser) {
                    ConsumerUser obj = (ConsumerUser) sentinel;
                    msg = obj.mostListenedGenre();
                }

            } else {
                msg = "User nickname not found";
            }

        return msg;

    }

     /**
     * Description: search for the most listened song genre across the platform 
     * pre: that there are songs played
     * pos: none
     * @return msg String most listened genre of the platform 
     */
    public String mostListenedGenreOnTheEntirePlatform() {

        String msg = "";

        int[] totalReproductions = new int[4];

            for (int i = 0; i < totalReproductions.length; i++) {

                totalReproductions[i] = totalplaybackGenres()[i];

            }

        int max = 0;
        int pos = -1;

            for (int i = 0; i < totalReproductions.length; i++) {

                if (totalReproductions[i]> max) {

                    max = totalReproductions[i];
                    pos = i;
                }
            }
            switch (pos) {
                case 0:
                    msg = "The most listened genre is: Rock, with a total of " + max + " plays";
                    break;
                case 1:
                    msg = "The most listened genre is: Pop, with a total of " + max + " plays";
                    break;
                case 2:
                    msg = "The most listened genre is: trap, with a total of " + max + " plays";
                    break;
                case 3:
                    msg = "The most listened genre is: House, with a total of " + max + " plays";
                    break;
                default:
                    msg = "No songs have been listened to";
                    break;
        }
        return msg;
    }
    
    /**
     * Description: sum of the reproductions in each specific genre
     * pre: none
     * pos: none
     * @return totalPlaybacks int[] reproductions by genre
     */
    public int[] totalplaybackGenres() {

        int[] totalPlaybacks = new int[4];

        for (int i = 0; i < allUsers.size(); i++) {

            if (allUsers.get(i) instanceof ConsumerUser) {

                ConsumerUser obj = (ConsumerUser) allUsers.get(i);

                for (int j = 0; j < totalPlaybacks.length; j++) {

                    totalPlaybacks[j] += obj.reproductionsPerGenre()[j];

                }

            }
        }
        return totalPlaybacks;
    }

    /**
     * Description: search for the podcast category most listened to by a specific user 
     * @param nickname nickname of the user you wish to consult
     * @return msg String category most listened to by the user 
     */
    public String mostListenedCategorybyUser(String nickname) {

        String msg = "";

        User obj = searchUser(nickname);

        if (obj != null) {

            if (obj instanceof ConsumerUser) {

                ConsumerUser sentinel = (ConsumerUser) obj;
                msg = sentinel.mostListenedCategory();

            }

        } else {

            msg = "nickname not found";
        }
        return msg;

    }

    /**
     * Description: sum of the reproductions in each specific category 
     * pre: none
     * pos: none
     * @return total int[] reproductions by category 
     */
    public int[] totalplaybackCategories() {

        int[] total = new int[4];

        for (int i = 0; i <allUsers.size(); i++) {

            if (allUsers.get(i) instanceof ConsumerUser) {

                ConsumerUser obj = (ConsumerUser)allUsers.get(i);

                for (int j = 0; j < total.length; j++) {

                    total[j] += obj.reproductionsPerCategory()[j];

                }

            }
        }

        return total;
    }
    
    /**
     * Description: search for the most listened podcast category across the platform 
     * pre: that there are podcasts played
     * pos: none
     * @return msg String most listened category of the platform 
     */
    public String mostListenedCategoryOnTheEntirePlatform() {

        String msg = "";

        int[] total = new int[4];

        for (int i = 0; i < total.length; i++) {

            total[i] = totalplaybackCategories()[i];

        }
        int max = 0;
        int pos = -1;
        for (int i = 0; i < total.length; i++) {
            if (total[i] > max) {
                max = total[i];
                pos = i;
            }
        }
        switch (pos) {
            case 0:
                msg = "The most listened category is: Politics, with a total of " + max + " plays";
                break;
            case 1:
                msg = "The most listened category is: Entertainment, with a total of " + max + " plays";
                break;
            case 2:
                msg = "The most listened category is: Videogame, with a total of " + max + " plays";
                break;
            case 3:
                msg = "The most listened category is: Fashion, with a total of " + max + " plays";
                break;
            default:
                msg = "No podcast have been listened to";
                break;
        }
        return msg;
    }

    /**
     * Description: generates a top five with the artists and content creators with the highest number of views 
     * pre: there must be registered artists and/or creators 
     * pos:none
     * @return msg String 
     */
    public String top5ArtistAndCreators() {

        String msg = "no songs or podcasts have been listened to";

        Artist[] top5Artist = new Artist[5];

        ContentCreator[] top5ContentCreators = new ContentCreator[5];

        ArrayList<Artist> artists = new ArrayList<Artist>();

        ArrayList<ContentCreator> contentCreators = new ArrayList<ContentCreator>();

            for (int i = 0; i < allUsers.size(); i++) {

                if (allUsers.get(i) instanceof Artist) {

                    artists.add((Artist) allUsers.get(i));

                } else if (allUsers.get(i) instanceof ContentCreator) {

                    contentCreators.add((ContentCreator) allUsers.get(i));
                    
                }
            }

        ProducerUser max = null;

        int count = 0;

        max = artists.get(0);

        for (int i = 0; i < artists.size(); i++) {

            if (artists.get(i).getNumberOfReprodution() > max.getNumberOfReprodution()) {

                max = artists.get(i);

            }
            if (i == artists.size() - 1) {

                if ((top5Artist[4] == null)) {

                    top5Artist[count] = (Artist) max;

                    count++;

                    artists.remove(max);

                    if (artists.size() > 0) {

                        max = artists.get(0);
                        i = 0;

                    }
                }
            }

        }

        count = 0;
        max = contentCreators.get(0);

        for (int i = 0; i < contentCreators.size(); i++) {

            if (contentCreators.get(i).getNumberOfReprodution() > max.getNumberOfReprodution()) {
                
                max = contentCreators.get(i);
            }
            if (i == contentCreators.size() - 1) {

                if ((top5ContentCreators[4] == null)) {

                    top5ContentCreators[count] = (ContentCreator) max;

                    count++;

                    contentCreators.remove(max);

                    if (contentCreators.size() > 0) {

                        max = contentCreators.get(0);
                        i = 0;
                    }
                }
            }

        }
        for (int i = 0; i < top5Artist.length; i++) {

            if (top5Artist[i] != null) {

                msg = "The top 5 artists are:\n ";
                msg += (i + 1) + "." + top5Artist[i].getName() + " with " + top5Artist[i].getNumberOfReprodution() + " plays";

            }
        }
        for (int i = 0; i < top5ContentCreators.length; i++) {

            if (top5ContentCreators[i] != null) {

                msg += "\nThe top 5 content creators are:\n ";

                msg += (i + 1) + "." + top5ContentCreators[i].getName() + " with " + top5ContentCreators[i].getNumberOfReprodution() + " plays";
            }
        }

        return msg;

    }

    /**
     * Description:collects podcast and song information and sorts them by number of plays 
     * pre:there must be registered songs and/or podcasts 
     * pos:none
     * @return msg String shows the top 10 songs and podcasts
     */
    public String top10SongsAndPodcast() {

        String msg = "haven't used the app yet ";

        Song[] top10Songs = new Song[10];
        Podcast[] top10Podcast = new Podcast[10];

        ArrayList<Song> songs = new ArrayList<Song>();
        ArrayList<Podcast> podcasts = new ArrayList<Podcast>();

        for (int i = 0; i < allAudios.size(); i++) {

            if (allAudios.get(i) instanceof Song) {

                    songs.add((Song) allAudios.get(i));

            } else if (allAudios.get(i) instanceof Podcast) {

                    podcasts.add((Podcast) allAudios.get(i));
            }
        }

        int counter = 0;
        Audio max = null;
        max = songs.get(0);
        for (int i = 0; i < songs.size(); i++) {

            if (songs.get(i).getNumberOfPlays() > max.getNumberOfPlays()) {
                max = songs.get(i);
            }
            if (i == songs.size() - 1) {
                if ((top10Songs[9] == null)) {

                    top10Songs[counter] = (Song) max;
                    counter++;
                    songs.remove(max);
                    if (songs.size() > 0) {
                        max = songs.get(0);
                        i = 0;
                    }
                }
            }

        }

        counter = 0;
        max = podcasts.get(0);
        for (int i = 0; i < podcasts.size(); i++) {

            if (podcasts.get(i).getNumberOfPlays() > max.getNumberOfPlays()) {
                max = podcasts.get(i);
            }
            if (i == podcasts.size() - 1) {
                if ((top10Podcast[9] == null)) {

                    top10Podcast[counter] = (Podcast) max;
                    counter++;
                    podcasts.remove(max);
                    if (podcasts.size() > 0) {
                        max = podcasts.get(0);
                        i = 0;
                    }
                }
            }

        }
        for (int i = 0; i < top10Songs.length; i++) {

            if (top10Songs[i] != null) {

                msg = "The top 10 songs are:\n ";

                msg += (i + 1) + "." + top10Songs[i].getName() + "with genre: " + top10Songs[i].getGenre().name() + " with "+ top10Songs[i].getNumberOfPlays() + " plays";
            }
        }

        for (int i = 0; i < top10Podcast.length; i++) {

                if (top10Podcast[i] != null) {

                    msg += "\nThe top 10 podcasts are:\n ";
                    msg += (i + 1) + "." + top10Podcast[i].getName() + "with Category: " + top10Podcast[i].getCategory().name() + " with " + top10Podcast[i].getNumberOfPlays() + " plays";
                }
        }

        return msg;
    }

    public String reportByGenre(String genre) {
        
        String msg = "The genre has not been listened to";
        
        int count = 0;

        int count2 =0;

        for (int i = 0; i < allAudios.size(); i++) {
            
            if (allAudios.get(i) instanceof Song) {

                Song report = (Song)allAudios.get(i);

                if (report.getGenre().name().equals(genre)) {

                    count += report.getSalesValue();

                }
            }
        }
        for(int i=0; i<allUsers.size();i++){

            if (allUsers.get(i) instanceof ConsumerUser) {


               for (int index = 0; index < ((ConsumerUser)allUsers.get(i)).getShoppingList().size(); index++) {

                if (((ConsumerUser)allUsers.get(i)).getShoppingList().get(index).getSelectedSong().getGenre().name().equals(genre)){

                    count2 += 1;

                }
                
               }

            }

        }

        if (count > 0 && count2>0){
            
            msg = "The genre " + genre + " has sold " + count2 + "songs";

        }

        return msg;
    }


    /**
     * Description: compiles the information of the songs and selects the best-selling one 
     * pre: that there are registered songs 
     * pos: none
     * @return msg String the best selling song 
     */
    public String bestSellingSongReport() {

        String msg = "It is not possible to generate the report yet ";
        Song max = null;

            for (int i = 0; i < allAudios.size(); i++) {

                if (allAudios.get(i) instanceof Song) {

                    Song songSold = (Song) allAudios.get(i);

                        if (max == null) {

                            max = songSold;

                        }
                        if (songSold.getTimesSold() > max.getTimesSold()) {

                            max = songSold;

                        }
                }
            }
        if (max!= null) {
            if (max.getTimesSold()>0) {

                msg = "The best-selling song on the platform is " + max.getName() + " with " + max.getTimesSold() + " sales";

            }
        }
        return msg;
    }

}
