package com.example.services;

import com.example.tables.Employee;
import com.example.tables.Project;
import com.example.tables.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryServiceTest {
    @InjectMocks
    private QueryServiceImpl queryService;
    @Mock
    private EntityManager em;

    private String jsonRepresentation(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            mapper.findAndRegisterModules();
            jsonString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(object);
            System.out.println(jsonString);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return jsonString;
    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Testing query 1")
    void query1Test() {
        List<Employee> dummyArr = new ArrayList<Employee>();
        dummyArr.add(new Employee(1, "Gasser", "gasser@g.com", "010", (short) 23,
                "1234", new Role("ase"), new HashSet<Project>()));
        Query query = Mockito.mock(Query.class);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(dummyArr);
        assertEquals(jsonRepresentation(dummyArr), queryService.query1JSON());
    }

    @Test
    @DisplayName("Testing query 2")
    void query2Test() {
        String projectName = "electricity";
        Project dummy = new Project(1, LocalDate.of(2020, 2, 2), projectName, new Employee(1, "Gasser", "gasser@g.com", "010", (short) 23,
                "1234", new Role("ase"), new HashSet<Project>()), new HashSet<>());
        Query query = Mockito.mock(Query.class);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyInt(), Mockito.anyString())).thenReturn(query);
        Mockito.when(query.getSingleResult()).thenReturn(dummy);
        assertEquals(jsonRepresentation(dummy.getEmployeeProjects()), queryService.query2JSON(projectName));
    }

    @Test
    @DisplayName("Testing query 3")
    void query3Test() {
        Project dummyProject = new Project(1, LocalDate.of(2020, 2, 2), "electricity", new Employee(1, "Gasser", "gasser@g.com", "010", (short) 23,
                "1234", new Role("ase"), new HashSet<Project>()), new HashSet<>());
        Employee dummyEmployee = new Employee(1, "Gasser", "gasser@g.com", "010", (short) 23,
                "1234", new Role("ase"), new HashSet<Project>());
        EntityTransaction transaction = Mockito.mock(EntityTransaction.class);
        Mockito.when(em.getTransaction()).thenReturn(transaction);

        Query query1 = Mockito.mock(Query.class);
        Mockito.when(em.createQuery("SELECT p FROM Project p WHERE p.id = ?1")).thenReturn(query1);
        Mockito.when(query1.setParameter(Mockito.anyInt(), Mockito.anyInt())).thenReturn(query1);
        Mockito.when(query1.getSingleResult()).thenReturn(dummyProject);

        Query query2 = Mockito.mock(Query.class);
        Mockito.when(em.createQuery("SELECT e FROM Employee e WHERE e.id = ?1")).thenReturn(query2);
        Mockito.when(query2.setParameter(Mockito.anyInt(), Mockito.anyInt())).thenReturn(query2);
        Mockito.when(query2.getSingleResult()).thenReturn(dummyEmployee);
        assertEquals("Successfully added ProjectID to Employee", queryService.query3(1, 1));

        HashSet<Project> dummySet = new HashSet<Project>();
        dummySet.add(dummyProject);
        assertEquals(dummySet.toString(), dummyEmployee.getEmployeeProjects().toString());
    }

    @Test
    @DisplayName("Testing query 4")
    void query4Test() {
        Role role = new Role("hr");
        List<Employee> dummyArr = new ArrayList<Employee>();
        dummyArr.add(new Employee(1, "Gasser", "gasser@g.com", "010", (short) 23,
                "1234", new Role("ase"), new HashSet<Project>()));
        Query query = Mockito.mock(Query.class);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyInt(), Mockito.any())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(dummyArr);
        assertEquals(jsonRepresentation(dummyArr), queryService.query4JSON("hr"));
    }

    @Test
    @DisplayName("Testing query 1 paginated")
    void query1PaginatedTest() {
        String json = String.format("{\"pageIndex\":\"%s\",\"pageSize\":\"%s\"}", 55, 1);
        List<Employee> dummyArr = new ArrayList<Employee>();
        dummyArr.add(new Employee(1, "Gasser", "gasser@g.com", "010", (short) 23,
                "1234", new Role("ase"), new HashSet<Project>()));
        Query query = Mockito.mock(Query.class);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setFirstResult(Mockito.anyInt())).thenReturn(query);
        Mockito.when(query.setMaxResults(Mockito.anyInt())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(dummyArr);
        assertEquals(jsonRepresentation(dummyArr), queryService.query1Paginated(json));
    }
}
