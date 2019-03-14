package droz00.adventure.files;

import droz00.adventure.Game;
import droz00.adventure.places.Place;
import droz00.adventure.places.PlaceManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Stored cut files, it is possible to paste them somewhere else
 */
public class Clipboard {

    private Map<String, File> fileMap;
    private Game game;
    private int maxFiles;

    public Clipboard(Game game) {
        this.game = game;
        fileMap = new HashMap<>();
        // Number of max files in the clipboard
        maxFiles = 1;

    }

    /**
     * Checks if the clipboard has free spot
     * @return
     */
    public boolean hasFreeSpot() {
        if (fileMap.size() < maxFiles) {
            return true;
        }
        return false;
    }

    /**
     * adds file(s) to the clipboard
     * @param file
     * @return
     */
    public boolean addFile(File file) {
        if (fileMap.size() < maxFiles) {
            fileMap.put(file.getFileName(), file);
            return true;
        }
        return false;
    }

    /**
     * remove files from the clipboard
     * @param name
     * @return
     */
    public File removeFile(String name) {
        return fileMap.remove(name);
    }

    /**
     * gets file(s) in the clipboard
     * @return
     */
    public Map<String, File> getFiles() {
        return fileMap;
    }

    /**
     * checks if clipboard has a certain file
     * @param name
     * @return
     */
    public boolean hasFile(String name) {
        return fileMap.containsKey(name);
    }


}
