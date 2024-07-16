package Restaurant.backend.rest.business;

import Restaurant.backend.entities.Reservation;
import Restaurant.backend.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationBusiness {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public Reservation updateReservation(Reservation reservation, Long id) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if (existingReservation.isPresent()) {
            Reservation updatedReservation = existingReservation.get();
            updatedReservation.setName(reservation.getName());
            updatedReservation.setPhone(reservation.getPhone());
            updatedReservation.setDate(reservation.getDate());
            updatedReservation.setTime(reservation.getTime());
            updatedReservation.setPeople(reservation.getPeople());
            return reservationRepository.save(updatedReservation);
        } else {
            throw new RuntimeException("Reservation not found with id " + id);
        }
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
