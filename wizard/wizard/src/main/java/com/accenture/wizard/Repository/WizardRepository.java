package com.accenture.wizard.Repository;

import com.accenture.wizard.Entity.Wizard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardRepository extends JpaRepository<Wizard, Integer> {
}
