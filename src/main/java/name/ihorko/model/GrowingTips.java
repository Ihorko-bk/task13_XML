package name.ihorko.model;

public class GrowingTips {
    private double temperature;
    private boolean light;
    private int watering;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
        this.light = light;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int watering) {
        this.watering = watering;
    }

    public GrowingTips() {
    }

    public GrowingTips(double temperature, boolean light, int watering) {
        this.temperature = temperature;
        this.light = light;
        this.watering = watering;
    }

    @Override
    public String toString() {
        return "temperature=" + temperature +
                ", light=" + light +
                ", watering=" + watering;
    }
}
