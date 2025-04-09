package rpg.software.demo.Controller;

import java.util.List;

import rpg.software.demo.Model.MagicItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rpg.software.demo.Service.MagicItemService;

@RestController
@RequestMapping("/magic-items")
public class MagicItemController {
    
    @Autowired
    private MagicItemService magicItemService;

    @GetMapping("/list")
    public List<MagicItem> listMagicItems() {
        return magicItemService.getAllMagicItems();
    }

    @GetMapping("/list/{id}")
    public MagicItem getMagicItemById(@PathVariable Long id) {
        return magicItemService.getMagicItemById(id);
    }

    @PostMapping("/create")
    public MagicItem createItem(@RequestBody MagicItem magicItem) {
        return magicItemService.createMagicItem(magicItem);
    }

    

}
