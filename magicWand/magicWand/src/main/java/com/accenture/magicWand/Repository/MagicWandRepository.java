package com.accenture.magicWand.Repository;

import com.accenture.magicWand.Entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface MagicWandRepository extends JpaRepository<MagicWand, Integer> {
}
