package droz00.adventure.commands;

import droz00.adventure.Game;
import droz00.adventure.places.Place;
import droz00.adventure.places.WebBrowser;

/**
 * Command check work only in Web Browser, otherwise it only shows descriptions.
 * In Web Browser can be use only once and you can either win or lose the game.
 */
public class CommandCheck extends Command {

    public CommandCheck(Game game) {
        super(game, "check");
        description = "Checks your internet browser, works only if your current place is any internet browser, be careful about" +
                " using it, you can only use it once";
    }

    /**
     * Checks if your are in Webbrowser, if not, returns description.
     * If so, checks if your internet browser is Safe Finder or Google
     * If its Safe Finder, game is lost
     * If its Google, game is won
     * @param parameters no parameters
     * @return answer
     */
    @Override
    public String executeCommand(String[] parameters) {
        if (game.getCurrentPlace() instanceof WebBrowser) {
            WebBrowser browser = (WebBrowser)game.getCurrentPlace();

            if (browser.getActualBrowser().equals("Google")) {
                game.gameEnds(true);
                return "You have finaly set the internet browser to Google, well done!";
            } else {
                game.gameEnds(false);
                return "Your internet browser is still Safe finder, boohoo :(";
            }
        } else {
            return "Your current place is not an internet browser";
        }
    }
}
