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

//            if (student.getID() == null || student.getID().equals("")) {
//        throw new ValidationException("ID invalid! \n");
//    }
//        if (student.getNume() == null || student.getNume().equals("")) {
//        throw new ValidationException("Nume invalid! \n");
//    }
//        if (student.getGrupa() <= 110 || student.getGrupa() >= 938) {
//        throw new ValidationException("Grupa invalida! \n");
//    }

    @Mock
    StudentXMLRepository studentXmlRepo;
    @Mock
    TemaXMLRepository temaXMLRepository;
    @Mock
    NotaXMLRepository notaXMLRepository;

    @Mock
    Validator<Student> validator;

    Service testService;


    @Before
    void init() {
        MockitoAnnotations.initMocks(Service.class);
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
