package org.kylecorp.api;

import com.google.common.collect.Range;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RateInfo {
    private HashMap<Range, Integer> rangeRate = new HashMap<Range, Integer>();

    public void addRangeRate(Range range, Integer rate) {
        rangeRate.putIfAbsent(range, rate);
    }

    public HashMap<Range, Integer> getRangeRate() {
        return rangeRate;
    }

    public Optional<Integer> getRate(Integer start, Integer end) {
        Optional<Map.Entry<Range, Integer>> val = rangeRate.entrySet().stream().filter(entry -> entry.getKey().containsAll(Arrays.asList(start, end))).findFirst();
        if (val.isPresent()) {
            return Optional.of(val.get().getValue());
        }
        return Optional.empty();
    }

}
