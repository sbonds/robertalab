package de.fhg.iais.roberta.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.fhg.iais.roberta.util.dbc.Assert;

public class DropDown {
    private final List<Pair<String, String>> sequence = new ArrayList<>();
    private final Map<String, Pair<String, String>> leftMap = new HashMap<>();
    private final Map<String, Pair<String, String>> rightMap = new HashMap<>();

    public DropDown() {
        // create maps and list
    }

    public void add(String left, String right) {
        Assert.notNull(left);
        Assert.notNull(right);
        Pair<String, String> entry = Pair.of(left, right);
        this.sequence.add(entry);
        Assert.isNull(this.leftMap.put(left, entry), "this key was already mapped in left: {}", left);
        Assert.isNull(this.rightMap.put(right, entry), "this key was already mapped in right: {}", right);
    }

    public String getByFirst(String first) {
        Assert.notNull(first);
        return this.leftMap.get(first).getSecond();
    }

    public String getBySecond(String second) {
        Assert.notNull(second);
        return this.rightMap.get(second).getFirst();
    }

    public Pair<String, String> getPairByFirst(String first) {
        Assert.notNull(first);
        Pair<String, String> pair = this.leftMap.get(first);
        Assert.notNull(pair);
        return pair;
    }

    public Pair<String, String> getPairBySecond(String second) {
        Assert.notNull(second);
        Pair<String, String> pair = this.rightMap.get(second);
        Assert.notNull(pair);
        return pair;
    }

    public List<String> getAllFirst() {
        return this.sequence.stream().map(e -> e.getFirst()).collect(Collectors.toList());
    }

    public List<String> getAllSecond() {
        return this.sequence.stream().map(e -> e.getSecond()).collect(Collectors.toList());
    }
}
