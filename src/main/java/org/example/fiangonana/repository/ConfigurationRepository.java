package org.example.fiangonana.repository;

import org.example.fiangonana.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {



    @Query("SELECT c FROM Configuration c")
    public Optional<Configuration> getConfiguration();

}
