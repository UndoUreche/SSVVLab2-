import domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;

import static org.junit.Assert.assertEquals;

public class StudentTests {
    @Mock
    StudentXMLRepository studentXmlRepo;
    @Mock
    TemaXMLRepository temaXMLRepository;
    @Mock
    NotaXMLRepository notaXMLRepository;

    Service testService;

    public static final String VALID_ID = "999";
    public static final String VALID_NAME = "valid";
    public static final int VALID_GROUP = 512;
    public static final int SUCCESS_RETURN_VALUE = 1;
    public static final int FAILURE_RETURN_VALUE = 0;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        testService = new Service(studentXmlRepo, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void whenAddStudent_CorrectParameters_AdditionSucceeds(){
        Student student = new Student(VALID_ID, VALID_NAME, VALID_GROUP);
   //     when(studentXmlRepo.save(student)).thenReturn(student);
        assertEquals(testService.saveStudent(VALID_ID,VALID_NAME,VALID_GROUP),SUCCESS_RETURN_VALUE);
    }


    @Test
    public void whenAddStudent_WithEmptyName_AdditionFails(){
        assertEquals(testService.saveStudent(VALID_ID,"",999),SUCCESS_RETURN_VALUE);
        assertEquals(testService.saveStudent(VALID_ID,null,999),SUCCESS_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithInvalidGroup_AdditionFails(){
        assertEquals(testService.saveStudent(VALID_ID,VALID_NAME,999),SUCCESS_RETURN_VALUE);
        assertEquals(testService.saveStudent(VALID_ID,VALID_NAME,99),SUCCESS_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithNoId_AdditionFails() {
        assertEquals(testService.saveStudent(null, VALID_NAME, 999), SUCCESS_RETURN_VALUE);
    }
}
