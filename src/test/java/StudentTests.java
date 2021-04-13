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

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        testService = new Service(studentXmlRepo, temaXMLRepository, notaXMLRepository);
    }

    @Test
    public void whenAddStudent_CorrectParameters_AdditionSucceeds(){
        Student student = new Student(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Constants.VALID_GROUP);
        when(studentXmlRepo.save(student)).thenReturn(null);
        assertEquals(testService.saveStudent(Constants.VALID_STUDENT_ID,Constants.VALID_STUDENT_NAME,Constants.VALID_GROUP), Constants.SUCCESS_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithEmptyName_AdditionFails(){
        Student entity = new Student(Constants.VALID_STUDENT_ID, "", Constants.VALID_GROUP);
        when(studentXmlRepo.save(entity)).thenReturn(entity);
        assertEquals(testService.saveStudent(Constants.VALID_STUDENT_ID,"",Constants.VALID_GROUP), Constants.FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithNullName_AdditionFails(){
        Student entity = new Student(Constants.VALID_STUDENT_ID, null, Constants.VALID_GROUP);
        when(studentXmlRepo.save(entity)).thenReturn(entity);
        assertEquals(testService.saveStudent(Constants.VALID_STUDENT_ID,null,Constants.VALID_GROUP), Constants.FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupTooBig_AdditionFails(){
        Student entity = new Student(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, 999);
        when(studentXmlRepo.save(entity)).thenReturn(entity);
        assertEquals(testService.saveStudent(Constants.VALID_STUDENT_ID,Constants.VALID_STUDENT_NAME,999), Constants.FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupTooSmall_AdditionFails() {
        Student entity = new Student(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, 99);
        when(studentXmlRepo.save(entity)).thenReturn(entity);
        assertEquals(testService.saveStudent(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, 99), Constants.FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupCornerBig_AdditionFails() {
        Student entity = new Student(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Constants.UPPER_LIMIT_GROUP + 1);
        when(studentXmlRepo.save(entity)).thenReturn(entity);
        assertEquals(testService.saveStudent(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Constants.UPPER_LIMIT_GROUP + 1), Constants.FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupCornerSmall_AdditionFails() {
        Student entity = new Student(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Constants.LOWER_LIMIT_GROUP - 1);
        when(studentXmlRepo.save(entity)).thenReturn(entity);
        assertEquals(testService.saveStudent(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Constants.LOWER_LIMIT_GROUP - 1), Constants.FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupInvalidUpperInt_AdditionFails() {
        Student entity = new Student(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Integer.MAX_VALUE + 1);
        when(studentXmlRepo.save(entity)).thenReturn(entity);
        assertEquals(testService.saveStudent(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Integer.MAX_VALUE + 1), Constants.FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupMax_AdditionSucceeds() {
        Student entity = new Student(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Constants.UPPER_LIMIT_GROUP);
        when(studentXmlRepo.save(entity)).thenReturn(null);
        assertEquals(testService.saveStudent(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Constants.UPPER_LIMIT_GROUP), Constants.SUCCESS_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupMin_AdditionSucceeds() {
        Student entity = new Student(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Constants.UPPER_LIMIT_GROUP);
        when(studentXmlRepo.save(entity)).thenReturn(null);
        assertEquals(testService.saveStudent(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Constants.UPPER_LIMIT_GROUP), Constants.SUCCESS_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithGroupInvalidLowerInt_AdditionFails() {
        Student entity = new Student(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Integer.MIN_VALUE - 1);
        when(studentXmlRepo.save(entity)).thenReturn(entity);
        assertEquals(testService.saveStudent(Constants.VALID_STUDENT_ID, Constants.VALID_STUDENT_NAME, Integer.MIN_VALUE - 1), Constants.FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithNullId_AdditionFails() {
        Student entity = new Student(null, Constants.VALID_STUDENT_NAME, Constants.VALID_GROUP);
        when(studentXmlRepo.save(entity)).thenReturn(entity);
        assertEquals(testService.saveStudent(null, Constants.VALID_STUDENT_NAME, Constants.VALID_GROUP), Constants.FAILURE_RETURN_VALUE);
    }

    @Test
    public void whenAddStudent_WithEmptyId_AdditionFails() {
        Student entity = new Student("", Constants.VALID_STUDENT_NAME, Constants.VALID_GROUP);
        when(studentXmlRepo.save(entity)).thenReturn(entity);
        assertEquals(testService.saveStudent("", Constants.VALID_STUDENT_NAME, Constants.VALID_GROUP), Constants.FAILURE_RETURN_VALUE);
    }
}
