package com.company.bookstore.repository;

import com.company.bookstore.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Component
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    List<Publisher> publishers = new ArrayList<Publisher>();
    public default Publisher getPublisherById(Integer id) {
        List<Publisher> idMatch = this.publishers;
        Publisher resultPublisher = null;
        for (Publisher p : idMatch) {
            if (p.getId().equals(id)) {
                resultPublisher = p;
            }
        }
        return resultPublisher;
    }
}
