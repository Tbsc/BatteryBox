package tbsc.batterybox.util;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import tbsc.batterybox.reference.Reference;

import java.util.HashMap;

/**
 * @author Tbsc on 14/11/2015, 11:04
 */
public class ConfigUtil {

    public static void readConfig(Configuration config) {
        Property capacityT1 = config.get(Reference.Config.TIER_1, "maxCapacityTier1",
                Reference.Config.defaultCapacityT1, "Maximum capacity of tier 1 (coal)");
        Property extractT1 = config.get(Reference.Config.TIER_1, "maxExtractTier1",
                Reference.Config.defaultExtractT1, "Maximum extract rate of tier 1 (coal)");
        Property receiveT1 = config.get(Reference.Config.TIER_1, "maxReceiveTier1",
                Reference.Config.defaultReceiveT1, "Maximum receive rate of tier 1 (coal)");

        Property capacityT2 = config.get(Reference.Config.TIER_2, "maxCapacityTier2",
                Reference.Config.defaultCapacityT2, "Maximum capacity of tier 2 (iron)");
        Property extractT2 = config.get(Reference.Config.TIER_2, "maxExtractTier2",
                Reference.Config.defaultExtractT2, "Maximum extract rate of tier 2 (iron)");
        Property receiveT2 = config.get(Reference.Config.TIER_2, "maxReceiveTier2",
                Reference.Config.defaultReceiveT2, "Maximum receive rate of tier 2 (iron)");

        Property capacityT3 = config.get(Reference.Config.TIER_3, "maxCapacityTier3",
                Reference.Config.defaultCapacityT3, "Maximum capacity of tier 3 (gold)");
        Property extractT3 = config.get(Reference.Config.TIER_3, "maxExtractTier3",
                Reference.Config.defaultExtractT3, "Maximum extract rate of tier 3 (gold)");
        Property receiveT3 = config.get(Reference.Config.TIER_3, "maxReceiveTier3",
                Reference.Config.defaultReceiveT3, "Maximum receive rate of tier 3 (gold)");

        Property capacityT4 = config.get(Reference.Config.TIER_4, "maxCapacityTier4",
                Reference.Config.defaultCapacityT4, "Maximum capacity of tier 4 (diamond)");
        Property extractT4 = config.get(Reference.Config.TIER_4, "maxExtractTier4",
                Reference.Config.defaultExtractT4, "Maximum extract rate of tier 4 (diamond)");
        Property receiveT4 = config.get(Reference.Config.TIER_4, "maxReceiveTier4",
                Reference.Config.defaultReceiveT4, "Maximum receive rate of tier 4 (diamond)");

        Property capacityCreative = config.get(Reference.Config.TIER_CREATIVE, "maxCapacityCreative",
                Reference.Config.defaultCapacityCreative, "Maximum capacity of tier 5 (creative)");
        Property extractCreative = config.get(Reference.Config.TIER_CREATIVE, "maxExtractCreative",
                Reference.Config.defaultExtractCreative, "Maximum extract rate of tier 5 (creative)");
        Property receiveCreative = config.get(Reference.Config.TIER_CREATIVE, "maxReceiveCreative",
                Reference.Config.defaultReceiveCreative, "Maximum receive rate of tier 5 (creative)");

        HashMap<Reference.Config.Tiers, Integer> map = new HashMap<Reference.Config.Tiers, Integer>();

        map.put(Reference.Config.Tiers.T1_CAPACITY, capacityT1.getInt());
        map.put(Reference.Config.Tiers.T1_OUT, extractT1.getInt());
        map.put(Reference.Config.Tiers.T1_IN, receiveT1.getInt());

        map.put(Reference.Config.Tiers.T2_CAPACITY, capacityT2.getInt());
        map.put(Reference.Config.Tiers.T2_OUT, extractT2.getInt());
        map.put(Reference.Config.Tiers.T2_IN, receiveT2.getInt());

        map.put(Reference.Config.Tiers.T3_CAPACITY, capacityT3.getInt());
        map.put(Reference.Config.Tiers.T3_OUT, extractT3.getInt());
        map.put(Reference.Config.Tiers.T3_IN, receiveT3.getInt());

        map.put(Reference.Config.Tiers.T4_CAPACITY, capacityT4.getInt());
        map.put(Reference.Config.Tiers.T4_OUT, extractT4.getInt());
        map.put(Reference.Config.Tiers.T4_IN, receiveT4.getInt());

        map.put(Reference.Config.Tiers.CREATIVE_CAPACITY, capacityCreative.getInt());
        map.put(Reference.Config.Tiers.CREATIVE_OUT, extractCreative.getInt());
        map.put(Reference.Config.Tiers.CREATIVE_IN, receiveCreative.getInt());

        Reference.Config.batteryRateConfig.putAll(map);
    }

}
