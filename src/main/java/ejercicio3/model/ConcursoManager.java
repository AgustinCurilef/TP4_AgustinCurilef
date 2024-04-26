package ejercicio3.model;

import java.util.List;

public interface ConcursoManager {
    List<Concurso> todosLosConcursos();

    void saveConcurso(Concurso concurso);
}
