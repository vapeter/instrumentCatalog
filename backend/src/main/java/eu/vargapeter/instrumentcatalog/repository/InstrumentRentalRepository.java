package eu.vargapeter.instrumentcatalog.repository;

import eu.vargapeter.instrumentcatalog.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstrumentRentalRepository extends JpaRepository<Rental, Integer> {

    @Query(value = "SELECT * FROM rental WHERE instrument_id = :instrumentId", nativeQuery = true)
    List<Rental> previousRentalsOfInstrument(@Param("instrumentId") Integer instrumentId);

    @Query(value = "SELECT * FROM rental WHERE instrument_id = :instrumentId AND end_of_rental IS NULL", nativeQuery = true)
    Rental getLastRentalOfInstrumentById(@Param("instrumentId") Integer instrumentId);
}
