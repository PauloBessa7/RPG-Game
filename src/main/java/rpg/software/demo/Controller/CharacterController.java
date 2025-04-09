package rpg.software.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rpg.software.demo.DTO.UpdateNameDTO;
import rpg.software.demo.Model.Character;
import rpg.software.demo.Model.MagicItem;
import rpg.software.demo.Service.CharacterService;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    
    @Autowired
    private CharacterService characterService;

    @GetMapping("/list")
    public List<Character> listCharacters() {
        return characterService.getAllCharacters();
    }

    @GetMapping("/list/{id}")
    public Character getCharacterById(@PathVariable Long id) {
        return characterService.getCharacterById(id);
    }

    @PostMapping("/create")
    public Character createCharacter(@RequestBody Character character) {
        return characterService.createCharacter(character);
    }

    @DeleteMapping("/remove/{id}")
    public Boolean removeCharacter(@PathVariable Long id) {
        return characterService.removeCharacter(id);
    }

    @PostMapping("/update/{id}")
    public Character updateCharacterName(@PathVariable Long id, @RequestBody UpdateNameDTO dto) {
        return characterService.updateName(id, dto.name());
    }

    @PostMapping("/add-magic-item/{idCharacter}/{idMagicItem}")
    public Character addMagicItem(@PathVariable Long idCharacter, @PathVariable Long idMagicItem) {
        return characterService.addMagicItem(idCharacter, idMagicItem);
    }

    @GetMapping("/list-magic-items/{idCharacter}")
    public List<MagicItem> listMagicItems(@PathVariable Long idCharacter) {
        return characterService.listMagicItemsByCharacterId(idCharacter);
    }

    @DeleteMapping("/remove-magic-item/{idCharacter}/{idMagicItem}")
    public Character removeMagicItem(@PathVariable Long idCharacter, @PathVariable Long idMagicItem) {
        return characterService.removeMagicItem(idCharacter, idMagicItem);	
    }

    @GetMapping("list-amulets/{idCharacter}")
    public Character listAmulets(@PathVariable Long idCharacter) {
        return characterService.getAmulet(idCharacter);
    }
}
