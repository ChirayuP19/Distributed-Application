package com.pm.favouriteservice.repository;

import com.pm.favouriteservice.entity.Favourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite,Long> {

    Optional<Favourite> findByUserIdAndProductId(String userId, Long productId);

    List<Favourite> findByUserId(String userId);

}
