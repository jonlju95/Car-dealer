package se.ecutb.car_dealer.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.ecutb.car_dealer.entities.Car;
import se.ecutb.car_dealer.repositories.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(String id) {
        return carRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Couldn't find car with id %s", id)));
    }

    public Car findByRegNum(String regNum) {
        return carRepository.findByRegNum(regNum).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Couldn't find car with id %s", regNum)));
    }

    public Car create(Car car) {
        return carRepository.save(car);
    }

    public Car update(Car car, String id) {
        return carRepository.findById(id).map(newCar -> {
                    newCar.setBrand(car.getBrand());
                    newCar.setModel(car.getModel());
                    newCar.setYear(car.getYear());
                    newCar.setStatus(car.getStatus());
                    newCar.setRegNum(car.getRegNum());
                    return carRepository.save(newCar);
                }).orElseGet(() -> {
                    car.setId(id);
                    return carRepository.save(car);
        });
    }

    public void delete(String id) {
        if(!carRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Couldn't find car with id %s", id));
        }
        carRepository.deleteById(id);
    }


}
