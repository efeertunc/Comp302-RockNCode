package domain.gameObjects.avatar;

import domain.SoundManager;
import domain.building.BuildingTracker;
import domain.gameObjects.DynamicTile;
import domain.gameObjects.EmptyTile;
import domain.gameObjects.powerUps.PowerUpTile;
import domain.gameObjects.powerUps.PowerUpTypes;
import domain.gameObjects.powerUps.bottle.BottleState;
import domain.gameObjects.powerUps.bottle.HoldBottle;
import domain.gameObjects.powerUps.bottle.HoldNothing;
import domain.gameObjects.powerUps.protectVest.HasNoVest;
import domain.gameObjects.powerUps.protectVest.HasVest;
import domain.gameObjects.powerUps.protectVest.VestState;
import factory.PanelType;
import factory.ViewType;
import helperComponents.AnimationTracker;
import helperComponents.Animator;
import helperComponents.Direction;
import helperComponents.Position;
import domain.building.Building;
import panels.RunPanel;
import main.EscapeFromKoc;

import java.awt.event.KeyEvent;
import java.util.Random;

public class Avatar extends DynamicTile {
    /**
     * OVERVIEW: This class is a DynamicTile which is an ObjectTile datatype. This class is used for
     * defining the Avatar object in the map. Dynamic attribute makes its movement easier. Also, this class
     * have the life,time, currenttime, bag , haskey  and state (bottlestate and veststate) attributes.
     *The rep invariant is life>0 && life<4 && time<1000 && time>0
     **/
    public int life; // neden public?
    private int time;
    private double currentTime;
    private boolean hasKey;
    private Bag bag;
    private BottleState bottleState;
    private VestState vestState;

    private double vestTime;
    private double hintTime;

    private AvatarInfoObserver avatarInfoObserver;
    private RunningMapObserver runningMapObserver;
    private SoundManager sound;
    private Random rand;
    private Animator animator;

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


        this.bag = new Bag();
        vestTime = 0; /// bunun databaseden alınması lazım
        hintTime = 0; /// bunun databaseden alınması lazım
        sound = new SoundManager();
        rand = new Random();

        animator = new Animator(this);
        loadAnimations();
    }

    public Avatar(int life, int time,  int x, int y, int image, Bag bag, double vestTime, double hintTime, boolean hasKey) {
        this.life = life;
        this.time = time;
        currentTime = (double) time;
        setPosition(new Position(x,y));
        setImage(image);
        // sonradan değişebilir
        bottleState = new HoldNothing();
        vestState = new HasNoVest();
        this.bag = bag;
        this.vestTime = vestTime;
        this.hintTime = hintTime;
        this.hasKey = hasKey;
        sound = new SoundManager();
        rand = new Random();

        animator = new Animator(this);
        loadAnimations();
    }

    private void loadAnimations()
    {
        animator.addAnimation(AnimationTracker.getInstance().getGameAnimations().get(0)); //AvatarHit
    }
    public void subscribeAvatarInfoObserver(AvatarInfoObserver avatarInfoObserver) {
        this.avatarInfoObserver = avatarInfoObserver;
    }


    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }


    public void doAction(int keyCode) {
        // REQUIRES: keyCode is one of the KeyEvent types.
        // EFFECTS: According to key types, changes the states or uses the hint or throw the bottle
        // to that direction
        switch (keyCode) {
            case KeyEvent.VK_B -> bag.usePowerUp(PowerUpTypes.BOTTLE);
            case KeyEvent.VK_P -> bag.usePowerUp(PowerUpTypes.VEST);
            case KeyEvent.VK_H -> bag.usePowerUp(PowerUpTypes.HINT);
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
        //REQUIRES: Avatar is not null and have a feasible location.
        // MODIFIES: Updates the position of the avatar.
        // EFFECTS: Effect of drawing the avatar in the new location.
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
            if (building.getMap_obj()[y][x] instanceof EmptyTile) { // tile empty{

                int oldX = getPosition().getX();
                int oldY = getPosition().getY();
                building.getMap_obj()[oldY][oldX] = new EmptyTile(oldX, oldY, 4);
                building.getMap_obj()[y][x] = this;
                getPosition().setPos(x,y);
                Random rand = new Random();
                int selectedSound = rand.nextInt(2);
                sound.playSoundEffect(selectedSound+8);
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
    public boolean searchKey(int x, int y, Building building) throws IllegalArgumentException {
        // REQUIRES: 12>=x>=0 and 17>=y>=0 and building is not null.
        // MODIFIES: Changes the image of the avatar if the key is found. Changes the haskey attribute.
        // Deletes the key in that building
        // EFFECTS: Effect of drawing the opened door.

        if (x<0 || y < 0) {
            throw new IllegalArgumentException("Indexes cannot be negative");
        }

        System.out.println("avatar şurada "+ getPosition().getX() +" , "+ getPosition().getY());

        int xDiff = Math.abs(getPosition().getX() - x);
        int yDiff = Math.abs(getPosition().getY() - y);

        if (xDiff > 1 || yDiff>1) {
            throw new NullPointerException("Obstacle is out of reach");
        }
        if (building.checkObstacle(x,y) == null) { //it's obstacle

            throw new NullPointerException("There is no obstacle in that position");
        }

        if (building.checkObstacle(x,y).getKey() != null) { //hasKey

            System.out.println("KEY HAS BEEN FOUND");
            setImage(6);
            building.deleteKey();
            hasKey=true;

            return true;
        }

        return false;
    }


    public boolean searchPowerTile(int x, int y, Building building) {
        if (x<0 || y < 0) {
            throw new IllegalArgumentException("Indexes cannot be negative");
        }

        if (building.checkPowerTile(x,y) == null) {

            throw new NullPointerException("There is no power up in that position");
        }

        PowerUpTile powerUpTile = building.checkPowerTile(x,y);
        if (powerUpTile != null) {
            //setImage(6);
            powerUpTile.clicked();
            building.deletePowerTile(x, y);
            return true;
        }

        return false;


    }


    /**
     * it is called when the player presses the B key, and presses the AWSD keys while holding a bottle
     * @param dir Direction of the throws
     */
    public String throwBottle(Direction.fourDir dir) {
        // REQUIRES: Avatar is in bottlestate.
        // MODIFIES: changes the bottlestate of the Avatar.
        // EFFECTS: the bottle is thrown and shown in the map.
        System.out.println("Bottle has been thrown");
        Building currentBuilding = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex());
        Position bottlePos = currentBuilding.findBottleLastPos(getPosition(), dir);
        runningMapObserver.notifyBottleIsThrown(bottlePos);



        bag.decreasePowerUp(PowerUpTypes.BOTTLE);
        changeBottleState(); // after avatar throws bottle successfully, he holds nothing
        return "Bottle has been thrown " + dir.toString();
    }

    /**
     * it is called when the player presses the P key
     */
    public boolean changeVestState() {
        // REQUIRES: bag is initialized earlier. Inıtıal states are defined.
        // MODIFIES:  changes the veststate of the Avatar.
        if ((vestState instanceof HasNoVest) && (bag.consistsOf(PowerUpTypes.VEST))) {
            System.out.println(vestState);
            vestState = new HasVest();
            setVestTime(20);
            setImage(2); // it is not correct number
            bag.decreasePowerUp(PowerUpTypes.VEST);
            return true;
        }
        else if (vestState instanceof HasVest) {
            System.out.println(vestState);
            vestState = new HasNoVest();
            setImage(3); // it is not correct number
            return false;
        }
        return false;
    }


    /**
     * it is called when the player presses the B key to change the bottle state
     */
    public boolean changeBottleState() {
        // REQUIRES: bag is initialized earlier. Inıtıal states are defined.
        // MODIFIES:  changes the bottlestate of the Avatar.

        if ((bottleState instanceof HoldNothing) && bag.consistsOf(PowerUpTypes.BOTTLE)) {

            bottleState = new HoldBottle();
            return true;
        }
        else if (bottleState instanceof HoldBottle) {
            bottleState = new HoldNothing();
            return false;
        }
        return false;
    }

    public void takeDamage(int damage) {
        setLife(life-damage);
        if (life <=0) {
            //vanish();
        }
        //runningMapObserver.notifyAvatarTakesDamage();
        animator.setState(0);
        sound.playSoundEffect(5);
    }
    @Override
    public void update(double intervalTime) {
        // REQUIRES: intervalTime is nonnegative double.
        // MODIFIES:  updates the currentTime by decreasing.
        //EFFECTS: If currentTime reaches 0, sets it to 0 forever.

        if (vestState instanceof HasVest) {
            int oldVestTime = ((int) vestTime);
            vestTime -= intervalTime/1000000000;
            int newVestTime = ((int) vestTime);

            if ((vestTime <= 0) && (oldVestTime != newVestTime)) {
                vestTime = 0;
                changeVestState();
            }
        }

        int oldHintTime = ((int) hintTime);
        hintTime -= intervalTime/1000000000;
        int newHintTime = ((int) hintTime);

        if (hintTime > 0){
            ((RunPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).getRunningMap().setHintPowerUp(true);
        }

        if(isHasKey()){
            hintTime = 0;
            setHintTime(hintTime, false);
        }

        if ((hintTime <= 0) && (oldHintTime != newHintTime)) {
            hintTime = 0;
            setHintTime(hintTime, false);
        }

        currentTime -=  intervalTime/1000000000;
        if (currentTime < 0) {
            currentTime = 0;
        }

        animator.animatorUpdate();
    }

    public double getTime() {
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
        //avatarInfoObserver.updateLife_inPlayerPanel(this.life);
    }



    public void addLife(int i) {
        // REQUIRES: i is between 0 and 3.
        // MODIFIES: Updates the remaining life of the avatar.
        setLife(getLife() + i);
    }

    public boolean repOk(){
        return life > 0 && life < 4 && time < 1000 && time > 0;
    }


    private void notifyAvatarObserver() {
    }

    public BottleState getBottleState() {
        return bottleState;
    }

    public VestState getVestState() {
        return vestState;
    }

    public double getVestTime() {
        return vestTime;
    }

    public void setVestTime(int vestTime) {
        this.vestTime = vestTime;
    }

    public double getHintTime() {return hintTime; }
    public void setHintTime(double hintTime, boolean bool) {
        this.hintTime = hintTime;
        if (bool) {
            bag.decreasePowerUp(PowerUpTypes.HINT);
        }
        ((RunPanel) EscapeFromKoc.getInstance().getView(ViewType.GameView).getPanel(PanelType.Run)).getRunningMap().setHintPowerUp(bool);
    }

    public void vanish() {
        Building b = BuildingTracker.getBuildingList().get(BuildingTracker.getCurrentIndex());
        b.getMap_obj()[getPosition().getY()][getPosition().getX()] = new EmptyTile(getPosition().getX(),getPosition().getY(), 4);
        System.out.println("Player Vanished");
    }

    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

}
