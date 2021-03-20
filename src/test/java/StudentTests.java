import domain.Student;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.ValidationException;
import validation.Validator;

import static org.junit.Assert.assertEquals;

public class StudentTests {
    @Mock
    StudentXMLRepository studentXmlRepo;
    @Mock
    TemaXMLRepository temaXMLRepository;
    @Mock
    NotaXMLRepository notaXMLRepository;

    Service testService;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        testService = new Service(studentXmlRepo, temaXMLRepository, notaXMLRepository);
    }


    @Test
    public void whenAddStudent_WithEmptyName_AdditionFails(){
        assertEquals(testService.saveStudent("999","",999),1);
        assertEquals(testService.saveStudent("999",null,999),1);
    }

    @Test
    public void whenAddStudent_WithInvalidGroup_AdditionFails(){
        assertEquals(testService.saveStudent("999","valid",999),1);
        assertEquals(testService.saveStudent("999","valid",99),1);
    }

    @Test
    public void whenAddStudent_WithNoId_AdditionFails() {
        assertEquals(testService.saveStudent(null, "", 999), 1);
    }
}
