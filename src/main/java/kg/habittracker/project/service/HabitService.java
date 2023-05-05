package kg.habittracker.project.service;

import kg.habittracker.project.repository.HabitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;
}
