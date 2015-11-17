package tbsc.batterybox.util;

/**
 * @author Tbsc on 6/11/2015, 7:41
 */
public class BatteryStatsUtil {

    /**
     * Used to create {@link BatteryStats} instances, for creation of tile entities.
     * @param maxEnergy Max energy it will be *able* to hold
     * @param maxTransfer Amount of energy that will be able to transfer (in/out, RF/t)
     * @param maxDefaultTrasfer Amount of energy transfer that is the default
     * @return {@link BatteryStats} with the specified stats
     */
    public static BatteryStats getNewBatteryStats(int maxEnergy, int maxTransfer, int maxDefaultTrasfer) {
        return new BatteryStats(maxEnergy, maxTransfer, maxTransfer, maxDefaultTrasfer, maxDefaultTrasfer);
    }

    public static class BatteryStats {

        private int maxEnergy;
        private int maxExtract;
        private int maxReceive;
        private int maxDefaultExtract;
        private int maxDefaultReceive;

        private boolean isCreative;

        protected BatteryStats(int maxEnergy, int maxExtract, int maxReceive, int maxDefaultExtract, int maxDefaultReceive) {
            this.maxEnergy = maxEnergy;
            this.maxExtract = maxExtract;
            this.maxReceive = maxReceive;
            this.maxDefaultExtract = maxDefaultExtract;
            this.maxDefaultReceive = maxDefaultReceive;

            this.isCreative = maxEnergy == -1;
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
