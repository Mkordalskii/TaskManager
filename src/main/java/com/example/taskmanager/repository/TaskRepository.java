package com.example.taskmanager.repository;

import com.example.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

/*Page<Task>: Spring Data JPA automatycznie obsługuje stronicowanie i zwraca wynik w postaci obiektu Page, który zawiera listę wyników i dodatkowe informacje (np. liczba stron, bieżąca strona).
findByCompleted(boolean completed, Pageable pageable): Spring wygeneruje tę metodę automatycznie, dzięki konwencji nazw metod.
Pageable: Parametr służący do określenia rozmiaru strony, numeru strony oraz sortowania wyników.*/
public interface TaskRepository extends JpaRepository<Task, Long>
{
    Page<Task> findByCompleted(boolean completed, Pageable pageable);
}
