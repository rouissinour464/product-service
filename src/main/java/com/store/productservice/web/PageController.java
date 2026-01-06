
package com.store.productservice.web;
import com.store.productservice.dto.ProductRequest; import com.store.productservice.service.ProductService; import jakarta.validation.Valid; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.validation.BindingResult; import org.springframework.web.bind.annotation.*; import java.time.LocalDateTime; import java.time.format.DateTimeFormatter;
@Controller
public class PageController {
    private final ProductService service;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    public PageController(ProductService service){this.service=service;}
    @GetMapping("/") public String index(Model model, @RequestParam(value="q", required=false) String q){ if(q!=null && !q.isBlank()){ model.addAttribute("products", service.searchByName(q)); model.addAttribute("q", q);} else { model.addAttribute("products", service.getAll()); model.addAttribute("q", ""); } return "index"; }
    @GetMapping("/products/new") public String newProduct(Model model){ model.addAttribute("product", new ProductRequest()); model.addAttribute("now", LocalDateTime.now().format(DTF)); return "new"; }
    @PostMapping("/products") public String create(@Valid @ModelAttribute("product") ProductRequest product, BindingResult br){ if(br.hasErrors()) return "new"; service.create(product); return "redirect:/"; }
    @GetMapping("/products/{id}/edit") public String edit(@PathVariable Long id, Model model){ model.addAttribute("product", service.getById(id)); model.addAttribute("id", id); model.addAttribute("now", LocalDateTime.now().format(DTF)); return "edit"; }
    @PostMapping("/products/{id}") public String update(@PathVariable Long id, @Valid @ModelAttribute("product") ProductRequest product, BindingResult br){ if(br.hasErrors()) return "edit"; service.update(id, product); return "redirect:/"; }
    @PostMapping("/products/{id}/delete") public String delete(@PathVariable Long id){ service.delete(id); return "redirect:/"; }
}
