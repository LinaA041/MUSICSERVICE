package ui;
import java.util.Scanner;

import model.MusicServiceController;

//Description: welcome to neotunes, where you can really own your music catalog,
//by registering as a user, registering your songs and even buying them. 

public class MusicServiceManager {

    public static Scanner sc = new Scanner(System.in);
	public static MusicServiceController musicController = new MusicServiceController ();

    public static void main(String[] args){
		
        MusicServiceManager preMenu = new MusicServiceManager();

        int option;
        System.out.println("Welcome to NeoTunes");

        do {
            do {
                preMenu.menu();
                option = sc.nextInt();

            } while (option < 0 || option > 16);

        } while (option != 0);
        
    }

    private void menu(){
	
		boolean exit = false;

		while (!exit) {	
        
        System.out.println("¡Welcome to NeoTunes!\nPlease select an option ");
        System.out.println("1.Register producer users\n2.Register consumer users\n3.Record audio\n4.Create playlist\n5.Edit playlist\n6.Share a playlist ");
        System.out.println("7.Play audio \n8.Buy a song\n9.Generate report of accumulated reproductions \n10.Know the most listened song genre\n11.Know the most listened podcast category  ");
        System.out.println("12.Check out the top five artists and content creators\n13.Check the top ten songs and podcasts" );
        System.out.println("14.Generate report by genre\n15.Generate report of the best-selling song on the platform\n16.Exit" );
        int select = sc.nextInt();

        
			switch (select) {
            
                case 1:
                  System.out.println("1.Register an artist");
                  System.out.println("2.Register a content creator ");
                  
                    int option = sc.nextInt();

                        if(option==1) {

                            registerArtist();

                        } else if(option==2) {

                            registerContentCreator();

                        }else {

                            System.out.println("Type a valid option");
                            
                        }
                        break;
                case 2:
                  System.out.println("1.Register a premium user");
                  System.out.println("2.Register a standard user");
                
                    int option2 = sc.nextInt();

                        if(option2==1) {

                           registerPremiumUser();
                        
                        } else if(option2==2) {
                           
                           registerStandarUser();

                        }else {

                            System.out.println("Type a valid option");

                        }
                        break;
                case 3: 
                    System.out.println("1.Record a song");
                    System.out.println("2.Register a podcast ");
                    
                    int option3 = sc.nextInt();

                        if(option3==1) {

                            recordSong();

                        } else if(option3==2) {

                            registerPodcast();

                        }else {

                            System.out.println("Type a valid option");
                            
                        }
                        break;
                case 4: 
                  createPlaylist();
                  break;
                case 5:

                    System.out.println("1.Rename the playlist");
                    System.out.println("2.Add audio to the playlist");
                    System.out.println("3.Remove audio from playlist");
                    
                        int option4 = sc.nextInt();

                            if(option4==1) {

                                renamePlaylist();

                            } else if(option4==2) {

                                addAudioToPlaylist();

                            }else if(option4==3){

                                deleteAudio();

                            }else {

                                System.out.println("Type a valid option");
                                
                            }
                        break;
                case 6:
                  sharePlaylist();
                  break;
                case 7:
                   playAudio();
                break;
                case 8:
                 buySong();
                break;
                case 9:
                System.out.print("1.To know the total number of reproductions of the songs ");
                System.out.println("2.Know the total number of plays of the podcasts ");
                int option5 =sc.nextInt();

                if(option5==1){
                    
                    System.out.println(musicController.reportOnAccumulatedSongsPlays());

                }else if (option5==2){

                    System.out.println(musicController.reportOnAccumulatedPodcastPlays());

                } else {

                    System.out.println("Type a valid option");
                }
                case 10:
                System.out.print("1.Know the most listened song genre for the entire platform.");
                System.out.println("2.Knowing the most listened song genre for a specific user ");
                int option6 =sc.nextInt();

                if(option6==1){
                    
                    System.out.println(musicController.mostListenedGenreOnTheEntirePlatform());

                }else if (option6==2){

                    mostListenedGenreToByUser();

                } else {

                    System.out.println("Type a valid option");
                }
                break;
               case 11: 

                 System.out.print("1.Know the most listened song genre for the entire platform.");
                 System.out.println("2.Knowing the most listened song genre for a specific user ");
                 int option7 =sc.nextInt();

                if(option7==1){
                    
                    System.out.println(musicController.mostListenedCategoryOnTheEntirePlatform());

                }else if (option7==2){

                    mostListenedCategoryToByUser();

                } else {

                    System.out.println("Type a valid option");
                }
                break;
                case 12: 
                System.out.println(musicController.top5ArtistAndCreators());
                break;
                case 13:
                System.out.println(musicController.top10SongsAndPodcast());
                break;
                case 14:
                reportByGenre();
                break;
                case 15:
                System.out.println(musicController.bestSellingSongReport());
                break;
                case 16:
				exit = true;
				System.out.println("Thanks for using our system");
				break;
			default:
				System.out.println("You must type a valid option");
				break;
            }
        }
    
    }
    
    /**
     * Description: asks for the necessary data to register a artist 
     * pre: none 
     * pos: none 
     * @return not return 
     */
    public void registerArtist(){

        System.out.println("Enter the artist's nickname ");
        sc.nextLine();
        String nickname = sc.nextLine();
  
        System.out.println("Enter the artist id");
        String id = sc.nextLine();
        
        System.out.println("Type the artist's name ");
        String name = sc.nextLine();
        
        
        System.out.println("Type the url corresponding to the artist ");
        String representativeUrl = sc.nextLine();
        
  
          if (musicController.registerArtist(nickname, id,name, representativeUrl)) {
  
              System.out.println("Artist successfully registered");
  
          } else {
              System.out.println("Error,Artist could not be registered");
          }
  
    }

    
     /**
     * Description: asks for the necessary data to register a content creator
     * pre: none 
     * pos: none 
     * @return not return 
     */
    public void registerContentCreator(){

        System.out.println("Enter the content creator's nickname");
        sc.nextLine();
        String nickname = sc.nextLine();
  
        System.out.println("Enter the content creator id");
        String id = sc.nextLine();
        
        System.out.println("Type the content creator's name ");
        String name = sc.nextLine();
        
        
        System.out.println("Type the url corresponding to the content creator ");
        String representativeUrl = sc.nextLine();
        
  
        if (musicController.registerContentCreator(nickname, id,name, representativeUrl)) {
  
            System.out.println("Content creator successfully registered");
  
        } else {
            
            System.out.println("Error, contente creator could not be registered");
        }

    }
    
     /**
     * Description: asks for the necessary data to register a premium user 
     * pre: none 
     * pos: none 
     * @return not return 
     */
    public void registerPremiumUser(){

        System.out.println("Enter the premium user's nickname");
        sc.nextLine();
        String nickname = sc.nextLine();
  
        System.out.println("Enter the premium user id");
        String id = sc.nextLine();
        
  
          if(musicController.registerPremiumUser(nickname, id)) {
  
              System.out.println("Premium user successfully registered");
  
          } else {
              System.out.println("Error, premium user could not be registered");
          }
        
    }

    /**
     * Description: asks for the necessary data to register a standard user 
     * pre: none 
     * pos: none 
     * @return not return 
     */
    public void registerStandarUser(){

        System.out.println("Enter the premium user's nickname");
        sc.nextLine();
        String nickname = sc.nextLine();
  
        System.out.println("Enter the premium user id");
        String id = sc.nextLine();
        
          if(musicController.registerStandardUser(nickname, id)) {
  
              System.out.println("Standard user successfully registered");
  
          } else {
              System.out.println("Error, standard user could not be registered");
          }

    }

    /**
     * Description:request the necessary data to register a song 
     * pre: none
     * pos: none
     * @return not return 
     */
    public void recordSong(){

        System.out.println("Enter the podcast name");
        String name = sc.nextLine();
        
        System.out.println("Type the url pertaining to the album artwork ");
        String distinctiveUrl = sc.nextLine();

        System.out.println("How many minutes is the song?");
        int duration = sc.nextInt();

        System.out.println("Please, type the nickname of the artist the song belongs to");
        String nickname = sc.nextLine();

        System.out.println("Type the name of the album the song belongs to ");
        String album = sc.nextLine();
        
        System.out.println("What is your song genre\n0.Rock\n1.Pop\n2.Trap\n3.House");
        int genre = sc.nextInt();

        System.out.println("Type the selling value of the song (in dollars)");
        double salesValue = sc.nextDouble();

        musicController.registerSong(name,distinctiveUrl, duration, nickname, album, genre, salesValue);

    }

    /**
     * Description: request data to register a podcast 
     * pre: none
     * pos: none 
     * @return not return 
     */
    public void registerPodcast(){

        System.out.println("Enter the podcast name");
        String name = sc.nextLine();

        System.out.println("Enter the url of your podcast image");
        String distinctiveUrl = sc.nextLine();
        
        System.out.println("How many minutes is the podcast?");
        int duration = sc.nextInt();
        
        System.out.println("Please, enter the nickname of the creator to which the content belongs ");
        String nickname = sc.nextLine();

        System.out.println("Please, write a brief description of the content of your podcast");
        String description = sc.nextLine();

        System.out.println("Select your podcast category: \n0.Politics\n1.Entertainment\n2.VideoGame\n3.Fashion");
        int category = sc.nextInt();

        musicController.registerPodcast(name,distinctiveUrl, duration, nickname, description, category);
    }


    /**
     * Description: requests data to create an empty playlist to which songs can be added later on  
     * pre: none
     * pos: none 
     * @return not return 
     */
    public void createPlaylist(){

        System.out.println("Enter the playlist name");
        sc.nextLine();
        String playlistName = sc.nextLine();

        System.out.println("Please, enter your user nickname again");
        sc.nextLine();
        String nickname = sc.nextLine();

        musicController.createPlaylist(playlistName, nickname);

    }
  
    /**
     * Description: requests data to create an empty playlist to which songs can be added later on  
     * pre:none
     * pos: none
     * @return not return 
     */
    public void renamePlaylist(){

        System.out.println("Please, enter your user nickname again");
        String nickname = sc.nextLine();

        System.out.println("Enter the current name of the playlist");
        String playlistName = sc.nextLine();

        System.out.println("");
        String newPlaylistName = sc.nextLine();

        musicController.editPlaylistName(nickname, playlistName, newPlaylistName);

    }

    /**
     * Description:request data to add an audio to a playlist 
     * pre:none
     * pos:none
     * return not return
     */
    private void addAudioToPlaylist(){
       
        System.out.println("Please, enter the name of the audio you want to add");
        String audioName = sc.nextLine();

        System.out.println("Enter the name of the playlist it will be added to");
        String playlistName = sc.nextLine();

        System.out.println("Please, enter your user nickname again");
        String nickname = sc.nextLine();

        musicController.addAudioToPlaylist(audioName,playlistName, nickname);

    }

    /**
     * Description: request data to delete an audio 
     * pre: none
     * pos: none
     * @return no return 
     */
    private void deleteAudio(){

        System.out.println("Enter the name of the playlist the audio belongs to ");
        String playlistName = sc.nextLine();

        System.out.println("Please, enter your user nickname again");
        String nickname = sc.nextLine();

        System.out.println("Please, enter the name of the audio you want to remove");
        String audioName = sc.nextLine();


        musicController.deleteAudioToPlaylist(playlistName, nickname,audioName);

    }

    /**
     * Description: request data to share a playlist 
     * pre: none
     * pos:none
     * @return not return
     */
    private void sharePlaylist(){

        System.out.println("Please, enter your user nickname ");
        String nickname = sc.nextLine();

        System.out.println("Type the name of the playlist you want to share  ");
        String playlistName = sc.nextLine();
        
        musicController.sharePlaylist(nickname, playlistName);

    }

    /**
     * Description: requests the user's data and the name of the audio you want to listen to 
     * pre: none
     * pos: none
     * @return not return 
     */
    private  void playAudio(){
    
        System.out.println("Please, enter your user nickname ");
        String nickname = sc.nextLine();

        System.out.println("Type the name of the audio you want to listen to ");
        String audioName = sc.nextLine();
        
        musicController.playAudio(nickname, audioName);

    }

    /**
     * Description: request buyer's data and the song you want to buy 
     * pre: none
     * pos: none
     * @return not return
     */
    private void buySong(){

        System.out.println("Please, enter your user nickname ");
        String nickname = sc.nextLine();

        System.out.println("Type the name of the audio you want to listen to ");
        String audioName = sc.nextLine();
        
        musicController.buySong(nickname, audioName);

    }
    
     /**
     * Description :in this method, we will be able to consult the most listened song genre for a specific user.
     * pre:none
     * pos: none
     * @return not return  
     */
    private void mostListenedGenreToByUser(){
     
        System.out.println("Please, enter your user nickname ");
        String nickname = sc.nextLine();

       musicController.mostListenedGenreToByUser(nickname);
    }

    /**
     * Description: requests the gender in order to make your report 
     * pre: none
     * pos: none
     * @return not return 
     */
    private void reportByGenre(){

        System.out.println("Select the genre to show : \n1.Rock\n2. Pop\n3. Trap\4. House");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                System.out.println(musicController.reportByGenre("ROCK"));
                break;
            case 2:
                System.out.println(musicController.reportByGenre("POP"));
                break;
            case 3:
                System.out.println(musicController.reportByGenre("TRAP"));
                break;
            case 4:
                System.out.println(musicController.reportByGenre("HOUSE"));
                break;
            }
    }

    /**
     * Description :in this method, we will be able to consult the most listened podcast category for a specific user.
     * pre:none
     * pos: none
     * @return not return  
     */
    private void mostListenedCategoryToByUser(){

        System.out.println("Please, enter your user nickname ");
        String nickname = sc.nextLine();

       musicController.mostListenedCategorybyUser(nickname);

    }
}