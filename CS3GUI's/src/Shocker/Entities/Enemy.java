package Shocker.Entities;

/**
 * @author warham_905444
 */
public abstract class Enemy extends Entity{
    protected int health;
    protected int damage;
    protected int exp;
    public int getExp(){
        return exp;
    }
}
