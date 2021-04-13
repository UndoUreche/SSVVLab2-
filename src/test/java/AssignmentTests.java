import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.TemaValidator;
import validation.ValidationException;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

public class AssignmentTests {
    @Mock
    StudentXMLRepository studentXmlRepo;
    @Mock
    TemaXMLRepository temaXMLRepository;
    @Mock
    NotaXMLRepository notaXMLRepository;

    Service testService;
    TemaValidator temaValidator;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        testService = new Service(studentXmlRepo, temaXMLRepository, notaXMLRepository);
        temaValidator = new TemaValidator();
        try {
            PrintWriter testPrintWriter = new PrintWriter("testXML.xml");
            testPrintWriter.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
            testPrintWriter.print("<Entitati>\n");
            testPrintWriter.print("</Entitati>\n");
            testPrintWriter.close();

        } catch (IOException e) {
            System.out.println("Error when initiating test xml files");
        }
    }

    @Test
    public void whenAddAssignment_CorrectParameters_AdditionSucceeds() {
        Tema entity = new Tema(Constants.VALID_ASSIGNMENT_ID, Constants.VALID_ASSIGNMENT_DESCRIPTION, Constants.VALID_ASSIGNMENT_STARTLINE, Constants.VALID_ASSIGNMENT_DEADLINE);
        when(temaXMLRepository.save(entity)).thenReturn(null);
        assertEquals(testService.saveTema(Constants.VALID_ASSIGNMENT_ID,Constants.VALID_ASSIGNMENT_DESCRIPTION,Constants.VALID_ASSIGNMENT_STARTLINE,Constants.VALID_ASSIGNMENT_DEADLINE), 1);
    }

    @Test
    public void whenAddAssignment_IdenticalWithExisting_AdditionFails() {
        Tema entity = new Tema(Constants.VALID_ASSIGNMENT_ID,Constants.VALID_ASSIGNMENT_DESCRIPTION,Constants.VALID_ASSIGNMENT_STARTLINE,Constants.VALID_ASSIGNMENT_DEADLINE);
        when(temaXMLRepository.save(entity)).thenReturn(entity);
        assertEquals(testService.saveTema(Constants.VALID_ASSIGNMENT_ID,Constants.VALID_ASSIGNMENT_DESCRIPTION,Constants.VALID_ASSIGNMENT_STARTLINE,Constants.VALID_ASSIGNMENT_DEADLINE), 0);
    }


    @Test
    public void whenAddAssignmentToXMLRepo_CorrectParameters_AdditionSucceeds() {
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "testXML.xml");
        Tema entity = new Tema(Constants.VALID_ASSIGNMENT_ID,Constants.VALID_ASSIGNMENT_DESCRIPTION,Constants.VALID_ASSIGNMENT_DEADLINE,Constants.VALID_ASSIGNMENT_STARTLINE);
        assertEquals(null, temaXMLRepository.save(entity));
    }

    @Test
    public void whenAddAssignmentToXMLRepo_InvalidId_AdditionFails() {
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "testXML.xml");
        Tema entity = new Tema("",Constants.VALID_ASSIGNMENT_DESCRIPTION,Constants.VALID_ASSIGNMENT_DEADLINE,Constants.VALID_ASSIGNMENT_STARTLINE);
        assertThrows(ValidationException.class, () -> {
            temaXMLRepository.save(entity);
        });
    }

    @Test
    public void whenAddAssignmentToXMLRepo_NullId_AdditionFails() {
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "testXML.xml");
        Tema entity = new Tema(null,Constants.VALID_ASSIGNMENT_DESCRIPTION,Constants.VALID_ASSIGNMENT_DEADLINE,Constants.VALID_ASSIGNMENT_STARTLINE);
        assertThrows(ValidationException.class, () -> {
            temaXMLRepository.save(entity);
        });
    }

    @Test
    public void whenAddAssignmentToXMLRepo_InvalidDescription_AdditionFails() {
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "testXML.xml");
        Tema entity = new Tema(Constants.VALID_ASSIGNMENT_ID, "",Constants.VALID_ASSIGNMENT_DEADLINE,Constants.VALID_ASSIGNMENT_STARTLINE);
        assertThrows(ValidationException.class, () -> {
            temaXMLRepository.save(entity);
        });
    }

    @Test
    public void whenAddAssignmentToXMLRepo_NullDescription_AdditionFails() {
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "testXML.xml");
        Tema entity = new Tema(Constants.VALID_ASSIGNMENT_ID, null,Constants.VALID_ASSIGNMENT_DEADLINE,Constants.VALID_ASSIGNMENT_STARTLINE);
        assertThrows(ValidationException.class, () -> {
            temaXMLRepository.save(entity);
        });
    }

    @Test
    public void whenAddAssignmentToXMLRepo_DeadlineBeforeMin_AdditionFails() {
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "testXML.xml");
        Tema entity = new Tema(Constants.VALID_ASSIGNMENT_ID,Constants.VALID_ASSIGNMENT_DESCRIPTION, Constants.MIN_ASSIGNMENT_DEADLINE - 1,Constants.VALID_ASSIGNMENT_STARTLINE);
        assertThrows(ValidationException.class, () -> {
            temaXMLRepository.save(entity);
        });
    }


    @Test
    public void whenAddAssignmentToXMLRepo_DeadlineAfterMax_AdditionFails() {
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "testXML.xml");
        Tema entity = new Tema(Constants.VALID_ASSIGNMENT_ID, Constants.VALID_ASSIGNMENT_DESCRIPTION, Constants.MAX_ASSIGNMENT_DEADLINE + 1, Constants.VALID_ASSIGNMENT_STARTLINE);
        assertThrows(ValidationException.class, () -> {
            temaXMLRepository.save(entity);
        });
    }


    @Test
    public void whenAddAssignmentToXMLRepo_DeadlineBeforeStartline_AdditionFails() {
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "testXML.xml");
        //DEADLINE AND STARTLINE INVERSED
        Tema entity = new Tema(Constants.VALID_ASSIGNMENT_ID, Constants.VALID_ASSIGNMENT_DESCRIPTION, Constants.VALID_ASSIGNMENT_STARTLINE, Constants.VALID_ASSIGNMENT_DEADLINE);
        assertThrows(ValidationException.class, () -> {
            temaXMLRepository.save(entity);
        });
    }

    @Test
    public void whenAddAssignmentToXMLRepo_StartlineBeforeMin_AdditionFails() {
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "testXML.xml");
        Tema entity = new Tema(Constants.VALID_ASSIGNMENT_ID, Constants.VALID_ASSIGNMENT_DESCRIPTION, Constants.VALID_ASSIGNMENT_DEADLINE, Constants.MIN_ASSIGNMENT_DEADLINE - 1);
        assertThrows(ValidationException.class, () -> {
            temaXMLRepository.save(entity);
        });
    }

    @Test
    public void whenAddAssignmentToXMLRepo_StartlineAfterMax_AdditionFails() {
        TemaXMLRepository temaXMLRepository = new TemaXMLRepository(temaValidator, "testXML.xml");
        Tema entity = new Tema(Constants.VALID_ASSIGNMENT_ID, Constants.VALID_ASSIGNMENT_DESCRIPTION, Constants.VALID_ASSIGNMENT_DEADLINE, Constants.MAX_ASSIGNMENT_DEADLINE + 1);
        assertThrows(ValidationException.class, () -> {
            temaXMLRepository.save(entity);
        });
    }

    @Test
    public void whenAddMultipleAssignments_Valid_AdditionsSucceeds(){
        //this could be achieved using a stream for multiple assignments, but it is not required
        Tema entity = new Tema(Constants.VALID_ASSIGNMENT_ID, Constants.VALID_ASSIGNMENT_DESCRIPTION, Constants.VALID_ASSIGNMENT_STARTLINE, Constants.VALID_ASSIGNMENT_DEADLINE);
        Tema entity2 = new Tema(Constants.VALID_ASSIGNMENT_ID + "2", Constants.VALID_ASSIGNMENT_DESCRIPTION + "2", Constants.VALID_ASSIGNMENT_STARTLINE, Constants.VALID_ASSIGNMENT_DEADLINE);
        when(temaXMLRepository.save(entity)).thenReturn(null);
        when(temaXMLRepository.save(entity2)).thenReturn(null);

        assertEquals(testService.saveTema(Constants.VALID_ASSIGNMENT_ID, Constants.VALID_ASSIGNMENT_DESCRIPTION, Constants.VALID_ASSIGNMENT_STARTLINE, Constants.VALID_ASSIGNMENT_DEADLINE), 1);
        assertEquals(testService.saveTema(Constants.VALID_ASSIGNMENT_ID + "2", Constants.VALID_ASSIGNMENT_DESCRIPTION + "2", Constants.VALID_ASSIGNMENT_STARTLINE, Constants.VALID_ASSIGNMENT_DEADLINE), 1);
    }


}
