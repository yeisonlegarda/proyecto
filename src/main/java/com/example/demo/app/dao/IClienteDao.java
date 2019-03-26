package com.example.demo.app.dao;



import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.app.models.entitys.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long>{

}
