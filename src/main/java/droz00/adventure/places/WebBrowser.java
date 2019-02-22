package droz00.adventure.places;

import droz00.adventure.files.File;

/**
 * important place - default browser is Safe Finder and if you delete certain files, you can change
 * it to Google and so win the game
 */
public class WebBrowser extends Place {

    private String actualBrowser;

    public WebBrowser(String name, String description) {
        super(name, description);
        actualBrowser = "Safe finder";
    }

    public String getActualBrowser() {
        return actualBrowser;
    }

    /**
     * Checks if the files here are 0, if so, it sets the actual browser to Google
     * @param name
     * @return
     */
    @Override
    public File removeFile(String name) {
        File file = super.removeFile(name);

        if (getFiles().size() == 0) {
            actualBrowser = "Google";
        }
        return file;
    }
}
