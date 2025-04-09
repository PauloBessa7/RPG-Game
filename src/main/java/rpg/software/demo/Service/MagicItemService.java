package rpg.software.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rpg.software.demo.Model.MagicItem;
import rpg.software.demo.Repository.MagicItemRepository;

@Service
public class MagicItemService {
    
    @Autowired
    private MagicItemRepository magicItemRepository;

    public List<MagicItem> getAllMagicItems() {
        return magicItemRepository.findAll();
    }

    public MagicItem createMagicItem(MagicItem magicItem) {
        switch (magicItem.getType()) {
            case WEAPON:
                magicItem.setDefence(0);
                break;
            case ARMOR:
                magicItem.setPower(0);
                break;
            case AMULET:
                if (magicItem.getPower() > 10 || magicItem.getDefence() > 10 || (magicItem.getPower() < 1 && magicItem.getDefence() < 1)) {
                    throw new RuntimeException("1 Amulet must have power and defence between 1 and 10");
                }
                break;
            default:
                throw new RuntimeException("Invalid item type");
        }
        if(magicItem.getPower() > 10 ||magicItem.getDefence() > 10 || (magicItem.getPower() < 1 && magicItem.getDefence() < 1)){
            throw new RuntimeException("2 Power and Defence must be between 1 and 10");
        }

        return magicItemRepository.save(magicItem);
    }

    public MagicItem getMagicItemById(Long id) {
        return magicItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Magic item not found with id: " + id));
    }
   
}
