package domain.gameObjects.powerUps;

public interface CollectablePowerUpI {
      void increment();
      void decrease();
      int getNum();
      void setNumToPowerUp(int num);
}
