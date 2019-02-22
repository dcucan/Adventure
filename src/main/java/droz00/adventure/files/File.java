package droz00.adventure.files;

import java.util.HashMap;
import java.util.Map;

/**
 * General class for the files
 * It has name, description and isPossibleToCut(if false, they cant be cut)
 */
public class File {

    private String name;
    private String type;
    private boolean isPossibleToCut;


    public File(String name, String type, boolean isPossibleToCut) {
        this.name = name;
        this.type = type;
        this.isPossibleToCut = isPossibleToCut;


    }

    public String getFileName() {
        return name + "." + type;
    }



    public boolean getIsPossibleToCut(){
        return isPossibleToCut;
    }

}
