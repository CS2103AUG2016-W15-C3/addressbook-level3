package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

/**
 * Edit the name of a person in the address book.
 */
public class EditPhoneCommand extends Command{

    public static final String COMMAND_WORD = "edit-phone";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" 
            + "Edit the phone number of a person identified by the index number used.\n\t"
            + "Parameters: INDEX p/NEW NUMBER\n\t"
            + "Example: " + COMMAND_WORD + " 1 p/12345678";
    
    public static final String MESSAGE_SUCCESS = "Contact updated: %1$s";
    
    private String newNumber;
    
    public EditPhoneCommand(int targetVisibleIndex, String newNumber) {
        super(targetVisibleIndex);
        this.newNumber = newNumber;
    }
    
    @Override
    public CommandResult execute() {
        try {
            Person target = (Person) getTargetPerson();
            Person newPerson = target;
            newPerson.setPhone(newNumber);
            addressBook.removePerson(target);
            addressBook.addPerson(newPerson);
            return new CommandResult(String.format(MESSAGE_SUCCESS, newPerson));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (IllegalValueException ive) {
            return new CommandResult(ive.getMessage());
        }
    }
}
