package seedu.addressbook.commands;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.logic.Logic;

import static org.junit.Assert.*;

import org.junit.Test;

public class EditNameCommandTest {

    @Test
    public void constructor_valid(){
        EditNameCommand edit = new EditNameCommand(0, "Wendy");
        assertTrue((edit.getTargetIndex()==0)?true:false);
    }

    @Test
    public void execute_invalid() throws Exception{
        AddressBook addressBook = new AddressBook();
        Person person = new Person(new Name("Jane"), new Phone("12345", false), new Email("happyGirl@gmail.com", false), new Address("77th Street", false), new UniqueTagList());
        addressBook.addPerson(person);
        
        Logic logic = new Logic();
        logic.execute("list");
        logic.execute("edit-name 1 n/Wendy");
        
    }
}
