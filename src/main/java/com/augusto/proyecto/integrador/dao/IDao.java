package com.augusto.proyecto.integrador.dao;

import java.util.List;

public interface IDao <T>{
    List<T> listarElementos();
    T buscarPorId(int id);
    T buscarPorEmail(String email);

}
