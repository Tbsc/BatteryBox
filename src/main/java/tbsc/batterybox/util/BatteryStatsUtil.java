package tbsc.batterybox.util;

/**
 * @author Tbsc on 6/11/2015, 7:41
 */
public class BatteryStatsUtil {

    public static BatteryStats getNewBatteryStats(int energy, int maxEnergy, int maxExtract, int maxRecive, int maxDefaultExtract, int maxDefaultReceive) {
        return new BatteryStats(energy, maxEnergy, maxExtract, maxRecive, maxDefaultExtract, maxDefaultReceive);
    }

    public static BatteryStats getNewBatteryStats(int energy, int maxEnergy, int maxTransfer, int maxDefaultTrasfer) {
        return new BatteryStats(energy, maxEnergy, maxTransfer, maxTransfer, maxDefaultTrasfer, maxDefaultTrasfer);
    }

    public static class BatteryStats {

        private int energy;
        private int maxEnergy;
        private int maxExtract;
        private int maxReceive;
        private int maxDefaultExtract;
        private int maxDefaultReceive;

        public BatteryStats(int energy, int maxEnergy, int maxExtract, int maxReceive, int maxDefaultExtract, int maxDefaultReceive) {
            this.energy = energy;
            this.maxEnergy = maxEnergy;
            this.maxExtract = maxExtract;
            this.maxReceive = maxReceive;
            this.maxDefaultExtract = maxDefaultExtract;
            this.maxDefaultReceive = maxDefaultReceive;
        }

        public int getEnergy() {
            return energy;
        }

        public void setEnergy(int energy) {
            this.energy = energy;
        }

        public int getMaxEnergy() {
            return maxEnergy;
        }

        public void setMaxEnergy(int maxEnergy) {
            this.maxEnergy = maxEnergy;
        }

        public int getMaxExtract() {
            return maxExtract;
        }

        public void setMaxExtract(int maxExtract) {
            this.maxExtract = maxExtract;
        }

        public int getMaxReceive() {
            return maxReceive;
        }

        public void setMaxReceive(int maxReceive) {
            this.maxReceive = maxReceive;
        }

        public int getMaxDefaultExtract() {
            return maxDefaultExtract;
        }

        public void setMaxDefaultExtract(int maxDefaultExtract) {
            this.maxDefaultExtract = maxDefaultExtract;
        }

        public int getMaxDefaultReceive() {
            return maxDefaultReceive;
        }

        public void setMaxDefaultReceive(int maxDefaultReceive) {
            this.maxDefaultReceive = maxDefaultReceive;
        }

    }

}
