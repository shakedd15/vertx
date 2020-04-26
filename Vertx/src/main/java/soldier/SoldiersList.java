package soldier;

import com.google.gson.Gson;

import java.util.List;
import java.util.Optional;

public class SoldiersList {

    private List<Soldier> soldiers;

    public SoldiersList(List<Soldier> soldiers){
        this.soldiers = soldiers;
    }

    public Optional<Soldier> getSoldierById(String id) {
        Optional<Soldier> optionalSoldier = Optional.empty();
        if (!soldiers.isEmpty()) {
            for (Soldier soldier : soldiers) {
                if (soldier.getId().equals(id)) {
                    optionalSoldier = Optional.of(soldier);
                    break;
                }
            }
        }
        return optionalSoldier;
    }

    public boolean addSoldier(Soldier soldier) {
        boolean isAdded = false;
        if (!isSoldierExist(soldier)) {
            soldiers.add(soldier);
            isAdded = true;
        }
        return isAdded;
    }

    public boolean removeSoldierById(String id) {
        boolean isRemoved = false;
        Optional<Soldier> optionalSoldier = getSoldierById(id);
        if (optionalSoldier.isPresent()) {
            soldiers.remove(optionalSoldier.get());
            isRemoved = true;
        }
        return isRemoved;
    }

    public boolean updateSoldier(Soldier soldier) {
        boolean isUpdated = false;
        int index = getSoldierIndexById(soldier.getId());
        if (index != -1) {
            soldiers.set(index, soldier);
            isUpdated = true;
        }
        return isUpdated;
    }

    public boolean isSoldierExist(Soldier soldier) {
        return getSoldierIndexById(soldier.getId())!=-1;
    }

    private int getSoldierIndexById(String id) {
        if (!soldiers.isEmpty()) {
            for (int i = 0; i < soldiers.size(); i++) {
                if (soldiers.get(i).getId().equals(id)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public String toJsonString() {
        return new Gson().toJson(soldiers);
    }

}
