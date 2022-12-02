package Models;

public final class Player {
    private static Player instance = new Player();
    private Account account;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }

        return instance;
    }

    public static void setInstance(Player player) {
        instance = player;
    }

    public Player(Account account) {
        this.account = account;
    }

    public Player() {
        this.account = new Account();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
