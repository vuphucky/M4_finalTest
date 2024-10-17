package com.codegym.service;

import com.codegym.model.Discount;
import com.codegym.repository.IDiscountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiscountService implements IDiscountService{
    @Autowired
    private IDiscountRepo discountRepo;


    @Override
    public Iterable<Discount> findAll() {
        return discountRepo.findAll();
    }

    @Override
    public Optional<Discount> findById(Long id) {
        return discountRepo.findById(id);
    }

    @Override
    public Discount save(Discount discount) {
        return discountRepo.save(discount);
    }

    @Override
    public void remove(Long id) {
     discountRepo.deleteById(id);
    }
}
