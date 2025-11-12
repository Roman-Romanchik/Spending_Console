package org.aren_rend;

public class Validator {
    private final boolean isValid = true;

    private boolean isNeedString(String menuItem, int firstItem, int lastItem, boolean yes) {
        if(menuItem == null || menuItem.trim().isEmpty()) {
            return !isValid;
        }
        try {
            int intMenuItem = Integer.parseInt(menuItem.trim());
            if(intMenuItem >= firstItem && intMenuItem <= lastItem) {
                return isValid;
            }
            return !isValid;
        } catch (NumberFormatException e) {
            return yes;
        }
    }

    public boolean isMenuItem(String menuItem, int firstItem, int lastItem) {
        return isNeedString(menuItem, firstItem, lastItem, !isValid);
    }

    public boolean isCategoryItem(String menuItem, int firstItem, int lastItem) {
        return isNeedString(menuItem, firstItem, lastItem, isValid);
    }
}