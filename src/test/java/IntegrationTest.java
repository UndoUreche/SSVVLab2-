import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class IntegrationTest {

    @Mock
    StudentXMLRepository studentXMLRepository;
    @Mock
    TemaXMLRepository temaXMLRepository;
    @Mock
    NotaXMLRepository notaXMLRepository;

    Service testService;
    Student student = new Student(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Constants.VALID_GROUP);
    Tema tema = new Tema(Constants.VALID_ASSIGNMENT_ID, Constants.VALID_ASSIGNMENT_DESCRIPTION,Constants.VALID_ASSIGNMENT_STARTLINE,Constants.VALID_ASSIGNMENT_DEADLINE);
    Nota nota = new Nota(new Pair<>(Constants.VALID_STUDENT_ID,Constants.VALID_ASSIGNMENT_ID),Constants.VALID_GRADE,Constants.VALID_INITIAL_WEEK,Constants.VALID_FEEDBACK);


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        testService = new Service(studentXMLRepository,temaXMLRepository,notaXMLRepository);
        when(studentXMLRepository.findOne(Constants.VALID_STUDENT_ID)).thenReturn(student);
        when(temaXMLRepository.findOne(Constants.VALID_ASSIGNMENT_ID)).thenReturn(tema);
    }

    /**
     * Preconditions:
     * Grade Student ID is Valid i.e it exists in the database
     * Grade Assignment ID is Valid i.e it exists in the database
     * Grade Value is Valid i.e. it is between 1 and 10
     * Initial Week is valid i.e it is between 1 and 14
     * Feedback is valid i.e. it is a string
     */
    @Test
    public void whenAddGrade_CorrectParameters_AdditionSucceeds(){
        when(notaXMLRepository.save(nota)).thenReturn(null);
        assertEquals(Constants.SUCCESS_RETURN_VALUE,testService.saveNota(Constants.VALID_STUDENT_ID,Constants.VALID_ASSIGNMENT_ID,Constants.VALID_GRADE,Constants.VALID_INITIAL_WEEK,Constants.VALID_FEEDBACK));
    }

    /**
     * Preconditions:
     * Assignment ID is Valid i.e it is a valid String assumed to not exist in the database
     * Assignment Descriptions is valid i.e it is a String
     * Assignment Startline is valid i.e. it is not after the deadline
     * Assignment Deadline is valid i.e. it is not before the startline
     */
    @Test
    public void whenAddAssignment_CorrectParameters_AdditionSucceeds() {
        when(temaXMLRepository.save(tema)).thenReturn(null);
        assertEquals(Constants.SUCCESS_RETURN_VALUE,testService.saveTema(Constants.VALID_ASSIGNMENT_ID, Constants.VALID_ASSIGNMENT_DESCRIPTION, Constants.VALID_ASSIGNMENT_STARTLINE, Constants.VALID_ASSIGNMENT_DEADLINE));
    }

    /**
     * Preconditions:
     * Student ID is Valid i.e. it is a String assumed to not exist in the database
     * Student Name is Valid i.e. it is a String
     * Student Group is valid i.e. it is an int in the interval [110,938]
     */
    @Test
    public void whenAddStudent_CorrectParameters_AdditionSucceeds(){
        when(studentXMLRepository.save(student)).thenReturn(null);
        assertEquals(Constants.SUCCESS_RETURN_VALUE, testService.saveStudent(Constants.VALID_STUDENT_ID,Constants.VALID_STUDENT_NAME,Constants.VALID_GROUP));
    }

    /**
     * Preconditions:
     * Student ID is Valid i.e. it is a String assumed to not exist in the database
     * Student Name is Valid i.e. it is a String
     * Student Group is valid i.e. it is an int in the interval [110,938]
     * Assignment ID is Valid i.e it is a valid String assumed to not exist in the database
     * Assignment Descriptions is valid i.e it is a String
     * Assignment Startline is valid i.e. it is not after the deadline
     * Assignment Deadline is valid i.e. it is not before the startline
     * Grade Student ID is Valid i.e it exists in the database
     * Grade Assignment ID is Valid i.e it exists in the database
     * Grade Value is Valid i.e. it is between 1 and 10
     * Initial Week is valid i.e it is between 1 and 14
     * Feedback is valid i.e. it is a string
     */
    @Test
    public void whenAddStudentGradeAssignment_CorrectParameters_AdditionSucceeds(){
        when(studentXMLRepository.save(student)).thenReturn(null);
        when(temaXMLRepository.save(tema)).thenReturn(null);
        when(notaXMLRepository.save(nota)).thenReturn(null);

        testService.saveStudent(Constants.VALID_STUDENT_ID,Constants.VALID_STUDENT_NAME,Constants.VALID_GROUP);
        testService.saveTema(Constants.VALID_ASSIGNMENT_ID, Constants.VALID_ASSIGNMENT_DESCRIPTION, Constants.VALID_ASSIGNMENT_STARTLINE, Constants.VALID_ASSIGNMENT_DEADLINE);
        testService.saveNota(Constants.VALID_STUDENT_ID,Constants.VALID_ASSIGNMENT_ID,Constants.VALID_GRADE,Constants.VALID_INITIAL_WEEK,Constants.VALID_FEEDBACK);

    }


}
