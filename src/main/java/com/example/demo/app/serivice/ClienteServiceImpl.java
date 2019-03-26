package com.example.demo.app.serivice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.app.dao.IClienteDao;
import com.example.demo.app.dao.IFacturaDao;
import com.example.demo.app.dao.IProductoDao;
import com.example.demo.app.models.entitys.Cliente;
import com.example.demo.app.models.entitys.Factura;
import com.example.demo.app.models.entitys.Producto;

@Service
@Repository("ClienteServiceImpl")
public class ClienteServiceImpl implements IClienteService {
	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired 
	IProductoDao productoDao;
	
	@Autowired 
	IFacturaDao facturaDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findOne(Long Id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(Id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Cliente> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clienteDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Producto> findByNombre (String term) {
		return productoDao.findByNombreLikeIgnoreCase("%"+term+"%");
	}

	@Override
	@Transactional
	public void saveFactura(Factura factura) {
		facturaDao.save(factura);
		
	}

	@Override
	public Producto findProductoById(Long id) {
		return productoDao.findById(id).orElse(null);
	}



}
