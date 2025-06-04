package org.example.fiangonana.service;


import lombok.extern.slf4j.Slf4j;
import org.example.fiangonana.model.Configuration;
import org.example.fiangonana.repository.ConfigurationRepository;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }


    @Cacheable(value = "conf", key = "'single'")
    public Configuration getConfigutation() {
        return configurationRepository.getConfiguration().orElse(new Configuration());
//        return configurationRepository.getConfiguration()
    }


    @CachePut(value = "conf", key = "'single'")
    public Configuration setConfiguration(Configuration configuration) {
        log.info("conf: {}", configuration);
        return configurationRepository.save(configuration);
    }



}
