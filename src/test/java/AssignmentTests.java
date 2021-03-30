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

public class AssignmentTests {

    public static final String VALID_ID = "999";
    public static final String VALID_DESCRIPTION = "description";
    public static final int VALID_STARTLINE = 1;
    public static final int VALID_DEADLINE = 4;

    @Mock
    StudentXMLRepository studentXmlRepo;
    @Mock
    TemaXMLRepository temaXMLRepository;
    @Mock
    NotaXMLRepository notaXMLRepository;

    Service testService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        testService = new Service(studentXmlRepo, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void whenAddAssignment_CorrectParameters_AdditionSucceeds(){
        Tema entity = new Tema(VALID_ID,VALID_DESCRIPTION,VALID_STARTLINE,VALID_DEADLINE);
        when(temaXMLRepository.save(entity)).thenReturn(null);
        assertEquals(testService.saveTema(VALID_ID,VALID_DESCRIPTION,VALID_STARTLINE,VALID_DEADLINE),1);
    }

    @Test
    public void whenAddAssignment_IdenticalWithExisting_AdditionFails(){
        Tema entity = new Tema(VALID_ID,VALID_DESCRIPTION,VALID_STARTLINE,VALID_DEADLINE);
        when(temaXMLRepository.save(entity)).thenReturn(entity);
        assertEquals(testService.saveTema(VALID_ID,VALID_DESCRIPTION,VALID_STARTLINE,VALID_DEADLINE), 0);
    }


}
