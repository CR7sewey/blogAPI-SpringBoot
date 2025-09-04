package com.example.blog.services;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IService<T> {

    T findById(@PathVariable String id);
    List<T> findAll();
    T insert(@RequestBody T t);
    T update(@RequestBody T t);
    void delete(@PathVariable String id);

}
