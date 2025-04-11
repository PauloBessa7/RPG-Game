package rpg.software.demo.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import rpg.software.demo.Enums.TypeItem;

@Entity
@Table(name = "magic_items")
public class MagicItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(example = "Diamond Armor")
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(
        example = "ARMOR"
    )
    private TypeItem type;

    @Column(nullable = false)
    @Schema(example = "0")
    private int power;

    @Column(nullable = false)
    @Schema(example = "2")
    private int defence;

    public MagicItem() {}

    public MagicItem(String name, TypeItem type, int power, int defence) {
        this.name = name;
        this.type = type;
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
        this.power = power;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    @Override
    public String toString() {
        return "MagicItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", power=" + power +
                ", defence=" + defence +
                '}';
    }
}
