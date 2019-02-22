package droz00.adventure.places;

import droz00.adventure.files.File;

/**
 * Special directory - if the player pastes any file here, it dissappear from the game
 */
public class Trash extends Place {


    public Trash(String description) {
        super("trash", description);
    }

    /**
     * overides the parent method and does nothing, so the file is deleted
     * @param file
     */
    @Override
    public void addFile(File file){}
}
