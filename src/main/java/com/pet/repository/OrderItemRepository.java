package com.pet.repository;

import com.pet.entity.OrderItem;
import com.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

    @Query("SELECT SUM(oi.quantity) FROM OrderItem oi WHERE oi.pet = :pet")
    Long sumQuantityByPet(@Param("pet") Pet pet);
}