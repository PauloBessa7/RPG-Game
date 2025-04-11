package rpg.software.demo.Model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import rpg.software.demo.Enums.TypeCharacter;

@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(example = "Charlinhos")
    private String name;

    @Column(nullable = false)
    @Schema(example = "Estrondoso God")
    private String namePlayer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(example = "BARD")
    private TypeCharacter characterClass;

    @Column(nullable = false)
    @Schema(example = "0")
    private int level;

    @Column(nullable = false)
    @Schema(example = "2")
    private int power;
    
    @Column(nullable = false)
    @Schema(example = "2")
    private int defence;

    @ManyToMany
    @JoinTable(
        name = "character_magic_items",
        joinColumns = @JoinColumn(name = "character_id"),
        inverseJoinColumns = @JoinColumn(name = "magic_item_id")
    )
    @Schema(example = "")
    private List<MagicItem> magicItems = new ArrayList<>();

    public Character() {}

    public Character(String name, String namePlayer, TypeCharacter characterClass, int level, int power, int defence) {
        this.name = name;
        this.namePlayer = namePlayer;
        this.characterClass = characterClass;
        this.level = level;
        this.power = power;
        this.defence = defence;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public TypeCharacter getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(TypeCharacter characterClass) {
        this.characterClass = characterClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public List<MagicItem> getMagicItems() {
        return magicItems;
    }

    public void setMagicItems(List<MagicItem> magicItems) {
        this.magicItems = magicItems;
    }

    public void addMagicItem(MagicItem magicItem) {
        this.magicItems.add(magicItem);
    }

    public void removeMagicItem(MagicItem magicItem) {
        this.magicItems.remove(magicItem);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", namePlayer='" + namePlayer + '\'' +
                ", characterClass=" + characterClass +
                ", level=" + level +
                ", power=" + power +
                ", defence=" + defence +
                ", magicItems=" + magicItems +
                '}';
    }  
} 
