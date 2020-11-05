package se.ecutb.car_dealer.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.ecutb.car_dealer.entities.Car;
import se.ecutb.car_dealer.services.CarService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> findAllCars() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/{regNum}")
    public ResponseEntity<Car> findBookByRegNum(@PathVariable String regNum) {
        return ResponseEntity.ok(carService.findByRegNum(regNum));
    }

    @Secured({"ROLE_DEALER", "ROLE_CEO"})
    @PostMapping
    public ResponseEntity<Car> saveCar(@Validated @RequestBody Car car) {
        return ResponseEntity.ok(carService.create(car));
    }

    @Secured({"ROLE_DEALER", "ROLE_CEO"})
    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@Validated @RequestBody Car car, @PathVariable String id) {
        return ResponseEntity.ok(carService.update(car, id));
    }

    @Secured({"ROLE_DEALER"})
    @DeleteMapping
    public void deleteCar(@RequestParam(required = false) String id) {
        carService.delete(id);
    }
}
