package stusyo222b.webappsspringproject.enums;

public enum OfficeWorkerFamilyState {
    MARRIED("Married"),
    SINGLE("Single"),
    CIVIL_PARTNERSHIP("Civil Partnership"),
    DIVORCED("Divorced"),
    WIDOWED("Widowed");

    private final String displayName;

    OfficeWorkerFamilyState(String displayName) {
        this.displayName = displayName;
    }

    public static OfficeWorkerFamilyState getOfficeWorkerFamilyStateById(Integer index) {
        if (index >= OfficeWorkerFamilyState.values().length) {
            return OfficeWorkerFamilyState.values()[0];
        } else {
            return OfficeWorkerFamilyState.values()[index];
        }
    }

    public String getDisplayName() {
        return displayName;
    }

    public static String[] getOffWorkerFamilyState() {
        OfficeWorkerFamilyState[] pl = values();
        String[] plNames = new String[pl.length];
        for (int i = 0; i < pl.length; i++) {
            plNames[i] = pl[i].getDisplayName();
        }
        return plNames;
    }

    public static int getEnumIndex(String value) {
        int index = -1;
        OfficeWorkerFamilyState[] pl = values();
        for (OfficeWorkerFamilyState lang: pl) {
            index++;
            if (lang.getDisplayName().equals(value)) {
                break;
            }
        }
        return index;
    }

    public static OfficeWorkerFamilyState getOfficeWorkerFamilyStateByName(String namePL) {
        int index = -1;
        OfficeWorkerFamilyState[] plValues = values();
        boolean flFound = false;
        while (!flFound && index<plValues.length-1) {
            index++;
            if (plValues[index].getDisplayName().equals(namePL)) {
                flFound = true;
            }
        }
        OfficeWorkerFamilyState pl = null;
        if (!flFound) {
            pl = OfficeWorkerFamilyState.values()[0];
        } else {
            pl = OfficeWorkerFamilyState.values()[index];
        }
        return pl;
    }


    public static String[]  getOfficeWorker() {
        OfficeWorkerFamilyState[] pl = values();
        String[] plNames = new String[pl.length];
        for (int i = 0; i < pl.length; i++) {
            plNames[i] = pl[i].getDisplayName();
        }
        return plNames;
    }

}
