package factory;

import domain.gameObjects.powerUps.*;
import domain.gameObjects.powerUps.bottle.PlasticBottle;
import domain.gameObjects.powerUps.protectVest.ProtectionVest;

public class PowerUpFactory {

    private static PowerUpFactory instance;

    private PowerUpFactory() {
    }

    public static PowerUpFactory getInstance() {
        if (instance == null) {
            instance = new PowerUpFactory();
        }
        return instance;
    }

    /**
     * Creates a power up based on the type
     * @param type
     * @return
     */
    public PowerUp createPowerUp(PowerUpTypes type) {
        return switch (type) {
            case BOTTLE -> new PlasticBottle();
            case VEST -> new ProtectionVest();
            case HINT -> new HintPower();
            case EXTRA_LIFE -> new ExtraLife();
            case EXTRA_TIME -> new ExtraTime();
            default -> throw new IllegalArgumentException("No such kind of power up type");
        };
    }
}
