package rpg.software.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rpg.software.demo.Model.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {}
