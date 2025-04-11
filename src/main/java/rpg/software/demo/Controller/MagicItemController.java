package rpg.software.demo.Controller;

import rpg.software.demo.Model.MagicItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import rpg.software.demo.Service.MagicItemService;

@RestController
@RequestMapping("/magic-items")
@Tag(name = "Magic Items", description = "Operations for Magic Items")
public class MagicItemController {

    @Autowired
    private MagicItemService magicItemService;

    @GetMapping("/list")
    @Operation(
        summary = "List all Magic Items", 
        description = "Get a list of all magic items",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Item created",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MagicItem.class)
                )
            )
        }
    )
    public ResponseEntity<Object> listMagicItems() {
        try{
            return ResponseEntity.ok(magicItemService.getAllMagicItems());
        } catch(Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/list/{id}")
    @Operation(
        summary = "List a magic item", 
        description = "Get a magic item by its specific ID",
        parameters = @Parameter(
            name = "id", 
            required = true, 
            example = "1" // Valor de exemplo para o parâmetro 'id'
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Item created",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MagicItem.class)
                )
            )
        }
    )
    public ResponseEntity<Object> getMagicItemById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(magicItemService.getMagicItemById(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/create")
    @Operation(
        summary = "Create magic item", 
        description = "Create a new magic item in the system", tags = {"Magic Items" }, 
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Object containing the magic item's data", 
            content = @Content(mediaType = "application/json", 
            examples = @ExampleObject(
                value = "{\"name\": \"Diamond Armor\", \"type\": \"ARMOR\", \"power\": 0, \"defence\": 2}"
                )
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Item created",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = MagicItem.class)
                )
            )
        }
    )
    public ResponseEntity<Object> createItem(@RequestBody MagicItem magicItem) {
        try {
            return ResponseEntity.ok(magicItemService.createMagicItem(magicItem));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
