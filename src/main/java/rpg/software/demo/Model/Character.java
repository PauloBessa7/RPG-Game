package rpg.software.demo.Model;

import jakarta.persistence.*;
import rpg.software.demo.Enums.TypeCharacter;
import rpg.software.demo.Enums.TypeItem;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String namePlayer;

    @Enumerated(EnumType.STRING)
    private TypeCharacter characterClass;

    private int level;

    private int power;

    private int defence;

    @ManyToMany
    @JoinTable(
        name = "character_magic_items",
        joinColumns = @JoinColumn(name = "character_id"),
        inverseJoinColumns = @JoinColumn(name = "magic_item_id")
    )
    private List<MagicItem> magicItems = new ArrayList<>();

    public Character() {
    }

    public Character(String name, String namePlayer, TypeCharacter characterClass, int level, int power, int defence) {
        this.name = name;
        this.namePlayer = namePlayer;
        this.characterClass = characterClass;
        this.level = level;
        this.power = power;
        this.defence = defence;
    }

    public boolean addMagicItem(MagicItem item) {
        int totalPower = this.power + this.defence;

        for (MagicItem m : magicItems) {
            totalPower += m.getPower() + m.getDefence();
        }

        int itemTotal = item.getPower() + item.getDefence();

        if (totalPower + itemTotal > 10) {
            return false;
        }

        magicItems.add(item);
        return true;
    }

    // Getters e Setters
    public Long getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getNamePlayer() { return namePlayer; }

    public void setNamePlayer(String namePlayer) { this.namePlayer = namePlayer; }

    public TypeCharacter getCharacterClass() { return characterClass; }

    public void setCharacterClass(TypeCharacter characterClass) { this.characterClass = characterClass; }

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }

    public int getPower() { return power; }

    public void setPower(int power) { this.power = power; }

    public int getDefence() { return defence; }

    public void setDefence(int defence) { this.defence = defence; }

    public List<MagicItem> getMagicItems() { return magicItems; }

    public void setMagicItems(List<MagicItem> magicItems) { this.magicItems = magicItems; }
}
