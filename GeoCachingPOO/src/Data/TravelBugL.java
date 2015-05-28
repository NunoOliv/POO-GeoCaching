/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Nuno Oliveira
 */
public class TravelBugL implements Serializable {

    private HashMap<String, TravelBug> bugs;

    /**
     * @return the bugs
     */
    public HashMap<String, TravelBug> getBugs() {
        return new HashMap<>(bugs);
    }

    /**
     * @param bugs the bugs to set
     */
    public void setBugs(HashMap<String, TravelBug> bugs) {
        this.bugs = new HashMap<>(bugs);
    }

    public boolean newTB(String nome) {
        TravelBug newTb = new TravelBug(nome);
        if (bugs.containsKey(nome)) {
            return false;
        }
        this.bugs.put(nome, newTb);
        return true;
    }

    public void addCacheRec(String bug, String cache) {
        bugs.get(bug).addCache(cache);
        bugs.get(bug).setCurrentCache(cache);
    }

    public void takeFromCache(String bug, String cache) {
        bugs.get(bug).setCurrentCache(null);
    }

    public boolean containsBug(String bug) {
        return bugs.containsKey(bug);
    }

}
