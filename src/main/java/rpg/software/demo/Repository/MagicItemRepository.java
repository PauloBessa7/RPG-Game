package rpg.software.demo.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import rpg.software.demo.Model.MagicItem;

public interface MagicItemRepository extends JpaRepository<MagicItem, Long> {}
