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
import static org.mockito.Mockito.when;

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
    public static final int SUCCESS_RETURN_VALUE = 0;
    public static final int FAILURE_RETURN_VALUE = 1;
    public static final int UPPER_LIMIT_GROUP = 938;
    public static final int LOWER_LIMIT_GROUP = 110;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        testService = new Service(studentXmlRepo, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void whenAddStudent_CorrectParameters_AdditionSucceeds(){
        Student student = new Student(VALID_ID, VALID_NAME, VALID_GROUP);
        when(studentXmlRepo.save(student)).thenReturn(student);
        assertEquals(testService.saveStudent(VALID_ID,VALID_NAME,VALID_GROUP),SUCCESS_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithEmptyName_AdditionFails(){
        Student entity = new Student(VALID_ID, "", VALID_GROUP);
        when(studentXmlRepo.save(entity)).thenReturn(null);
        assertEquals(testService.saveStudent(VALID_ID,"",VALID_GROUP),FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithNullName_AdditionFails(){
        Student entity = new Student(VALID_ID, null, VALID_GROUP);
        when(studentXmlRepo.save(entity)).thenReturn(null);
        assertEquals(testService.saveStudent(VALID_ID,null,VALID_GROUP),FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupTooBig_AdditionFails(){
        Student entity = new Student(VALID_ID, VALID_NAME, 999);
        when(studentXmlRepo.save(entity)).thenReturn(null);
        assertEquals(testService.saveStudent(VALID_ID,VALID_NAME,999),FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupTooSmall_AdditionFails() {
        Student entity = new Student(VALID_ID, VALID_NAME, 99);
        when(studentXmlRepo.save(entity)).thenReturn(null);
        assertEquals(testService.saveStudent(VALID_ID, VALID_NAME, 99), FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupCornerBig_AdditionFails() {
        Student entity = new Student(VALID_ID, VALID_NAME, UPPER_LIMIT_GROUP + 1);
        when(studentXmlRepo.save(entity)).thenReturn(null);
        assertEquals(testService.saveStudent(VALID_ID, VALID_NAME, UPPER_LIMIT_GROUP + 1), FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupCornerSmall_AdditionFails() {
        Student entity = new Student(VALID_ID, VALID_NAME, LOWER_LIMIT_GROUP - 1);
        when(studentXmlRepo.save(entity)).thenReturn(null);
        assertEquals(testService.saveStudent(VALID_ID, VALID_NAME, LOWER_LIMIT_GROUP - 1), FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupInvalidUpperInt_AdditionFails() {
        Student entity = new Student(VALID_ID, VALID_NAME, Integer.MAX_VALUE + 1);
        when(studentXmlRepo.save(entity)).thenReturn(null);
        assertEquals(testService.saveStudent(VALID_ID, VALID_NAME, Integer.MAX_VALUE + 1), FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupMax_AdditionSucceeds() {
        Student entity = new Student(VALID_ID, VALID_NAME, UPPER_LIMIT_GROUP);
        when(studentXmlRepo.save(entity)).thenReturn(entity);
        assertEquals(testService.saveStudent(VALID_ID, VALID_NAME, UPPER_LIMIT_GROUP), SUCCESS_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupMin_AdditionSucceeds() {
        Student entity = new Student(VALID_ID, VALID_NAME, UPPER_LIMIT_GROUP);
        when(studentXmlRepo.save(entity)).thenReturn(entity);
        assertEquals(testService.saveStudent(VALID_ID, VALID_NAME, UPPER_LIMIT_GROUP), SUCCESS_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupInvalidLowerInt_AdditionFails() {
        Student entity = new Student(VALID_ID, VALID_NAME, Integer.MIN_VALUE - 1);
        when(studentXmlRepo.save(entity)).thenReturn(null);
        assertEquals(testService.saveStudent(VALID_ID, VALID_NAME, Integer.MIN_VALUE - 1), FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithNullId_AdditionFails() {
        Student entity = new Student(null, VALID_NAME, VALID_GROUP);
        when(studentXmlRepo.save(entity)).thenReturn(null);
        assertEquals(testService.saveStudent(null, VALID_NAME, VALID_GROUP), FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithEmptyId_AdditionFails() {
        Student entity = new Student("", VALID_NAME, VALID_GROUP);
        when(studentXmlRepo.save(entity)).thenReturn(null);
        assertEquals(testService.saveStudent("", VALID_NAME, VALID_GROUP), FAILURE_RETURN_VALUE);
    }
}
