package tbsc.batterybox.reference;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Tbsc on 5/11/2015, 20:29
 */
public class Reference {

    public static final String MODID = "BatteryBox";
    public static final String NAME = "BatteryBox";
    public static final String VERION = "1.0.0.0";
    public static final String TEXTURE_PREFIX = MODID.toLowerCase();
    public static final String SERVER_PROXY = "tbsc.batterybox.proxy.ServerProxy";
    public static final String CLIENT_PROXY = "tbsc.batterybox.proxy.ClientProxy";
    public static final String GUI_FACTORY = "tbsc.batterybox.client.gui.GuiHandlerBB";
    public static final int GUI_BB = 0;

    public static class Config {

        public enum Tiers {

            T1_CAPACITY, T1_OUT, T1_IN,
            T2_CAPACITY, T2_OUT, T2_IN,
            T3_CAPACITY, T3_OUT, T3_IN,
            T4_CAPACITY, T4_OUT, T4_IN,
            CREATIVE_CAPACITY, CREATIVE_OUT, CREATIVE_IN

        }

        public static final ConcurrentHashMap<Tiers, Integer> batteryRateConfig = new ConcurrentHashMap<Tiers, Integer>();

        public static int defaultCapacityT1 = 400000;
        public static int defaultExtractT1 = 1000;
        public static int defaultReceiveT1 = 1000;

        public static int defaultCapacityT2 = 2000000;
        public static int defaultExtractT2 = 2500;
        public static int defaultReceiveT2 = 2500;

        public static int defaultCapacityT3 = 10000000;
        public static int defaultExtractT3 = 10000;
        public static int defaultReceiveT3 = 10000;

        public static int defaultCapacityT4 = 100000000;
        public static int defaultExtractT4 = 35000;
        public static int defaultReceiveT4 = 35000;

        public static int defaultCapacityCreative = -1;
        public static int defaultExtractCreative = -1;
        public static int defaultReceiveCreative = -1;

        public static int getMaxDefaultCapacityT1() {
            return batteryRateConfig.get(Tiers.T1_CAPACITY);
        }
        public static int getMaxDefaultExtractT1() {
            return batteryRateConfig.get(Tiers.T1_OUT);
        }
        public static int getMaxDefaultReceiveT1() {
            return batteryRateConfig.get(Tiers.T1_IN);
        }

        public static int getMaxDefaultCapacityT2() {
            return batteryRateConfig.get(Tiers.T2_CAPACITY);
        }
        public static int getMaxDefaultExtractT2() {
            return batteryRateConfig.get(Tiers.T2_OUT);
        }
        public static int getMaxDefaultReceiveT2() {
            return batteryRateConfig.get(Tiers.T2_IN);
        }

        public static int getMaxDefaultCapacityT3() {
            return batteryRateConfig.get(Tiers.T3_CAPACITY);
        }
        public static int getMaxDefaultExtractT3() {
            return batteryRateConfig.get(Tiers.T3_OUT);
        }
        public static int getMaxDefaultReceiveT3() {
            return batteryRateConfig.get(Tiers.T3_IN);
        }

        public static int getMaxDefaultCapacityT4() {
            return batteryRateConfig.get(Tiers.T4_CAPACITY);
        }
        public static int getMaxDefaultExtractT4() {
            return batteryRateConfig.get(Tiers.T4_OUT);
        }
        public static int getMaxDefaultReceiveT4() {
            return batteryRateConfig.get(Tiers.T4_IN);
        }

        public static int getMaxDefaultCapacityCreative() {
            return batteryRateConfig.get(Tiers.CREATIVE_CAPACITY);
        }
        public static int getMaxDefaultExtractCreative() {
            return batteryRateConfig.get(Tiers.CREATIVE_OUT);
        }
        public static int getMaxDefaultReceiveCreative() {
            return batteryRateConfig.get(Tiers.CREATIVE_IN);
        }

        public static final String TIER_1 = "coal_tier1";
        public static final String TIER_2 = "iron_tier2";
        public static final String TIER_3 = "gold_tier3";
        public static final String TIER_4 = "diamond_tier4";
        public static final String TIER_CREATIVE = "creative_tier5";

    }

}
