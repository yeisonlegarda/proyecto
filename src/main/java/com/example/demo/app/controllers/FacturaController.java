package com.example.demo.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.app.models.entitys.Cliente;
import com.example.demo.app.models.entitys.Factura;
import com.example.demo.app.models.entitys.ItemFactura;
import com.example.demo.app.models.entitys.Producto;
import com.example.demo.app.serivice.IClienteService;



@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {
	@Autowired
	private IClienteService clienteService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	@GetMapping("/form/{clienteId}")
	public String Crear(@PathVariable(value="clienteId") Long clienteId,Map<String,Object> model,RedirectAttributes flash) {
		Cliente cliente = clienteService.findOne(clienteId);
		if(cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe");
			return "redirec:/listar";
		}
		Factura factura = new Factura();
		factura.setCliente(cliente);
		model.put("factura", factura);
		model.put("titulo", "Crear factura");
		return "factura/form";
	}
	@GetMapping(value="/cargar-productos/{term}",produces= {"application/json"})
	public @ResponseBody List<Producto> cargarProductos(@PathVariable(value="term") String term){
		return clienteService.findByNombre(term);
	}
	@PostMapping("/form")
	public String guardar(@Valid Factura factura, BindingResult result, Model model,
			@RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
			SessionStatus status) {
			
		for (int i = 0; i < itemId.length; i++) {
			Producto producto = clienteService.findProductoById(itemId[i]);

			ItemFactura linea = new ItemFactura();
			linea.setCantidad(cantidad[i]);
			linea.setProducto(producto);
			factura.addItemFactura(linea);

			log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i].toString());
		}
		clienteService.saveFactura(factura);
		status.setComplete();

		flash.addFlashAttribute("success", "Factura creada con éxito!");

		return "redirect:/ver/" + factura.getCliente().getId();
	}
		
}
