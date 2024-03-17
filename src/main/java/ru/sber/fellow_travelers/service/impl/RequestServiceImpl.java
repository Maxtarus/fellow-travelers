package ru.sber.fellow_travelers.service.impl;

import org.springframework.stereotype.Service;
import ru.sber.fellow_travelers.entity.Request;
import ru.sber.fellow_travelers.repository.RequestRepository;
import ru.sber.fellow_travelers.service.RequestService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public void save(Request request) {
        requestRepository.save(request);
    }

    @Override
    public List<Request> findAllByPassengerId(long id) {
        List<Request> requests = (List<Request>) requestRepository.findAll();
        return requests.stream()
                .filter(request -> request.getPassenger().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(long id) {
        requestRepository.deleteById(id);
    }

}
