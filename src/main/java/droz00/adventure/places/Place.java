package droz00.adventure.places;

import droz00.adventure.files.File;

import java.util.HashMap;
import java.util.Map;

/**
 * General class for places
 * Has map of neighbor places
 * Has map of files in each place
 */
public class Place {


    private String name;
    private Map<String, Place> neighbors;
    private String description;
    private Map<String, File> filesInDirectory;

    public Place(String name, String description) {
        this.name = name;
        this.description = description;
        neighbors = new HashMap<>();
        filesInDirectory = new HashMap<>();
    }

    /**
     * Checks if place has a certain file
     * @param name
     * @return
     */
    public boolean hasFile(String name) {
        return filesInDirectory.containsKey(name);
    }

    /**
     * Checks if place has a certain neighbor
     * @param name
     * @return
     */
    public boolean hasNeighbor(String name){
        return getNeighbors().containsKey(name);
    }

    /**
     * adds file to the directory
     * @param file
     */
    public void addFile(File file) {
        filesInDirectory.put(file.getFileName(), file);
    }

    /**
     * remove file from the directory
     * @param name
     * @return
     */
    public File removeFile(String name) {
        return filesInDirectory.remove(name);
    }

    /**
     * adds neighbor
     * @param place
     */
    public void addNeighbor(Place place) {
        neighbors.put(place.getName(), place);
    }

    /**
     * gets neighbor
     * @param name
     * @return
     */
    public Place getNeighbor(String name) {
        return neighbors.get(name);
    }

    /**
     * removes neighbor
     * @param name
     * @return
     */
    public Place removeNeighbor(String name){
        return neighbors.remove(name);
    }

    /**
     * get name of the directory
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * gets map of the neighbors of the directory
     * @return
     */
    public Map<String, Place> getNeighbors() {
        return neighbors;
    }

    /**
     * gets map of the files in directory
     * @return
     */
    public Map<String, File> getFiles() {
        return filesInDirectory;
    }

    /**
     * Because of having unique names, so I can leave generation of hashcode up to the name
     * @return hashcode
     */
    @Override
    public int hashCode() {

        return getName().hashCode();

    }


}
