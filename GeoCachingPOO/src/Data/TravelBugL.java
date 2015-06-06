package Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class TravelBugL implements Serializable {

    private HashMap<String, TravelBug> bugs;

    /**
     *
     */
    public TravelBugL() {
        this.bugs = new HashMap<>();
    }
    
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

    /**
     *
     * @param nome
     * @return
     */
    public boolean newTB(String nome) {
        TravelBug newTb = new TravelBug(nome);
        if (bugs.containsKey(nome)) {
            return false;
        }
        this.bugs.put(nome, newTb);
        return true;
    }

    /**
     *
     * @param bug
     * @param cache
     */
    public void addCacheRec(String bug, String cache) {
        bugs.get(bug).addCache(cache);
        bugs.get(bug).setCurrentCache(cache);
    }

    /**
     *
     * @param bug
     * @param cache
     */
    public void takeFromCache(String bug, String cache) {
        bugs.get(bug).setCurrentCache(null);
    }
    
    /**
     *
     * @return
     */
    public ArrayList<String> getFreeBugs() {
        ArrayList<String> ret = new ArrayList<>();
        for(String tb : bugs.keySet()) {
            if (bugs.get(tb).getCurrentCache() == null) {
                ret.add(tb);
            }
        }
        return ret;
    }

    /**
     *
     * @param bug
     * @return
     */
    public boolean containsBug(String bug) {
         if(bugs.containsKey(bug) == true) return true; else return false;
    }

    /**
     *
     * @param bug
     * @return
     */
    public String getBugDetails(String bug) {
        return bugs.get(bug).toString();
    }
    
    /**
     *
     * @param bug
     * @return
     * @throws Exception
     */
    public String getCurrCache(String bug) throws Exception {
        if(bugs.get(bug).getCurrentCache() !=null) return bugs.get(bug).getCurrentCache();
        else throw new Exception();
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.bugs);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TravelBugL other = (TravelBugL) obj;
        if (!Objects.equals(this.bugs, other.getBugs())) {
            return false;
        }
        return true;
    }

    
}
