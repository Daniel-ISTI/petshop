package br.com.tt.petshop.controller;

import br.com.tt.petshop.enums.EspecieEnum;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animais-listar")
    public String listar(Model model, @RequestParam Long clienteId) {
        model.addAttribute("animais", animalService.listar(clienteId));
        return "animais-listar";
    }

    @GetMapping("/animais-adicionar")
    public String adicionar(Model model){
        model.addAttribute("especies", animalService.listarEspecies());
        //model.addAttribute("animal", new Animal());
        return "/animais-adicionar";
    }

    @PostMapping("/animais-form")
    public String salvar(Model model, Animal animal){
        animalService.adicionar(animal);
        return "/animais-adicionar";
    }

    public List<String> listarEspecies(){
        return animalService.listarEspecies();
    }
}
