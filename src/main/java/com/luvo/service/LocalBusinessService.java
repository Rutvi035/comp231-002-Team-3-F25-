package com.luvo.service;

import com.luvo.model.LocalBusiness;
import com.luvo.repository.ILocalBusinessRepository;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class LocalBusinessService {

    private static final Logger log = LoggerFactory.getLogger(LocalBusinessService.class);

    private final ILocalBusinessRepository localBusinessRepository;

    public LocalBusinessService(ILocalBusinessRepository localBusinessRepository) {
        this.localBusinessRepository = localBusinessRepository;
    }

    public List<LocalBusiness> findAll() {
        try {
            return localBusinessRepository.findAll();
        } catch (Exception ex) {
            log.error("Failed to load local businesses", ex);
            return Collections.emptyList();
        }
    }

    public Optional<LocalBusiness> findById(String id) {
        try {
            return localBusinessRepository.findById(id);
        } catch (Exception ex) {
            log.error("Failed to find local business by id={}", id, ex);
            return Optional.empty();
        }
    }

    public Optional<LocalBusiness> save(LocalBusiness business) {
        try {
            return Optional.of(localBusinessRepository.save(business));
        } catch (Exception ex) {
            log.error("Failed to save local business", ex);
            return Optional.empty();
        }
    }

    public void delete(String id) {
        try {
            localBusinessRepository.deleteById(id);
        } catch (Exception ex) {
            log.error("Failed to delete local business id={}", id, ex);
        }
    }
}
