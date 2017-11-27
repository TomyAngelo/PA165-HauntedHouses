package fi.muni.pa165.hauntedhouses.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import fi.muni.pa165.hauntedhouses.dao.PersonDao;
import fi.muni.pa165.hauntedhouses.entity.House;
import fi.muni.pa165.hauntedhouses.entity.Person;
import fi.muni.pa165.hauntedhouses.enums.Role;
/**
 * 
 * @author Mario Majernik, 422165
 *
 */
public class PersonServiceTest extends AbstractServiceTest{
	@Mock
	private PersonDao personDao;
	
	@InjectMocks
	@Autowired
	private PersonServiceImpl personService;
	
	private Person person1;
	private Person person2;
	
	private House house;
	
	private List<Person> listOfPersons;
	
	private List<Person> emptyList = Collections.emptyList();
	
	@BeforeMethod
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		person1 = new Person();
		person1.setLogin("Peter123");
		person1.setName("Peter");
		person1.setSurname("Maly");
		person1.setPasswordHash("sd#l1");
		person1.setType(Role.ADMIN);	
		
		person2 = new Person();
		person2.setLogin("Vito998");
		person2.setName("Viktor");
		person1.setSurname("Vysoky");
		person2.setPasswordHash("gf89fd@");
		person1.setType(Role.OWNER);
		
		listOfPersons = new ArrayList<>();
		listOfPersons.add(person1);
		listOfPersons.add(person2);
		
		house = new House();
		house.setAddress("Main 1");
		house.setBecameHauntedDate(new Date(5));
		house.setHistory("Very old house");
		house.setName("My house");
		house.setOwnerID(4l);
	}
	
	@Test
	public void registerPersonTest() {
		personService.registerPerson(person2, "pass");
    	verify(personDao).create(person2);
	}
	
	@Test
    public void updatePersonTest() {
		personService.registerPerson(person2, "pass");
    	verify(personDao).create(person2);
    	
    	person2.setType(Role.RESIDENT);
    	
    	personService.updatePerson(person2);
    	verify(personDao).update(person2);
    }
	
	@Test
	public void removePersonTest() {
		personService.registerPerson(person2, "pass");
    	verify(personDao).create(person2);
    	
    	personService.removePerson(person2);
    	verify(personDao).remove(person2);		
	}
	
	@Test
    public void getAllWithEmptyListReturnsEmptyListTest() {
        when(personDao.findAll()).thenReturn(emptyList);

        assertThat(personService.getAllPeople()).isEqualTo(emptyList);
    }
    
    @Test
    public void getAllWithNonEmptyListReturnsNonEmptyListTest() {
        when(personDao.findAll()).thenReturn(listOfPersons);       
        
        assertThat(personService.getAllPeople()).isEqualTo(listOfPersons);
    }
    
    @Test
    public void findByIdWithWrongIdReturnsNullTest() {
        Long id = 0l;
        when(personDao.findById(id)).thenReturn(null);
      
        assertThat(personService.findPersonById(id)).isNull();
    }

    @Test
    public void findByIdWithCorrectIdReturnsPersonTest() {
        Long id = 0l;
        when(personDao.findById(id)).thenReturn(person1);
       
        assertThat(personService.findPersonById(id)).isEqualTo(person1);
    }
    
    @Test
    public void inhabitHouseTest() {
    	personService.inhabitHouse(house, person1);
    	verify(personDao).findById(person1.getId()).setHouse(house);
    }
}
