package com.example.two.dto.converters;

import com.example.two.dto.ProductDto;
import com.example.two.models.Tag;

import java.util.List;
import java.util.Set;

public interface DtoMapperService {


    <S, T> List<T> dtoConverter(List<S> s, Class<T> targetClass);
    public <T> Object dtoClassConverter(Object source,Class<T> baseClass);



}
