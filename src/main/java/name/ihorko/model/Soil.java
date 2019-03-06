package name.ihorko.model;

public enum Soil {
    PODZOL("підзолиста"), GROUNG("ґрунтова"), DARN_PODZOLIC("дерново-підзолиста");

    private String soil;
    Soil(String soil) {
        this.soil = soil;
    }

    public static Soil getSoil(String soil) {
        if (soil.equals(PODZOL.soil)) return PODZOL;
        if (soil.equals(GROUNG.soil)) return GROUNG;
        if (soil.equals(DARN_PODZOLIC.soil)) return DARN_PODZOLIC;
        return null;
    }

    @Override
    public String toString() {
        return soil;
    }
}
