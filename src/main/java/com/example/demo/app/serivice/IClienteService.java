package com.example.demo.app.serivice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.app.models.entitys.Cliente;
import com.example.demo.app.models.entitys.Factura;
import com.example.demo.app.models.entitys.Producto;

public interface IClienteService {
	public List<Cliente>findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	public void save(Cliente cliente);
	
	public Cliente findOne(Long Id);
	
	public void delete(Long id);
	
	public List<Producto> findByNombre(String term);
	
	public void saveFactura(Factura factura);
	
	public Producto findProductoById(Long id);
}
