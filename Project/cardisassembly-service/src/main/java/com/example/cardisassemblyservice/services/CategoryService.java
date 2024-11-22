package com.example.cardisassemblyservice.services;

import com.example.cardisassemblyservice.domain.Category;
import com.example.cardisassemblyservice.exceptions.BaseException;
import com.example.cardisassemblyservice.exceptions.Messages;
import com.example.cardisassemblyservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category incrementStock(Long uuid) throws BaseException {
        Optional<Category> item = categoryRepository.findById(uuid);
        if(!item.isPresent()){
            throw new BaseException(Messages.ITEM_NOT_FOUND);
        }
        item.get().setQuantityStock(item.get().getQuantityStock() + 1);
        return categoryRepository.save(item.get());
    }

    public Category decrementStock(Long uuid) throws BaseException {
        Optional<Category> item = categoryRepository.findById(uuid);
        if(!item.isPresent()){
            throw new BaseException(Messages.ITEM_NOT_FOUND);
        }
        if( item.get().getQuantityStock() == 0){
            throw new BaseException(Messages.ITEM_NOT_FOUND);
        }
        item.get().setQuantityStock(item.get().getQuantityStock() - 1);
        return categoryRepository.save(item.get());
    }
    public  Optional<Category> findByName(String name )  {
      return  categoryRepository.findByName(name);
    }

    public  Optional<Category> findById(Long uuid )  {
        return  categoryRepository.findById(uuid);
    }
    public Category save(Category categoryToInsert) throws BaseException{
        Optional<Category> item = categoryRepository.findByName(categoryToInsert.getName());
        if(item.isPresent()){
            throw new BaseException(Messages.ITEM_EXISTS);
        }
        categoryToInsert.setQuantityStock(Long.valueOf(0));
        return  categoryRepository.save(categoryToInsert);
    }

    public void deleteCategory(Long uuid ) throws BaseException {
        Optional<Category> category = categoryRepository.findById(uuid);
        if (!category.isPresent()) {
            throw new BaseException(Messages.ITEM_NOT_FOUND);
        }
        categoryRepository.delete(category.get());
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
}
