package droz00.adventure.places;

import droz00.adventure.Game;
import droz00.adventure.files.File;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Sets up the directories, puts files in them
 */
public class PlaceManager {

    private Map<String, Place> placeMap;
    private Game game;

    public PlaceManager(Game game) {
        placeMap = new HashMap<>();
        this.game = game;
        setUp();

    }

    private void setUp() {
        Place macos = new Place("macos", "Macos description");
        Place windows = new Place("windows", "Windows description");
        Place finder = new Place("finder", "macos main directory");
        Place computer = new Place("computer", "windows main directory");
        Trash trash = new Trash("delete file");
        Place pictures = new Place("pictures","directory of pictures");
        Place downloads = new Place("downloads","directory of downloads");
        Place applications = new Place("applications","directory of applications");
        Place school = new Place("school","directory of school");
        WebBrowser safari = new WebBrowser("safari","Web browser safari");
        WebBrowser chrome = new WebBrowser("chrome","Web browser chrome");
        Place newDirectory = new Place("new_directory", "Directory");

        // register place to the game
        registerPlace(macos);
        registerPlace(windows);
        registerPlace(finder);
        registerPlace(computer);
        registerPlace(pictures);
        registerPlace(downloads);
        registerPlace(applications);
        registerPlace(school);
        registerPlace(safari);
        registerPlace(chrome);
        registerPlace(newDirectory);
        registerPlace(trash);

        // sets neighbors
        setNeighbors(macos,safari);
        setNeighbors(macos,chrome);
        setNeighbors(macos, windows);
        setNeighbors(macos, finder);
        setNeighbors(macos,trash);
        setNeighbors(windows,trash);
        setNeighbors(windows,computer);
        setNeighbors(finder,pictures);
        setNeighbors(finder,downloads);
        setNeighbors(finder,applications);
        setNeighbors(windows,trash);
        setNeighbors(finder,school);
        setNeighbors(computer, windows);
        setNeighbors(windows, newDirectory);

        game.setCurrentPlace(macos);

        //ads files to the directories
        macos.addFile(new File("Java","pdf",true));
        macos.addFile(new File("Picture","jpeg",true));
        pictures.addFile(new File("me","jpg",true));
        pictures.addFile(new File("screenshot","jpg",true));
        pictures.addFile(new File("prague","jpg",true));
        pictures.addFile(new File("xx7ui98qqq004","jpg",true));
        pictures.addFile(new File("forsale","jpg",true));
        downloads.addFile(new File("oxygen","bn",true));
        downloads.addFile(new File("homework","pdf",true));
        downloads.addFile(new File("invoice","xml",true));
        downloads.addFile(new File("invoice(1)","xml",true));
        downloads.addFile(new File("CV_Zuzana_Drozdova","pdf",true));
        downloads.addFile(new File("4iz210","pdf",true));
        safari.addFile(new File("bootcamp_assistant","vhd",true));
        safari.addFile(new File("windows10","vhd",true));
        downloads.addFile(new File("cviceni2","pdf",true));
        newDirectory.addFile(new File("Age_Of_Empires","vhd",true));
        newDirectory.addFile(new File("system","bn",false));
        computer.addFile(new File("screenshot","jpeg",true));
        computer.addFile(new File("Windows","txt",true));
        applications.addFile(new File("System", "mac",false));
        applications.addFile(new File("hdt875xxi876","config",false));
    }

    /**
     * Method to register the place(directory)
     * @param place
     */
    public void registerPlace(Place place) {
        placeMap.put(place.getName(), place);

    }

    /**
     * Method to set 2 neighbors
     * @param a
     * @param b
     */
    public void setNeighbors(Place a, Place b) {
        a.addNeighbor(b);
        b.addNeighbor(a);
    }

    public Collection<Place> getPlaces(){
        return placeMap.values();
    }



}
