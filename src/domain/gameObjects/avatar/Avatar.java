package domain.gameObjects.avatar;

import domain.building.BuildingTracker;
import domain.gameObjects.DynamicTile;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.powerUps.bottle.BottleState;
import domain.gameObjects.powerUps.bottle.HoldBottle;
import domain.gameObjects.powerUps.bottle.HoldNothing;
import domain.gameObjects.powerUps.protectVest.HasNoVest;
import domain.gameObjects.powerUps.protectVest.HasVest;
import domain.gameObjects.powerUps.protectVest.VestState;
import factory.PanelType;
import factory.ViewType;
import helperComponents.Direction;
import helperComponents.Position;
import domain.building.Building;
import main.EscapeFromKoc;
import panels.RunPanel;

import javax.print.attribute.standard.MediaSize;
import java.awt.event.KeyEvent;

public class Avatar extends DynamicTile {

    private int life;
    private int time;
    private double currentTime;
    private boolean hasKey;
    private Bag bag;
    private BottleState bottleState;
    private VestState vestState;



    public Avatar(int life, int time, int x, int y, int image) {
        this.life = life;
        this.time = time;
        currentTime = (double) time;
        setPosition(new Position(x,y));
        setImage(image);
        hasKey=false;

        // sonradan değişebilir
        bottleState = new HoldNothing();
        vestState = new HasNoVest();
        bag = new Bag();
    }

    public Bag getBag() {
        return bag;
    }

    public void doAction(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_B -> changeBottleState();
            case KeyEvent.VK_P -> changeVestState();
            case KeyEvent.VK_H -> bag.useHint();
            case KeyEvent.VK_UP -> bottleState.moveOrThrow(Direction.fourDir.up);
            case KeyEvent.VK_RIGHT -> bottleState.moveOrThrow(Direction.fourDir.right);
            case KeyEvent.VK_DOWN -> bottleState.moveOrThrow(Direction.fourDir.down);
            case KeyEvent.VK_LEFT -> bottleState.moveOrThrow(Direction.fourDir.left);
            default -> System.out.println("Error on doAction switch statement");
        }
    }


    /**
     * it is called when the player presses the AWSD keys while avatar is holding nothing
     * @param dir Direction of the movement
     */
    public String move(Direction.fourDir dir) {


        int avatarX = getPosition().getX();
        int avatarY = getPosition().getY();
        Building currentBuilding = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex());

        if (avatarX>=0 && avatarX<17 && avatarY>=0 && avatarY<12) {
            switch (dir) {
                case up -> updatePosition(avatarX, avatarY - 1, currentBuilding);
                case right -> updatePosition(avatarX + 1, avatarY, currentBuilding);
                case down -> updatePosition(avatarX, avatarY + 1, currentBuilding);
                case left -> updatePosition(avatarX - 1, avatarY, currentBuilding);
                default -> System.out.println("Error on moveplayer switch statement");
            }
            return "Updated position  x: " + getPosition().getX() + " y: " +getPosition().getY();
        }
        else{
            throw new NullPointerException("Out of map area");
        }

    }


    /**
     * it checks if the avatar can move to the new position, and then updates the position
     * @param x new x position
     * @param y new y position
     * @param building current building that the avatar is in
     */
    public boolean updatePosition(int x, int y, Building building) {
        if ((hasKey) && (this.getPosition().getX() == 16) && (this.getPosition().getY() == 10) && (x == 17) && (y == 10)) {
            ((RunPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).getRunController().nextLevel();
            return true;
        }
        if (x>=0 && x<17 && y>=0 && y<12) { // tile exists
            System.out.println("avatar x: " + this.getPosition().getX() + "avatar y: " + this.getPosition().getY());
            if (building.getMap_obj()[y][x] instanceof EmptyTile) { // tile empty{
                int oldX = getPosition().getX();
                int oldY = getPosition().getY();
                building.getMap_obj()[oldY][oldX] = new EmptyTile(oldX, oldY, 4);
                building.getMap_obj()[y][x] = this;
                getPosition().setPos(x,y);
                return true;
            }
        }
        else{
            throw new NullPointerException("Out of map area");
        }
        return false;
    }


    /**
     * it is called when the player searches for a key
     * @param x x position of the search area
     * @param y y position of the search area
     * @param building current building that the avatar is in
     */
    public boolean searchKey(int x, int y, Building building) {
        int xDiff = Math.abs(getPosition().getX() - x);
        int yDiff = Math.abs(getPosition().getY() - y);
        if (xDiff <= 1 && yDiff <= 1) //reachable
        {
            if (building.checkObstacle(x,y) != null)//it's obstacle
            {
                if (building.checkObstacle(x,y).getKey() != null) //hasKey
                {
                    System.out.println("KEY HAS BEEN FOUND");
                    setImage(6);
                    building.deleteKey();
                    hasKey=true;
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * it is called when the player presses the B key, and presses the AWSD keys while holding a bottle
     * @param dir Direction of the throws
     */
    public String throwBottle(Direction.fourDir dir) {
        System.out.println("Bottle has been thrown");
        changeBottleState(); // after avatar throws bottle successfully, he holds nothing
        return "Bottle has been thrown " + dir.toString();
    }

    /**
     * it is called when the player presses the P key
     */
    public boolean changeVestState() {

        if ((vestState instanceof HasNoVest) && (bag.consistsVest())) {
            System.out.println(vestState);
            vestState = new HasVest();

            return true;
        }
        else if (vestState instanceof HasVest) {
            System.out.println(vestState);
            vestState = new HasNoVest();
            return false;
        }
        return false;
    }


    /**
     * it is called when the player presses the B key to change the bottle state
     */
    public boolean changeBottleState() {
        if ((bottleState instanceof HoldNothing) && bag.consistsBottle()) {

            System.out.println(bag.consistsBottle());
            bottleState = new HoldBottle();
            return true;
        }
        else if (bottleState instanceof HoldBottle) {
            bottleState = new HoldNothing();
            return false;
        }
        return false;
    }

    @Override
    public void update(double intervalTime) {
        currentTime -=  intervalTime/1000000000;
        if (currentTime < 0)
        {
            currentTime = 0;
        }
    }

    public int getTime() {
        return time;
    }

    public double getCurrentTime() {
        return currentTime;
    }

    public boolean isHasKey() {
        return hasKey;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void addLife(int i) {
        setLife(getLife() + i);
    }

    public boolean repOk(){
        if(life>0 && life<4 && time<1000 && time>0){
            return true;
        }
        return false;
    }

}
