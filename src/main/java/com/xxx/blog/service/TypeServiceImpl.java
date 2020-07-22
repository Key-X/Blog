package com.xxx.blog.service;

import com.xxx.blog.NotFoundException;
import com.xxx.blog.dao.TypeRepository;
import com.xxx.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    //新增
    @Transactional
    @Override
    public Type saveType(Type type) {

        return typeRepository.save(type);
    }

    //查询
    @Transactional
    @Override
    public Type getType(Long id) {

        return typeRepository.findById(id).get();
    }

    @Override
    public Type getTypeByName(String name) {

        return typeRepository.findByName(name);
    }

    //分页查询
    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {

        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRepository.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable =PageRequest.of(0, size);
        return typeRepository.findTop(pageable);
    }

    //更新
    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.findById(id).get();
        if (t == null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type, t);

        return typeRepository.save(t);
    }

    //删除
    @Transactional
    @Override
    public void deleteType(Long id) {

        typeRepository.deleteById(id);
    }
}
