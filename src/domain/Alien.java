package domain;

import HelperComponents.Position;
import objects.ObjectTile;

import java.awt.image.BufferedImage;

public class Alien extends ObjectTile {
    public Alien() {

    }

    public Alien(Position position) {
        this.position =position;
    }

    public void Update(Building building, double intervalTime) {

    }
}
