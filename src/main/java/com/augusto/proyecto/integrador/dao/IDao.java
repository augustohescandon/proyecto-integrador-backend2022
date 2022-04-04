package com.augusto.proyecto.integrador.dao;

import java.util.List;

public interface IDao <T>{
    List<T> listarElementos();
    T buscarPorId(int id);
    T buscarPorEmail(String email);

    //Clase 25
    T guardar(T elemento);
    T actualizar(T elemento);

    //Clae27
    void eliminar(int id);
}
