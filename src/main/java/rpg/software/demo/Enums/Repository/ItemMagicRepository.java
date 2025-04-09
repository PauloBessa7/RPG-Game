package rpg.software.demo.Enums.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import rpg.software.demo.Enums.TypeItem;
import rpg.software.demo.Model.MagicItem;

public interface ItemMagicRepository extends JpaRepository<TypeItem, Integer> {
    Optional<List<MagicItem>> findAll(){};
    Optional<MagicItem> findItem(long id);
    boolean deleteItem();
    boolean createItem();
}