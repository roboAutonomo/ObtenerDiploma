package com.meli.obtenerdiploma.MockTests;

import com.meli.obtenerdiploma.model.StudentDTO;
import com.meli.obtenerdiploma.model.SubjectDTO;
import com.meli.obtenerdiploma.repository.StudentDAO;
import com.meli.obtenerdiploma.repository.StudentRepository;
import com.meli.obtenerdiploma.service.ObtenerDiplomaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.Subject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DiplomaServiceTest {

    @Mock
    private StudentDAO studentRepository;

    @InjectMocks
    private ObtenerDiplomaService diplomaService;

    @Test
    void testAnalyzeScore() {
        Long idStudent = (long)1;
        StudentDTO expectedStudent = new StudentDTO(idStudent, "Jhon", "Hola mundo!", 7.0,
                                        List.of(new SubjectDTO("Matemática", 10.00)));

        when(studentRepository.findById(idStudent)).thenReturn(expectedStudent);

        StudentDTO currentStudent = diplomaService.analyzeScores(idStudent);

        verify(studentRepository, atLeast(1)).findById(idStudent);
        assertThat(expectedStudent).isEqualTo(currentStudent);
    }
}
