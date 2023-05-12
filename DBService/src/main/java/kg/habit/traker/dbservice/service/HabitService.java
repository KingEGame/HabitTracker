package kg.habit.traker.dbservice.service;

import kg.habit.traker.dbservice.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;
}
