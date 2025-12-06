package com.luvo.service;

import com.luvo.model.LocalBusiness;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LocalBusinessService {

    private final Map<String, LocalBusiness> store = new HashMap<>();

    public List<LocalBusiness> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<LocalBusiness> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public void save(LocalBusiness business) {
        store.put(business.getId(), business);
    }

    public void delete(String id) {
        store.remove(id);
    }
}
