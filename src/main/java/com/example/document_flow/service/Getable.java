package com.example.document_flow.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Getable <T> {
    List<T> getAll();
    T getOne(Long id);
}
