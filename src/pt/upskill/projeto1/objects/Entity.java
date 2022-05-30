package pt.upskill.projeto1.objects;

import pt.upskill.projeto1.rogue.utils.Position;

public abstract class Entity {
    private Position position;
    private int health = 100;
    private int damage;

    public Entity(Position position, int health, int damage) {
        this.position = position;
        this.health = health;
        this.damage = damage;
    }

    public Entity(Position position) {
        this.position = position;
    }

    public Entity(int damage) {
        this.damage = damage;
    }

    public Entity(int health, int damage) {
        this.health = health;
        this.damage = damage;
    }

    public Entity(Position position, int damage) {
        this.position = position;
        this.damage = damage;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public abstract String getName();
}
