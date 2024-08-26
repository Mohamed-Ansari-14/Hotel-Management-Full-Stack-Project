package com.Hotel_Management.services.customer.booking;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Hotel_Management.dto.ReservationDto;
import com.Hotel_Management.dto.ReservationResponseDto;
import com.Hotel_Management.entity.Reservation;
import com.Hotel_Management.entity.Room;
import com.Hotel_Management.entity.User;
import com.Hotel_Management.enums.ReservationStatus;
import com.Hotel_Management.repository.ReservationRepository;
import com.Hotel_Management.repository.RoomRepository;
import com.Hotel_Management.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{

	private final UserRepository userRepository;

	private final RoomRepository roomRepository;

	private final ReservationRepository reservationRepository;
	
	public static final int SEARCH_RESULT_PER_PAGE = 4;

	public boolean postReservation(ReservationDto reservationDto) {
		Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
		Optional<Room> optionalRoom = roomRepository.findById(reservationDto.getRoomId());

		if(optionalRoom.isPresent() && optionalUser.isPresent()) {
			Reservation reservation = new Reservation();

			reservation.setRoom(optionalRoom.get());
			reservation.setUser(optionalUser.get());
			reservation.setCheckInDate(reservationDto.getCheckInDate());
			reservation.setCheckOutDate(reservationDto.getCheckOutDate());
			reservation.setReservationStatus(ReservationStatus.PENDING);

			Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate());   
			reservation.setPrice(optionalRoom.get().getPrice() * days);

			reservationRepository.save(reservation);
			return true;
		}

		return false;
	}

	public ReservationResponseDto getAllReservationByUserId(Long userId, int pageNumber) {
		
		Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);

		Page<Reservation> reservationPage = reservationRepository.findAllByUserId(pageable, userId);

		ReservationResponseDto reservationResponseDto = new ReservationResponseDto();

		reservationResponseDto.setReservationDtoList(reservationPage.stream()
				.map(Reservation::getReservationDto).collect(Collectors.toList()));

		reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
		reservationResponseDto.setTotalPages(reservationPage.getTotalPages());

		return reservationResponseDto;
	}
}


















