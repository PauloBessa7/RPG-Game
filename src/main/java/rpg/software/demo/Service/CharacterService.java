package rpg.software.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpg.software.demo.Enums.TypeItem;
import rpg.software.demo.Model.Character;
import rpg.software.demo.Model.MagicItem;
import rpg.software.demo.Repository.CharacterRepository;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MagicItemService magicItemService;

    public List<Character> getAllCharacters() {
        return characterRepository.findAll();
    }

    public Character createCharacter(Character character) {
        if((character.getDefence() + character.getPower()) > 10){
            throw new RuntimeException("Defence and Power sum must be less than or equal to 10");
        }else if(character.getDefence() < 1 || character.getPower() < 1){
            throw new RuntimeException("Defence and Power must be greater than or equal to 1");
        }else{
            return characterRepository.save(character);
        }
    }

    public Character getCharacterById(Long id) {
        return characterRepository.findById(id).orElseThrow(() -> new RuntimeException("Character not found with id: " + id));
    }

    public boolean removeCharacter(Long id) {
        Character character = getCharacterById(id);
        if (character != null) {
            characterRepository.delete(character);
            return true;
        }
        return false;
    }

    public Character updateName(Long id, String name) {
        Character character = getCharacterById(id);
        if (character != null ) {
            if(name == null || name.isEmpty()){
                throw new RuntimeException("Name cannot be null or empty");
            }
            character.setName(name);
            characterRepository.save(character);
        }else{
            throw new RuntimeException("Character not found with id: " + id);
        }
        return character;
    }

    public Character addMagicItem(Long characterId, Long magicItemId) {
        Character character = getCharacterById(characterId);
        if (character != null) {
            MagicItem magicItem = magicItemService.getMagicItemById(magicItemId);
            List<MagicItem> magicItems = character.getMagicItems();
            int magicItensPoints = magicItem.getPower() + magicItem.getDefence(); 
            for(MagicItem item : magicItems){ 
                magicItensPoints += item.getPower();
                magicItensPoints += item.getDefence();
            }

            if(magicItem.getType() == TypeItem.AMULET){ 
                for(MagicItem item : magicItems){ 
                    if(item.getType() == TypeItem.AMULET){
                        throw new RuntimeException("Character already has an AMULET");
                    }
                }
            }

            if((magicItensPoints + character.getDefence() + character.getPower()) > 10){ 
                throw new RuntimeException("Magic item power and defence sum must be less than or equal to 10");
            }

            if (magicItem != null) { 
                character.getMagicItems().add(magicItem);
                characterRepository.save(character);
            }

        }else{
            throw new RuntimeException("Character not found with id: " + characterId);
        }
        return character;
    }

    public Character removeMagicItem(Long characterId, Long magicItemId) {
        Character character = getCharacterById(characterId);
        if (character != null) {
            MagicItem magicItem = magicItemService.getMagicItemById(magicItemId);
            if (magicItem != null) {
                character.getMagicItems().remove(magicItem);
                characterRepository.save(character);
            }else{
                throw new RuntimeException("Magic item not found with id: " + magicItemId);
            }
        }else{
            throw new RuntimeException("Character not found with id: " + characterId);
        }
        return character;
    }

    public List<MagicItem> listMagicItemsByCharacterId(Long characterId) {
        Character character = characterRepository.findById(characterId)
            .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));
    
        return character.getMagicItems();
    }

    public Character getAmulet(Long characterId) {
        Character character = getCharacterById(characterId);
        if (character != null) {
            for (MagicItem magicItem : character.getMagicItems()) {
                if (magicItem.getType() == TypeItem.AMULET) {
                    return character;
                }
            }
            throw new RuntimeException("No AMULET found for character with id: " + characterId);
        }else{
            throw new RuntimeException("Character not found with id: " + characterId);
        }
    }
}


