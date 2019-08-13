package br.com.tt.petshop.api;

import br.com.tt.petshop.dto.AnimalDto;
import br.com.tt.petshop.exception.BusinessException;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.service.AnimalService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/animais")

public class AnimalEndpoint {

    private final AnimalService animalService;
    private final ModelMapper mapper;

    public AnimalEndpoint(AnimalService animalService, ModelMapper mapper) {
        this.animalService = animalService;
        this.mapper = mapper;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnimalDto>> findAll(){
        return ok(animalService.listar().stream()
        .map(c -> mapper.map(c, AnimalDto.class))
        .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnimalDto> findByClienteId(@PathVariable Long id) {
        Optional<AnimalDto> animalOptional = animalService.listar(id);
        if(animalOptional.isPresent()){
            AnimalDto dto = mapper.map(animalOptional.get(), AnimalDto.class);
            return ok(animalOptional.get());
        }
        return notFound().build();
    }
}
