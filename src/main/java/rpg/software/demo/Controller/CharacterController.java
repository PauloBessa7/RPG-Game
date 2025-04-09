package rpg.software.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import rpg.software.demo.DTO.UpdateNameDTO;
import rpg.software.demo.Model.Character;
import rpg.software.demo.Service.CharacterService;

@RestController
@RequestMapping("/characters")
@Tag(name = "Characters")
public class CharacterController {
    
    @Autowired
    private CharacterService characterService;

    @PostMapping("/update/{id}")
    @Operation(
        summary = "Update name if specific Character",
        parameters = @Parameter(
            name = "id",
            required = true,
            example = "1"
        ),
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(mediaType = "application/json",
            examples = @ExampleObject(
                value = "{\"name\": \"Charlinhos\"}"
                )
            )
        ),
        responses = @ApiResponse(
            responseCode = "200",    
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Character.class)
            )
        )
    )
    public ResponseEntity<Object> updateCharacterName(@PathVariable Long id, @RequestBody UpdateNameDTO dto) {
        try {
            return ResponseEntity.ok(characterService.updateName(id, dto.name()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    @Operation(
        summary = "Create a character",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    "{\"name\": \"Charlinhoso\", \"namePlayer\": \"Tenebroso\", \"characterClass\": \"BARD\", \"level\": \"0\", \"power\": \"2\", \"defence\": \"2\"}" 
                )
            )
        ),
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                    "{\"id\": \"1\", \"name\": \"Charlinhoso\", \"namePlayer\": \"Tenebroso\", \"characterClass\": \"BARD\", \"level\": \"0\", \"power\": \"2\", \"defence\": \"2\"}" 
                    )      
                )
            )
        }
    )
    public ResponseEntity<Object> createCharacter(@RequestBody Character character) {
        try {
            return ResponseEntity.ok(characterService.createCharacter(character));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/add-magic-item/{idCharacter}/{idMagicItem}")
    @Operation(
        summary = "Add magic item to character",
        parameters = {
            @Parameter(
                name = "idCharacter",
                required = true,
                example = "1"
            ),
            @Parameter(
                name = "idMagicItem",
                required = true,
                example = "1"
            ),
        },
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Character.class)
                )
            )
        }
    )
    public ResponseEntity<Object> addMagicItem(@PathVariable Long idCharacter, @PathVariable Long idMagicItem) {
        try {
            return ResponseEntity.ok(characterService.addMagicItem(idCharacter, idMagicItem));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @GetMapping("/list")
    @Operation(
        summary = "List all characters",
        responses = @ApiResponse(
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Character.class)   
            )
        )
    )
    public ResponseEntity<Object> listCharacters() {
        try {
            return ResponseEntity.ok(characterService.getAllCharacters());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/list/{id}")
    @Operation(
        summary = "Get specific characters",
        parameters = @Parameter(
            required = true,
            name = "id",
            example = "1"
        ),
        responses = @ApiResponse(
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Character.class)   
            )
        )
    )
    public ResponseEntity<Object> getCharacterById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(characterService.getCharacterById(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/list-magic-items/{idCharacter}")
    @Operation(
        summary = "List all magic items if specific character",
        parameters = @Parameter(
            required = true,
            name = "idCharacter",
            example = "1"
        ),
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        "[{\"id\": \"1\", \"name\": \"Diamond Armor\", \"type\": \"ARMOR\", \"power\": \"0\", \"defence\": \"2\"}]"
                    )
                )
            )
        }
    )
    public ResponseEntity<Object> listMagicItems(@PathVariable Long idCharacter) {
        try {
            return ResponseEntity.ok(characterService.listMagicItemsByCharacterId(idCharacter));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("list-amulets/{idCharacter}")
    @Operation(
        summary = "list amulet if specific character",
        parameters = @Parameter(
            required = true,
            name = "idCharacter",
            example = "1"
        ),
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(
                        implementation = Character.class
                    )
                )
            )
        }
    )
    public ResponseEntity<Object> listAmulets(@PathVariable Long idCharacter) {
        try {
            return ResponseEntity.ok(characterService.getAmulet(idCharacter));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @DeleteMapping("/remove/{id}")
    @Operation(
        summary = "delete specific character",
        parameters = @Parameter(
            required = true,
            name = "id",
            example = "1"
        ),
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        "true"
                    )
                )
            )
        }
    )
    public ResponseEntity<Object> removeCharacter(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(characterService.removeCharacter(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }


    @DeleteMapping("/remove-magic-item/{idCharacter}/{idMagicItem}")
    @Operation(
        summary = "Remote specific magic item in specific character",
        parameters = {
            @Parameter(
                required = true,
                name = "idCharacter",
                example = "1"
            ),
            @Parameter(
                required = true,
                name = "idMagicItem",
                example = "1"
            )
        },
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                        "{\"name\": \"Charlinhoso\", \"namePlayer\": \"Tenebroso\", \"characterClass\": \"BARD\", \"level\": \"0\", \"power\": \"2\", \"defence\": \"2\"}"
                    )
                )
            )
        }
    )
    public ResponseEntity<Object> removeMagicItem(@PathVariable Long idCharacter, @PathVariable Long idMagicItem) {
        try {
            return ResponseEntity.ok(characterService.removeMagicItem(idCharacter, idMagicItem));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }	
    }

}
