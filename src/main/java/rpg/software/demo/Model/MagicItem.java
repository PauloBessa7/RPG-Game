package rpg.software.demo.Model;

import jakarta.persistence.*;
import rpg.software.demo.Enums.TypeItem;

@Entity
@Table(name = "magic_items")
public class MagicItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TypeItem type;

    private int power;

    private int defence;

    public MagicItem() {
    }

    public MagicItem(String name, TypeItem type, int power, int defence) {
        this.name = name;
        this.type = type;

        // Regras de negócio aplicadas na instância
        if (type == TypeItem.WEAPON) {
            this.power = power;
            this.defence = 0;
        } else if (type == TypeItem.ARMOR) {
            this.power = 0;
            this.defence = defence;
        } else {
            this.power = power;
            this.defence = defence;
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeItem getType() {
        return type;
    }

    public void setType(TypeItem type) {
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        if (this.type == TypeItem.ARMOR) {
            this.power = 0;
        } else {
            this.power = power;
        }
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        if (this.type == TypeItem.WEAPON) {
            this.defence = 0;
        } else {
            this.defence = defence;
        }
    }
}
