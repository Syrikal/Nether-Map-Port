package com.syric.betternethermap.config;

import com.syric.betternethermap.BetterNetherMap;

import java.util.ArrayList;
import java.util.Comparator;

class DimensionEntry {

    protected final String dimension;
    protected final ArrayList<Integer> heights;

    public DimensionEntry(String s) {
        String tempDim = "";
        ArrayList<Integer> tempHeights = new ArrayList<>();

        try {
            String[] split = s.split(",");
            tempDim = split[0];
            for (int i = 1; i < split.length; i++) {
                tempHeights.add(Integer.parseInt(split[i]));
            }
        } catch (IndexOutOfBoundsException i) {
            BetterNetherMap.LOGGER.info("IndexOutOfBoundsException while parsing dimension entry");
        } catch (NumberFormatException n) {
            BetterNetherMap.LOGGER.info("NumberFormatException while parsing dimension entry");
        }

        dimension = tempDim;
        heights = tempHeights;
    }

    public static DimensionEntry deserialize(String s) {
        return new DimensionEntry(s);
    }

    public static boolean validateFixed(String s) {
        String[] split = s.split(",");
        if (split.length != 2) {
            return false;
        } else {
            try {
                for (int i = 1; i < split.length; i++) {
                    Integer.parseInt(split[i]);
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
    public static boolean validateSnap(String s) {
        String[] split = s.split(",");
        if (split.length < 2) {
            return false;
        } else {
            try {
                for (int i = 1; i < split.length; i++) {
                    Integer.parseInt(split[i]);
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public int getClosestValue(int y) {

        if (heights.size() == 0) {
            return -1000;
        }

        int closest = heights.stream().min(Comparator.comparingInt(i -> Math.abs(i-y))).orElse(256);
        BetterNetherMap.LOGGER.info("Found snap height of " + closest);

        return closest;
    }

}