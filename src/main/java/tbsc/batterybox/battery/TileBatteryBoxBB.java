package tbsc.batterybox.battery;

import cofh.api.energy.TileEnergyHandler;

/**
 * @author Tbsc on 9/11/2015, 14:48
 */
public abstract class TileBatteryBoxBB extends TileEnergyHandler {

    /** Using this abstract class, I can "skip" extending certain methods from the @link TileEntityHandler class.
     * This allows me to use some custom methods, and have a base class, so if I get more then 1 @link TileBatteryBox
     * class, they all have 1 type, so I won't have to make checks for every type of batterybox/tile entity that it adds.
     */

}
