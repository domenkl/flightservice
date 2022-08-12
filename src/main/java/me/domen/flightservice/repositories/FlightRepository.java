package me.domen.flightservice.repositories;

import me.domen.flightservice.models.Flight;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {

    default List<Flight> getFlightsByFilters(String origin, String destination) {
        List<Specification<Flight>> specifications = new ArrayList<>();
        Specification<Flight> specs = null;
        if (origin != null && !origin.equals("Any")) specifications.add(hasOrigin(origin));
        if (destination != null && !destination.equals("Any")) specifications.add(hasDestination(destination));

        if (specifications.size() > 0) specs = Specification.where(specifications.get(0));
        if (specifications.size() > 1) {
            for (int i = 1; i < specifications.size(); i++) {
                specs = specs.and(specifications.get(i));
            }
        }
        return specs == null ? findAll() : findAll(specs);
    }

    @Query(value = "select distinct origin from flights", nativeQuery = true)
    List<String> getFlightOrigins();

    @Query(value = "select distinct destination from flights", nativeQuery = true)
    List<String> getFlightDestinations();

    private Specification<Flight> hasOrigin(String origin) {
        return (root, query, cb) -> cb.equal(root.get("origin"), origin);
    }

    private Specification<Flight> hasDestination(String destination) {
        return (root, query, cb) -> cb.equal(root.get("destination"), destination);
    }
}
