package com.example.demo.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.app.models.entitys.Factura;

public interface IFacturaDao extends CrudRepository<Factura,Long> {

}
