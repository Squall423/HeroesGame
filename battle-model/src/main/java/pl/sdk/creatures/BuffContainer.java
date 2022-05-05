package pl.sdk.creatures;

import pl.sdk.spells.BuffOrDebuffSpell;
import pl.sdk.spells.BuffOrDebuffStatistic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BuffContainer extends HashMap<BuffOrDebuffSpell, Integer> implements PropertyChangeListener {
    Arrays getAll;

    List<BuffOrDebuffStatistic> getAllBuffStats() {
        return keySet().stream().map(BuffOrDebuffSpell::getBuffStats).collect(Collectors.toList());
    }

    void add(BuffOrDebuffSpell aBuffStatistic) {
        put(aBuffStatistic, aBuffStatistic.getDuration());
    }

    @Override
    public void propertyChange(PropertyChangeEvent aPropertyChangeEvent) {
        final List<BuffOrDebuffSpell> toRemove = new ArrayList<>();
        keySet().stream().forEach(buff -> {
            if (!get(buff).equals(0)) {
                put(buff, get(buff) - 1);
            } else {
                toRemove.add(buff);
            }
        });
        toRemove.forEach(this::remove);
    }
}
